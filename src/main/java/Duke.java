import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    //TASK CLASS
    static class Task { //same as ToDos
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

    static class Deadline extends Task {
        protected LocalDateTime dueDate; //includes date and time
        protected static DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");//24h clock

        public Deadline (String description, String dateTime) {
            super(description);
            this.dueDate = LocalDateTime.parse(dateTime, formatterD);
            this.identifier = "[D]";
        }

        @Override
        public String format() {
            return (super.format() + " (by: " + formatterD.format(this.dueDate) + ")");
        }
    }

    static class Event extends Task {
        protected LocalDate date;
        protected LocalTime start;
        protected LocalTime end;

        protected static DateTimeFormatter formatterED = DateTimeFormatter.ofPattern("dd/MM/yy");
        protected static DateTimeFormatter formatterET = DateTimeFormatter.ofPattern("HH:mm");

        public Event(String description, String period) {
            super(description);
            String[] dateTime = period.split(" ");
            String[] time = dateTime[1].split("-");
            this.date = LocalDate.parse(dateTime[0], formatterED);
            this.start = LocalTime.parse(time[0]);
            this.end = LocalTime.parse(time[1]);
            this.identifier = "[E]";
        }

        @Override
        public String format() {
            return (super.format() + " (at: " + formatterED.format(this.date) + " " + this.start.format(formatterET)
                    + "-" + this.end.format(formatterET) + ")");
        }
    }

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
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?"); //initial greeting
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
                        System.out.println(i+1 + "." + userlist.get(i).format());
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
                            int tasknum = Integer.parseInt(completedtask);
                            userlist.get(tasknum - 1).markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println(" [" + "\u2713" + "] " + userlist.get(tasknum - 1).description);
                            overrideFile(userlist);
                        } else {
                            System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                            t = new Task(null);
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
                                    "the format \'dd/mm/yy hhmm\'"); continue;
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
                                        "the format \'dd/mm/yy hhmm-hhmm\'"); continue;
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
                    } else {
                        s = "done";
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
