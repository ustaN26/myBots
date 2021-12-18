package ustaN.nwBot.listeners.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;
import ustaN.nwBot.nwBot;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommandClear {
/**
"╔"nwclear" : outil pour supprimer rapidement beaucoup de messages
"║(permission MESSAGE_MANAGE requise dans le channel où la commande est utilisée)
"║  parametres :
"║     - <nombre de message à clear> => supprime le nombre de message donné (100 par default si rien est donné)
"╚     - until [message ID] => supprime tous les messages jusqu'a celui indiqué
**/
    public static String helpMsg = "\""+ nwBot.prefix +"clear\" : outil pour supprimer rapidement beaucoup de messages\n" +
            "(permission MESSAGE_MANAGE requise dans le channel où la commande est utilisée)\n" +
            "\tparametres :\n" +
            "\t\t- <nombre de message à clear> => supprime le nombre de message donné (100 par default si rien est donné)\n" +
            "\t\t- until [message ID] => supprime tous les messages jusqu'a celui indiqué\n"+
            "\t\t- until (en reponse a un message) => supprime tous les messages jusqu'a celui ping\n";;
    public CommandClear(Command command) {
        command.getMsg().delete().complete();
        if(command.getMsg().getMember().hasPermission(command.getChan(),Permission.MESSAGE_MANAGE))
        {
            if (command.getArgs().length<2)
                command.getChan().sendMessage("Arguments manquant [message ID]: "+command.getMsg().getContentDisplay()+"\n"+helpMsg).queue();
            else if(command.getArgs()[1].equalsIgnoreCase("until"))
                if (hasReferenceMessage(command.getMsg()))
                {
                    check(command,command.getMsg().getReferencedMessage().getId());
                }else if(command.getArgs().length>=3)
                {
                    String arg = command.getArgs()[2];
                    if(arg.length()!=18)
                        arg=arg.substring(arg.length()-18);
                    check(command,arg);
                } else
                    command.getChan().sendMessage("Arguments manquant [message ID]: "+command.getMsg().getContentDisplay()+"\n"+helpMsg).queue();
            else
            {
                if(checkIsInt(command.getArgs()[1]))
                {
                    clear(Integer.parseInt(command.getArgs()[1]),command.getChan());
                }else
                    command.getChan().sendMessage("Veuiller entrer un nombre entier").queue(message -> {message.delete().queueAfter(15, TimeUnit.SECONDS);});
            }
        }else
            command.getChan().sendMessage("Vous n'avez pas la permission de gerer les messages").queue();
    }

    private boolean hasReferenceMessage(Message msg) {
        try{
            msg.getReferencedMessage();
            return true;
        }catch (Exception e)
        {
            return false;
        }
    }

    private void check(Command command, String arg)
    {
        boolean found=false;
        while(!found) {
            try {
                List<Message> messages = command.getChan().getHistory().retrievePast(100).complete();
                List<Message> toDelete = new ArrayList<>();
                for (Message msg : messages) {
                    if (msg.getIdLong() != Long.parseLong(arg))
                        toDelete.add(msg);
                    else {
                        toDelete.add(msg);
                        found = true;
                        break;
                    }
                }
                List<Message> pinnedMessages = new ArrayList<>();
                toDelete.forEach(m -> {
                    if (m.isPinned()) pinnedMessages.add(m);
                });
                toDelete.removeAll(pinnedMessages);
                command.getChan().deleteMessages(toDelete).complete();
            } catch (Exception e) {
                break;
            }
        }
    }
    private void clear(int nbrMsg, TextChannel chan) {
        while(true)
        {
            int temp;
            try {
                if(nbrMsg>100)
                {
                    nbrMsg-=100;
                    temp=100;
                }else if(nbrMsg==0)
                    break;
                else {
                    temp = nbrMsg;
                    nbrMsg=0;
                }
                List<Message> messages = chan.getHistory().retrievePast((temp > 1 ? temp : temp + 1)).complete();
                List<Message> pinnedMessages = new ArrayList<>();
                messages.forEach(m -> {
                    if (m.isPinned()) pinnedMessages.add(m);
                });
                messages.removeAll(pinnedMessages);
                chan.deleteMessages(messages).complete();
            }catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
    }
    public static boolean checkIsInt(String str) {
        try {
            Integer.parseInt(str);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
