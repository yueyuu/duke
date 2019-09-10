/**
 * Represents a task/to do.
 */
public class Task {
    //same as ToDos
    /**The description of this Task.*/
    protected String description;
    /**Specifies if this Task is done or not.*/
    protected boolean isDone;
    /**Specifies the type of task: To Do; Event; Deadline.*/
    protected String identifier;

    /**
     * Class constructor.
     *
     * @param description of this Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.identifier = "[T]";
    }

    /**
     * Returns the status symbol for this Task depending on the value of isDone.
     *
     * @return the status symbol for this Task.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a Task as done by setting isDone of this Task to be true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Formats the details of this Task and returns it as a string.
     * Format: [T][✘/✓] <task's description>.
     *
     * @return the formatted details of this Task.
     */
    public String format() {
        return (this.identifier + "[" + this.getStatusIcon() + "] " + this.description);
    }
}
