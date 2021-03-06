package Operations;

import DB.AdriaIndoorDocument;
import DB.DHMZBaseDocument;
import DB.DHMZObradenoDocument;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dataset.AdriaIndoorDataset;
import dataset.DHMZBaseDataset;
import dataset.DHMZObradenoDataset;
import org.bson.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static dataset.Filenames.*;

public class Reader {

    public static List<Document> SingleLine(WrappedReader br , Timestamp ts , String collectionName){
        List<Document> list = new ArrayList<>();
        try{
            String s = br.getBr().readLine();

            String[] attributes = s.split(",");

            if (attributes.length != 0) {
                if(collectionName.equals(AdriaCollectionName)){
                    //Dodatno da se u atribute doda broj sobe za AdriaIndoorDataset
                    s= s + "," + br.getFileName().substring(5 , br.getFileName().length()-4);
                    attributes = s.split(",");
                    Document doc = AdriaIndoorDocument.createDoc(new AdriaIndoorDataset(attributes) , ts);
                    list.add(doc);
                }
                else if(collectionName.equals(DHMZBaseCollectionName)){
                    Document doc = DHMZBaseDocument.createDoc(new DHMZBaseDataset(attributes) , ts);
                    list.add(doc);
                }
                else if(collectionName.equals(DHMZObradenoCollectionName)){
                    Document doc = DHMZObradenoDocument.createDoc(new DHMZObradenoDataset(attributes) , ts);
                    list.add(doc);
                }
            }
            StringManipulator.cleanFirstRow(br);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return list;
    }

    public static List<Document> fullFile(WrappedReader br , String collectionName){
        List<Document> list = new ArrayList<>();
        try{
            String s = br.getBr().readLine();

            while(s != null){

                String[] attributes = s.split(",");

                if (attributes.length != 0) {
                    if(collectionName.equals(AdriaCollectionName)){
                        //Dodatno da se u atribute doda broj sobe za AdriaIndoorDataset
                        s= s + "," + br.getFileName().substring(5 , br.getFileName().length()-4);
                        attributes = s.split(",");
                        Document doc = AdriaIndoorDocument.createDoc(new AdriaIndoorDataset(attributes));
                        list.add(doc);
                    }
                    else if(collectionName.equals(DHMZBaseCollectionName)){
                        Document doc = DHMZBaseDocument.createDoc(new DHMZBaseDataset(attributes));
                        list.add(doc);
                    }
                    else if(collectionName.equals(DHMZObradenoCollectionName)){
                        Document doc = DHMZObradenoDocument.createDoc(new DHMZObradenoDataset(attributes));
                        list.add(doc);
                    }
                }
                //StringManipulator.cleanFirstRow(br);
                s = br.getBr().readLine();
            }
            }catch (IOException ioe){
            ioe.printStackTrace();
        }

        return list;
    }

    public static List<Document> fullFileAlternate(WrappedReader br , String collectionName){
        List<Document> list = new ArrayList<>();
        try{
            String s = br.getBr().readLine();

            while(s != null){

                String[] attributes = s.split(",");

                if (attributes.length != 0) {
                    if(collectionName.equals(AdriaCollectionName)){
                        //Dodatno da se u atribute doda broj sobe za AdriaIndoorDataset
                        s= s + "," + br.getFileName().substring(5 , br.getFileName().length()-4);
                        attributes = s.split(",");
                        Document doc = AdriaIndoorDocument.createDoc(new AdriaIndoorDataset(attributes));
                        list.add(doc);
                    }
                    else if(collectionName.equals(DHMZBaseCollectionName)){
                        Document doc = DHMZBaseDocument.createDoc(new DHMZBaseDataset(attributes));
                        list.add(doc);
                    }
                    else if(collectionName.equals(DHMZObradenoCollectionName)){
                        Document doc = DHMZObradenoDocument.createDoc(new DHMZObradenoDataset(attributes));
                        list.add(doc);
                    }
                }
                //StringManipulator.cleanFirstRow(br);
                s = br.getBr().readLine();
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        return list;
    }

    public static List<Document> SingleLine(WrappedReader br , String collectionName){
        List<Document> list = new ArrayList<>();
        try{
            String s = br.getBr().readLine();

            String[] attributes = s.split(",");

            if (attributes.length != 0) {
                if(collectionName.equals(AdriaCollectionName)){
                    //Dodatno da se u atribute doda broj sobe za AdriaIndoorDataset
                    s= s + "," + br.getFileName().substring(5 , br.getFileName().length()-4);
                    attributes = s.split(",");
                    Document doc = AdriaIndoorDocument.createDoc(new AdriaIndoorDataset(attributes));
                    list.add(doc);
                }
                else if(collectionName.equals(DHMZBaseCollectionName)){
                    Document doc = DHMZBaseDocument.createDoc(new DHMZBaseDataset(attributes));
                    list.add(doc);
                }
                else if(collectionName.equals(DHMZObradenoCollectionName)){
                    Document doc = DHMZObradenoDocument.createDoc(new DHMZObradenoDataset(attributes));
                    list.add(doc);
                }
            }
            StringManipulator.cleanFirstRow(br);
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return list;
    }

    public static String[] parseDateTime(String s){
        String[]str = s.split("-");
        str[0] = str[0] + "-" + str[1] + "-" + str[2];
        str[1] = str[3];
        return str;
    }

    public static String[] parseDHMZbaseDateTime(String s){
        String[]str = s.split(" ");
        return str;
    }
}
