import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


//Temporary class - it's a "dummy" GUI

public class ClientConsole {
    private static boolean loopCondition = true;

    public static void run() throws IOException {

        System.out.println("Welcome to the battleships game, please provide your name");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();

        Socket socket = new Socket("localhost", 5000);
        Scanner scan2 = new Scanner(socket.getInputStream());
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println(name);

        String response = scan2.nextLine();
        System.out.println(response);



        String response2 = scan2.nextLine();
        System.out.println(response2);

        boolean isMyTurn = response2.contains("Your turn");
        System.out.println("turn: " + isMyTurn);

        while (loopCondition) {
            if (isMyTurn){
                String firedShot = scan.nextLine();
                printWriter.println(firedShot);
            }

            String nextStepMessage = scan2.nextLine();
            System.out.println(nextStepMessage);

            isMyTurn = response2.contains("Your turn");
        }
    }
}
