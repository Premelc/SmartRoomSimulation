package Sim;

import DB.Connect;
import DB.InsertDocuments;
import Simulations.Simulator;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import dataset.Filenames;
import org.bson.*;

import java.util.Timer;
import java.util.TimerTask;

import static Simulations.AdriaIndoorSimulator.AdriaLog;
import static Simulations.DHMZObradenoSimulator.DHMZObradenoLog;
import static dataset.Filenames.*;

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
        alternateArchive(mongoClient);
        //archive(mongoClient);
        //createIndexes(mongoClient);


        long end1 = System.currentTimeMillis();
        System.out.println("Done");
        System.out.println("Executed in: " + (end1 - start1) + "ms");
        return;
    }

    public static void archive(MongoClient mongoClient){
        //Sprema sve AdriaIndoor podatke u jednu kolekciju ~150mil dokumenata
        InsertDocuments.insertAll(DHMZObradenoLog, DHMZObradenoCollectionName , Filenames.DHMZObradenoFileNames , Filenames.DHMZObradenoFolderNames, mongoClient ,  Filenames.DHMZObradeniRes);
        InsertDocuments.insertAll(AdriaLog, AdriaCollectionName , Filenames.adriaRoomNames , Filenames.adriaFolderNames, mongoClient ,  Filenames.AdriaRes);
        //TODO dodati insertAll za DHMZBase podatke
    }

    public static void alternateArchive(MongoClient mongoClient){
        InsertDocuments.insertAllAlternate(DHMZObradenoLog, DHMZObradenoCollectionName , Filenames.DHMZObradenoFileNames , Filenames.DHMZObradenoFolderNames, mongoClient ,  Filenames.DHMZObradeniRes);
        InsertDocuments.insertAllAlternate(AdriaLog, AdriaCollectionName, Filenames.adriaRoomNames , Filenames.adriaFolderNames, mongoClient ,  Filenames.AdriaRes);
        //TODO dodati insertAllAlternate za DHMZBase podatke
    }

    public static void createIndexes(MongoClient mongoClient){
        String[] years = {"2013" , "2014" , "2015" , "2016" , "2017" , "2018" , "2019" , "2020" , "2021"};

        for(String s : years){
            MongoDatabase db = mongoClient.getDatabase(s);
            for(String str : adriaRoomNames){
                //db.getCollection(str).drop();
                String c =  str.substring(5 , str.length()-4);
               String res = db.getCollection(c).createIndex(Indexes.descending("vrijeme"));
               System.out.println("created index : "+ res + " in " + s + " " + str);
            }
        }

    }

}
