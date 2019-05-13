package gr.prog.clirpg.model;

import gr.prog.clirpg.RpgException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class RoomBuilder {
	private static RoomBuilder instance;
	private List<String> descriptions = new ArrayList<>();
	private Room initialRoom = new Room("This is initial room. You can explore the world and get fame in battles.", Collections.emptyList());
	private Random rndGenerator;

	private RoomBuilder(String filename) {
		InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
		if (is == null) {
			throw new RpgException("Rooms description file not found");
		}
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		try {
			String line = buf.readLine();
			while (line != null) {
				descriptions.add(line);
				line = buf.readLine();
			}
		} catch (IOException e) {
			throw new RpgException("Fail to read file with rooms description", e);
		}
		rndGenerator = new Random();
	}

	public static RoomBuilder getInstance() {
		if (instance == null) {
			instance = new RoomBuilder("rooms/description.txt");
		}
		return instance;
	}

	public Room generateRoom() {
		List<Character> characters = new ArrayList<>();
		if (rndGenerator.nextInt(3) == 0) {
			characters.addAll(generateGoblinFamily());
		}
		String description = descriptions.get(rndGenerator.nextInt(descriptions.size()));
		return new Room(description, characters);
	}

	private List<Character> generateGoblinFamily(){
		List<Character> goblins = new ArrayList<>();
		int parentsCount = rndGenerator.nextInt(2) + 1;
		IntStream.range(0, parentsCount).forEach($ -> goblins.add(new Enemy(
				50 + rndGenerator.nextInt(50),
				5 + rndGenerator.nextInt(5),
				"Big goblin")));
		int babyCount = rndGenerator.nextInt(3) + 1;
		IntStream.range(0, babyCount).forEach($->goblins.add(new Enemy(
				10 + rndGenerator.nextInt(30),
				1 + rndGenerator.nextInt(3),
				"Baby goblin")));
		return goblins;
	}

	public Room getInitialRoom() {
		return initialRoom;
	}
}
