package gr.prog.clirpg.repository;

import gr.prog.clirpg.RpgException;
import gr.prog.clirpg.model.characters.Hero;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryRepository implements Repository<Hero> {
	private static InMemoryRepository instance;

	private final List<byte[]> storage = new ArrayList<>();

	private InMemoryRepository() {
	}

	public static InMemoryRepository getInstance() {
		if (instance == null) {
			instance = new InMemoryRepository();
		}
		return instance;
	}

	@Override
	public void save(Hero hero) {
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
			 ObjectOutput out = new ObjectOutputStream(bos)) {
			out.writeObject(hero);
			byte[] bytes = bos.toByteArray();
			storage.add(bytes);
		} catch (IOException e) {
			throw new RpgException(e);
		}
	}

	@Override
	public Hero findById(int id) {
		if (id >= storage.size()) {
			return null;
		}
		byte[] bytes = storage.get(id);
		return readHero(bytes).orElse(null);
	}

	@Override
	public List<Hero> findAll() {
		List<Hero> result = new ArrayList<>();
		for (byte[] bytes : storage) {
			readHero(bytes).ifPresent(result::add);
		}
		return result;
	}

	@Override
	public void clear() {
		storage.clear();
	}

	private Optional<Hero> readHero(byte[] bytes) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			 ObjectInput in = new ObjectInputStream(bis)) {
			return Optional.of((Hero) in.readObject());
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
