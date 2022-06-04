package Simulations;

import DB.Connect;
import DB.InsertDocuments;
import com.mongodb.client.MongoClient;
import dataset.Filenames;
import Operations.Logs;
import java.sql.Timestamp;
import java.util.Date;
import java.util.TimerTask;

import static dataset.Filenames.AdriaCollectionName;

public class AdriaIndoorSimulator extends TimerTask {

        public static String AdriaLog = "src\\main\\resources\\Logs\\AdriaIndoorLog.txt";
        private MongoClient mongoClient;

    public AdriaIndoorSimulator(MongoClient mongoClient) {
         this.setMongoClient(mongoClient);
    }

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public void SmartRoomReadingsSimulator(Timestamp ts) {
            //MongoClient mongoClient = Connect.mongoConnect();
            Logs.logMessage(AdriaLog , "----------------------------------------------");
            Logs.logMessage(AdriaLog , "Connection started: " + ts);
            Logs.logMessage(AdriaLog , "----------------------------------------------");
            InsertDocuments.insertSingleReading(AdriaLog, AdriaCollectionName , Filenames.adriaRoomNames, Filenames.adriaFolderNames[0], this.getMongoClient() ,ts , Filenames.AdriaRes);
        //InsertDocuments.insertSingleAdriaReading(Filenames.adriaRoomNames, Filenames.adriaFolderNames[0], mongoClient,ts );

          //mongoClient.close();
            Logs.logMessage(AdriaLog , "----------------------------------------------");
            Logs.logMessage(AdriaLog , "Connection closed: " + ts);
            Logs.logMessage(AdriaLog , "----------------------------------------------");
        }

        public void run() {
            //OVO TIMER POKREĆE
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            ts.setTime(ts.getTime() + 7200000);
            SmartRoomReadingsSimulator(ts);

        }
}
