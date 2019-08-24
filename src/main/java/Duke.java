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
        //END OF INITIAL STARTUP


        class Task {
            protected String description;
            protected boolean isDone;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
            }

            public String getStatusIcon() {
                return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
            }

            public void markAsDone() {
                this.isDone = true;
            }
        }



        String userinput; //what the user types in
        Scanner in = new Scanner(System.in); //setting up to read in input from user
        ArrayList<Task> userlist = new ArrayList<Task>(); //array to store userinputs: ArrayList is similar to vectors in c++
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
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < userlist.size(); i ++) {
                        System.out.print(i+1 + ".[" + userlist.get(i).getStatusIcon() + "] ");
                        System.out.println(userlist.get(i).description);
                    }
                }
                continue;
            }
            if (userinput.startsWith("done ")) {
                String completedtask = userinput.substring(5);
                int tasknum = Integer.parseInt(completedtask);
                userlist.get(tasknum-1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [" + "\u2713" + "] " + userlist.get(tasknum-1).description);
                continue;
            }
            Task t = new Task(userinput);
            userlist.add(t); //add input into the list
            System.out.println("added: " + t.description);

        }
    }
}
