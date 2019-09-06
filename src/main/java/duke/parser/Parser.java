package duke.parser;

import duke.command.Command;

import duke.exception.DukeMissingDescriptionException;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeWrongInputException;

import java.util.Scanner;

/**
 * Represents the Parser that breaks down the user input.
 * The parser reads the first word which gives the type of command to be created.
 * The rest of the line is then used as an argument for the constructing the command object as details of the command.
 */
public class Parser {
    /**
     * Calls the static method of the abstract Command class to create the corresponding Command Object.
     *
     * @param fullLine contains the type of command and its details if applicable
     * @return a Command object.
     * @throws DukeWrongInputException when user gives an invalid input.
     * @throws DukeEmptyDescriptionException when user gives a command without any information where applicable.
     * @throws DukeMissingDescriptionException when user gives a command with missing information or
     * incorrectly formatted information.
     */
    public static Command parseCommand(String fullLine) throws DukeWrongInputException, DukeEmptyDescriptionException,
            DukeMissingDescriptionException {
        Scanner commandScanner = new Scanner(fullLine);
        String typeOfCommand = commandScanner.next().trim().toLowerCase();
        String detailsOfCommand = fullLine.substring(typeOfCommand.length()).trim();
        switch(typeOfCommand) {
        case "todo":
            return Command.addTodoCommand(detailsOfCommand);

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

        case "find":
            return Command.addFindCommand(detailsOfCommand);

        default:
            throw new DukeWrongInputException();
        }
    }
}
