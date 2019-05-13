package gr.prog.clirpg.model;

import java.util.List;

public class Room {
	private String description;
	private List<Character> characters;
	private List<Item> items;

	public Room(String description, List<Character> characters) {
		this.description = description;
		this.characters = characters;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Character> getCharacters() {
		return characters;
	}

	public void setCharacters(List<Character> characters) {
		this.characters = characters;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
