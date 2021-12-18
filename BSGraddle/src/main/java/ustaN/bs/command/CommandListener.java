package ustaN.bs.command;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ustaN.bs.BSBot;
import ustaN.bs.OnlyTest.CreateTextManager;
import ustaN.bs.command.list.CommandHelp;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if(event.getAuthor().isBot())
            return;
        if(event.getMessage().getContentDisplay().length()>4 && event.getMessage().getContentDisplay().subSequence(0,4).toString().compareToIgnoreCase(BSBot.prefix)==0)
        {
            Command command = new Command(event);
            String cmd = command.getCmd();
            System.out.println(command);
            if(cmd.equalsIgnoreCase("help"))
                new CommandHelp(command);
            if(cmd.equalsIgnoreCase("createText"))
                new CreateTextManager(command);
            /*else if (cmd.equalsIgnoreCase("Candidature"))
                new CommandCandidature(command);
            else if(cmd.equalsIgnoreCase("log"))
                new CommandLog(command);
            else if(cmd.equalsIgnoreCase("paramFCM"))
                new CommandParamGeneral(command);
            else if(cmd.equalsIgnoreCase("clear"))
                new CommandClear(command);
            else if(cmd.equalsIgnoreCase("profile"))
                new CommandProfile(command);
            else if(cmd.equalsIgnoreCase("casier"))
                new CommandCasier(command);
            else if(cmd.equalsIgnoreCase("parammembercount"))
                new CommandParamMemberCount(command);*/
            //BSBot.getMain().sendLog(event.getGuild().getId()+"%"+event.getAuthor().getName()+"%"+event.getChannel().getName()+"%"+event.getMessage().getContentDisplay(),"commande");
        }
        else
            ;//BSBot.getMain().sendLog(event.getMessage());
    }
}
