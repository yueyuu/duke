import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo); //start up logo
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n"); //initial greeting
        System.out.println("-Note for date and time format-");
        System.out.println("\tDeadlines: dd/mm/yy hh:mm");
        System.out.println("\tEvents: dd/mm/yy hh:mm-hh:mm\n");
        //END OF INITIAL STARTUP

        Scanner in = new Scanner(System.in); //setting up to read in input from user
        ArrayList<Task> userList = new ArrayList<Task>(); //array to store userinputs: ArrayList is similar to vectors in c++
        Storage.readFromFile(userList);
        Task t;


        while (true) {
            String input = in.nextLine().trim(); // read in input (the whole line)
            String[] userInput = input.split(" ", 2); //extract out the command word

            if (userInput[0].equals("bye")) { //BYE
                System.out.println("Bye! Hope to see you again soon!");
                return;
            } else if (userInput[0].equals("list")) { //LIST
                if (userList.isEmpty()) {
                    System.out.println("Congrats! You have no tasks! :D");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < userList.size(); i++) {
                        System.out.println("\t" + (i+1) + "." + userList.get(i).format());
                    }
                }
                continue;
            } else  {
                try {
                    if (userInput[0].equals("done")) { //DONE
                        try {
                            int taskNum = Integer.parseInt(userInput[1]) - 1; //if fail gives exception n
                            userList.get(taskNum).markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("\t" + userList.get(taskNum).format());
                            Storage.overrideFile(userList);
                        } catch (NumberFormatException n) {
                            System.out.println("The task number has to be in digits. :)");
                        }
                        continue;
                    } else if (userInput[0].equals("delete")) { //REMOVING TASK
                        try {
                            int taskNum = Integer.parseInt(userInput[1])-1;
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println("\t" + userList.get(taskNum).format());
                            userList.remove(taskNum);
                            System.out.printf("Now you have %d tasks in the list.\n", userList.size());
                            Storage.overrideFile(userList);
                        } catch (NumberFormatException f) {
                            System.out.println("The task number has to be in digits. :)");
                        }
                        continue;
                    } else if (userInput[0].equals("find")) { //FINDING TASK: search for keyword
                        String keyword = userInput[1];
                        ArrayList<Task> match = new ArrayList<Task>(); //to store task that contains the keyword
                        for (Task task : userList) {
                            if (task.description.contains(keyword)) {
                                match.add(task);
                            }
                        }
                        if (match.isEmpty()) {
                            System.out.println("There are no matching tasks. :-(");
                        } else {
                            System.out.println("Here are the matching tasks in your list:");
                            for (int j = 0; j < match.size(); j++) {
                                System.out.println("\t" + (j+1) + "." + match.get(j).format());
                            }
                        }
                        continue;
                    } else if (userInput[0].equals("todo")) {
                        t = new Task(userInput[1]);
                    } else if (userInput[0].equals("deadline")) {
                        String[] deadline = userInput[1].split(" /by ", 2);
                        try {
                            t = new Deadline(deadline[0], deadline[1]);
                        }  catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                            System.out.println("Please input the date and time(24h format) in " +
                                    "the format \'dd/mm/yy hh:mm\'");
                            continue;
                        }
                    } else if (userInput[0].equals("event")) {
                        String[] event = userInput[1].split(" /at ", 2);
                        try {
                            t = new Event(event[0], event[1]);
                        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException ex) {
                            System.out.println("Please input the date and time(24h format) in " +
                                    "the format \'dd/mm/yy hh:mm-hh:mm\'"); continue;
                        }
                    } else {
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.printf("☹ OOPS!!! The description of a %s cannot be empty.\n", userInput[0]);
                    continue;
                }
            }

            userList.add(t); //add input into the arraylist
            Storage.writeToFile(t);
            System.out.println("Got it. I've added this task: ");
            System.out.println(" " + t.format());
            System.out.printf("Now you have %d tasks in the list.\n", userList.size());

        }
    }
}
