package soap.config;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class MongoConfig {
    private  MongoClient mongoClient;

    private MongoDatabase database;

    public MongoConfig() {
        this.mongoClient = new MongoClient();
        this.database =  mongoClient.getDatabase("testingTest");
    }

//    public void insert(){
//
//        List<Integer> books = Arrays.asList(27464, 747854);
//        Document person = new Document("_id", "jo")
//                .append("name", "Jo Bloggs")
//                .append("address", new BasicDBObject("street", "123 Fake St")
//                        .append("city", "Faketon")
//                        .append("state", "MA")
//                        .append("zip", 12345))
//                .append("bookzs", books);
//        MongoCollection<Document> collection = database.getCollection("people");
//        collection.insertOne(person);
//    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public MongoDatabase getDatabase() {
        return database;
    }
}
