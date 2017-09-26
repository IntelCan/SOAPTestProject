package soap.service.mng;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import lombok.extern.slf4j.Slf4j;
import org.bson.BSONObject;
import org.bson.Document;
import soap.config.MongoConfig;
import soap.model.dto.ContributorsDTO;
import soap.model.dto.NewTaskDTO;
import soap.model.mng.TaskMng;
import soap.model.mng.UserAccountMng;

import javax.jws.WebParam;
import javax.jws.WebService;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        this.collectionTasks = mongoConfig.getDatabase().getCollection("taskss");
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
    public TaskMng addContributorsToTask(@WebParam(name = "taskName") String task_name,
                                         @WebParam(name = "contributors") ContributorsDTO name_contributors) {
        Set<UserAccountMng> contributors = new HashSet<>();
        Gson gson = new Gson();
        BasicDBObject contributorsBSON = new BasicDBObject();
        for(String name : name_contributors.getData()) {
            contributorsBSON.append("contributor", collectionUsers.find(new BasicDBObject("name", name)).first());

//        Arrays.stream(name_contributors).map(name_contributor ->{
//            Document document = collectionUsers.find(new BasicDBObject("name", name_contributor)).first();
//
//        });
        }
    //        collectionTasks.findOneAndUpdate(new BasicDBObject("name", task_name), new BasicDBObject("contributors", contributorsBSON));
        collectionTasks.find(new BasicDBObject("name", task_name));
        collectionTasks

        return null;
    }

    @Override
    public void endTask(String task_name) {

    }

    public static Document createBSONObj(TaskMng taskMng){
        Document document = new Document();
        document.append("name", taskMng.getName())
                .append("dateStartOfTask", taskMng.getDateStartOfTask().toString())
                .append("dateEndOfTask", taskMng.getDateEndOfTask().toString())
                .append("owner", UserAccountServiceMngImpl.createBSONObj(taskMng.getOwner()))
                .append("contributors", contributorsMapping(taskMng.getContributors()));
        return document;
    }

//    public static UserAccountMng createTaskFromBSON(Document document){
//
//        TaskMng taskMng = new TaskMng();
//        taskMng.setName(document.get("name").toString());
//        return userMng;
//    }

    public static BasicDBObject contributorsMapping(Set<UserAccountMng> userAccountMngs){
        BasicDBObject contributors = new BasicDBObject();
        userAccountMngs.stream().map( contributor -> contributors.put("contributor", UserAccountServiceMngImpl.createBSONObj(contributor)));
        return contributors;
    }
}
