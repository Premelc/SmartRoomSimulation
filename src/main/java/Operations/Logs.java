package Operations;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Logs {

    public static void logMessage(String logPath , String message){
        try {
            FileWriter fw = new FileWriter(logPath , true);
            BufferedWriter out = new BufferedWriter(fw);
            out.write(message);
            out.write("\n");
            out.flush();
            out.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
