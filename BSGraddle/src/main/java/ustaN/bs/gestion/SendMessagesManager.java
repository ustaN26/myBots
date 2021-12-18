package ustaN.bs.gestion;

import net.dv8tion.jda.api.entities.MessageChannel;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SendMessagesManager {
    public static void send(MessageChannel chan, String msg)
    {
        if(msg.length()>2000)
        {
            List<String> messages = new ArrayList<>();
            while(msg.length()>0)
            {
                String tempMsg = "";
                try{ tempMsg = msg.substring(0, msg.substring(0,2000).lastIndexOf("\n"));}
                catch (Exception e) {
                    try{tempMsg = msg.substring(0,2000);}
                    catch (Exception ex)
                    {tempMsg = msg;}
                }
                messages.add(tempMsg);
                msg = msg.substring(tempMsg.length());
            }
            for (String m : messages)
                chan.sendMessage(String.format(m, StandardCharsets.UTF_8)).complete();
        }
        else
            chan.sendMessage(String.format(msg, StandardCharsets.UTF_8)).complete();
    }
}
