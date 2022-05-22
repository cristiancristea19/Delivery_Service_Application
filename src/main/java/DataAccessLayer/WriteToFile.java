package DataAccessLayer;

import BusinessLogicLayer.MenuItem;
import BusinessLogicLayer.Order;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {

    public static void write(String path, String info){
        try{
            FileWriter writer = new FileWriter(path);
            writer.write(info);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
