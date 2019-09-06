package duke.command;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

import duke.task.Event;

import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeEmptyDescriptionException;
/**
 * Represents a Event which contains a description of the Event and when it occurs at.
 */
public class EventCommand extends Command{
    private String description;
    private String duringWhen;
    /**
     * Instantiates a Event Object
     *
     * @param details contains both the description of the Event and when the Event occurs.
     * @throws DukeEmptyDescriptionException when details is an empty string.
     * @throws DukeMissingDescriptionException when details contains missing information or is in a wrong format.
     */
    public EventCommand(String details) throws DukeMissingDescriptionException, DukeEmptyDescriptionException {
        super(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("event");
        } else {
            String[] info = details.split("/at");
            if (info.length == 1) {
                throw new DukeMissingDescriptionException("event");
            } else {
                this.description = info[0].trim();
                assert !this.description.isEmpty() : "Event description is empty";
                if(info[1].split("/").length == 3) {
                    String[] date = info[1].trim().split(" ");
                    assert date.length == 2 : "Date is not split into Day, Month, Year and Time";
                    String dateWord = Command.dateToWords(date[0]);
                    String time = Command.timeConverter(date[1]);
                    this.duringWhen = dateWord + ", " + time;
                } else {
                    this.duringWhen = info[1].trim();
                    assert !this.duringWhen.isEmpty() : "Description of Event is empty";
                }
            }
        }
    }

    /**
     * Adds the Event Object to the TaskList of the Duke Object.
     * Stores the Event in the StorageData and prints out a message to confirm that it has been added.
     *
     * @param tasks TaskList of Duke Object
     * @param ui DukeUI of Duke Object
     * @param storage StorageData of Duke Object
     */
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        Event current = new Event(this.description, this.duringWhen);
        assert current != null : "Event object is not created";
        tasks.add(current);
        storage.addEventData(this.description, this.duringWhen);
        return ui.printAddEventMessage(current, tasks.size());
    }
}
