package Sim;

import DB.Connect;
import DB.InsertDocuments;
import Simulations.Simulator;
import com.mongodb.client.MongoClient;
import dataset.Filenames;

import java.util.Timer;
import java.util.TimerTask;

import static Simulations.AdriaIndoorSimulator.AdriaLog;
import static Simulations.DHMZObradenoSimulator.DHMZObradenoLog;
import static dataset.Filenames.AdriaCollectionName;
import static dataset.Filenames.DHMZObradenoCollectionName;

public class mainSmartRoomSimulator{

    public static void main(String[] args){
        long start1 = System.currentTimeMillis();
        System.out.println("Spajam se na bazu...");
        MongoClient mongoClient = Connect.mongoConnect();

            /*
        Thread t1 = new Simulator(Filenames.AdriaCollectionName ,mongoClient);
        Thread t2 = new Simulator(Filenames.DHMZObradenoCollectionName ,mongoClient);
        //Thread t3 = new Simulator(Filenames.DHMZBaseCollectionName ,mongoClient);

        t1.start();
        t2.start();
        //t3.start();
        */

        archive(mongoClient);


        long end1 = System.currentTimeMillis();
        System.out.println("Done");
        System.out.println("Executed in: " + (end1 - start1) + "ms");
        return;
    }

    public static void archive(MongoClient mongoClient){
        InsertDocuments.insertAll(DHMZObradenoLog, DHMZObradenoCollectionName , Filenames.DHMZObradenoFileNames , Filenames.DHMZObradenoFolderNames, mongoClient ,  Filenames.DHMZObradeniRes);
        InsertDocuments.insertAll(AdriaLog, AdriaCollectionName , Filenames.adriaRoomNames , Filenames.adriaFolderNames, mongoClient ,  Filenames.AdriaRes);
        //TODO dodati insertAll za DHMZBase podatke
    }

}
