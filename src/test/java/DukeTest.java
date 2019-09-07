import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Empty_Description_Test")

class DukeTest {
    //creating a stream to hold the output
    private ByteArrayOutputStream output = new ByteArrayOutputStream();
    private PrintStream mine = new PrintStream(output);
    //saving the original System.out
    private PrintStream original = System.out;

    private TaskList list = new TaskList();
    private Parser parser = new Parser();

    DukeTest() throws IOException {
    }

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

    //------------------------------------------TESTS-----------------------------------------------

    @Test
    void testExecuteDuke_emptyTodo_throwsException() throws IOException {
        try {
            parser.in = new Scanner("todo");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyTodoWithWhiteSpace_throwsException() throws IOException {
        try {
            parser.in = new Scanner("  todo   ");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a todo cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyDeadline_givesException() throws IOException {
        try {
            parser.in = new Scanner("deadline");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a deadline cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyDeadlineWithWhiteSpace_givesException() throws IOException {
        try {
            parser.in = new Scanner("       deadline  ");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a deadline cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyEvent_givesException() throws IOException {
        try {
            parser.in = new Scanner("event");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a event cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyEventWithWhiteSpace_givesException() throws IOException {
        try {
            parser.in = new Scanner("       event  ");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a event cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyDone_givesException() throws IOException {
        try {
            parser.in = new Scanner("done");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a done cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyDoneWithWhiteSpace_givesException() throws IOException {
        try {
            parser.in = new Scanner("       done  ");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a done cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyDelete_givesException() throws IOException {
        try {
            parser.in = new Scanner("delete");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a delete cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyDeleteWithWhiteSpace_givesException() throws IOException {
        try {
            parser.in = new Scanner("       delete  ");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a delete cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyFine_givesException() throws IOException {
        try {
            parser.in = new Scanner("find");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a find cannot be empty.\n", output.toString());
        }
    }

    @Test
    void testExecuteDuke_emptyFindWithWhiteSpace_givesException() throws IOException {
        try {
            parser.in = new Scanner("       find  ");
            Duke.executeDuke(list, parser);
        } catch (NoSuchElementException e) {
            assertEquals("☹ OOPS!!! The description of a find cannot be empty.\n", output.toString());
        }
    }

}