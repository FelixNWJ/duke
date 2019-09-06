import java.io.IOException;

import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.geometry.Insets;

import javafx.scene.Node;

import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;

import javafx.scene.paint.Color;

import javafx.scene.text.Font;

/**
 * Represents the Hbox that contains the dialog that we want to display.
 * This control represents a dialog box consisting of an ImageView to represent the speaker's face and a label
 * containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox based on the DialogBox fxml file.
     *
     * @param text is the input from the user or output from Duke that we want to display.
     * @param img is the image representing the user or Duke.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainController.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialog.setText(text);
        displayPicture.setImage(img);
    }

    public void setResizeImage() {
        this.displayPicture.fitWidthProperty().bind(this.widthProperty().divide(2));
        this.displayPicture.fitHeightProperty().bind(this.heightProperty().divide(2));
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Static command to create a DialogBox that contains user input and the user's image.
     *
     * @param text is the user input that we want to display.
     * @param img is the image representing the user.
     * @return the DialogBox object that contains the arguments loaded into this static method.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        var db = new DialogBox(text, img);;
        db.flip();
        db.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        db.dialog.setTextFill(Color.BLACK);
        db.dialog.setFont(new Font("Verdana", 12));
        return db;
    }

    /**
     * Static command to create a DialogBox that contains Duke's response and Duke's reply image.
     *
     * @param text is the response from Duke that we want to display.
     * @param img is the image representing Duke.
     * @return the DialogBox object that contains the arguments loaded into this static method.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        db.dialog.setTextFill(Color.RED);
        db.dialog.setFont(new Font("Verdana", 12));
        return db;
    }
}