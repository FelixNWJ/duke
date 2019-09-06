import duke.exception.DukeWrongTimeFormatException;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.geometry.Pos;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;

import javafx.stage.Stage;

import java.awt.Dimension;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

import duke.ui.DukeUi;

import duke.tasklist.TaskList;

import duke.storagedata.StorageData;

import duke.command.Command;

import duke.parser.Parser;

import duke.exception.DukeWrongInputException;
import duke.exception.DukeEmptyDescriptionException;
import duke.exception.DukeMissingDescriptionException;

public class Duke extends Application {
    private DukeUi dukeUI;
    private StorageData storage;
    private TaskList tasks;

    public Duke() {
        this("data/tasks.txt");
    }

    /**
     * Instantiates a Duke Object.
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
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            VBox vb = fxmlLoader.load();
            Scene scene = new Scene(vb);
            stage.setScene(scene);
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

/*
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
 */
}


