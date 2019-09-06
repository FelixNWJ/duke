package duke.task;

/**
 * Represents a Todo Task that is to be done.
 */
public class Todo extends Task{

    /**
     * Creates a Todo Object
     *
     * @param description contains details of the task to be done.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a string to be stored in a File so that loading from the file will return a correct Todo object.
     * Format is a Letter T for Todo and its description
     *
     * @return a string representing a Todo object.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
