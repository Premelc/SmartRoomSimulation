package DB;

import dataset.DHMZObradenoDataset;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.sql.Timestamp;

public class DHMZObradenoDocument {

    public static Document createDoc(DHMZObradenoDataset data , Timestamp ts){
        Document doc = new Document("_id" , /*data.get_id()*/ new ObjectId());
        doc.append("vrijeme" , ts.getTime())
                .append("zracenje" , data.getZracenje())
                .append("temperatura" ,data.getTemperatura())
                .append("smjerVjetra" , data.getSmjerVjetra())
                .append("brzinaVjetra" ,data.getBrzinaVjetra())
                .append("vlaznost" , data.getRelativnaVlaznost())
        .append("_class" , "com.premelc.smartroommonitoringapp.models.DHMZObradeno");
        return doc;
    }

    public static Document createDoc(DHMZObradenoDataset data){
        Document doc = new Document("_id" , /*data.get_id()*/ new ObjectId());
        doc.append("vrijeme" , data.getTs().getTime())
                .append("zracenje" , data.getZracenje())
                .append("temperatura" ,data.getTemperatura())
                .append("smjerVjetra" , data.getSmjerVjetra())
                .append("brzinaVjetra" ,data.getBrzinaVjetra())
                .append("vlaznost" , data.getRelativnaVlaznost())
                        .append("_class" , "com.premelc.smartroommonitoringapp.models.DHMZObradeno");

        return doc;
    }

}
