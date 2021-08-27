package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests TileGroup's append method.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestTileGroupAppend {

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
	 * Throws an IllegalArgumentException if passed a null Tile.
	 */
	@Test
	public void shouldNotAllowNull() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> this.testTileGroup.append(null));
		assertEquals("tile cannot be null", exception.getMessage());
	}

	/**
	 * The group should not contain any Tiles if none have been appended.
	 */
	@Test
	public void emptyGroupsShouldBeEmpty() {
		assertEquals(0, this.testTileGroup.tiles().size());
		assertTrue(this.testTileGroup.tiles().isEmpty());
	}

	/**
	 * After appending a single tile, the TileGroup should contain the Tile.
	 */
	@Test
	public void shouldHaveOneTileInTileGroup() {
		Tile testTile = new Tile('A');
		this.testTileGroup.append(testTile);

		assertEquals(1, this.testTileGroup.tiles().size());
		assertEquals(testTile, this.testTileGroup.tiles().get(0));
	}

	/**
	 * Should be able to append many kinds of tiles.
	 */
	@Test
	public void shouldHaveManyTilesInTileGroup() {
		Tile tileM = new Tile('M');
		Tile tileA = new Tile('A');
		Tile tileN = new Tile('N');
		Tile tileY = new Tile('Y');

		this.testTileGroup.append(tileM);
		this.testTileGroup.append(tileA);
		this.testTileGroup.append(tileN);
		this.testTileGroup.append(tileY);

		assertEquals(4, this.testTileGroup.tiles().size());

		assertEquals(tileM, this.testTileGroup.tiles().get(0));
		assertEquals(tileA, this.testTileGroup.tiles().get(1));
		assertEquals(tileN, this.testTileGroup.tiles().get(2));
		assertEquals(tileY, this.testTileGroup.tiles().get(3));
	}

	/**
	 * While adding tiles, should be able to add multiple Tiles with the same
	 * letter.
	 */
	@Test
	public void shouldHaveManyTilesIncludingDuplicatesInTileGroup() {
		Tile tileJ = new Tile('J');
		Tile tileA1 = new Tile('A');
		Tile tileV = new Tile('V');
		Tile tileA2 = new Tile('A');

		this.testTileGroup.append(tileJ);
		this.testTileGroup.append(tileA1);
		this.testTileGroup.append(tileV);
		this.testTileGroup.append(tileA2);

		assertEquals(4, this.testTileGroup.tiles().size());

		assertEquals(tileJ, this.testTileGroup.tiles().get(0));
		assertEquals(tileA1, this.testTileGroup.tiles().get(1));
		assertEquals(tileV, this.testTileGroup.tiles().get(2));
		assertEquals(tileA2, this.testTileGroup.tiles().get(3));
	}

	/**
	 * Should not be able to add the same Tile object twice -- as it would cause
	 * issues later.
	 */
	@Test
	public void canNotAddSameTileTwice() {
		Tile testTile = new Tile('X');
		this.testTileGroup.append(testTile);

		Exception exception = assertThrows(IllegalArgumentException.class, () -> this.testTileGroup.append(testTile));
		assertEquals("can not add same tile twice", exception.getMessage());
	}
}
