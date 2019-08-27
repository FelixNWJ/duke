package duke.parser;

import duke.command.Command;

import duke.exception.DukeWrongInputException;
import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeEmptyDescriptionException;

import java.util.Scanner;

public class Parser {
    public static Command parseCommand(String fullLine) throws DukeWrongInputException,
            DukeEmptyDescriptionException, DukeMissingDescriptionException {
        Scanner commandScanner = new Scanner(fullLine);
        String typeOfCommand = commandScanner.next().trim().toLowerCase();
        String detailsOfCommand = fullLine.substring(typeOfCommand.length()).trim();

        switch(typeOfCommand) {
        case "todo":
            return Command.addToDoCommand(detailsOfCommand);

        case "deadline":
            return Command.addDeadlineCommand(detailsOfCommand);

        case "event":
            return Command.addEventCommand(detailsOfCommand);

        case "list":
            return Command.addListCommand();

        case "done":
            return Command.addDoneCommand(detailsOfCommand);

        case "delete":
            return Command.addDeleteCommand(detailsOfCommand);

        case "bye":
            return Command.addByeCommand();

        default:
                throw new DukeWrongInputException();
        }
    }
}
