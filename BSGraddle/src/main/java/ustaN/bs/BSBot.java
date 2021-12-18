package ustaN.bs;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import ustaN.bs.command.CommandListener;
import ustaN.bs.gestion.HelpManager;

import javax.security.auth.login.LoginException;

public class BSBot {
    private static BSBot BSBotMain;
    public static String prefix = "BSB.";
    private static JDA bsbot;
    public BSBot()
    {
        BSBotMain=this;
        setup();
    }

    /******* MANAGERS *******/
    private static HelpManager helpManager;
    private void initManagers()
    {
        helpManager = HelpManager.get();
    }
    public static HelpManager getHelpManager() {return helpManager;}
    /******* MANAGERS *******/

    private void setup() {
        try {
            bsbot = JDABuilder.createDefault("NjM1ODUyNjcyMTA4NTkzMTYy.Xa3F8g.N7ozfZC8YHbEfYt8rk4OgHv3G28").build();
        } catch (LoginException e) {
            e.printStackTrace();
        }
        System.out.println("BSBot start");

        initManagers();
        bsbot.addEventListener(new CommandListener());
    }
    public static BSBot getMain(){return BSBotMain;}
}
