package duke.command;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

/**
 * Represents a FindCommand Object.
 */
public class FindCommand extends Command {
    private String keyWord;

    /**
     * Creates a Find Command Object
     * @param details contains the keyword used to find the specific tasks required.
     */
    public FindCommand(String details) {
        super(details);
        this.keyWord = details;
    }

    /**
     * Finds the Tasks that contains the keyword in the TaskList and prints out using the UI.
     * @param tasks TaskList of Duke App.
     * @param ui UI of Duke App.
     * @param storage StorageData of Duke App.
     */
    @Override
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        return ui.getKeyWordTasks(tasks, this.keyWord);
    }
}
