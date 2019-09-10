import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * It enables reading, writing and overwriting the Data.txt file in a formatted manner.
 */
public class Storage {

    /**
     *Writes, by appending, the new task added to the TaskList to the Data.txt file in a formatted manner.
     *
     * @param task new Task to be added to a list.
     * @throws IOException If a Filewriter object cannot be created and if data cannot be written to the Data.txt file created.
     */
    public static void writeToFile (Task task) throws IOException {
        FileWriter file = new FileWriter("C:\\Users\\yy2\\Documents\\CS2113Tproject\\duke\\src\\main\\java\\Data.txt", true); //curr working dir is duke
        file.write(task.identifier + ';');
        file.write(Boolean.toString(task.isDone) + ';');
        file.write(task.description);
        if (task.identifier.equals("[D]")) {
            file.write( ';' + Deadline.formatterD.format(((Deadline)task).dueDate));
            //write the date and time as a string to the file
        } else if (task.identifier.equals("[E]")) {
            file.write(';' + Event.formatterED.format(((Event)task).date) + ' ');
            file.write(Event.formatterET.format(((Event)task).start) + '-');
            file.write(Event.formatterET.format(((Event)task).end));
        }
        file.write("\n");
        file.close();
    }

    /**
     *Rewrites the data in the existing Data.txt file.
     *
     * @param list a list of tasks to be written to the Data.txt file.
     * @throws IOException If a Filewriter object cannot be created and if data cannot be written to the Data.txt file.
     */
    public static void overrideFile (ArrayList<Task> list) throws IOException {
        FileWriter file = new FileWriter("C:\\Users\\yy2\\Documents\\CS2113Tproject\\duke\\src\\main\\java\\Data.txt");
        file.write(""); // to clear the file
        file.close();
        for (Task l : list) {
            writeToFile(l);
        }
    }

    /**
     *Load saved data from the Data.txt file into an TaskList.
     *
     * @param list the list that the tasks stored in the Data.txt file is being put into.
     * @throws FileNotFoundException if the Data.txt file that is being read does not exists.
     */
    public static void readFromFile (ArrayList<Task> list) throws FileNotFoundException {
        File data = new File("C:\\Users\\yy2\\Documents\\CS2113Tproject\\duke\\src\\main\\java\\Data.txt");
        Scanner txtFile = new Scanner(data);
        while (txtFile.hasNextLine()) {
            Scanner s = new Scanner(txtFile.nextLine());
            s.useDelimiter(";");
            String id = s.next();
            String done = s.next();
            String des = s.next();
            Task t;
            if (id.equals("[D]")) {
                String due = s.next();
                t = new Deadline(des, due);
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
}
