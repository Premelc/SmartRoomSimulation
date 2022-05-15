package DB;

import dataset.AdriaIndoorDataset;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.sql.Timestamp;

public class AdriaIndoorDocument implements DocumentI {


    public static Document createDoc(AdriaIndoorDataset data){

        Document doc = new Document("_id" , new ObjectId());
            doc.append("RB" , data.getRB())
                    .append("room_num" , data.getRoomName())
                    .append("vrijeme" , data.getTimestamp().getTime())
                    .append("timestamp" , data.getTimestamp())
                    .append("zadana" , data.getZadana())
                    .append("izmjerena" ,data.getIzmjerena())
                    .append("status_klime" , data.getStatusKlime())
                    .append("brzina_puhanja" ,data.getBrzinaPuhanja())
                    .append("ventil" , data.getVentil())
                    .append("prisutnost" , data.getPrisutnost())
                    .append("prozor" ,data.getProzor())
                    .append("mod_klime" , data.getModKlime())
                    .append("wc_set" , data.getWcSet())
                    .append("wc_mjerenja" , data.getWcMjerena());
        return doc;
    }

    public static Document createDoc(AdriaIndoorDataset data , Timestamp ts){


        Document doc = new Document("_id" , new ObjectId());
        doc.append("RB" , data.getRB())
                .append("room_num" , data.getRoomName())
                .append("vrijeme" , ts.getTime())
                .append("timestamp" , ts)
                .append("zadana" , data.getZadana())
                .append("izmjerena" ,data.getIzmjerena())
                .append("status_klime" , data.getStatusKlime())
                .append("brzina_puhanja" ,data.getBrzinaPuhanja())
                .append("ventil" , data.getVentil())
                .append("prisutnost" , data.getPrisutnost())
                .append("prozor" ,data.getProzor())
                .append("mod_klime" , data.getModKlime())
                .append("wc_set" , data.getWcSet())
                .append("wc_mjerenja" , data.getWcMjerena());

        return doc;
    }

}
