package duke.task;

/**
 * Represents a Deadline that contains the description of the deadline and when it is due by.
 */
public class Deadline extends Task {
    protected String time;

    /**
     * Creates the Deadline Task Object.
     * @param description contains the description of the Deadline
     * @param time contains the information of when the deadline is due.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Gets the time when the deadline ends.
     *
     * @return the time of the deadline.
     */
    public String getTime() {
        return time;
    }

    /**
     * Creates a string to be stored in a File so that loading from the file will return a correct Deadline object.
     * Format is a Letter D for deadline, its description, and by when it has to be done
     *
     * @return a string representing a Deadline object.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
