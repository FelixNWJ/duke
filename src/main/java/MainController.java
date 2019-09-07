import javafx.geometry.Pos;

import javafx.scene.image.Image;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;

import javafx.scene.image.ImageView;

import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Priority;

import javafx.stage.Stage;

/**
 * Controller class for the main window of the Duke application.
 * Command Line Input is at the bottom left of the window.
 * User input entered in reflected on the right window, along with the reply of the Duke application.
 * Any commands that involve listing potentially more than one task will output the list above the command line input.
 */
public class MainController {
    @FXML
    private VBox root;

    @FXML
    private SplitPane mainWindow;

    @FXML
    private SplitPane leftWindow;

    @FXML
    private AnchorPane helpArea;

    @FXML
    private AnchorPane textInputArea;

    @FXML
    private HBox dummyArea;

    @FXML
    private TextField userInput;

    @FXML
    private Button confirm;

    @FXML
    private ScrollPane rightWindow;

    @FXML
    private GridPane dialogBox;

    private int responseArea = 0;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Reddit.jpg"));
    private Image dialogImage = new Image(this.getClass().getResourceAsStream("/images/Isla.gif"));
    private Image taskImage = new Image(this.getClass().getResourceAsStream("/images/Task.jpg"));
    private Image mainBackgroundImage = new Image(this.getClass().getResourceAsStream("/images/mainBackground.png"));

    /**
     * Sets the Duke attribute of the controller to a particular Duke object to process user input.
     *
     * @param mainDuke a Duke object that we want to use to process our user input.
     */
    public void setDuke(Duke mainDuke) {
        this.duke = mainDuke;
    }

    /**
     * Initialises the Stage properties that is shown.
     * The children which are windows in the Stage are set to always grow when the root grows.
     * Scrollpane on the right is binded to the Hbox inside it so that the scrollpane automatically scrolls down.
     * Sets a background image to the top left of the stage window and binds it so that it resizes accordingly
     */
    @FXML
    public void initialize() {
        root.setVgrow(mainWindow, Priority.ALWAYS);
        //root.setVgrow(rightWindow, Priority.ALWAYS);
        //root.setVgrow(leftWindow, Priority.ALWAYS);;
        helpArea.prefWidthProperty().bind(leftWindow.widthProperty());
        helpArea.prefHeightProperty().bind(leftWindow.heightProperty());
        rightWindow.vvalueProperty().bind(dialogBox.heightProperty());
        ImageView temp = new ImageView(mainBackgroundImage);
        temp.setPreserveRatio(false);
        temp.fitWidthProperty().bind(helpArea.widthProperty());
        temp.fitHeightProperty().bind(helpArea.heightProperty());
        helpArea.getChildren().addAll(temp);
        userInput.nodeOrientationProperty().bind(textInputArea.nodeOrientationProperty());
    }

    /**
     * Generates an output based on the command input by the user in the Textfield userInput.
     * If the command by the user potentially involves listing multiple tasks, the list is displayed in listArea.
     * Otherwise, user input and output by Duke are displayed in the dialogBox.
     * If the command by the user is "bye", the application will shut down.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();
        String response = this.duke.run(input);
        DialogBox duke = DialogBox.getDukeDialog(response, dialogImage);
        if(input.equals("list") || input.contains("find")) {
            duke = DialogBox.getDukeDialog("List in the main area.", dialogImage);
            DialogBox list = DialogBox.getDukeDialog(response, taskImage);
            list.setResizeImage();
            dummyArea.getChildren().clear();
            dummyArea.getChildren().addAll(list);
        }
        DialogBox user = DialogBox.getUserDialog(input, userImage);
        dialogBox.add(user, 0, responseArea++);
        dialogBox.add(duke, 1, responseArea++);
        user.setAlignment(Pos.CENTER_LEFT);
        duke.setAlignment(Pos.CENTER_RIGHT);
        userInput.clear();
        if(input.equals("bye")) {
            ((Stage) mainWindow.getScene().getWindow()).close();
        }

    }
}
