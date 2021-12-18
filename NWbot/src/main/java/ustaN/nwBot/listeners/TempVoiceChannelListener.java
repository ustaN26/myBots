package ustaN.nwBot.listeners;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceJoinEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceLeaveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceMoveEvent;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ustaN.nwBot.ID;
import ustaN.nwBot.nwBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TempVoiceChannelListener extends ListenerAdapter {
    private static List<Long> tempVoiceChan;
    public static void init()
    {
        tempVoiceChan = new ArrayList<>();
    }

    @Override
    public void onGuildVoiceJoin(GuildVoiceJoinEvent event)
    {
        try {
            if (event.getChannelJoined().getId().equalsIgnoreCase(ID.tempChanRoom.id)) {
                Guild guild = event.getEntity().getGuild();
                User user = event.getEntity().getUser();
                guild.createVoiceChannel(user.getName() + "'s room", guild.getCategoryById(ID.VoiceChanCategorie.id)).queue(
                        voiceChannel -> {
                            tempVoiceChan.add(voiceChannel.getIdLong());
                            guild.moveVoiceMember(event.getEntity(), voiceChannel).queue();
                            nwBot.log("Voice channel \"" + voiceChannel.getName() + "\" created");
                        });
            }
        } catch (Exception e) {
            File file = new File("erreur.log");
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(file);
            } catch (FileNotFoundException ex) {
            }
            e.printStackTrace(pw);
            pw.close();
            nwBot.getbot().getTextChannelById(ID.logChan.id).sendMessage(" erreur create temp channel or move").addFile(file, "erreur.log").queue();
        }
    }
    @Override
    public void onGuildVoiceLeave(GuildVoiceLeaveEvent event)
    {
        try {
            if (tempVoiceChan.contains(event.getChannelLeft().getIdLong())) {
                VoiceChannel chan = event.getChannelLeft();
                if (chan.getMembers().isEmpty()) {
                    String logtxt = "Voice channel \"" + chan.getName() + "\" deleted";
                    tempVoiceChan.remove(chan.getIdLong());
                    chan.delete().complete();
                    nwBot.log(logtxt);
                }
            }
        }catch (Exception e)
        {
            File file = new File("erreur.log");
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(file);
            } catch (FileNotFoundException ex) {}
            e.printStackTrace(pw);
            pw.close();
            nwBot.getbot().getTextChannelById(ID.logChan.id).sendMessage("erreur delete temp channel after leave").addFile(file,"erreur.log").queue();
        }
    }
}
