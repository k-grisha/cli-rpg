package gr.prog.clirpg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Room implements Serializable {
	private final String description;
	private final List<Character> characters = new ArrayList<>();
	private final List<Item> items = new ArrayList<>();

	public Room(String description, List<Character> characters) {
		this.description = description;
		this.characters.addAll(characters);
	}

	public String getDescription() {
		return description;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public List<Item> getItems() {
		return items;
	}

}
