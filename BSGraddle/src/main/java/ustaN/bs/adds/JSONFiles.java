package ustaN.bs.adds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class JSONFiles {

    public static void save(File file, String json){
        final FileWriter fw;

        try{
            fw = new FileWriter(file);
            fw.write(json);
            fw.flush();
            fw.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public static String loadContents(File file){
        if(file.exists()){
            try{
                final BufferedReader reader = new BufferedReader(new FileReader(file));
                final StringBuilder text = new StringBuilder();

                String line;

                while ((line = reader.readLine()) != null){
                    text.append(line);
                }

                reader.close();
                return text.toString();

            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return "";
    }
}
