package timer;

import static org.junit.Assert.*;

import org.junit.Test;

import map.GameMap;

public class GameTimerTest {

	@Test
	public void testStart() {
		IGameTimer gameTimer = new GameTimer();
		gameTimer.start(null);
		assertEquals(gameTimer.getEvolutionState(), false);
		gameTimer.start(new GameMap());
		assertEquals(gameTimer.getEvolutionState(), true);
	}

	@Test
	public void testStop() {
		IGameTimer gameTimer = new GameTimer();
		gameTimer.start(new GameMap());
		gameTimer.stop();
		assertEquals(gameTimer.getEvolutionState(), false);
	}

	@Test
	public void testSetEvolutionTimes() {
		IGameTimer gameTimer = new GameTimer();
		// 无效等价类，为0时，为默认值
		gameTimer.setEvolutionTimes(0);
		assertEquals(gameTimer.getEvolutionTimes(), IGameTimer.DEFAULT_MAX_EVOLUTION_TIMES);
		// 有效等价类
		gameTimer.setEvolutionTimes(1);
		assertEquals(gameTimer.getEvolutionTimes(), 1);
	}

	@Test
	public void testSetEvolutionState() {
		// 
		IGameTimer gameTimer = new GameTimer();
		gameTimer.setEvolutionState(true, null);
		assertEquals(gameTimer.getEvolutionState(), false);
	}

}
