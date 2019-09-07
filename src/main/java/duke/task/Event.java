package duke.task;

/**
 * Represents a Event that contains the description of the Event and when it occurs.
 */
public class Event extends Task {
    protected String period;

    /**
     * Creates the Event Task Object.
     *
     * @param description contains the description of the Event
     * @param period contains the information of when the Event occurs.
     */
    public Event(String description, String period) {
        super(description);
        this.period = period;
    }

    /**
     * Returns the time period when the event occurs at.
     *
     * @return the time when the event occurs.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Creates a string to be stored in a File so that loading from the file will return a correct Event object.
     * Format is a Letter E for event, its description, and by when it occurs at
     *
     * @return a string representing a Event object.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + period + ")";
    }
}
