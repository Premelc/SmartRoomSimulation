package DB;

import Operations.Reader;
import Operations.WrappedReader;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static dataset.Filenames.*;

public class InsertDocuments {

    public static List<Document> insertSingleReading(String log, String collectionName, String[] fileName, String folderName, MongoClient mongoClient, Timestamp ts, String resPath) {
        long start1 = System.currentTimeMillis();

        MongoDatabase SmartRoomTrialDb = mongoClient.getDatabase(dbName);
        MongoCollection<Document> Collection = SmartRoomTrialDb.getCollection(collectionName);
        Operations.Logs.logMessage(log, "        Accessing collection " + collectionName);
        Operations.Logs.logMessage(log, "        " + fileName.length + " Entries expected ");

        List<Document> list = new ArrayList<>();
        int i = 0;

        for (i = 0; i < fileName.length; i++) {
            String path = resPath + "" + folderName + "\\" + fileName[i];
            try {
                BufferedReader br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
                WrappedReader wr = new WrappedReader(fileName[i], br, path);
                list.addAll(Reader.SingleLine(wr, ts, collectionName));
                wr.getBr().close();
                wr = null;
            } catch (IOException ioe) {
                //Dolazi do IOExceptiona ako ne pronadje file u folderu, nije potrebno išta napraviti jer traži sve fajlove u svakom folderu i sve ih prije ili kasnije pronađe
                //System.out.println("Nije u tom direktoriju");
            }
        }
        try{
            Collection.insertMany(list, new InsertManyOptions().ordered(false));
            Operations.Logs.logMessage(log, "        " + list.size() + " " + collectionName + " readings entered at: " + ts);
            long end1 = System.currentTimeMillis();
            Operations.Logs.logMessage(log, "        Elapsed time: " + (end1 - start1) + "ms");
        }catch(Exception e){
            Operations.Logs.logMessage(log, "        " + "FAILED TO LOAD DOCUMENTS:  " + ts);
            Operations.Logs.logMessage(log, e.getStackTrace().toString());
            Operations.Logs.logMessage(log, e.getMessage());
            long end1 = System.currentTimeMillis();
            Operations.Logs.logMessage(log, "        Elapsed time: " + (end1 - start1) + "ms");
        }
        return list;
    }

    public static List<Document> insertAll(String log, String collectionName, String[] fileName, String folderName, MongoClient mongoClient, String resPath) {
        long start1 = System.currentTimeMillis();

        MongoDatabase SmartRoomTrialDb = mongoClient.getDatabase(archiveDBName);
        MongoCollection<Document> Collection = SmartRoomTrialDb.getCollection(collectionName);

        if(collectionName.equals(AdriaCollectionName)){
            System.out.println("        Accessing collection " + collectionName);
            System.out.println("        " + fileName.length * 7330 + " Entries expected ");
            Operations.Logs.logMessage(log, "        Accessing collection " + collectionName);
            Operations.Logs.logMessage(log, "        " + fileName.length * 7330 + " Entries expected ");
        }
        else if(collectionName.equals(DHMZObradenoCollectionName)){
            System.out.println("        Accessing collection " + collectionName);
            System.out.println("        " + 1 * 8761 + " Entries expected ");
            Operations.Logs.logMessage(log, "        Accessing collection " + collectionName);
            Operations.Logs.logMessage(log, "        " + 1 * 8761 + " Entries expected ");
        }
        else if(collectionName.equals(DHMZBaseCollectionName)){
            System.out.println("        Accessing collection " + collectionName);
            System.out.println("        " + fileName.length + " Entries expected ");
            Operations.Logs.logMessage(log, "        Accessing collection " + collectionName);
            Operations.Logs.logMessage(log, "        " + fileName.length + " Entries expected ");
        }

        List<Document> list = new ArrayList<>();
        int i = 0;
        int tot = 0;
            for (i = 0; i < fileName.length; i++) {
                String path = resPath + "" + folderName + "\\" + fileName[i];
                try {
                    BufferedReader br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
                    WrappedReader wr = new WrappedReader(fileName[i], br, path);
                    list.addAll(Reader.fullFile(wr,collectionName));
                    wr.getBr().close();
                    wr = null;
                } catch (IOException ioe) {
                    //Dolazi do IOExceptiona ako ne pronadje file u folderu, nije potrebno išta napraviti jer traži sve fajlove u svakom folderu i sve ih prije ili kasnije pronađe
                    //System.out.println("Nije u tom direktoriju");
                }

            try{
                Collection.insertMany(list, new InsertManyOptions().ordered(false));
                System.out.println("        " + list.size() + " " + collectionName + " readings entered at: " + new Date(System.currentTimeMillis()));
                Operations.Logs.logMessage(log, "        " + list.size() + " " + collectionName + " readings entered at: " + new Date(System.currentTimeMillis()));
                tot += list.size();
                list.clear();
                long end1 = System.currentTimeMillis();
                System.out.println("        Elapsed time: " + (end1 - start1) + "ms");
                System.out.println();
                Operations.Logs.logMessage(log, "        Elapsed time: " + (end1 - start1) + "ms");
            }catch(Exception e){
                Operations.Logs.logMessage(log, "        " + "FAILED TO LOAD DOCUMENTS:  " + new Date(System.currentTimeMillis()));
                Operations.Logs.logMessage(log, e.getStackTrace().toString());
                Operations.Logs.logMessage(log, e.getMessage());
                long end1 = System.currentTimeMillis();
                Operations.Logs.logMessage(log, "        Elapsed time: " + (end1 - start1) + "ms");
            }
        }

        long end2 = System.currentTimeMillis();
        Operations.Logs.logMessage(log, "        Total readings entered: " + tot);
        Operations.Logs.logMessage(log, "        Elapsed time: " + (end2 - start1) + "ms");


        return list;
    }

    public static List<Document> insertAll(String log, String collectionName, String[] fileName, String[] folderName, MongoClient mongoClient, String resPath) {
        long start1 = System.currentTimeMillis();

        MongoDatabase SmartRoomTrialDb = mongoClient.getDatabase(archiveDBName);
        MongoCollection<Document> Collection = SmartRoomTrialDb.getCollection(collectionName);

        if(collectionName.equals(AdriaCollectionName)){
            System.out.println("        Accessing collection " + collectionName);
            System.out.println("        " + fileName.length * 7330 + " Entries expected ");
            Operations.Logs.logMessage(log, "        Accessing collection " + collectionName);
            Operations.Logs.logMessage(log, "        " + fileName.length * 7330 + " Entries expected ");
        }
        else if(collectionName.equals(DHMZObradenoCollectionName)){
            System.out.println("        Accessing collection " + collectionName);
            System.out.println("        " + 1 * 8761 + " Entries expected ");
            Operations.Logs.logMessage(log, "        Accessing collection " + collectionName);
            Operations.Logs.logMessage(log, "        " + 1 * 8761 + " Entries expected ");
        }
        else if(collectionName.equals(DHMZBaseCollectionName)){
            System.out.println("        Accessing collection " + collectionName);
            System.out.println("        " + fileName.length + " Entries expected ");
            Operations.Logs.logMessage(log, "        Accessing collection " + collectionName);
            Operations.Logs.logMessage(log, "        " + fileName.length + " Entries expected ");
        }

        List<Document> list = new ArrayList<>();
        int i = 0;
        int tot = 0;
        int j = 0;

        for(j = 0; j < folderName.length; j++){
            for (i = 0; i < fileName.length; i++) {
                String path = resPath + "" + folderName[j] + "\\" + fileName[i];
                try {
                    BufferedReader br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8);
                    WrappedReader wr = new WrappedReader(fileName[i], br, path);
                    list.addAll(Reader.fullFile(wr,collectionName));
                    wr.getBr().close();
                    wr = null;
                } catch (IOException ioe) {
                    //Dolazi do IOExceptiona ako ne pronadje file u folderu, nije potrebno išta napraviti jer traži sve fajlove u svakom folderu i sve ih prije ili kasnije pronađe
                    //System.out.println("Nije u tom direktoriju");
                }

                try{
                    Collection.insertMany(list, new InsertManyOptions().ordered(false));
                    System.out.println("        " + list.size() + " " + collectionName + " readings entered at: " + new Date(System.currentTimeMillis()));
                    Operations.Logs.logMessage(log, "        " + list.size() + " " + collectionName + " readings entered at: " + new Date(System.currentTimeMillis()));
                    tot += list.size();
                    list.clear();
                    long end1 = System.currentTimeMillis();
                    System.out.println("        Elapsed time: " + (end1 - start1) + "ms");
                    System.out.println();
                    Operations.Logs.logMessage(log, "        Elapsed time: " + (end1 - start1) + "ms");
                    Operations.Logs.logMessage(log, " ");
                }catch(Exception e){
                    Operations.Logs.logMessage(log, "        " + "FAILED TO LOAD DOCUMENTS:  " + new Date(System.currentTimeMillis()));
                    Operations.Logs.logMessage(log, e.getStackTrace().toString());
                    Operations.Logs.logMessage(log, e.getMessage());
                    long end1 = System.currentTimeMillis();
                    Operations.Logs.logMessage(log, "        Elapsed time: " + (end1 - start1) + "ms");
                }
            }
        }


        long end2 = System.currentTimeMillis();
        System.out.println("        Total readings entered: " + tot);
        System.out.println("        Elapsed time: " + (end2 - start1) + "ms");
        Operations.Logs.logMessage(log, "        Total readings entered: " + tot);
        Operations.Logs.logMessage(log, "        Elapsed time: " + (end2 - start1) + "ms");


        return list;
    }


}
