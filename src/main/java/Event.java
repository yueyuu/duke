import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event.
 */
public class Event extends Task{
    /**The date that this Event is occurring on.*/
    protected LocalDate date;
    /**The starting time of this Event.*/
    protected LocalTime start;
    /**The ending time of this Event.*/
    protected LocalTime end;

    /**
     * The format that the date of the Event needs to be in.
     */
    protected static DateTimeFormatter formatterED = DateTimeFormatter.ofPattern("dd/MM/yy");
    /**
     * The format that the start/end time of this Event needs to be in.
     */
    protected static DateTimeFormatter formatterET = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Class constructor.
     *
     * @param description this Event's description.
     * @param period the date and timing of this Event.
     */
    public Event(String description, String period) {
        super(description);
        String[] dateTime = period.split(" ");
        String[] time = dateTime[1].split("-");
        this.date = LocalDate.parse(dateTime[0], formatterED);
        this.start = LocalTime.parse(time[0]);
        this.end = LocalTime.parse(time[1]);
        this.identifier = "[E]";
    }

    /**
     * Returns the details of this Event as a formatted string.
     * The format is '[E][✘/✓] <event's description> (at: <date of event> <start time-end time>)'.
     *
     * @return the details of this Event as a string.
     */
    @Override
    public String format() {
        return (super.format() + " (at: " + formatterED.format(this.date) + " " + this.start.format(formatterET)
                + "-" + this.end.format(formatterET) + ")");
    }
}
