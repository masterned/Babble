package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests TileRack's method override of TileGroup's append.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestTileRackAppend {

	/**
	 * Should throw TileRackFullException when trying to append too many Tiles.
	 */
	@Test
	public void shouldNotAppendToFullRack() {
		TileRack testTileRack = new TileRack();

		for (int count = 0; count < TileRack.MAX_SIZE; count++) {
			testTileRack.append(new Tile('A'));
		}
		
		assertThrows(TileRackFullException.class, () -> testTileRack.append(new Tile('B')));
	}
}
