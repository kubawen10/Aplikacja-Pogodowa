import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class JsonMaker {
    public static void saveMeasurements(Kupa k){
        //its a test
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String path = "src/measurements/" + k.getName() + ".txt";

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path))){
            bw.write(gson.toJson(k.getMeas()));
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
