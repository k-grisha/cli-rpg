package gr.prog.clirpg.model.world;

import gr.prog.clirpg.RpgException;

import java.io.Serializable;

/**
 * World of Hero
 */
public class World implements Serializable {
	private Room[][] rooms;
	private final int size;
	private final Position initialPosition = new Position(0, 0);
	private final static RoomBuilder roomBuilder = RoomBuilder.getInstance();

	/**
	 * Construct new square world
	 */
	public World(int size) {
		this.size = size;
		this.rooms = new Room[size][size];
		this.rooms[initialPosition.x][initialPosition.y] = roomBuilder.getInitialRoom();
	}

	/**
	 * Get room by position. If does not exist it will be generated
	 *
	 * @param position Position
	 * @return Room
	 */
	public Room getRoom(Position position) {
		if (!isValid(position)) {
			throw new RpgException("Invalid room position");
		}
		Room room = rooms[position.x][position.y];
		if (room == null) {
			room = roomBuilder.generateRandomRoom();
			rooms[position.x][position.y] = room;
		}
		return room;
	}

	/**
	 * Get initial position, where Hero was born
	 *
	 * @return Position
	 */
	public Position getInitialPosition() {
		return initialPosition;
	}

	/**
	 * Position validation regarding world size
	 *
	 * @param position Position
	 * @return true - if valid, false - otherwise
	 */
	public boolean isValid(Position position) {
		return position.x < size && position.x >= 0 && position.y < size && position.y >= 0;
	}

	/**
	 * Get world size
	 *
	 * @return World size
	 */
	public int getSize() {
		return size;
	}
}
