package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test PlayedWord's getScore method.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestPlayedWordGetScore {

	private PlayedWord testPlayedWord;

	/**
	 * Before each test, initialize a PlayedWord object.
	 */
	@BeforeEach
	public void initEach() {
		this.testPlayedWord = new PlayedWord();
	}

	/**
	 * Empty hand should always have score of zero.
	 */
	@Test
	public void emptyWordShoudlHaveScoreOfZero() {
		assertEquals(0, this.testPlayedWord.getScore());
	}

	/**
	 * One Tile score should equal the Tile's point value.
	 */
	@Test
	public void scoreAOneTileWord() {
		Tile testTile = new Tile('A');

		this.testPlayedWord.append(testTile);

		assertEquals(testTile.getPointValue(), this.testPlayedWord.getScore());
	}

	/**
	 * Should add up the point values of all the Tiles in the PlayedWord.
	 */
	@Test
	public void scoreAWordWithMultipleDifferingTiles() {
		Tile[] testTiles = { new Tile('K'), new Tile('I'), new Tile('N'), new Tile('G') };

		int pointValueTotal = 0;

		for (Tile testTile : testTiles) {
			this.testPlayedWord.append(testTile);
			pointValueTotal += testTile.getPointValue();
		}

		assertEquals(pointValueTotal, this.testPlayedWord.getScore());
	}

	/**
	 * Should return the correct score even when multiple Tiles in the PlayedWord
	 * contain the same letter.
	 */
	@Test
	public void scoreAWordContainingDuplicateTiles() {
		Tile[] testTiles = { new Tile('Q'), new Tile('U'), new Tile('E'), new Tile('E'), new Tile('N') };

		int pointValueTotal = 0;

		for (Tile testTile : testTiles) {
			this.testPlayedWord.append(testTile);
			pointValueTotal += testTile.getPointValue();
		}

		assertEquals(pointValueTotal, this.testPlayedWord.getScore());
	}
}
