import java.util.ArrayList;

/**
 * Prints messages to the user.
 */
public class Ui {
    /**
     * Prints the initial message when Duke starts up.
     */
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

    /**
     * Tells the user that the task specified has been successfully deleted.
     *
     * @param userList the list containing the user's tasks.
     * @param taskNum the index of the task that is being deleted.
     */
    public static void printDelete(ArrayList<Task> userList, int taskNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + userList.get(taskNum).format());
        System.out.println(String.format("Now you have %d tasks in the list.", userList.size()-1));
    }

    /**
     * Says bye to the user.
     */
    public static void printBye() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Prints out the user's list of tasks if the list of not empty.
     * Otherwise, it tells the user that the list is empty.
     *
     * @param b if true, the list is empty. else if false, the list has tasks.
     * @param userList the user's list of tasks.
     */
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

    /**
     * Tells the user that the tasks specified has been successfully marked as done.
     *
     * @param userList the user's list of tasks.
     * @param taskNum the index of the tasks to be marked as done.
     */
    public static void printDone (ArrayList<Task> userList, int taskNum) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("\t" + userList.get(taskNum).format());
    }

    /**
     * Shows the user the tasks that match their search if there are matches.
     * Otherwise, it tells the user that there are no matching tasks.
     *
     * @param list the user's list of tasks.
     */
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

    /**
     * Tells the user that the specified task has been successfully added to their list.
     *
     * @param t the task to be added to the list.
     * @param size the number of tasks in the user's list after adding the new task.
     */
    public static void printAdd (Task t, int size) {
        System.out.println("Got it. I've added this task: ");
        System.out.println(" " + t.format());
        System.out.println(String.format("Now you have %d tasks in the list.", size));
    }

    /**
     * Tells the user the correct format to input the date and time for deadlines.
     */
    public static void printDeadlineError() {
        System.out.println("Please input the date and time(24h format) in the format \'dd/mm/yy hh:mm\'");
    }

    /**
     * Tells the user the correct format to input the date and time for events.
     */
    public static void printEventError() {
        System.out.println("Please input the date and time(24h format) in the format \'dd/mm/yy hh:mm-hh:mm\'");
    }

    /**
     * Tells the user that the command word they have entered is invalid.
     */
    public static void printDono() {
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    /**
     * Tells the user that the command that they have entered cannot have an empty description.
     * This is onyl printed if the command is valid.
     *
     * @param s the command that the user typed in.
     */
    public static void printEmptyDescription(String s) {
        System.out.printf("☹ OOPS!!! The description of a %s cannot be empty.\n", s);
    }

    /**
     * Asks the user to enter some words.
     */
    public static void printEnterSomething() {
        System.out.println("Please enter a command! :D");
    }
}
