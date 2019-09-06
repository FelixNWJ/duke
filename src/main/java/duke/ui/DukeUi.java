package duke.ui;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import duke.tasklist.TaskList;

/**
 * Represents the User Interface of the Duke App.
 * Lists the messages that are printed out when a corresponding command is executed.
 */
public class DukeUi {
    public DukeUi() {

    }

    /**
     * Returns the String representing the standard welcome message from Duke.
     *
     * @return a String containing a welcome message
     */
    public String printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Represents a String to be printed when Duke is closed by closing it or inputting "bye".
     *
     * @return a String saying bye to the user.
     */
    public String printByeMessage() {
        return "Bye! Hope to see you soon! :)";
    }

    /**
     * Represents a String to show that a Deadline Task has been added.
     *
     * @param current the Deadline Task to be added.
     * @param numberOfTasks the current number of Tasks in Duke, inclusive of this Deadline that is just added.
     * @return a String that tells the user that the Deadline has been added to Duke.
     */
    public String printAddDeadlineMessage(Deadline current, int numberOfTasks) {
        String reply = "Got it. I've added this task:";
        String deadLine = current.toString();
        String update = "Now you have " + numberOfTasks + " tasks in the list.";
        return (reply + "\n" + deadLine + "\n" + update);
    }

    /**
     * Represents a String to show that a Task has been deleted.
     *
     * @param current the Task to be deleted.
     * @param numberOfTasks the current number of Tasks in Duke, exclusive of this Task that is just deleted.
     * @return a String that tells the user that the Task has been deleted from Duke.
     */
    public String printTaskDeletedMessage(Task current, int numberOfTasks) {
        String reply = "Got it. I've removed this task:";
        String taskDeleted = current.toString();
        String update = "Now you have " + numberOfTasks + " tasks in the list.";
        return (reply + "\n" + taskDeleted + "\n" + update);
    }

    /**
     * Represents a String to show that a Task has been done.
     *
     * @param tasks is the TaskList of the Duke object.
     * @param taskNumber is the number of the Task stored in the Duke object that has been done.
     * @return a String that tells the user that the Task has been marked as done in Duke.
     */
    public String printTaskDoneMessage(TaskList tasks, int taskNumber) {
        String reply = "Nice! I've marked this task as done:";
        String taskDone = tasks.get(taskNumber - 1).toString();
        return (reply + "\n" + taskDone);
    }

    /**
     * Represents a String to show that a Event Task has been added.
     *
     * @param current the Event Task to be added.
     * @param numberOfTasks the current number of Tasks in Duke, inclusive of this Event that is just added.
     * @return a String that tells the user that the Event has been added to Duke.
     */
    public String printAddEventMessage(Event current, int numberOfTasks) {
        String reply = "Got it. I've added this task:";
        String event = current.toString();
        String update = "Now you have " + numberOfTasks + " tasks in the list.";
        return (reply + "\n" + event + "\n" + update);
    }

    /**
     * Represents a String that is the concatenation of all the Task objects stored in Duke thus far.
     *
     * @param tasks is the TaskList of the Duke object.
     * @return a string that lists out all the tasks.
     */
    public String printTasks(TaskList tasks) {
        String reply = "Here are the tasks in your list:";
        for(int i = 0; i < tasks.size(); i++) {
            Task current = (Task)tasks.get(i);
            reply += "\n" + (i + 1) + "." + current.toString();
        }
        return reply;
    }

    /**
     * Represents a String to show that a Todo Task has been added.
     *
     * @param current the Todo Task to be added.
     * @param numberOfTasks the current number of Tasks in Duke, inclusive of this Todo that is just added.
     * @return a String that tells the user that the Todo has been added to Duke.
     */
    public String printAddTodoMessage(Todo current, int numberOfTasks) {
        String reply = "Got it. I've added this task:";
        String toDo = current.toString();
        String update = "Now you have " + numberOfTasks + " tasks in the list.";
        return (reply + "\n" + toDo + "\n" + update);
    }

    /**
     * Prints out tasks that contains the keyWord specified.
     *
     * @param task TaskList that contains tasks to check from.
     * @param keyWord keyWord to check if task description contains the keyWord.
     */
    public String printKeyWordTasks(TaskList task, String keyWord) {
        String reply = "Here are the matching tasks in your list:";
        for(int i = 0; i < task.size(); i++) {
            Task current = (Task)task.get(i);
            if(current.getDescription().contains(keyWord)) {
                reply += "\n" + current.toString();
            }
        }
        return reply;
    }
}
