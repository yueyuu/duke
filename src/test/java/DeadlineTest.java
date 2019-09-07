import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void format() {
        Task d = new Deadline("return book", "04/03/02 02:00");
        assertEquals("[D][✘] return book (by: 04/03/02 02:00)", d.format());
        d.markAsDone();
        assertEquals("[D][✓] return book (by: 04/03/02 02:00)", d.format());
    }
}