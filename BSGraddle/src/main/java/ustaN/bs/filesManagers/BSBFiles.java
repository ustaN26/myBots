package ustaN.bs.filesManagers;

import java.io.File;
import java.io.IOException;

public class BSBFiles {
    private File file;

    public BSBFiles(String name){

        File firstdirectory = new File("BSB", "BSBFiles");
        if(!firstdirectory.exists()){
            firstdirectory.mkdir();
        }
        this.file = new File(firstdirectory, name+".json");
    }

    public boolean exist() {
        return this.file.exists();
    }

    public void create(){
        try {
            this.file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(){
        this.file.delete();
    }

    public File getFile() {
        return file;
    }
}
