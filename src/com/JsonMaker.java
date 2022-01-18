package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class JsonMaker {
    public static void saveMeasurements(Kupa k){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String path = "src/measurements/" + k.getName() + ".json";

        BufferedWriter bw = null;
        try{
            bw = new BufferedWriter(new FileWriter(path));
            bw.write(gson.toJson(k.getMeas()));
            bw.flush();
        }
        catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
