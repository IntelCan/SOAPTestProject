package soap.service.mng;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import soap.config.MongoConfig;
import soap.model.dto.NewTaskDTO;
import soap.model.mng.TaskMng;
import soap.model.mng.UserAccountMng;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

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
        return null;
    }

    @Override
    public TaskMng addContributorsToTask(String task_name, String... name_contributors) {
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

    public static UserAccountMng createTaskFromBSON(Document document){

        DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date date = df.parse("06/27/2007");
        TaskMng taskMng = new TaskMng();
        taskMng.setName(document.get("name").toString());
        taskMng.setDateEndOfTask(format.parse(document.get("dateStartOfTask").toString()));
        return userMng;
    }

    public static BasicDBObject contributorsMapping(Set<UserAccountMng> userAccountMngs){
        BasicDBObject contributors = new BasicDBObject();
        userAccountMngs.stream().map( contributor -> contributors.put("contributor", UserAccountServiceMngImpl.createBSONObj(contributor)));
        return contributors;
    }
}
