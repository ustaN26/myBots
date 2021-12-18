package ustaN.nwBot.listeners.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import ustaN.nwBot.nwBot;

public class Command {
    private MessageReceivedEvent event;
    private User user;
    private Message msg;
    private TextChannel chan;
    private String[] args;
    private String cmd;

    public Command(MessageReceivedEvent event_)
    {
        event=event_;
        user = event.getAuthor();
        msg = event.getMessage();
        chan = event.getTextChannel();
        args = msg.getContentDisplay().replaceFirst(nwBot.prefix,"").split(" ");
        cmd = args[0];
    }

    public User getUser() { return user; }
    public Message getMsg() { return msg; }
    public TextChannel getChan() { return chan; }
    public String[] getArgs() { return args; }
    public String getCmd() { return cmd; }
}
