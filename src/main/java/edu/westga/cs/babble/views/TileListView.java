package edu.westga.cs.babble.views;

import edu.westga.cs.babble.model.Tile;
import edu.westga.cs.babble.model.TileGroup;
import edu.westga.cs.babble.model.TileNotInGroupException;
import javafx.geometry.Orientation;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.util.Callback;

/**
 * A modified version of ListView with defaults built in to work with Tiles.
 * 
 * @author Spencer Dent
 * @version 2021-10-29
 */
public class TileListView extends ListView<Tile> {

	private TileGroup containedTiles;
	private TileGroup otherTileGroup;

	/**
	 * Creates a new TileListView using the provided tiles, and initializes the
	 * element's defaults.
	 * 
	 * @param containedTiles - the group of Tiles to use as its model
	 * @param otherTileGroup - when the Tile is clicked in the containedTiles group,
	 *                       it moves to this otherTileGroup
	 * @param toolTipString  - the text to display in the box that appears when
	 *                       hovering over the element
	 */
	public TileListView(TileGroup containedTiles, TileGroup otherTileGroup, String toolTipString) {
		if (containedTiles == null || otherTileGroup == null) {
			throw new IllegalArgumentException("Both lists of Tiles cannot be null.");
		}
		this.containedTiles = containedTiles;
		this.otherTileGroup = otherTileGroup;

		this.setFixedCellSize(24.0);
		this.setOrientation(Orientation.HORIZONTAL);
		this.setPrefHeight(25.0);
		this.setTooltip(new Tooltip(toolTipString));

		this.setItems(containedTiles.tiles());

		this.setCellFactory(new Callback<ListView<Tile>, ListCell<Tile>>() {
			@Override
			public ListCell<Tile> call(ListView<Tile> list) {
				return new ListCell<Tile>() {
					@Override
					public void updateItem(Tile tile, boolean empty) {
						super.updateItem(tile, empty);

						if (empty || tile == null) {
							this.setText(null);
							this.setAccessibleText(null);
							this.setGraphic(null);
						} else {
							this.setText(tile.getLetter() + "");
							this.setAccessibleText(tile.getLetter() + "");
						}
					}
				};
			}
		});

		this.setOnMouseClicked(mouseClickedEvent -> {
			Tile clickedTile = this.getSelectionModel().getSelectedItem();
			if (clickedTile != null) {
				try {
					this.containedTiles.remove(clickedTile);
					this.otherTileGroup.append(clickedTile);
				} catch (TileNotInGroupException exception) {
					exception.printStackTrace();
				}
			}
		});

	}
}
