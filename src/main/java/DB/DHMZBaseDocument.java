package DB;

import dataset.DHMZBaseDataset;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.Timestamp;

public class DHMZBaseDocument implements DocumentI {

    public static Document createDoc (DHMZBaseDataset data , Timestamp ts){
        org.bson.Document doc = new org.bson.Document("_id" , new ObjectId());
        return doc;
    }

    public static Document createDoc (DHMZBaseDataset data){
        org.bson.Document doc = new org.bson.Document("_id" , new ObjectId());
        return doc;
    }

}
