package edu.westga.cs.babble.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import edu.westga.cs.babble.model.EmptyTileBagException;
import edu.westga.cs.babble.model.PlayedWord;
import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileBag;
import edu.westga.cs.babble.model.TileNotInGroupException;
import edu.westga.cs.babble.model.TileRack;
import edu.westga.cs.babble.model.TileRackFullException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

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
	/* communicating with the other controller */
	private final WordDictionary wordDictionary;

	/* communicating with the models */
	private final TileBag tileBag;
	private final TileRack tileRack;
	private final PlayedWord playedWord;
	private final IntegerProperty scoreValue;

	/* communicating with the view */
	@FXML private ListView<Tile> tileListView;
	@FXML private ListView<Tile> selectedLetterListView;

	@FXML private Button resetButton;
	@FXML private Button playWordButton;

	@FXML private TextField scoreTextField;

	/**
	 * Constructor: initializes all of the instance variables, and prepares the
	 * models.
	 * 
	 * The constructor along side of the initialize method allows the instance
	 * variables to be declared as constants (preventing accidental reassignment in
	 * the future)
	 */
	public BabbleController() {
		this.wordDictionary = new WordDictionary();

		this.tileBag = new TileBag();

		this.tileRack = new TileRack();
		this.fillTileRackWithTilesFromTileBag();

		this.playedWord = new PlayedWord();

		this.scoreValue = new SimpleIntegerProperty(0);
	}

	/**
	 * Called by JavaFX has finished setting up the view. Attaches the controller's
	 * models to the view objects and initializes the values on the view.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {

		this.scoreTextField.textProperty().bind(this.scoreValue.asString());

		// TODO? extract commonality into private inner class
		this.tileListView.setItems(this.tileRack.tiles());
		this.tileListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> list) {
				return new TileCell();
			}
		});
		this.tileListView.setOnMouseClicked(mouseClickedEvent -> {
			Tile clickedTile = this.tileListView.getSelectionModel().getSelectedItem();
			try {
				this.tileRack.remove(clickedTile);
				this.playedWord.append(clickedTile);
			} catch (TileNotInGroupException exception) {
				exception.printStackTrace();
			}
		});
		
		this.selectedLetterListView.setItems(this.playedWord.tiles());
		this.selectedLetterListView.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> list) {
				return new TileCell();
			}
		});
		this.selectedLetterListView.setOnMouseClicked(mouseClickedEvent -> {
			Tile clickedTile = this.selectedLetterListView.getSelectionModel().getSelectedItem();
			try {
				this.playedWord.remove(clickedTile);
				this.tileRack.append(clickedTile);
			} catch (TileNotInGroupException exception) {
				exception.printStackTrace();
			}
		});
	}

	static class TileCell extends ListCell<Tile> {
		@Override
		public void updateItem(Tile tile, boolean empty) {
			super.updateItem(tile, empty);

			if (empty || tile == null) {
				this.setText(null);
				this.setGraphic(null);
			} else {
				this.setText(tile.getLetter() + "");
			}
		}
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
		if (!this.playedWord.tiles().isEmpty()) {			
			this.tileRack.tiles().addAll(this.playedWord.tiles());
			this.playedWord.tiles().clear();
		}
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
		
		String hand = this.playedWord.getHand();
		
		if (this.playedWord.tiles().isEmpty() || !this.wordDictionary.isValidWord(hand)) {
			new Alert(AlertType.INFORMATION, "Not a valid word").showAndWait();
			return;
		}
		
		System.out.println(hand + " is a valid word");

		this.scoreValue.set(this.scoreValue.get() + this.playedWord.getScore());
		
		// TODO clear "Your Word" ListView
		
		// TODO refresh "Tiles" ListView
	}

	private void fillTileRackWithTilesFromTileBag() {
		int numberOfTilesNeeded = this.tileRack.getNumberOfTilesNeeded();

		for (int index = 0; index < numberOfTilesNeeded; index++) {
			try {
				this.tileRack.append(this.tileBag.drawTile());
			} catch (TileRackFullException | EmptyTileBagException exception) {
			}
		}
	}
}
