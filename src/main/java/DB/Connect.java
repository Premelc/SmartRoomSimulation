package DB;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class Connect {

    private static final String defaultConnection = "mongodb://localhost:27017";



    //Connect with a specified connection string
    public static MongoClient mongoConnect(String connectionString){
        MongoClient mc = MongoClients.create(connectionString);
        return mc;
    }

    //Connect with the default connection string
    public static MongoClient mongoConnect(){
        MongoClient mc = MongoClients.create(defaultConnection);
        return mc;
    }

}
