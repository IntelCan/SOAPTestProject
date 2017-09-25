package soap.service.mng;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import soap.config.MongoConfig;
import soap.model.mng.UserAccountMng;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class UserAccountServiceMngImpl implements UserAccountServiceMng {

    private MongoConfig mongoConfig;

    private MongoCollection<Document> collectionUsers;

    public UserAccountServiceMngImpl() {
        this.mongoConfig = new MongoConfig();
        this.collectionUsers = mongoConfig.getDatabase().getCollection("users");

    }

    @Override
    @WebMethod
    public UserAccountMng createUserAccount(UserAccountMng userAccountMng) {
        collectionUsers.insertOne(createBSONObj(userAccountMng));
        return userAccountMng;
    }

    @Override
    @WebMethod
    public boolean deleteUser(String name) {
        BasicDBObject searchCriteria = new BasicDBObject();
        searchCriteria.put("name", name);
        collectionUsers.deleteOne(searchCriteria);
        return true;
    }

    @Override
    @WebMethod
    public List<UserAccountMng> getAllUser() {
      MongoCursor<Document> cursor = collectionUsers.find().iterator();
      List<UserAccountMng> users = new ArrayList<>();
      while (cursor.hasNext()){
          Document doc = cursor.next();
          users.add(createUserFromBSON(doc));
      }

        return users;
    }

    @Override
    @WebMethod
    public UserAccountMng getUserByName(String name) {
        BasicDBObject searchCriteria = new BasicDBObject();
        searchCriteria.put("name", name);
        return createUserFromBSON(collectionUsers.find(searchCriteria).first());
    }

    public static Document createBSONObj(UserAccountMng userMng){
        Document document = new Document();
        document.append("name", userMng.getName());
        return document;
    }

    public static UserAccountMng createUserFromBSON(Document document){

        UserAccountMng userMng = new UserAccountMng();
        userMng.setName(document.get("name").toString());
        return userMng;
    }
}
