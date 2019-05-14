package gr.prog.clirpg.repository;

import gr.prog.clirpg.RpgException;
import gr.prog.clirpg.model.Hero;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

//todo Generic
public class InMemoryRepository implements HeroRepository {
	private static InMemoryRepository instance;

	private final List<byte[]> storage = new ArrayList<>();

	public InMemoryRepository() {
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
		//todo validation
		byte[] bytes = storage.get(id);
		return readHero(bytes);
	}

	@Override
	public List<Hero> findAll() {
		List<Hero> result = new ArrayList<>();
		for (byte[] bytes : storage) {
			result.add(readHero(bytes));
		}
		return result;
	}

	private Hero readHero(byte[] bytes) {
		try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
			 ObjectInput in = new ObjectInputStream(bis)) {
			return (Hero) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RpgException(e);
		}

	}

}
