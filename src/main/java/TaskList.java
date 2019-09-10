import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks that needs to be done.
 */
public class TaskList {
    /**
     * A list storing the user's task.
     */
    protected ArrayList<Task> userList = new ArrayList<Task>(); //ArrayList is similar to vectors in c++

    /**
     * Class constructor.
     * Loads saved data from the Data.txt file into this TaskList.
     *
     * @throws IOException if the Data.txt file cannot be read.
     */
    public TaskList() throws IOException {
            Storage.readFromFile(userList);
    }

    /**
     * Deletes the specified task from this TaskList and updates the data in Data.txt.
     *
     * @param taskNum the index of the specified task.
     * @throws IOException if data cannot be written to Data.txt or if Data.txt does not exist.
     */
    public void deleteTask(int taskNum) throws IOException {
        userList.remove(taskNum);
        Storage.overrideFile(userList);
    }

    /**
     * Marks the specified task in this Tasklist as completed and updates Data.txt.
     *
     * @param taskNum the index of the specified task.
     * @throws IOException if data cannot be written to Data.txt or if Data.txt does not exist.
     */
    public void doneTask(int taskNum) throws IOException {
        userList.get(taskNum).markAsDone();
        Storage.overrideFile(userList);
    }

    /**
     * Add tasks that matches the user's keyword into a list.
     *
     * @param match the list that will contain the matching tasks.
     * @param keyword the phrase that the user wants to search for.
     */
    public void findTask(ArrayList<Task> match, String keyword) {
        for (Task task : userList) {
            if (task.description.contains(keyword)) {
                match.add(task);
            }
        }
    }

    /**
     * Adds the specified task to this TaskList and updates Data.txt.
     *
     * @param t the task to be added into this TaskList.
     * @throws IOException if data cannot be written to Data.txt or if Data.txt does not exist.
     */
    public void addTask (Task t) throws IOException {
        userList.add(t); //add input into the arraylist
        Storage.writeToFile(t);
        Ui.printAdd(t, userList.size());
    }
}
