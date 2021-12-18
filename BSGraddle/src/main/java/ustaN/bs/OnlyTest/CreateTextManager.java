package ustaN.bs.OnlyTest;

import ustaN.bs.command.Command;
import ustaN.bs.gestion.SendMessagesManager;

import java.security.SecureRandom;
import java.util.Random;

public class CreateTextManager {

    public CreateTextManager(Command command) {
        command.getMsg().delete().queue();
        String msg="";
        byte[] array = new byte[Integer.parseInt(command.getArgs()[1])];
        new Random().nextBytes(array);
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder( Integer.parseInt(command.getArgs()[1]) );
        for( int i = 0; i < Integer.parseInt(command.getArgs()[1]); i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        msg = sb.toString();
        try{
            if (command.getArgs()[2].equalsIgnoreCase("space"))
            {
                System.out.println("space");
                int i = new Random().nextInt(msg.getBytes().length);
                msg = msg.substring(0,i)+"\n"+msg.substring(i);
            }
        }catch (Exception ignored){}
        SendMessagesManager.send(command.getChan(),msg);
    }
}
