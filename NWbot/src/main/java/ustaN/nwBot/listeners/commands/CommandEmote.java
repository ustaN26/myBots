package ustaN.nwBot.listeners.commands;

import net.dv8tion.jda.api.entities.Emote;

public class CommandEmote {

    public CommandEmote(Command command) {
        command.getMsg().delete().complete();
        String emotesTxt = "emote infos:";
        if (!command.getMsg().getEmotes().isEmpty()) {
            for (Emote emote : command.getMsg().getEmotes())
                emotesTxt += "mention "+emote.getAsMention()+" ; code "+emote.getName();
        }
        command.getChan().sendMessage(emotesTxt).queue();
    }
}
