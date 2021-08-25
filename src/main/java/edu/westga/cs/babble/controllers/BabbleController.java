package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileRack;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * Serves as the controller for the Babble Game Application.
 * 
 * Passes information between the models and view, reacting to user input and
 * updating the game state appropriately.
 * 
 * @author Spencer Dent
 * @version 2021-08-24
 */
public class BabbleController implements Initializable {
	/* communicating with the models */
	private TileBag tileBag;
	private TileRack tileRack;
	private PlayedWord playedWord;
	private IntegerProperty scoreValue;

	/* communicating with the view */
	@FXML private ListView<Tile> tileListView;
	@FXML private ListView<Tile> selectedLetterListView;

	@FXML private Button resetButton;
	@FXML private Button playWordButton;

	@FXML private TextField scoreTextField;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		this.tileBag = new TileBag();

		this.tileRack = new TileRack();

		this.playedWord = new PlayedWord();
		
		this.scoreValue = new SimpleIntegerProperty(0);
		this.scoreTextField.textProperty().bind(this.scoreValue.asString());
	}

	/**
	 * Event handling function called when the "Reset" Button is clicked.
	 * 
	 * Places any Tiles moved from the "Tiles" ListView to the "Your Word" ListView
	 * back in the "Tiles" ListView.
	 * 
	 * @param clickEvent - the mouse event fired from the "Reset" button on click
	 */
	@FXML
	public void handleResetButtonClicked(MouseEvent clickEvent) {
		/* TODO: write code to handle click event */
		this.scoreValue.setValue(0);
	}

	/**
	 * Event handling function called when the "Play Word" Button is clicked.
	 * 
	 * Checks to see if the Tiles in the "Your Word" ListView make up a valid word.
	 * If the word is valid, the score is updated appropriately, the "Your Word"
	 * ListView is cleared, and the "Tiles" ListView is populated with new Tiles
	 * from the TileBag. Otherwise, an Alert is presented to the user indicating
	 * that the "word" is invalid.
	 * 
	 * @param clickEvent - the mouse event fired from the "Play Word" Button on
	 *                   click
	 */
	@FXML
	public void handlePlayWordButtonClicked(MouseEvent clickEvent) {
		/* TODO: write code to handle click event */
		this.scoreValue.set(this.scoreValue.get() + 1);
	}
}
