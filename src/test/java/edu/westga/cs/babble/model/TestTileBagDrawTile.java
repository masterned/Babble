package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests TileBag's drawTile method.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestTileBagDrawTile {

	private TileBag testTileBag;

	/**
	 * Before each test, start with a new TileBag.
	 */
	@BeforeEach
	public void initEach() {
		this.testTileBag = new TileBag();
	}

	/**
	 * Makes sure that it can return up to 98 Tiles.
	 */
	@Test
	public void canDrawAllTiles() {
		for (int counter = 0; counter < 98; counter++) {
			try {
				this.testTileBag.drawTile();
			} catch (EmptyTileBagException exception) {
				fail("Ran out of tiles");
			}
		}
	}

	/**
	 * Make sure it throws a BagEmptyException if attempting to pull more than 98
	 * Tiles.
	 */
	@Test
	public void canNotDrawTooManyTiles() {
		this.canDrawAllTiles();

		assertThrows(EmptyTileBagException.class, () -> this.testTileBag.drawTile());
	}

	/**
	 * Makes sure that it has the same letter distribution as the English edition of
	 * Scrabble.
	 */
	@Test
	public void hasProperTileDistribution() {
		HashMap<Character, Integer> neededLetters = new HashMap<Character, Integer>();
		neededLetters.put('A', 9);
		neededLetters.put('B', 2);
		neededLetters.put('C', 2);
		neededLetters.put('D', 4);
		neededLetters.put('E', 12);
		neededLetters.put('F', 2);
		neededLetters.put('G', 3);
		neededLetters.put('H', 2);
		neededLetters.put('I', 9);
		neededLetters.put('J', 1);
		neededLetters.put('K', 1);
		neededLetters.put('L', 4);
		neededLetters.put('M', 2);
		neededLetters.put('N', 6);
		neededLetters.put('O', 8);
		neededLetters.put('P', 2);
		neededLetters.put('Q', 1);
		neededLetters.put('R', 6);
		neededLetters.put('S', 4);
		neededLetters.put('T', 6);
		neededLetters.put('U', 4);
		neededLetters.put('V', 2);
		neededLetters.put('W', 2);
		neededLetters.put('X', 1);
		neededLetters.put('Y', 2);
		neededLetters.put('Z', 1);

		for (int counter = 0; counter < 98; counter++) {
			try {
				char tileCharacter = this.testTileBag.drawTile().getLetter();
				neededLetters.replace(tileCharacter, neededLetters.get(tileCharacter) - 1);
			} catch (EmptyTileBagException exception) {
			}
		}

		for (Integer letterCount : neededLetters.values()) {
			if (letterCount != 0) {
				fail("Does not contain the proper letter distribution");
			}
		}
	}
}
