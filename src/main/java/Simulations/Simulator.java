package Simulations;

import com.mongodb.client.MongoClient;
import dataset.Filenames;

import java.util.Timer;
import java.util.TimerTask;

public class Simulator extends Thread{

    private String colName;
    private MongoClient mongoClient;

    public MongoClient getMongoClient() {
        return mongoClient;
    }

    public void setMongoClient(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Simulator(String colName , MongoClient mongoClient){
        this.setColName(colName);
        this.setMongoClient(mongoClient);
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public void run() {
        //OVO TIMER POKREĆE
        if(this.getColName().equals(Filenames.AdriaCollectionName)){
            Timer timer = new Timer();
            TimerTask task = new AdriaIndoorSimulator(this.getMongoClient());
            //Svakih 5min
            timer.schedule(task , 200 , 300000);
        }
        else if(this.getColName().equals(Filenames.DHMZObradenoCollectionName)){
            Timer timer = new Timer();
            TimerTask task = new DHMZObradenoSimulator(this.getMongoClient());
            //Svakih 1h
            timer.schedule(task , 200 , 3600000);
        }
        else{
            //TODO: Dodati simulator za DHMZBase
        }
    }
}
