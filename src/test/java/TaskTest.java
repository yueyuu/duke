import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Task_Test")
public class TaskTest {
    @Test
    void testFormat() {
        Task t = new Task("borrow book");
        assertEquals("[T][✘] borrow book", t.format());
        t.markAsDone();
        assertEquals("[T][✓] borrow book", t.format());
    }
}