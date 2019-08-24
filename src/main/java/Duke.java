import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); //start up logo

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?"); //initial greeting

        String userinput; //what the user types in
        Scanner in = new Scanner(System.in); //setting up to read in input from user
        ArrayList<String> userlist = new ArrayList<String>(); //array to store userinputs: ArrayList is similar to vectors in c++
        while (true) {
            userinput = in.nextLine(); // read in input
            if (userinput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            if (userinput.equals("list")) {
                if (userlist.isEmpty()) {
                    System.out.println(" ");
                } else {
                    for (int i = 0; i < userlist.size(); i ++) {
                        System.out.println(i+1 + ". " + userlist.get(i));
                    }
                }
                continue;
            }
            userlist.add(userinput); //add input into the list
            System.out.println("added: " + userinput);

        }
    }
}
