package gr.prog.clirpg.model.world;

import gr.prog.clirpg.model.characters.Character;
import gr.prog.clirpg.model.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Room (Location)
 */
public class Room implements Serializable {
	private final String description;
	private final List<Character> characters = new ArrayList<>();
	private final List<Item> items = new ArrayList<>();

	public Room(String description, List<Character> characters) {
		this.description = description;
		this.characters.addAll(characters);
	}

	/**
	 * Get room/location description
	 *
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get all characters in location
	 *
	 * @return List of characters
	 */
	public List<Character> getCharacters() {
		return characters;
	}

	/**
	 * Get all items in location
	 *
	 * @return List of items
	 */
	public List<Item> getItems() {
		return items;
	}

}
