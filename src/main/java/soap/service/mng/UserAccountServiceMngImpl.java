package soap.service.mng;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import soap.config.MongoConfig;
import soap.model.UserAccount;
import soap.model.dto.UserAccountDTO;

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
    public List<UserAccount> getAllUser() {
      MongoCursor<Document> cursor = collectionUsers.find().iterator();
      List<UserAccount> users = new ArrayList<>();
      while (cursor.hasNext()){
          Document doc = cursor.next();
          users.add(gson.fromJson(doc.toJson(), UserAccount.class));
      }
      return users;
    }

    @Override
    @WebMethod
    public UserAccount getUserByName(@WebParam(name = "userName") String user_name) {
        BasicDBObject searchCriteria = new BasicDBObject();
        searchCriteria.put("name", user_name);
        Document doc = collectionUsers.find(searchCriteria).first();
        UserAccount userAccount = gson.fromJson(doc.toJson(), UserAccount.class);
        return userAccount;
    }
}
