
import java.util.Scanner;

public class gameTest {
    public static void main(String[] args){
        System.out.println("testing the game");

        GameHandler testgame = new GameHandler(); //create new game


        
        Scanner getInput = new Scanner(System.in);  // Create a Scanner object
        while(true){
            
            System.out.println("Enter player number");
            String playernumber = (getInput.nextLine());  // Read user input
            //System.out.println("Username is: " + userName);  // Output user input
            
            System.out.println("Enter action");
            String action = (getInput.nextLine());

            executeCommand(playernumber, action, testgame);
        }


    }

    public static void executeCommand(String x, String y, GameHandler g){
        
        if (y == ""){
            g.game.print();
            //System.out.println(g.storydeck.draw().id);
        }
    }
    
}
/*
*/