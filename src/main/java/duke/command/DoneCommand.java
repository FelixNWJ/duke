package duke.command;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

import duke.exception.DukeTaskDoneException;
/**
 * Represents a DoneCommand Object which contains the task number to be deleted.
 */
public class DoneCommand extends Command {
    private int taskNumber;
    /**
     * Instantiates a DoneCommand object.
     *
     * @param details contains the task number of the task that has been completed.
     */
    public DoneCommand(String details) {
        super(details);
        this.taskNumber = Integer.valueOf(details);
    }
    /**
     * Marks the task as done from the TaskList in the Duke Object, then updates the data from the file in StorageData.
     *
     * @param tasks TaskList of Duke Object
     * @param ui DukeUI of Duke Object
     * @param storage StorageData of Duke Object
     */
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        try {
            tasks.done(this.taskNumber);
            storage.markTaskDoneInData(this.taskNumber);
            return ui.printTaskDoneMessage(tasks, this.taskNumber);
        } catch (DukeTaskDoneException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException ex) {
            return "OOPS!!! Invalid Task Number detected.";
        }
    }
}
