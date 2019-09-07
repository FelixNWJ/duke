import duke.exception.DukeWrongTimeFormatException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.layout.VBox;

import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

import duke.command.Command;

import duke.parser.Parser;

import duke.exception.DukeWrongInputException;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingDescriptionException;

/**
 * Duke class that is an application that mainly contains capabilities to store, locate, and mark tasks as done.
 * Contains a dukeUI that outputs Strings based on the command input by the user.
 * Contains a storage that has the file that we write to and read from in order to save and load ask data respectively.
 * Contains a tasks that is TaskList which wraps around an ArrayList that stores our Task objects.
 */
public class Duke extends Application {
    private DukeUi dukeUI;
    private StorageData storage;
    private TaskList tasks;

    /**
     * Creates a Duke object that calls the Constructor for the Duke object that uses a String as an argument.
     * Created due to Application requiring a constructor that takes in no parameters.
     */
    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Instantiates a Duke Object.
     *
     * @param filePath This is the File that is used to load data from and save into.
     */
    public Duke(String filePath) {
        this.dukeUI = new DukeUi();
        this.storage = new StorageData(new File(filePath));
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            System.out.println("File name has changed.");
        }
    }

    /**
     * Starts the Duke Application.
     * Prints out a welcome message and waits for user input.
     * User input is then passed through the Parser object in the Duke object, creating a Command object.
     * Command object is then executed accordingly.
     */
    public String run(String input) {
        try {
            Command c = Parser.parseCommand(input);
            assert !input.isEmpty() : "Empty command created";
            assert c != null : "Parser.parseCommand(input) did not create a command object";
            return c.execute(this.tasks, this.dukeUI, this.storage);
        } catch (DukeWrongInputException e) {
            return e.getMessage();
        } catch (DukeMissingDescriptionException ex) {
            return ex.getMessage();
        } catch (DukeEmptyDescriptionException exx) {
            return exx.getMessage();
        } catch (DukeWrongTimeFormatException exxx) {
            return exxx.getMessage();
        }
    }

    /**
     * Method that loads and shows the Graphical User Interface when we start up the Duke Application.
     * Sets title of the Duke application and makes it resizable.
     * Sets the controller of the root to be a Duke object that is created at this point before displaying the window.
     *
     * @param stage Stage that we want to display to the user.
     */
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            VBox vb = fxmlLoader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);
            stage.setTitle("Isla");
            stage.setResizable(true);
            fxmlLoader.<MainController>getController().setDuke(new Duke());
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Used to get the welcome message from the dukeUI.
     * @return a welcome message.
     */
    public String getWelcomeMessage() {
        return this.dukeUI.getWelcomeMessage();
    }


}


