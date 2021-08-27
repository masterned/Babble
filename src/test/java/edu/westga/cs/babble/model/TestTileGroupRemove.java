package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests TileGroup's remove method.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestTileGroupRemove {

	private TileGroup testTileGroup;

	/**
	 * Before each test, generates a new unnamed derivative of the abstract
	 * TileGroup class.
	 */
	@BeforeEach
	public void initEach() {
		this.testTileGroup = new TileGroup() {
		};
	}

	/**
	 * Should throw an IllegalArgumentException when attempting to remove a null
	 * Tile.
	 */
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> this.testTileGroup.remove(null));
	}

	/**
	 * Should throw a TileNotInGroupException when attempting to remove Tile from
	 * empty group.
	 */
	@Test
	public void canNotRemoveFromEmptyTileGroup() {
		Tile testTile = new Tile('T');
		assertThrows(TileNotInGroupException.class, () -> this.testTileGroup.remove(testTile));
	}

	/**
	 * Should throw a TileNotInGroupException when attempting to remove a Tile not
	 * in the TileGroup.
	 */
	@Test
	public void canNotRemoveTileNotInTileGroup() {
		this.testTileGroup.append(new Tile('T'));
		this.testTileGroup.append(new Tile('E'));
		this.testTileGroup.append(new Tile('A'));
		this.testTileGroup.append(new Tile('M'));

		assertThrows(TileNotInGroupException.class, () -> this.testTileGroup.remove(new Tile('I')));
	}

	/**
	 * Is able to remove a tile in a group of one, making it empty.
	 */
	@Test
	public void canRemoveOnlyTileInTileGroup() {
		Tile testTile = new Tile('O');

		this.testTileGroup.append(testTile);

		try {
			this.testTileGroup.remove(testTile);
		} catch (TileNotInGroupException exception) {
			fail("Unable to remove Tile");
		}

		assertEquals(0, this.testTileGroup.tiles().size());
		assertTrue(this.testTileGroup.tiles().isEmpty());
	}

	/**
	 * Is able to remove the first tile within a group of multiple Tiles.
	 */
	@Test
	public void canRemoveFirstOfManyTilesFromTileGroup() {
		Tile testTile = new Tile('X');

		this.testTileGroup.append(testTile);
		this.testTileGroup.append(new Tile('O'));
		this.testTileGroup.append(new Tile('R'));
		this.testTileGroup.append(new Tile('G'));

		try {
			this.testTileGroup.remove(testTile);
		} catch (TileNotInGroupException exception) {
			fail("Unable to remove Tile");
		}

		assertEquals(3, this.testTileGroup.tiles().size());

		assertEquals('O', this.testTileGroup.tiles().get(0).getLetter());
		assertEquals('R', this.testTileGroup.tiles().get(1).getLetter());
		assertEquals('G', this.testTileGroup.tiles().get(2).getLetter());
	}

	/**
	 * Is able to remove the last Tile (position wise) within a group of many Tiles.
	 */
	@Test
	public void canRemoveLastOfManyTilesFromtTileGroup() {
		this.testTileGroup.append(new Tile('T'));
		this.testTileGroup.append(new Tile('A'));
		this.testTileGroup.append(new Tile('P'));

		Tile testTile = new Tile('E');
		this.testTileGroup.append(testTile);

		try {
			this.testTileGroup.remove(testTile);
		} catch (TileNotInGroupException exception) {
			fail("Unable to remove Tile");
		}

		assertEquals(3, this.testTileGroup.tiles().size());

		assertEquals('T', this.testTileGroup.tiles().get(0).getLetter());
		assertEquals('A', this.testTileGroup.tiles().get(1).getLetter());
		assertEquals('P', this.testTileGroup.tiles().get(2).getLetter());
	}

	/**
	 * Is able to remove a Tile in the middle of other Tiles.
	 */
	@Test
	public void canRemoveMiddleOfManyTilesFromTileGroup() {
		Tile testTile = new Tile('H');

		this.testTileGroup.append(new Tile('S'));
		this.testTileGroup.append(new Tile('O'));
		this.testTileGroup.append(testTile);
		this.testTileGroup.append(new Tile('O'));

		try {
			this.testTileGroup.remove(testTile);
		} catch (TileNotInGroupException exception) {
			fail("Unable to remove Tile");
		}

		assertEquals(3, this.testTileGroup.tiles().size());

		assertEquals('S', this.testTileGroup.tiles().get(0).getLetter());
		assertEquals('O', this.testTileGroup.tiles().get(1).getLetter());
		assertEquals('O', this.testTileGroup.tiles().get(2).getLetter());
	}

	/**
	 * Is able to remove multiple Tiles from the group.
	 */
	@Test
	public void canRemoveMultipleTilesFromTileGroup() {
		Tile tileM = new Tile('M');
		Tile tileU = new Tile('U');
		Tile tileL = new Tile('L');
		Tile tileT = new Tile('T');
		Tile tileI = new Tile('I');

		this.testTileGroup.append(tileM);
		this.testTileGroup.append(tileU);
		this.testTileGroup.append(tileL);
		this.testTileGroup.append(tileT);
		this.testTileGroup.append(tileI);

		try {
			this.testTileGroup.remove(tileM);
			this.testTileGroup.remove(tileU);
			this.testTileGroup.remove(tileL);
			this.testTileGroup.remove(tileI);
		} catch (TileNotInGroupException exception) {
			fail("Unable to remove Tile");
		}

		assertEquals(1, this.testTileGroup.tiles().size());
		assertEquals(tileT, this.testTileGroup.tiles().get(0));
	}

	/**
	 * Removes a Tile within a group that contains another Tile with the same
	 * letter. Does so without disturbing the other Tile.
	 */
	@Test
	public void doesNotRemoveDuplicateTilesFromTileGroup() {
		Tile testTile = new Tile('A');

		this.testTileGroup.append(new Tile('J'));
		this.testTileGroup.append(testTile);
		this.testTileGroup.append(new Tile('V'));
		this.testTileGroup.append(new Tile('A'));

		try {
			this.testTileGroup.remove(testTile);
		} catch (TileNotInGroupException exception) {
			fail("Unable to remove Tile");
		}

		assertEquals(3, this.testTileGroup.tiles().size());

		assertEquals('J', this.testTileGroup.tiles().get(0).getLetter());
		assertEquals('V', this.testTileGroup.tiles().get(1).getLetter());
		assertEquals('A', this.testTileGroup.tiles().get(2).getLetter());
	}
}
