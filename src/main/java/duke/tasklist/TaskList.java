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
        assert this.tasks != null : "ArrayList not created for TaskList";
    }

    /**
     * Creates a TaskList when there is a saved file present.
     *
     * @param current is the ArrayList that is translated from the File stored in the StorageData of the Duke App.
     */
    public TaskList(ArrayList<Task> current) {
        this.tasks = current;
    }

    /**
     * Returns the number of tasks stored in the ArrayList in the TaskList.
     *
     * @return the number of tasks in the TaskList object.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Gets the Task object at that particular index.
     *
     * @param taskNumber is the number of the task that we want to obtain from the ArrayList.
     * @return the Task object in the TaskList that is stored at the index represented by taskNumber.
     */
    public Task get(int taskNumber) {
        return tasks.get(taskNumber);
    }

    /**
     * Adds a particular Task object to the ArrayList of the TaskList object.
     *
     * @param current the Task object that we want to add.
     */
    public void add(Task current) {
        tasks.add(current);
    }

    /**
     * Deletes a Task from the ArrayList stored in the TaskList object.
     *
     * @param taskNumber is the number of the Task that we want to delete from the TaskList.
     * @param ui is the DukeUi stored in the Duke object that also stores this TaskList
     * @return the String that represents the deleted task.
     * @throws IndexOutOfBoundsException when a taskNumber greater than the TaskList size or a negative number is given.
     */
    public String delete(int taskNumber, DukeUi ui) throws IndexOutOfBoundsException{
        Task current = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        return ui.printTaskDeletedMessage(current, tasks.size());
    }

    /**
     * Sets a Task from the ArrayList stored in the TaskList object to done status.
     *
     * @param taskNumber is the number of the Task that we want to set as done from the TaskList.
     * @return the String that represents the task that is done along with its modified status.
     * @throws IndexOutOfBoundsException when a taskNumber greater than the TaskList size or a negative number is given.
     * @throws DukeTaskDoneException when a taskNumber that corresponds to a task that is already done is given.
     */
    public void done (int taskNumber) throws DukeTaskDoneException, IndexOutOfBoundsException{
        Task current = tasks.get(taskNumber - 1);
        if(current.getStatus()) {
            throw new DukeTaskDoneException();
        } else {
            current.markAsDone();
            assert current.getStatus() == true : "Task not marked as done when method indicates so.";
        }
    }
}
