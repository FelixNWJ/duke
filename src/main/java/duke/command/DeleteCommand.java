package duke.command;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;
/**
 * Represents a DeleteCommand Object which contains the task number to be deleted.
 */
public class DeleteCommand extends Command{
    private int taskNumber;

    /**
     * Instantiates a DeleteCommand object.
     *
     * @param details contains the task number of the task to be deleted.
     */
    public DeleteCommand(String details) {
        super(details);
        this.taskNumber = Integer.valueOf(details);
    }

    /**
     * Deletes the task number from the TaskList in the Duke Object, then erases the data from the file in StorageData.
     *
     * @param tasks TaskList of Duke Object
     * @param ui DukeUI of Duke Object
     * @param storage StorageData of Duke Object
     */
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        try {
            storage.deleteTaskInData(this.taskNumber);
            return tasks.delete(this.taskNumber, ui);
        } catch (IndexOutOfBoundsException e) {
            return e.getMessage();
        }
    }
}

