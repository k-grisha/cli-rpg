package gr.prog.clirpg.model;

import gr.prog.clirpg.RpgException;

import java.io.Serializable;

public class World implements Serializable {
	private Room[][] rooms;
	private final int size;
	private final Position initialPosition = new Position(0, 0);
	private final static RoomBuilder roomBuilder = RoomBuilder.getInstance();

	public World(int size) {
		this.size = size;
		this.rooms = new Room[size][size];
		this.rooms[initialPosition.x][initialPosition.y] = roomBuilder.getInitialRoom();
	}

	public Room getRoom(Position position) {
		if (!isValid(position)) {
			throw new RpgException("Invalid room position");
		}
		Room room = rooms[position.x][position.y];
		if (room == null) {
			room = roomBuilder.generateRoom();
			rooms[position.x][position.y] = room;
		}
		return room;
	}

	public Position getInitialPosition() {
		return initialPosition;
	}

	public boolean isValid(Position position) {
		return position.x < size && position.x >= 0 && position.y < size && position.y >= 0;
	}

	public int getSize() {
		return size;
	}
}
