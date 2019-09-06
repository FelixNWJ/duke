package duke.exception;

/**
 * Class representing an exception whereby wrong time format is given from user.
 */
public class DukeWrongTimeFormatException extends DukeException {
    /**
     * Creates a DukeWrongTimeFormatException object.
     */
    public DukeWrongTimeFormatException() {
        super("Wrong Input Format detected. " +
                "Please input date and time in a DD/MM/YYYY time format. E.G 14/05/2019 1800");
    }
}
