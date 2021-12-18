package ustaN.bs.gestion;

public enum TextEnums {
// ╔ alt+201
// ║ alt+186
// ╚ alt+200
// ═ alt+205

    HELP_BASIC("This is an basic Help Message"),
    HELP_STAFF("This is an staff Help Message"),
    HELP_ADMIN("This is an admin Help Message"),
    ERROR("An error was occured"),
    DONT_PERM("You don't have permission"),
    UNKNOW("Unknown command");

    private String message;

    TextEnums(String message){
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
