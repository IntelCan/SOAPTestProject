package soap.service.mng;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import soap.config.MongoConfig;
import soap.model.dto.UserAccountDTO;
import soap.model.mng.UserAccountMng;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class UserAccountServiceMngImpl implements UserAccountServiceMng {

    private MongoConfig mongoConfig;

    private MongoCollection<Document> collectionUsers;

    private Gson gson;

    public UserAccountServiceMngImpl() {
        this.mongoConfig = new MongoConfig();
        this.collectionUsers = mongoConfig.getDatabase().getCollection("users");
        gson = new Gson();

    }

    @Override
    @WebMethod
    public UserAccountDTO createUserAccount(@WebParam(name = "userAccount")UserAccountDTO userAccountDTO) {
        Document document = Document.parse(gson.toJson(userAccountDTO));
        collectionUsers.insertOne(document);
        return userAccountDTO;
    }

    @Override
    @WebMethod
    public boolean deleteUser(@WebParam(name = "nameUser") String user_name) {
        BasicDBObject searchCriteria = new BasicDBObject();
        searchCriteria.put("name", user_name);
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
          users.add(gson.fromJson(doc.toJson(), UserAccountMng.class));
      }
      return users;
    }

    @Override
    @WebMethod
    public UserAccountMng getUserByName(@WebParam(name = "userName") String user_name) {
        BasicDBObject searchCriteria = new BasicDBObject();
        searchCriteria.put("name", user_name);
        Document doc = collectionUsers.find(searchCriteria).first();
        UserAccountMng userAccountMng = gson.fromJson(doc.toJson(), UserAccountMng.class);
        return userAccountMng;
    }
}
