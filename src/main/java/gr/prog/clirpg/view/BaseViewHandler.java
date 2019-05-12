package gr.prog.clirpg.view;

import gr.prog.clirpg.RpgException;
import gr.prog.clirpg.model.Hero;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class BaseViewHandler implements View.ViewHandler {

	private static Hero hero;
	private final String content;

	BaseViewHandler(String filename) {
		InputStream is = getClass().getClassLoader().getResourceAsStream("views/" + filename);
		if (is == null) {
			throw new RpgException("View file not found: " + filename);
		}
		BufferedReader buf = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		try {
			String line = buf.readLine();
			while (line != null) {
				sb.append(line).append("\n");
				line = buf.readLine();
			}
		} catch (IOException e) {
			throw new RpgException("Fail to read file with view: " + filename, e);
		}
		content = sb.toString();
	}

	String getContent() {
		return content;
	}

	static Hero getHero() {
		return hero;
	}

	static void setHero(Hero hero) {
		BaseViewHandler.hero = hero;
	}

}
