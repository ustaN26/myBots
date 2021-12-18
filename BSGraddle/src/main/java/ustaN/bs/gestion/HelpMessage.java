package ustaN.bs.gestion;

import java.util.ArrayList;
import java.util.List;

public class HelpMessage {
    private static List<HelpMessage> myHelpsMsg ;
    private String contents;
    private int power;

    public HelpMessage()
    {
        myHelpsMsg = new ArrayList<>();
    }

    //TODO power 0: utilisateurs standards // 1 : modo sur serveur // 2 : admin sur serveur // 3 : BSB admin only


    public HelpMessage(String contents, int power){
        this.contents = contents;
        this.power = power;

        myHelpsMsg.add(this);
    }
    public String getContents() { return contents; }
    public int getPower() { return power; }

    public static List<HelpMessage> getHelpList(){
        return myHelpsMsg;
    }
}
