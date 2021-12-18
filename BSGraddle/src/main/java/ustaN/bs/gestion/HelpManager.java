package ustaN.bs.gestion;

import ustaN.bs.command.list.CommandHelp;

import java.util.HashMap;
import java.util.Map;

public class HelpManager {
    private Map<Integer, String> messages;

    public HelpManager(){
        this.messages = new HashMap<>();
    }
    public void update()
    {
        initHelp();
        messages.put(0,"Aide BSB commandes standards:");
        messages.put(1,"Aide BSB commandes staff:");
        messages.put(2,"Aide BSB commandes Admin:");
        messages.put(3,"Aide BSB commandes BSB Admin:");
        for(HelpMessage e : HelpMessage.getHelpList())
        {
            messages.put(e.getPower(),messages.get(e.getPower())+"\n"+e.getContents());
        }
    }

    public static HelpManager get(){
        HelpManager temp = new HelpManager();
        temp.update();
        return temp;
    }

    public String getHelpMessage(int power) {
        return messages.get(power);
    }

    public void initHelp()
    {
        new HelpMessage();
        new CommandHelp();
        /*new CommandLog();
        new CommandParamGeneral();
        new CommandCandidature();
        new CommandClear();
        new CommandProfile();
        new CommandCasier();
        new CommandParamMemberCount();*/
    }
}
