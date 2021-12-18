package ustaN.bs.gestion;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

public class CheckManager {
    /*public static boolean checkStaff(User user) {
        for(Guild guild : GuildManager.getStaffGuilds())
            for(Member mbr : guild.getMembers())
                if(mbr.getUser().equals(user))
                    return true;
        return false;
    }

    public static boolean checkIsAdmin(User user, Guild guild) {
        if(GuildManager.getGuild(guild).getAdmins().contains(user))
            return true;
        return false;
    }*/

    public static boolean checkHasPermission(User user, TextChannel chan, Permission... permission) {
        try
        {
            if(chan.getGuild().getMember(user).hasPermission(chan,permission))
                return true;
            else
                return false;
        }
        catch (Exception e){e.printStackTrace();}
        return false;
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

    public static boolean isModo(User user, Guild guild) {
        return false;//TODO
    }

    public static boolean isAdmin(User user, Guild guild) {
        return false;//TODO
    }

    public static boolean isBSStaff(User user) {
        return false;//TODO
    }

/*    public static Member checkIsIDMember(String arg) {
        for (Guild guild: GuildManager.getGuilds())
            for(Member mbr : guild.getMembers())
                if(mbr.getId().equalsIgnoreCase(arg))
                    return mbr;
        return null;
    }*/
}
