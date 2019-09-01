public class Task {
    //same as ToDos
    protected String description;
    protected boolean isDone;
    protected String identifier;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.identifier = "[T]";
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String format() {
        return (this.identifier + "[" + this.getStatusIcon() + "] " + this.description);
    }
}
