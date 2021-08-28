package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests PlayedWord's matches method.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestPlayedWordMatches {

	private PlayedWord testPlayedWord;

	/**
	 * Before each test create a new PlayedWord to work with.
	 */
	@BeforeEach
	public void initEach() {
		this.testPlayedWord = new PlayedWord();
	}

	/**
	 * A hand that matches the word should return true.
	 */
	@Test
	public void hasTilesForAMultipleLetterWord() {
		this.testPlayedWord.append(new Tile('W'));
		this.testPlayedWord.append(new Tile('O'));
		this.testPlayedWord.append(new Tile('R'));
		this.testPlayedWord.append(new Tile('D'));

		assertTrue(this.testPlayedWord.matches("WORD"));
	}

	/**
	 * A hand that matches the single-letter word should return true.
	 */
	@Test
	public void hasTilesForASingleLetterWord() {
		this.testPlayedWord.append(new Tile('A'));
		assertTrue(this.testPlayedWord.matches("A"));
	}

	/**
	 * The Tiles in the hand need to be in order.
	 */
	@Test
	public void cannotMatchWordWhenTilesAreScrambled() {
		this.testPlayedWord.append(new Tile('G'));
		this.testPlayedWord.append(new Tile('D'));
		this.testPlayedWord.append(new Tile('O'));

		assertFalse(this.testPlayedWord.matches("DOG"));
	}

	/**
	 * All of the tiles to make up the word are needed.
	 */
	@Test
	public void cannotMatchWordIfInsufficientTiles() {
		this.testPlayedWord.append(new Tile('F'));
		this.testPlayedWord.append(new Tile('R'));
		this.testPlayedWord.append(new Tile('O'));

		assertFalse(this.testPlayedWord.matches("FROG"));
	}

	/**
	 * The word to match against can contain multiples of the same letter.
	 */
	@Test
	public void canMatchWordWithDuplicateLetters() {
		this.testPlayedWord.append(new Tile('H'));
		this.testPlayedWord.append(new Tile('E'));
		this.testPlayedWord.append(new Tile('L'));
		this.testPlayedWord.append(new Tile('L'));
		this.testPlayedWord.append(new Tile('O'));

		assertTrue(this.testPlayedWord.matches("HELLO"));
	}

	/**
	 * A hand full of tiles should not match against empty text.
	 */
	@Test
	public void nonEmptyWordShouldNotMatchEmptyText() {
		this.testPlayedWord.append(new Tile('H'));
		this.testPlayedWord.append(new Tile('E'));
		this.testPlayedWord.append(new Tile('L'));
		this.testPlayedWord.append(new Tile('L'));
		this.testPlayedWord.append(new Tile('O'));

		assertFalse(this.testPlayedWord.matches(""));
	}

	/**
	 * An empty hand should not match against empty text.
	 */
	@Test
	public void emptyWordShouldNotMatchEmptyText() {
		assertFalse(this.testPlayedWord.matches(""));
	}

	/**
	 * Testing against a null String should throw an IllegalArgumentException.
	 */
	@Test
	public void shouldNotAllowNull() {
		assertThrows(IllegalArgumentException.class, () -> this.testPlayedWord.matches(null));
	}
}
