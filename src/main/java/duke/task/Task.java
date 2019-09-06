package duke.task;

/**
 * Represents a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a Task Object.
     * Status of the Task is set to not Done by default.
     *
     * @param description contains details of the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the boolean representing whether the task is done or not, done being true, not done being false.
     *
     * @return a boolean representing whether the task is done or not.
     */
    public boolean getStatus() {
        return this.isDone;
    }

    /**
     * Returns the icon of the status of the Task.
     * A Tick represents done, a Cross represents not done.
     *
     * @return the status of the Task represented as an icon.
     */
    public String getStatusIcon() {
        if(isDone) {
            return "\u2713";
        } else {
            return "\u2718";
        }
    }

    /**
     * Gets the description of a Task object in general.
     *
     * @return the description of the Task object.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * isDone boolean of the Task Object is changed.
     */
    public void markAsDone() {
        this.isDone = !this.isDone;
    }

    /**
     * Returns a string of a general Task object with a status icon that shows if its done or not.
     *
     * @return a string representing a general Task object.
     */
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}
