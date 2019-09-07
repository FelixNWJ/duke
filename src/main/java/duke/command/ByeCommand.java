package duke.command;

import duke.tasklist.TaskList;

import duke.ui.DukeUi;

import duke.storagedata.StorageData;

/**
 * Represents a Command object where the user input is "bye.
 */
public class ByeCommand extends Command {
    public ByeCommand() {
        super();
    }

    /**
     * Prints message that is for shutting down of the Duke app.
     * Terminates program afterwards.
     *
     * @param tasks TaskList of Duke Object.
     * @param ui DukeUI of Duke Object.
     * @param storage StorageData of Duke Object.
     */
    @Override
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        return ui.getByeMessage();
        //System.exit(0);
    }
}
