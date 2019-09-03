import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> userList = new ArrayList<Task>(); //array to store userinputs: ArrayList is similar to vectors in c++

    public TaskList() throws IOException {
            Storage.readFromFile(userList);
    }

    public void deleteTask(int taskNum) throws IOException {
        userList.remove(taskNum);
        Storage.overrideFile(userList);
    }

    public void doneTask(int taskNum) throws IOException {
        userList.get(taskNum).markAsDone();
        Storage.overrideFile(userList);
    }

    public void findTask(ArrayList<Task> match, String keyword) {
        for (Task task : userList) {
            if (task.description.contains(keyword)) {
                match.add(task);
            }
        }
    }

    public void addTask (Task t) throws IOException {
        userList.add(t); //add input into the arraylist
        Storage.writeToFile(t);
        Ui.printAdd(t, userList.size());
    }
}
