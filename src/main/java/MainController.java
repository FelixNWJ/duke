import com.sun.javafx.iio.gif.GIFImageLoader2;
import com.sun.javafx.iio.gif.GIFImageLoaderFactory;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.awt.*;

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

    public void setDuke(Duke d) {
        this.duke = d;
    }

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

    @FXML
    void handleUserInput() {
        String input = userInput.getText();
        String response = this.duke.run(input);
        DialogBox duke = DialogBox.getDukeDialog(response, dialogImage);
        if(input.equals("list")) {
            duke = DialogBox.getDukeDialog("List in the main area.", dialogImage);
            dummyArea.getChildren().clear();
            dummyArea.getChildren().addAll(DialogBox.getDukeDialog(response, taskImage));
        }
        DialogBox user = DialogBox.getUserDialog(input, userImage);
        dialogBox.add(user, 0, responseArea++);
        dialogBox.add(duke, 1, responseArea++);
        user.setAlignment(Pos.CENTER_LEFT);
        duke.setAlignment(Pos.CENTER_RIGHT);
        userInput.clear();
        if(input.equals("bye")) {
            System.exit(0);
        }
    }
}
