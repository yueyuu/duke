import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime dueDate; //includes date and time
    protected static DateTimeFormatter formatterD = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm");//24h clock

    public Deadline (String description, String dateTime) {
        super(description);
        this.dueDate = LocalDateTime.parse(dateTime, formatterD);
        this.identifier = "[D]";
    }

    @Override
    public String format() {
        return (super.format() + " (by: " + formatterD.format(this.dueDate) + ")");
    }
}
