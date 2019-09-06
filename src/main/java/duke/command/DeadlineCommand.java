package duke.command;

import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingDescriptionException;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

import duke.task.Deadline;

/**
 * Represents a Deadline which contains a description of the deadline and when the deadline is due.
 */
public class DeadlineCommand extends Command{
    private String description;
    private String byWhen;

    /**
     * Instantiates a Deadline Object
     *
     * @param details contains both the description of the deadline and when the deadline is due.
     * @throws DukeEmptyDescriptionException when details is an empty string.
     * @throws DukeMissingDescriptionException when details contains missing information or is in a wrong format.
     */
    public DeadlineCommand(String details) throws DukeEmptyDescriptionException, DukeMissingDescriptionException {
        super(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("deadline");
        } else {
            String[] info = details.split("/by");
            if (info.length == 1) {
                throw new DukeMissingDescriptionException("deadline");
            } else {
                this.description = info[0].trim();
                assert !this.description.isEmpty() : "Description of Deadline is empty";
                if(info[1].split("/").length == 3) {
                      String[] date = info[1].trim().split(" ");
                      assert date.length == 2 : "Date is not split into Day, Month, Year and Time";
                      String dateWord = Command.dateToWords(date[0]);
                      String time = Command.timeConverter(date[1]);
                      this.byWhen = dateWord + ", " + time;
                } else {
                    this.byWhen = info[1].trim();
                    assert !this.byWhen.isEmpty() : "No time to indicate when deadline is due";
                }
            }
        }
    }

    /**
     * Adds the Deadline Object to the TaskList of the Duke Object.
     * Stores the Deadline in the StorageData and prints out a message to confirm that it has been added.
     *
     * @param tasks TaskList of Duke Object
     * @param ui DukeUI of Duke Object
     * @param storage StorageData of Duke Object
     */
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        String details = this.getDetails();
        Deadline current = new Deadline(this.description, this.byWhen);
        assert current != null : "Deadline object not created";
        tasks.add(current);
        storage.addDeadlineData(this.description, this.byWhen);
        return ui.printAddDeadlineMessage(current, tasks.size());
    }
}
