package gr.prog.clirpg.view.handlers;

import gr.prog.clirpg.model.characters.Character;
import gr.prog.clirpg.model.characters.Enemy;
import gr.prog.clirpg.services.CurrentHero;
import gr.prog.clirpg.view.View;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;

public class FightHandlerTest {

	private CurrentHero currentHero = Mockito.mock(CurrentHero.class, Mockito.RETURNS_DEEP_STUBS);
	private FightHandler fightHandler = new FightHandler(currentHero);

	@Test
	public void dispatchCommand_values_success() {
		Mockito.when(currentHero.getHero().getCurrentRoom().getCharacters()).thenReturn(Collections.singletonList(Mockito.mock(Enemy.class)));
		View view = fightHandler.dispatchCommand("0");
		Assert.assertEquals(View.FIGHT, view);
		Mockito.verify(currentHero.getHero(), Mockito.times(1)).attack(Mockito.any(Character.class));
		Mockito.clearInvocations(currentHero);
		view = fightHandler.dispatchCommand("100");
		Assert.assertEquals(View.FIGHT, view);
	}
}