package duke.command;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

import duke.task.Todo;

import duke.exception.DukeEmptyDescriptionException;
/**
 * Represents a TodoCommand Object which contains a description of the task to be done.
 */
public class TodoCommand extends Command{
    /**
     * Instantiates a TodoCommand Object
     * @param details contains description of the task to be done.
     * @throws DukeEmptyDescriptionException when details is an empty string.
     */
    public TodoCommand(String details) throws DukeEmptyDescriptionException{
        super(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("todo");
        }
    }
    /**
     * Adds the Todo Object to the TaskList of the Duke Object.
     * Stores the Todo in the StorageData and prints out a message to confirm that it has been added.
     * @param tasks TaskList of Duke Object
     * @param ui DukeUI of Duke Object
     * @param storage StorageData of Duke Object
     */
    @Override
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        String details = this.getDetails();
        Todo current = new Todo(details);
        tasks.add(current);
        storage.addData(current);
        return ui.getAddTodoMessage(current, tasks.size());

    }
}
