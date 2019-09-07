import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.Normalizer;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {
    //creating a stream to hold the output
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream mine = new PrintStream(output);
    //saving the original System.out
    private PrintStream original = System.out;

    //to prevent system exit in method being tested

    @BeforeEach
    public void setupStream() {
        //tell java to print to my own stream
        System.setOut(mine);
    }

    @AfterEach
    public void restoreStream(){
        System.out.flush();
        System.setOut(original);
    }

    @Test
    void testReadInput() {
        Parser p = new Parser();
        p.in = new Scanner("deadline do homework /by 04/05/10 00:00");
        p.readInput();
        assertTrue(p.command.equals("deadline") && p.userInput[1].equals("do homework /by 04/05/10 00:00"));
    }

    @Test
    void testParse_emptyUserInput_printString() throws IOException {
        TaskList l = new TaskList();
        Parser emp = new Parser();
        emp.command = "";
        emp.parse(l);
        assertEquals("Please enter a command! :D\r\n", output.toString());
    }

    @Test
    void testParse_doneTask_success() throws IOException {
        TaskList l = new TaskList();
        Task t = new Task("read book");
        l.userList.add(t);
        Parser emp = new Parser();
        emp.command = "done";
        emp.userInput = new String[4];
        emp.userInput[1] = Integer.toString(l.userList.size());
        emp.parse(l);
        assertEquals("Nice! I've marked this task as done:\r\n" +
                "\t[T][✓] read book\r\n", output.toString());
    }

    @Test
    void testParse_doneTask_exceptionThrown() throws IOException{
        TaskList l = new TaskList();
        Task t = new Task("read book");
        l.userList.add(t);
        Parser emp = new Parser();
        emp.command = "done";
        emp.userInput = new String[4];
        emp.userInput[1] = "k";
        emp.parse(l);
        assertEquals("The task number has to be in digits. :)\r\n", output.toString());
    }

    @Test
    void testParse_deleteTask_success() throws IOException{
        TaskList l = new TaskList();
        Task t = new Task("read book");
        l.userList.add(t);
        Parser del = new Parser();
        del.command = "delete";
        del.userInput = new String[4];
        del.userInput[1] = Integer.toString(l.userList.size());
        del.parse(l);
        String s = "Noted. I've removed this task:\r\n" + " [T][✘] read book\r\n" +
                String.format("Now you have %d tasks in the list.\r\n", l.userList.size());
        assertEquals(s, output.toString());
    }

    @Test
    void testParse_deleteTask_exceptionThrown() throws IOException{
        TaskList l = new TaskList();
        Parser del = new Parser();
        del.command = "done";
        del.userInput = new String[4];
        del.userInput[1] = "k";
        del.parse(l);
        assertEquals("The task number has to be in digits. :)\r\n", output.toString());
    }

    @Test
    void testParse_addDeadline_success() throws IOException {
        TaskList l = new TaskList();
        Task t = new Deadline("return book", "03/04/12 23:00");
        l.userList.add(t);
        Parser emp = new Parser();
        emp.command = "deadline";
        emp.userInput = new String[4];
        emp.userInput[1] = "return book /by 03/04/12 23:00";
        emp.parse(l);
        assertEquals("Got it. I've added this task: \r\n" + " [D][✘] return book (by: 03/04/12 23:00)\r\n" +
                String.format("Now you have %d tasks in the list.\r\n", l.userList.size()), output.toString());
    }
}