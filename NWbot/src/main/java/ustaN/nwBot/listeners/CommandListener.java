package ustaN.nwBot.listeners;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import ustaN.nwBot.listeners.commands.Command;
import ustaN.nwBot.listeners.commands.CommandClear;
import ustaN.nwBot.nwBot;

public class CommandListener extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        if (event.getAuthor().isBot())
            return;
        if (event.getMessage().getContentDisplay().startsWith(nwBot.prefix)) {
            System.out.println("cmd");
            Command cmd = new Command(event);
            if(cmd.getCmd().equals("clear"))
                new CommandClear(cmd);
            else
            {
                cmd.getChan().sendMessage(":c").queue();
            }
        }
    }
}
