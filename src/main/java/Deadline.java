import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline.
 */
public class Deadline extends Task {
    /**The due date of this Deadline.*/
    protected LocalDateTime dueDate; //includes date and time
    /**The time that this Deadline is due at.*/
    protected static DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");//24h clock

    /**
     * Class constructor.
     *
     * @param description of this Deadline.
     * @param dateTime the due date and time of this Deadline.
     */
    public Deadline (String description, String dateTime) {
        super(description);
        this.dueDate = LocalDateTime.parse(dateTime, formatterD);
        this.identifier = "[D]";
    }

    /**
     * Formats the details of this Deadline and returns it as a string.
     * Format: [D][✘/✓] <deadline's description> (by: <due time>).
     *
     * @return the formatted details of this Deadline.
     */
    @Override
    public String format() {
        return (super.format() + " (by: " + formatterD.format(this.dueDate) + ")");
    }
}
