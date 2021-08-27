package edu.westga.cs.babble.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests PlayedWord's clear method.
 * 
 * @author Spencer Dent
 * @version 2021-08-26
 */
public class TestPlayedWordClear {

	private PlayedWord testPlayedWord;

	/**
	 * Before each test, create a new PlayedWord object.
	 */
	@BeforeEach
	public void initEach() {
		this.testPlayedWord = new PlayedWord();
	}

	/**
	 * Clearing an empty word should result in an empty word (no change).
	 */
	@Test
	public void shouldClearEmptyWord() {
		this.testPlayedWord.clear();
		assertEquals(0, this.testPlayedWord.tiles().size());
	}

	/**
	 * Should be able to clear a PlayedWord consisting of a single Tile.
	 */
	@Test
	public void shouldClearWordWithOneTile() {
		this.testPlayedWord.append(new Tile('A'));

		this.testPlayedWord.clear();

		assertEquals(0, this.testPlayedWord.tiles().size());
	}

	/**
	 * Should be able to clear a PlayedWord consisting of multiple Tiles.
	 */
	@Test
	public void shouldClearWordWithManyTiles() {
		this.testPlayedWord.append(new Tile('M'));
		this.testPlayedWord.append(new Tile('A'));
		this.testPlayedWord.append(new Tile('N'));
		this.testPlayedWord.append(new Tile('Y'));

		this.testPlayedWord.clear();

		assertEquals(0, this.testPlayedWord.tiles().size());
	}
}
