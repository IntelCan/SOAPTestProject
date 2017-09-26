package soap.service.mng;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import soap.config.MongoConfig;
import soap.model.dto.ContributorsDTO;
import soap.model.dto.NewTaskDTO;
import soap.model.mng.TaskMng;
import soap.model.mng.UserAccountMng;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.*;

@WebService
@Slf4j
public class TaskServiceMngImpl implements TaskServiceMng{

    private MongoConfig mongoConfig;

    private MongoCollection<Document> collectionUsers;

    private MongoCollection<Document> collectionTasks;

    public TaskServiceMngImpl() {
        this.mongoConfig = new MongoConfig();
        this.collectionUsers = mongoConfig.getDatabase().getCollection("users");
        this.collectionTasks = mongoConfig.getDatabase().getCollection("tasks");
    }

    @Override
    public TaskMng createTask(NewTaskDTO newTaskDTO, String name) {
        BasicDBObject searchCriteria = new BasicDBObject();
        searchCriteria.put("name", name);
        UserAccountMng owner = UserAccountServiceMngImpl.createUserFromBSON(collectionUsers.find(searchCriteria).first());
        TaskMng taskMng = new TaskMng();
        taskMng.setName(newTaskDTO.getName());
        taskMng.setDateStartOfTask(new Date());
        taskMng.setOwner(owner);

        Gson gson = new Gson();
        log.info(gson.toJson(taskMng));
        Document doc = Document.parse(gson.toJson(taskMng));
        collectionTasks.insertOne(doc);
        return taskMng;
    }

    @Override
    public List<String> addContributorsToTask(@WebParam(name = "taskName") String task_name,
                                         @WebParam(name = "contributors") ContributorsDTO names_contributors) {

        names_contributors.getData().forEach(name -> {
            BasicDBObject taskSearchCriteria = new BasicDBObject("name", task_name);
            BasicDBObject userSearchCriteria = new BasicDBObject("name", name);

            Document docWithContributor = collectionUsers.find(userSearchCriteria).first();
            BasicDBObject bsonContributors = new BasicDBObject("contributors", docWithContributor);

            collectionTasks.updateOne(taskSearchCriteria, new BasicDBObject("$addToSet", bsonContributors));
        });

        return names_contributors.getData();

    }

    @Override
    public void endTask(String task_name) {

        BasicDBObject taskSearchCriteria = new BasicDBObject("name", task_name);
        BasicDBObject bsonDateEndOfTask = new BasicDBObject("dateEndOfTask", new Date().toString());

        collectionTasks.updateOne(taskSearchCriteria, new BasicDBObject("$set",bsonDateEndOfTask ));

    }

}
