package ustaN.bs.command.list;

import net.dv8tion.jda.api.entities.TextChannel;
import ustaN.bs.BSBot;
import ustaN.bs.command.Command;
import ustaN.bs.gestion.CheckManager;
import ustaN.bs.gestion.HelpMessage;
import ustaN.bs.gestion.SendMessagesManager;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class CommandHelp {
/**
═ "BSB.help" => envoie à l'executant une aide avec toutes les commandes à sa dispositions
**/
    public static HelpMessage HELP = new HelpMessage("═ \"BSB.help\" => envoie à l'executant une aide avec toutes les commandes à sa dispositions", 0);
    public CommandHelp(){;}//genere help message;
    public CommandHelp(Command command) {
        command.getMsg().delete().queue();
        if(sendHelp(command))command.getChan().sendMessage(command.getUser().getAsMention()+", tu as reçu l'aide en mp !").queue();
        else command.getChan().sendMessage(command.getUser().getAsMention()+", active tes messages mp !").queue();
    }

    private boolean sendHelp(Command command) {
        String text = "Commandes disponibles pour vous sur le serveur " + command.getChan().getGuild().getName() + "\n";
        text += BSBot.getHelpManager().getHelpMessage(0);
        if(CheckManager.isModo(command.getUser(),command.getChan().getGuild()))
            text += "\n"+BSBot.getHelpManager().getHelpMessage(1);
        if(CheckManager.isAdmin(command.getUser(),command.getChan().getGuild()))
            text += "\n"+BSBot.getHelpManager().getHelpMessage(2);
        if(CheckManager.isBSStaff(command.getUser()))
            text += "\n"+BSBot.getHelpManager().getHelpMessage(3);
        String textFinal = text;
        try {
            SendMessagesManager.send(command.getUser().openPrivateChannel().complete(), textFinal);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
