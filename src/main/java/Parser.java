import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Process and store the user's input.
 */
public class Parser {
    //deals with making sense of the user command
    /**Stores the user input.*/
    protected String[] userInput;
    /**Stores the command word entered by the user.*/
    protected String command;
    /**The Scanner object that contains the user's input.*/
    protected Scanner in;

    /**
     * Class constructor.
     * Sets up the Scanner object to prepare to read input from the user.
     */
    public Parser() {
        this.in = new Scanner(System.in); //setting up to read in input from user
    }

    /**
     * Makes sense of the user's input.
     * Splits up the user's input into command word and detials of the command.
     */
    public void readInput() {
        String input = in.nextLine().trim(); // read in input (the whole line)
        userInput =  input.split(" ", 2); //extract out the command word
        command = userInput[0];
    }

    /**
     * Uses the command keyword to decide the methods that need to be called to carry out the action the user wants.
     *
     * @param list the TaskList containing the user's tasks.
     * @throws IOException if the addTask methods fails.
     */
    public void parse(TaskList list) throws IOException {
        if (command.equals("")) { //user enters nothing
            Ui.printEnterSomething();
        } else if (command.equals("bye")) { //BYE
            Ui.printBye();
            System.exit(0);
        } else if (command.equals("list")) { //LIST
            Ui.printList(list.userList.isEmpty(), list.userList);
        } else if (command.equals("done")) { //DONE
            try {
                int taskNum = Integer.parseInt(userInput[1]) - 1; //if fail gives exception n
                list.doneTask(taskNum);
                Ui.printDone(list.userList, taskNum);
            } catch (NumberFormatException | IOException n) {
                System.out.println("The task number has to be in digits. :)");
            }
        } else if (command.equals("delete")) { //REMOVING TASK
            try {
                int taskNum = Integer.parseInt(userInput[1])-1;
                Ui.printDelete(list.userList, taskNum);
                list.deleteTask(taskNum);
            } catch (NumberFormatException | IOException f) {
                System.out.println("The task number has to be in digits. :)");
            }
        } else if (command.equals("find")) { //FINDING TASK: search for keyword
            String keyword = userInput[1];
            ArrayList<Task> match = new ArrayList<Task>(); //to store task that contains the keyword
            list.findTask(match, keyword);
            Ui.printFind(match);
        } else if (command.equals("todo")) {
            Task t = new Task(userInput[1]);
            list.addTask(t);
        } else if (command.equals("deadline")) {
            String[] deadline = userInput[1].split(" /by ", 2);
            try {
                Task t = new Deadline(deadline[0], deadline[1]);
                list.addTask(t);
            }  catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                Ui.printDeadlineError();
            }
        } else if (command.equals("event")) {
            String[] event = userInput[1].split(" /at ", 2);
            try {
                Task t = new Event(event[0], event[1]);
                list.addTask(t);
            } catch (DateTimeParseException | ArrayIndexOutOfBoundsException ex) {
                Ui.printEventError();
            }
        } else {
            Ui.printDono();
        }
    }
}
