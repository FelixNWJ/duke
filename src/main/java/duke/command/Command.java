package duke.command;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingDescriptionException;

import duke.exception.DukeWrongTimeFormatException;
import duke.tasklist.TaskList;

import duke.ui.DukeUi;

import duke.storagedata.StorageData;

/**
 * Represents an abstract generic Command class.
 * Contains attribute details that represents the details of the Command.
 */
public abstract class Command {
    private String details;

    /**
     * Instantiates a Command Object which has no details.
     *
     * @return a Command Object
     */
    public Command() {
        this.details = "";
    }

    /**
     * Instantiates a TodoCommand Object
     *
     * @param details the details of the task to be stored in the TodoCommand Object
     * @return a TodoCommand Object
     * @throws DukeEmptyDescriptionException when details is an empty string.
     */
    public static Command addTodoCommand(String details) throws DukeEmptyDescriptionException {
        return new TodoCommand(details);
    }

    /**
     * Instantiates a DeadlineCommand Object
     *
     * @param details the details of the task to be stored in the DeadlineCommand Object
     * @return a DeadlineCommand Object
     * @throws DukeEmptyDescriptionException   when details is an empty string.
     * @throws DukeMissingDescriptionException when details has missing information or details are in an incorrect format.
     */
    public static Command addDeadlineCommand(String details) throws DukeMissingDescriptionException,
            DukeEmptyDescriptionException, DukeWrongTimeFormatException {
        return new DeadlineCommand(details);
    }

    /**
     * Instantiates a EventCommand Object
     *
     * @param details the details of the task to be stored in the EventCommand Object.
     * @return a EventCommand Object.
     * @throws DukeMissingDescriptionException when has missing information or details are in an incorrect format.
     * @throws DukeEmptyDescriptionException   when details is an empty string
     */
    public static Command addEventCommand(String details) throws
            DukeMissingDescriptionException, DukeEmptyDescriptionException, DukeWrongTimeFormatException {
        return new EventCommand(details);
    }

    /**
     * Instantiates a ListCommand Object
     *
     * @return a TodoCommand Object
     */
    public static Command addListCommand() {
        return new ListCommand();
    }

    /**
     * Instantiates a DoneCommand Object
     *
     * @param details the task number of the completed task to be stored in the DoneCommand Object
     * @return a DoneCommand Object
     * @throws DukeEmptyDescriptionException when details is an empty string.
     */
    public static Command addDoneCommand(String details) throws DukeEmptyDescriptionException {
        return new DoneCommand(details);
    }

    /**
     * Instantiates a DeleteCommand Object
     *
     * @param details the task number of the task to be deleted and stored in the DeleteCommand Object
     * @return a DeleteCommand Object
     * @throws DukeEmptyDescriptionException when details is an empty string.
     */
    public static Command addDeleteCommand(String details) throws DukeEmptyDescriptionException {
        return new DeleteCommand(details);
    }

    /**
     * Instantiates a ByeCommand Object
     *
     * @return a ByeCommand Object
     */
    public static Command addByeCommand() {
        return new ByeCommand();
    }

    /**
     * Instantiates a FindCommand Object
     *
     * @param details the keyWord to use in finding tasks
     * @return a FindCommand Object
     */
    public static Command addFindCommand(String details) {
        return new FindCommand(details);
    }

    /**
     * Instantiates a Command Object
     *
     * @param details is the details to be stored in the Command Object.
     * @return a Command Object
     */
    public Command(String details) {
        this.details = details;
    }

    /**
     * Returns the description of the Command.
     *
     * @return the details of the Command.
     */
    public String getDetails() {
        return this.details;
    }

    /**
     * Carries out the command based on what type of Command Object it is.
     *
     * @param tasks   TaskList of Duke Object
     * @param ui      DukeUI of Duke Object
     * @param storage StorageData of Duke Object
     */
    public abstract String execute(TaskList tasks, DukeUi ui, StorageData storage);

}
