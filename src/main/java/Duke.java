import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void writeToFile (Task task) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Data.txt", true); //curr working dir is duke
        file.write(task.identifier + ';');
        file.write(Boolean.toString(task.isDone) + ';');
        file.write(task.description + ';');
        if (task.identifier.equals("[D]")) {
            file.write(Deadline.formatterD.format(((Deadline)task).dueDate) + ';');
            //write the date and time as a string to the file
        } else if (task.identifier.equals("[E]")) {
            file.write(Event.formatterED.format(((Event)task).date) + ' ');
            file.write(Event.formatterET.format(((Event)task).start) + '-');
            file.write(Event.formatterET.format(((Event)task).end) + ';');
        }
        file.close();
    }

    public static void overrideFile (ArrayList<Task> list) throws IOException {
        FileWriter file = new FileWriter("src/main/java/Data.txt");
        file.write(""); // to clear the file
        file.close();
        for (Task l : list) {
            writeToFile(l);
        }
    }

    public static void readFromFile (ArrayList<Task> list) throws FileNotFoundException {
        File data = new File("src/main/java/Data.txt");
        Scanner s = new Scanner(data);
        s.useDelimiter(";");
        while (s.hasNext()) {
            String id = s.next();
            String done = s.next();
            String des = s.next();
            Task t;
            if (id.equals("[D]")) {
                String due = s.next();
                t = new Deadline(des, due);
                if (done.equals("true")) {
                    t.markAsDone();
                }
            } else if (id.equals("[E]")) {
                String due = s.next();
                t = new Event(des, due);
            } else {
                t = new Task(des);
            }
            if (done.equals("true")) {
                t.markAsDone();
            }
            list.add(t);
        }
    }

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

        String userinput; //what the user types in
        Scanner in = new Scanner(System.in); //setting up to read in input from user
        ArrayList<Task> userlist = new ArrayList<Task>(); //array to store userinputs: ArrayList is similar to vectors in c++
        readFromFile(userlist);
        Task t;


        while (true) {
            userinput = in.nextLine(); // read in input (the whole line)
            //BYE
            if (userinput.equals("bye") || userinput.equals("bye ")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            }
            //LIST
            else if (userinput.equals("list") || userinput.equals("list ")) {
                if (userlist.isEmpty()) {
                    System.out.println("Congrats! You have no tasks! :D");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < userlist.size(); i++) {
                        System.out.println("\t" + (i+1) + "." + userlist.get(i).format());
                    }
                }
                continue;
            }
            //DONE
            else  {
                try {
                    if (userinput.startsWith("done")) {
                        if (userinput.charAt(4) == ' ' && userinput.charAt(5) != ' ') {
                            String completedtask = userinput.substring(5);
                            int tasknum = Integer.parseInt(completedtask)-1;
                            userlist.get(tasknum).markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("\t" + userlist.get(tasknum).format());
                            overrideFile(userlist);
                        } else {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        continue;
                    } else if (userinput.startsWith("delete")) { //REMOVING TASK
                        if (userinput.charAt(6) == ' ' && Character.isDigit(userinput.charAt(7))) {
                            String deleteTask = userinput.substring(7);
                            int taskNum = Integer.parseInt(deleteTask)-1;
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println("\t" + userlist.get(taskNum).format());
                            userlist.remove(taskNum);
                            System.out.printf("Now you have %d tasks in the list.\n", userlist.size());
                            overrideFile(userlist);
                        } else {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        continue;
                    }
                    else if (userinput.startsWith("find")) { //FINDING TASK
                        if (userinput.charAt(4) == ' ' && userinput.charAt(5) != ' ') {
                            String keyword = userinput.substring(5);
                            ArrayList<Task> match = new ArrayList<Task>(); //to store task that contains the keyword
                            for (Task task : userlist) {
                                if (task.description.contains(keyword)) {
                                    match.add(task);
                                }
                            }
                            if (match.isEmpty()) {
                                System.out.println("There are no matching tasks. :-(");
                            } else {
                                System.out.println("Here are the matching tasks in your list:");
                                for (int j = 0; j < match.size(); j++) {
                                    System.out.println((j+1) + "." + match.get(j).format());
                                }
                            }
                        } else {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        }
                        continue;
                    }
            //ADDING TASK

                    else if (userinput.startsWith("todo")) {
                        if (userinput.charAt(4) == ' ' && userinput.charAt(5) != ' ') {
                            t = new Task(userinput.substring(5));
                        } else {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                            t = new Task(null); continue;
                        }
                    } else if (userinput.startsWith("deadline")) {
                        if (userinput.charAt(8) == ' ' && userinput.charAt(9) != ' ') {
                            int indexd = userinput.indexOf(" /by");
                            String dateTime = userinput.substring(indexd + 5);
                            try {
                                t = new Deadline(userinput.substring(9, indexd), dateTime);
                            } catch (DateTimeParseException e) {
                                System.out.println("Please input the date and time(24h format) in " +
                                    "the format \'dd/mm/yy hh:mm\'"); continue;
                            }
                        } else {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                            t = new Task(null); continue;
                        }
                    } else if (userinput.startsWith("event")) {
                        if (userinput.charAt(5) == ' ' && userinput.charAt(6) != ' ') {
                            int indexe = userinput.indexOf(" /at");
                            String dateDur = userinput.substring(indexe + 5);
                            try {
                                t = new Event(userinput.substring(6, indexe), dateDur);
                            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException ex) {
                                System.out.println("Please input the date and time(24h format) in " +
                                        "the format \'dd/mm/yy hh:mm-hh:mm\'"); continue;
                            }
                        } else {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                            t = new Task(null); continue;
                        }
                    } else {
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                        t = new Task(null); continue;
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    String s;
                    if (userinput.startsWith("todo")) {
                        s = "todo";
                    } else if (userinput.startsWith("deadline")) {
                        s = "deadline";
                    } else if (userinput.startsWith("event")){
                        s = "event";
                    } else if (userinput.startsWith("done")){
                        s = "done";
                    } else if (userinput.startsWith("find")) {
                        s = "find";
                    } else {
                        s = "delete";
                    }
                    System.out.printf("☹ OOPS!!! The description of a %s cannot be empty.\n", s);
                    t = new Task(null);
                    continue;
                }
            }

            userlist.add(t); //add input into the arraylist
            writeToFile(t);
            System.out.println("Got it. I've added this task: ");
            System.out.println(" " + t.format());
            System.out.printf("Now you have %d tasks in the list.\n", userlist.size());

        }
    }
}
