package ustaN.nwBot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import ustaN.nwBot.listeners.commands.CommandListener;
import ustaN.nwBot.listeners.TempVoiceChannelListener;
import ustaN.nwBot.listeners.TermsAcceptListener;

public class nwBot {
    private static nwBot mainbot;
    public static String prefix = "nw";
    private static JDA bot;

    public static JDA getbot() {
        return bot;
    }

    public nwBot() {
        mainbot = this;
        connect();
        init();
        while (true) {
            try {
                log("bot online :kissing_heart:");
                break;
            } catch (Exception e) {}
        }
        initAsConnected();
    }

    private void initAsConnected() {
        TextChannel chan = bot.getGuildById(ID.guild.id).getTextChannelById(ID.termsChan.id);
        chan.addReactionById(ID.termsMsg.id, "U+1F7E0").queue();
        chan.addReactionById(ID.termsMsg.id, "U+1F7E2").queue();
        chan.addReactionById(ID.termsMsg.id, "U+1F535").queue();
        TempVoiceChannelListener.init();
    }

    private void connect() {
        for (int i = 1; i != 4; i++)
            try {
                bot = JDABuilder.createDefault("OTA4NzY4NzAwNjUwMzU2NzY2.YY6izg.zD4rFQmsYFHIqdE1R0xNii0WgVY").build();
                System.out.println("connection réussie");
                break;
            } catch (Exception e) {
                System.out.println("connection échouée essaie " + i);
                if (i == 3) {
                    System.out.println("arret du bot");
                    System.exit(0);
                }
            }
    }

    private void init() {
        bot.addEventListener(new CommandListener());
        bot.addEventListener(new TermsAcceptListener());
        bot.addEventListener(new TempVoiceChannelListener());
    }

    public static void log(String str) {
        bot.getTextChannelById(ID.logChan.id).sendMessage(str).queue();
    }
}