import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void testFormat() {
        Task e = new Event("project meeting",  "15/09/89 22:30-04:03");
        assertEquals("[E][✘] project meeting (at: 15/09/89 22:30-04:03)", e.format());
        e.markAsDone();
        assertEquals("[E][✓] project meeting (at: 15/09/89 22:30-04:03)", e.format());
    }
}