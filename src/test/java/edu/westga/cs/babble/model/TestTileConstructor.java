package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests the constructor for the Tile class.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestTileConstructor {

	/**
	 * Should throw an IllegalArgumentException if passed a character other than a
	 * letter.
	 */
	@Test
	public void shouldNotAllowNonLetters() {
		Exception exception = assertThrows(IllegalArgumentException.class, () -> new Tile('1'));
		assertEquals("letter must be between A and Z", exception.getMessage());
	}

	/**
	 * Private helper method to test the creation of Tiles with upper and lower-case
	 * characters. Verifies that the constructor creates a tile with the correct
	 * letter and score value.
	 * 
	 * @param score      - the expected score of the tile
	 * @param letterList - a String containing all of the letters to be tested
	 */
	private void shouldCreateXPointTiles(int score, String letterList) {
		String[] lowerLetterStrings = letterList.toLowerCase().split("");
		for (String letterString : lowerLetterStrings) {

			char tileCharacter = letterString.charAt(0);

			Tile testTile = new Tile(tileCharacter);

			assertEquals(Character.toUpperCase(tileCharacter), testTile.getLetter());
			assertEquals(score, testTile.getPointValue());
		}

		String[] upperLetterStrings = letterList.toUpperCase().split("");
		for (String letterString : upperLetterStrings) {

			char tileCharacter = letterString.charAt(0);

			Tile testTile = new Tile(tileCharacter);

			assertEquals(tileCharacter, testTile.getLetter());
			assertEquals(score, testTile.getPointValue());
		}
	}

	/**
	 * All one-point letters (upper & lower-case) can be created with the correct
	 * letter and score values.
	 */
	@Test
	public void shouldCreateOnePointTiles() {
		this.shouldCreateXPointTiles(1, "EAIONRTLSU");
	}

	/**
	 * Both two-point letters (upper & lower-case) can be created with the correct
	 * letter and score values.
	 */
	@Test
	public void shouldCreateTwoPointTiles() {
		this.shouldCreateXPointTiles(2, "DG");
	}

	/**
	 * All three-point letters (upper & lower-case) can be created with the correct
	 * letter and score values.
	 */
	@Test
	public void shouldCreateThreePointTiles() {
		this.shouldCreateXPointTiles(3, "BCMP");
	}

	/**
	 * All four-point letters (upper & lower-case) can be created with the correct
	 * letter and score values.
	 */
	@Test
	public void shouldCreateFourPointTiles() {
		this.shouldCreateXPointTiles(4, "FHVWY");
	}

	/**
	 * The only five-point letter (upper & lower-case) can be created with the
	 * correct letter and score values.
	 */
	@Test
	public void shouldCreateFivePointTiles() {
		this.shouldCreateXPointTiles(5, "K");
	}

	/**
	 * Both eight-point letters (upper & lower-case) can be created with the correct
	 * letter and score values.
	 */
	@Test
	public void shouldCreateEightPointTiles() {
		this.shouldCreateXPointTiles(8, "JX");
	}

	/**
	 * Both ten-point letters (upper & lower-case) can be created with the correct
	 * letter and score values.
	 */
	@Test
	public void shouldCreateTenPointTiles() {
		this.shouldCreateXPointTiles(10, "QZ");
	}
}
