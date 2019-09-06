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

    public String getWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return ("Hello from\n" + logo);
    }

    public String getByeMessage() {
        return "Bye! Hope to see you soon! :)";
    }

    public String getAddDeadlineMessage(Deadline current, int numberOfTasks) {
        String reply = "Got it. I've added this task:";
        String deadLine = current.toString();
        String update = "Now you have " + numberOfTasks + " tasks in the list.";
        return (reply + "\n" + deadLine + "\n" + update);
    }

    public String getTaskDeletedMessage(Task current, int numberOfTasks) {
        String reply = "Got it. I've removed this task:";
        String taskDeleted = current.toString();
        String update = "Now you have " + numberOfTasks + " tasks in the list.";
        return (reply + "\n" + taskDeleted + "\n" + update);
    }

    public String getTaskDoneMessage(TaskList tasks, int taskNumber) {
        String reply = "Nice! I've marked this task as done:";
        String taskDone = tasks.get(taskNumber - 1).toString();
        return (reply + "\n" + taskDone);
    }

    public String getAddEventMessage(Event current, int numberOfTasks) {
        String reply = "Got it. I've added this task:";
        String event = current.toString();
        String update = "Now you have " + numberOfTasks + " tasks in the list.";
        return (reply + "\n" + event + "\n" + update);
    }

    public String getTasks(TaskList tasks) {
        String reply = "Here are the tasks in your list:";
        for(int i = 0; i < tasks.size(); i++) {
            Task current = (Task)tasks.get(i);
            reply += "\n" + (i + 1) + "." + current.toString();
        }
        return reply;
    }

    public String getAddTodoMessage(Todo current, int numberOfTasks) {
        String reply = "Got it. I've added this task:";
        String toDo = current.toString();
        String update = "Now you have " + numberOfTasks + " tasks in the list.";
        return (reply + "\n" + toDo + "\n" + update);
    }

    /**
     * Prints out tasks that contains the keyWord specified
     * @param task TaskList that contains tasks to check from.
     * @param keyWord keyWord to check if task description contains the keyWord.
     */
    public String getKeyWordTasks(TaskList task, String keyWord) {
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
