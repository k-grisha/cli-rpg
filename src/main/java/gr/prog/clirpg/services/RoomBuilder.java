package gr.prog.clirpg.services;

import gr.prog.clirpg.RpgException;
import gr.prog.clirpg.model.Room;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class RoomBuilder {
	private static RoomBuilder instance;
	private List<String> descriptions;
	private Room initialRoom = new Room("This is initial room. You can explore the world and get fame in battles.");
	private Random rndGenerator;

	private RoomBuilder(String filename) {
		URL url = getClass().getClassLoader().getResource(filename);
		if (url == null) {
			throw new RpgException("Rooms description file not found");
		}
		try {
			Path path = Paths.get(url.toURI());
			descriptions = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (URISyntaxException | IOException e) {
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

	public Room getRandomRoom(){
		int index = rndGenerator.nextInt(descriptions.size());
		return new Room(descriptions.get(index));
	}

	public Room getInitialRoom() {
		return initialRoom;
	}
}
