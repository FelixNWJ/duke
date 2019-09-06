package duke.command;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

/**
 * Represents a ListCommand object to list out the tasks in the list.
 */
public class ListCommand extends Command{
    /**
     * Instantiates a ListCommand Object.
     */
    public ListCommand() {
        super();
    }

    /**
     * Gets the data from the TaskList, and prints out each task for the user.
     *
     * @param tasks TaskList of Duke Object
     * @param ui DukeUI of Duke Object
     * @param storage StorageData of Duke Object
     */
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        return ui.printTasks(tasks);
    }
}