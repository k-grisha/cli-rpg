package gr.prog.clirpg.services;

import gr.prog.clirpg.RpgException;
import gr.prog.clirpg.model.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoomBuilder {
	private static RoomBuilder instance;
	private List<String> descriptions = new ArrayList<>();
	private Room initialRoom = new Room("This is initial room. You can explore the world and get fame in battles.");
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

	public Room getRandomRoom() {
		int index = rndGenerator.nextInt(descriptions.size());
		return new Room(descriptions.get(index));
	}

	public Room getInitialRoom() {
		return initialRoom;
	}
}
