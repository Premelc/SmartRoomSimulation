package DB;

import dataset.DHMZObradenoDataset;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.Timestamp;

public class DHMZObradenoDocument {

    public static Document createDoc(DHMZObradenoDataset data , Timestamp ts){
        Document doc = new Document("_id" , new ObjectId());
        doc.append("vrijeme" , ts.getTime())
                .append("timestamp" , ts)
                .append("zracenje" , data.getZracenje())
                .append("temperatura" ,data.getTemperatura())
                .append("smjer_vjetra" , data.getSmjerVjetra())
                .append("brzinaVjetra" ,data.getBrzinaVjetra())
                .append("vlaznost" , data.getRelativnaVlaznost());
        return doc;
    }

    public static Document createDoc(DHMZObradenoDataset data){
        Document doc = new Document("_id" , new ObjectId());
        doc.append("vrijeme" , data.getDatum())
                .append("timestamp" , data.getTs())
                .append("zracenje" , data.getZracenje())
                .append("temperatura" ,data.getTemperatura())
                .append("smjer_vjetra" , data.getSmjerVjetra())
                .append("brzinaVjetra" ,data.getBrzinaVjetra())
                .append("vlaznost" , data.getRelativnaVlaznost());
        return doc;
    }

}
