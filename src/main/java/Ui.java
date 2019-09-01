import java.util.ArrayList;

public class Ui {
    public static void startup() {
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
    }

    public static void printDelete(ArrayList<Task> userList, int taskNum) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println("\t" + userList.get(taskNum).format());
        System.out.printf("Now you have %d tasks in the list.\n", userList.size()-1);
    }

    public static void printBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    public static void printList (boolean b, ArrayList<Task> userList) {
        if (b) {
            System.out.println("Congrats! You have no tasks! :D");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < userList.size(); i++) {
                System.out.println("\t" + (i+1) + "." + userList.get(i).format());
            }
        }
    }

    public static void printDone (ArrayList<Task> userList, int taskNum) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + userList.get(taskNum).format());
    }

    public static void printFind (ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("There are no matching tasks. :-(");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int j = 0; j < list.size(); j++) {
                System.out.println("\t" + (j+1) + "." + list.get(j).format());
            }
        }
    }

    public static void printAdd (Task t, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + t.format());
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public static void printDeadlineError() {
        System.out.println("Please input the date and time(24h format) in the format \'dd/mm/yy hh:mm\'");
    }

    public static void printEventError() {
        System.out.println("Please input the date and time(24h format) in the format \'dd/mm/yy hh:mm-hh:mm\'");
    }

    public static void printDono() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    public static void printEmptyDescription(String s) {
        System.out.printf("☹ OOPS!!! The description of a %s cannot be empty.\n", s);
    }
}
