import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws IOException {
        Ui.startup();
        TaskList list = new TaskList();
        Parser parser = new Parser();


        while (true) {
            String[] userInput = parser.readInput();

            if (userInput[0].equals("bye")) { //BYE
                Ui.printBye();
                return;
            } else if (userInput[0].equals("list")) { //LIST
                Ui.printList(list.userList.isEmpty(), list.userList);
            } else  {
                try {
                    if (userInput[0].equals("done")) { //DONE
                        try {
                            int taskNum = Integer.parseInt(userInput[1]) - 1; //if fail gives exception n
                            list.doneTask(taskNum);
                            Ui.printDone(list.userList, taskNum);
                        } catch (NumberFormatException n) {
                            System.out.println("The task number has to be in digits. :)");
                        }
                    } else if (userInput[0].equals("delete")) { //REMOVING TASK
                        try {
                            int taskNum = Integer.parseInt(userInput[1])-1;
                            Ui.printDelete(list.userList, taskNum);
                            list.deleteTask(taskNum);
                        } catch (NumberFormatException f) {
                            System.out.println("The task number has to be in digits. :)");
                        }
                    } else if (userInput[0].equals("find")) { //FINDING TASK: search for keyword
                        String keyword = userInput[1];
                        ArrayList<Task> match = new ArrayList<Task>(); //to store task that contains the keyword
                        list.findTask(match, keyword);
                        Ui.printFind(match);
                    } else if (userInput[0].equals("todo")) {
                        Task t = new Task(userInput[1]);
                        list.addTask(t);
                    } else if (userInput[0].equals("deadline")) {
                        String[] deadline = userInput[1].split(" /by ", 2);
                        try {
                            Task t = new Deadline(deadline[0], deadline[1]);
                            list.addTask(t);
                        }  catch (DateTimeParseException | ArrayIndexOutOfBoundsException e) {
                            Ui.printDeadlineError();
                        }
                    } else if (userInput[0].equals("event")) {
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
                } catch (ArrayIndexOutOfBoundsException e) {
                    Ui.printEmptyDescription(userInput[0]);
                }
            }
        }
    }
}
