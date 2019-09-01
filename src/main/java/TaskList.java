import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> userList = new ArrayList<Task>(); //array to store userinputs: ArrayList is similar to vectors in c++

    public TaskList() throws IOException {
        try {
            Storage.readFromFile(userList);
        } catch (FileNotFoundException f) {
            System.out.println("Could not find existing task list.");
            Files.createFile(Paths.get("src/main/java/Data.txt")); //create an empty new file
            Storage.readFromFile(userList);
            System.out.println("Empty task list created.");
        }
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
