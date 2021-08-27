package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test TileRack's getNumberOfTilesNeeded method.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestTileRackGetNumberOfTilesNeeded {

	private TileRack testTileRack;

	/**
	 * Before each test, create a new TileRack to use.
	 */
	@BeforeEach
	public void initEach() {
		this.testTileRack = new TileRack();
	}

	/**
	 * An empty TileRack has all of its spaces available.
	 */
	@Test
	public void emptyTileRackShouldNeedMaxSizeNumberOfTiles() {
		assertEquals(TileRack.MAX_SIZE, this.testTileRack.getNumberOfTilesNeeded());
	}

	/**
	 * A TileRack with only one Tile has all but one of its spaces available.
	 */
	@Test
	public void tileRackWithOneTileShoudNeedMaxSizeMinusOneTiles() {
		this.testTileRack.append(new Tile('A'));

		assertEquals(TileRack.MAX_SIZE - 1, this.testTileRack.getNumberOfTilesNeeded());
	}

	/**
	 * A TileRack with n Tiles in it has its total number of spaces minus n
	 * available.
	 */
	@Test
	public void tileRackWithSeveralTilesShouldNeedSomeTiles() {
		this.testTileRack.append(new Tile('O'));
		this.testTileRack.append(new Tile('T'));
		this.testTileRack.append(new Tile('T'));
		this.testTileRack.append(new Tile('F'));

		assertEquals(TileRack.MAX_SIZE - 4, this.testTileRack.getNumberOfTilesNeeded());
	}

	/**
	 * A full TileRack has no spaces available.
	 */
	@Test
	public void fullTileRackNeedsZeroTiles() {
		for (int counter = 0; counter < TileRack.MAX_SIZE; counter++) {
			this.testTileRack.append(new Tile('A'));
		}

		assertEquals(0, this.testTileRack.getNumberOfTilesNeeded());
	}
}
