package duke.command;

import duke.exception.DukeWrongTimeFormatException;
import duke.time.TimeConverter;
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
    public EventCommand(String details) throws DukeMissingDescriptionException, DukeEmptyDescriptionException,
            DukeWrongTimeFormatException {
        super(details);
        if(details.isEmpty()) {
            throw new DukeEmptyDescriptionException("event");
        } else {
            String[] info = details.split("/at");
            if (info.length == 1) {
                throw new DukeMissingDescriptionException("event");
            } else {
                this.description = info[0].trim();
                this.duringWhen = TimeConverter.convert(info[1].trim());
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
    @Override
    public String execute(TaskList tasks, DukeUi ui, StorageData storage) {
        Event current = new Event(this.description, this.duringWhen);
        assert current != null : "Event object is not created";
        tasks.add(current);
        storage.addData(current);
        return ui.getAddEventMessage(current, tasks.size());
    }
}
