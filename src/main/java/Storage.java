import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
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

    public static void overrideFile (ArrayList<Task> list) throws IOException {
        FileWriter file = new FileWriter("C:\\Users\\yy2\\Documents\\CS2113Tproject\\duke\\src\\main\\java\\Data.txt");
        file.write(""); // to clear the file
        file.close();
        for (Task l : list) {
            writeToFile(l);
        }
    }

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
                //if (done.equals("true")) {
                //    t.markAsDone();
                //}
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
