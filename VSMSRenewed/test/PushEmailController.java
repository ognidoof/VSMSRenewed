
import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Use Pushbullet JAVA API in Gradle as your possible ways.
   Learn first Team Treehouse Dependency management and Local Development Environment 
 */

/**
 *
 * @author vincentt.2013
 */
public class PushEmailController {

    public static void main(String[] args) {
        try {
            // Execute a command without arguments
            String command = getEmailCommand("vincentt.2013@sis.smu.edu.sg");
            System.out.println(command);
            Process child = Runtime.getRuntime().exec(command);
            BufferedReader br = new BufferedReader(new InputStreamReader(child.getInputStream()));
            System.out.println("The line is "+br.readLine());
            String s;
            while(( s = br.readLine())!=null){
                System.out.println("line: "+s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getEmailCommand(String email) {
        String command = "curl --header 'Access-Token: iK9P8vOguisnDXFr58BzKAhFbeYmavIS' \\\n"
                + "     --header 'Content-Type: application/json' \\\n"
                + "     --data-binary '{\"email\":\""+email+"\",\"body\":\n"
                + "     \"REKTquisition UPDATE: (Animal Farm) ********************************************************************************** BBB has sent an order: Here are the items: \\n 1. 25 kg Rib Eye 2. 200 Leg of Duck 3. 40 Kg Ground Beef. Please reply back to this number: 8722xxxx\",\"title\":\"Push Bullet Testing Pushed\",\"type\":\"note\"}' \\\n"
                + "     --request POST \\\n"
                + "     https://api.pushbullet.com/v2/pushes";
        return command;
    }
}
