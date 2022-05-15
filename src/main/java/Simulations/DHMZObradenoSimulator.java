package Simulations;

import DB.InsertDocuments;
import com.mongodb.client.MongoClient;
import dataset.Filenames;
import Operations.Logs;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TimerTask;

import static dataset.Filenames.DHMZObradenoCollectionName;

public class DHMZObradenoSimulator extends TimerTask {

    public static String DHMZObradenoLog = "C:\\WORKSPACE\\IntelliJ Idea\\SmartRoomSimulation\\src\\main\\resources\\Logs\\DHMZObradenoLog.txt";
    private MongoClient mongoClient;

    public DHMZObradenoSimulator(MongoClient mongoClient) {
        this.setMongoClient(mongoClient);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void SmartRoomReadingsSimulator(Timestamp ts){
        Logs.logMessage(DHMZObradenoLog , "----------------------------------------------");
        Logs.logMessage(DHMZObradenoLog , "Connection started: " + ts);
        Logs.logMessage(DHMZObradenoLog , "----------------------------------------------");

        InsertDocuments.insertSingleReading(DHMZObradenoLog, DHMZObradenoCollectionName , Filenames.DHMZObradenoFileNames , Filenames.DHMZObradenoFolderNames[0], this.getMongoClient() , ts , Filenames.DHMZObradeniRes);

        Logs.logMessage(DHMZObradenoLog , "----------------------------------------------");
        Logs.logMessage(DHMZObradenoLog , "Connection closed: " + ts);
        Logs.logMessage(DHMZObradenoLog , "----------------------------------------------");
    }

    public void run() {
        //OVO TIMER POKREĆE
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        ts.setTime(ts.getTime() + 7200000);
        SmartRoomReadingsSimulator(ts);
    }

}
