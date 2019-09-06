package duke.tasklist;

import duke.task.Task;

import duke.ui.DukeUi;

import duke.exception.DukeTaskDoneException;

import java.util.ArrayList;

/**
 * Represents a class that wraps around an ArrayList that stores the Tasks of the Duke App.
 */
public class TaskList {
    ArrayList<Task> tasks;

    /**
     * Creates a TaskList when there is no saved file and initialises the ArrayList to store 100 Tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>(100);
    }

    /**
     * Creates a TaskList when there is a saved file present
     * @param current is the ArrayList that is translated from the File stored in the StorageData of the Duke App.
     */
    public TaskList(ArrayList<Task> current) {
        this.tasks = current;
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public void add(Task current) {
        tasks.add(current);
    }

    public void delete(int taskNumber) throws IndexOutOfBoundsException{
        tasks.remove(taskNumber - 1);
    }

    public void done (int taskNumber) throws DukeTaskDoneException, IndexOutOfBoundsException{
        Task current = tasks.get(taskNumber - 1);
        if(current.getStatus()) {
            throw new DukeTaskDoneException();
        } else {
            current.markAsDone();
        }
    }
}
