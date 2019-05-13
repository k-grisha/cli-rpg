package gr.prog.clirpg.services;

import gr.prog.clirpg.model.Hero;
import gr.prog.clirpg.model.Position;
import gr.prog.clirpg.model.Room;
import gr.prog.clirpg.model.World;
import gr.prog.clirpg.model.Character;

import java.util.HashMap;
import java.util.Map;

public class WorldService {
	private static WorldService instance;
	private Map<Hero, World> worlds = new HashMap<>();

	private WorldService() {
	}

	public static WorldService getInstance() {
		if (instance == null) {
			instance = new WorldService();
		}
		return instance;
	}

	public void generateNewWorld(Hero hero, int size) {
		World world = new World(size);
		hero.setPosition(world.getInitialPosition());
		worlds.put(hero, world);
	}

	public Room getCurrentRoom(Hero hero) {
		return worlds.get(hero).getRoom(hero.getPosition());
	}

	public Room getRoom(Hero hero, Position position) {
		return worlds.get(hero).getRoom(position);
	}

	public boolean moveUp(Hero hero) {
		Position newPosition = new Position(hero.getPosition().x, hero.getPosition().y + 1);
		return move(hero, newPosition);
	}

	public boolean moveDown(Hero hero) {
		Position newPosition = new Position(hero.getPosition().x, hero.getPosition().y - 1);
		return move(hero, newPosition);
	}

	public boolean moveLeft(Hero hero) {
		Position newPosition = new Position(hero.getPosition().x - 1, hero.getPosition().y);
		return move(hero, newPosition);
	}

	public boolean moveRight(Hero hero) {
		Position newPosition = new Position(hero.getPosition().x + 1, hero.getPosition().y);
		return move(hero, newPosition);
	}

	public int getWorldSize(Hero hero){
		return worlds.get(hero).getSize();
	}

	private boolean move(Hero hero, Position position) {
		World world = worlds.get(hero);
		if (!world.isValid(position)) {
			return false;
		}
		hero.setPosition(position);
		return true;
	}
}
