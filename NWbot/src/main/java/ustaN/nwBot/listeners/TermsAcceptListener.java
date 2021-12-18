package ustaN.nwBot.listeners;

import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ustaN.nwBot.ID;
import ustaN.nwBot.nwBot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class TermsAcceptListener extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        try{
            if (event.getUser().isBot())
                return;
            if (event.getMessageId().equalsIgnoreCase(ID.termsMsg.id))
            {
                Guild guild = event.getGuild();
                User user = event.getUser();
                if (event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+1F7E0")) {
                    askForRole(user, guild.getRoleById(ID.roleMembre.id).getAsMention());
                } else if (event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+1F535")) {
                    askForRole(user, guild.getRoleById(ID.roleAllier.id).getAsMention());
                } else if (event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+1F7E2")) {
                    askForRole(user, guild.getRoleById("915573105274028042").getAsMention());
                }
                return;
            }
            if (event.getTextChannel().getId().equalsIgnoreCase(ID.acceuilChan.id))
            {
                Guild guild = event.getGuild();
                Message msg = event.retrieveMessage().complete();
                if (!msg.getMentionedUsers().isEmpty())
                    if (!msg.getMentionedRoles().isEmpty()) {
                        if (event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+2705")) {
                            for (User user : msg.getMentionedUsers())
                                for (Role role : msg.getMentionedRoles())
                                    event.getGuild().addRoleToMember(user.getId(), role).queue();
                            msg.delete().queue();
                        } else if (event.getReactionEmote().getAsCodepoints().equalsIgnoreCase("U+274e")) {
                            for (Role role : msg.getMentionedRoles()) {
                                if (role.equals(guild.getRoleById(ID.roleMembre.id))) {
                                    for (User user : msg.getMentionedUsers())
                                        guild.getTextChannelById(ID.termsChan.id).removeReactionById(ID.termsMsg.id, "U+1f538",user).queue();
                                }
                                if (role.equals(guild.getRoleById(ID.roleAllier.id))) {
                                    for (User user : msg.getMentionedUsers())
                                        guild.getTextChannelById(ID.termsChan.id).removeReactionById(ID.termsMsg.id, "U+1f539",user).queue();
                                }
                            }
                            msg.delete().queue();
                        }
                        else
                            msg.removeReaction(event.getReactionEmote().getEmote()).queue();
                    }
            }
        }
        catch (Exception e)
        {
            File file = new File("erreur.log");
            PrintWriter pw = null;
            try {
                pw = new PrintWriter(file);
            } catch (FileNotFoundException ex) {
            }
            e.printStackTrace(pw);
            pw.close();
            nwBot.log("erreur");
            nwBot.getbot().getTextChannelById(ID.logChan.id).sendFile(file,"erreur.log").queue();
        }

    }

    private void askForRole(User user, String roleMention) {
        Guild guild = nwBot.getbot().getGuildById(ID.guild.id);
        String msgTxt = user.getAsMention() + " souhaite obtenir le role " + roleMention;
        guild.getTextChannelById(ID.acceuilChan.id).sendMessage(msgTxt).queue(msg -> {
            msg.addReaction("U+2705").queue();
            msg.addReaction("U+274e").queue();
        });
    }
}
