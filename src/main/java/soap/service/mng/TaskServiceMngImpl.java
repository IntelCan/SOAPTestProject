package soap.service.mng;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import soap.config.MongoConfig;
import soap.model.Task;
import soap.model.UserAccount;
import soap.model.dto.ContributorsDTO;
import soap.model.dto.NewTaskDTO;

import javax.jws.WebMethod;
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
    @WebMethod
    public Task createTask(@WebParam(name = "newTask") NewTaskDTO newTaskDTO,
                           @WebParam(name = "ownerName") String owner_name) {
        Gson gson = new Gson();
        BasicDBObject searchCriteria = new BasicDBObject();
        searchCriteria.put("name", owner_name);
        Document document = collectionUsers.find(searchCriteria).first();
        UserAccount owner = gson.fromJson(document.toJson(), UserAccount.class);
        Task task = new Task();
        task.setName(newTaskDTO.getName());
        task.setDateStartOfTask(new Date());
        task.setOwner(owner);

        log.info(gson.toJson(task));
        Document doc = Document.parse(gson.toJson(task));
        collectionTasks.insertOne(doc);
        return task;
    }

    @Override
    @WebMethod
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
    @WebMethod
    public void endTask(@WebParam(name = "taskName") String task_name) {

        BasicDBObject taskSearchCriteria = new BasicDBObject("name", task_name);
        BasicDBObject bsonDateEndOfTask = new BasicDBObject("dateEndOfTask", new Date().toString());

        collectionTasks.updateOne(taskSearchCriteria, new BasicDBObject("$set",bsonDateEndOfTask ));

    }

}
