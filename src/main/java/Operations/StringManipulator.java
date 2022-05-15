package Operations;

import com.mongodb.client.model.InsertManyOptions;
import dataset.Filenames;
import org.bson.Document;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static dataset.Filenames.AdriaRes;

public class StringManipulator {

    public static void getNames(String s){
        String[] sarr = s.split("\"");
        for (String f : sarr){
            if(f.length() > 8){
                f = f.substring((f.length()-7));
                f = "\"" + f + "\",";
                System.out.println(f);
            }
        }
    }

    public static void cleanFirstRow(WrappedReader br){
        try{
            List<String> list2 = new ArrayList<>();
            String s;
            do{
                s = br.getBr().readLine();
                list2.add(s);
            }while(s != null);

            FileWriter fw = new FileWriter(br.getFilePath());
            BufferedWriter out = new BufferedWriter(fw);
            for(String str : list2){
                out.write(str + "\n");
            }
            out.flush();
            out.close();
            System.out.println("Cleaned: " + br.getFilePath());
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    public static void cleanAllFirstRows(){
        for(int j = 0 ; j < 106 ; j++) {
            //Flip through folders
            for (int i = 0; i < 166; i++) {
                //Flip through files
                String path = AdriaRes + "" + Filenames.adriaFolderNames[j] + "\\" + Filenames.adriaRoomNames[i];
                try (BufferedReader br = Files.newBufferedReader(Path.of(path), StandardCharsets.UTF_8)) {
                    WrappedReader wr = new WrappedReader(Filenames.adriaRoomNames[i], br, path);
                    cleanFirstRow(wr);
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        }
    }



}
