import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    protected LocalDate date;
    protected LocalTime start;
    protected LocalTime end;

    protected static DateTimeFormatter formatterED = DateTimeFormatter.ofPattern("dd/MM/yy");
    protected static DateTimeFormatter formatterET = DateTimeFormatter.ofPattern("HH:mm");

    public Event(String description, String period) {
        super(description);
        String[] dateTime = period.split(" ");
        String[] time = dateTime[1].split("-");
        this.date = LocalDate.parse(dateTime[0], formatterED);
        this.start = LocalTime.parse(time[0]);
        this.end = LocalTime.parse(time[1]);
        this.identifier = "[E]";
    }

    @Override
    public String format() {
        return (super.format() + " (at: " + formatterED.format(this.date) + " " + this.start.format(formatterET)
                + "-" + this.end.format(formatterET) + ")");
    }
}
