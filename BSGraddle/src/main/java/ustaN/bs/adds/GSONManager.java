package ustaN.bs.adds;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ustaN.bs.gestion.HelpManager;

public class GSONManager {

    private Gson gson;

    public GSONManager(){
        this.gson = new GsonBuilder().setPrettyPrinting().serializeNulls().disableHtmlEscaping().create();
    }

    public String serialize(HelpManager helpFile) {
        return this.gson.toJson(helpFile);
    }
    public HelpManager getHelpFile(String json) {
        return this.gson.fromJson(json, HelpManager.class);
    }

    /*public String serialize(CandidatureManager candidatureManager) {return this.gson.toJson(candidatureManager); }
    public CandidatureManager getCandidatureManager(String json)  { return this.gson.fromJson(json, CandidatureManager.class); }

    public String serialize(BSMemberFile member) {return this.gson.toJson(member); }
    public BSMemberFile getMemberProfile(String json) { return this.gson.fromJson(json, BSMemberFile.class); }

    public String serialize(MemberCountFile memberCountManager) { return this.gson.toJson(memberCountManager);}
    public MemberCountFile getMemberCountManager(String json) { return this.gson.fromJson(json, MemberCountFile.class); }

    public String serialize(InviteFile inviteManager) { return this.gson.toJson(inviteManager); }
    public InviteFile getInviteManager(String json) { return this.gson.fromJson(json, InviteFile.class); }

    public String serialize(BSGuildFile guild) { return this.gson.toJson(guild); }
    public BSGuildFile getGuild(String json) {return this.gson.fromJson(json, BSGuildFile.class); }

    public String serialize(LogFile logManager) { return this.gson.toJson(logManager); }
    public LogFile getLogManager(String json) {return this.gson.fromJson(json, LogFile.class); }*/

}