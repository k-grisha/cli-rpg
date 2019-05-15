package gr.prog.clirpg.model.world.roomFactories;

import gr.prog.clirpg.RpgException;
import gr.prog.clirpg.model.world.Room;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Room Factory
 */
public abstract class RoomFactory {
	private final List<String> descriptions = new ArrayList<>();
	private final String fileName;

	/**
	 * Construct room factory
	 *
	 * @param fileName File with room descriptions
	 */
	RoomFactory(String fileName) {
		this.fileName = fileName;
	}

	public List<String> getDescriptions() {
		if (descriptions.isEmpty()) {
			loadDescriptions();
		}
		return descriptions;
	}

	private void loadDescriptions() {
		InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);
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
	}

	/**
	 * Rooms creator
	 *
	 * @return Room
	 */
	public abstract Room createRoom();
}
