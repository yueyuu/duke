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

        //TASK CLASS
        class Task { //same as ToDos
            protected String description;
            protected boolean isDone;
            protected String identifier;

            public Task(String description) {
                this.description = description;
                this.isDone = false;
                this.identifier = "[T]";
            }

            public String getStatusIcon() {
                return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
            }
            public void markAsDone() {
                this.isDone = true;
            }
            public String format() {
                return (this.identifier + "[" + this.getStatusIcon() + "] " + this.description);
            }
        }

        class Deadline extends Task {
            protected String dueDate; //includes date and time

            public Deadline (String description, String dueDate) {
                super(description);
                this.dueDate = dueDate;
                this.identifier = "[D]";
            }

            @Override
            public String format() {
                return (super.format() + " (by: " + this.dueDate + ")");
            }
        }

        class Event extends Task {
            protected String period; //includes date and timing of event

            public Event(String description, String period) {
                super(description);
                this.period = period;
                this.identifier = "[E]";
            }

            @Override
            public String format() {
                return (super.format() + " (at: " + this.period + ")");
            }
        }


        String userinput; //what the user types in
        Scanner in = new Scanner(System.in); //setting up to read in input from user
        ArrayList<Task> userlist = new ArrayList<Task>(); //array to store userinputs: ArrayList is similar to vectors in c++
        Task t;
        while (true) {
            userinput = in.nextLine(); // read in input
            //BYE
            if (userinput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            //LIST
            else if (userinput.equals("list")) {
                if (userlist.isEmpty()) {
                    System.out.println(" ");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < userlist.size(); i ++) {
                        System.out.println(i+1 + "." + userlist.get(i).format());
                    }
                }
                continue;
            }
            //DONE
            else if (userinput.startsWith("done ")) {
                String completedtask = userinput.substring(5);
                int tasknum = Integer.parseInt(completedtask);
                userlist.get(tasknum-1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" [" + "\u2713" + "] " + userlist.get(tasknum-1).description);
                continue;
            }
            //ADDING TASK

            else if (userinput.startsWith("todo ")) {
                t = new Task(userinput.substring(5));
            } else if (userinput.startsWith("deadline ")) {
                int indexd = userinput.indexOf(" /by");
                t = new Deadline(userinput.substring(9, indexd), userinput.substring(indexd+5));
            } else if (userinput.startsWith("event ")){
                int indexe = userinput.indexOf(" /at");
                t = new Event(userinput.substring(6, indexe), userinput.substring(indexe+5));
            } else {
                t = new Task(null);
            }

            userlist.add(t); //add input into the list
            System.out.println("Got it. I've added this task: ");
            System.out.println(" " + t.format());
            System.out.printf("Now you have %d tasks in the list.\n", userlist.size());

        }
    }
}
