package controller;

import logic.GameLogic;
import logic.ILogic;
import map.GameMap;
import map.IMap;
import timer.GameTimer;
import timer.IGameTimer;
import ui.GameGUI;
import ui.IGameGUI;

/**
 * 游戏控制类
 * 也是和UI交互的接口
 */
public class GameController implements IGameController{
	
	private IMap map; // 游戏地图
	private ILogic logic; // 游戏逻辑
	private IGameTimer gameTimer; // 游戏计时器
	private IGameGUI gameGUI; // 游戏UI
	
	public GameController() {
		this.map = new GameMap();
		this.logic = new GameLogic();
		this.gameTimer = new GameTimer();
		this.gameGUI = new GameGUI();
		
		this.gameTimer.addGameTimerTask(logic);
		this.gameTimer.addGameTimerTask(gameGUI);
		this.gameGUI.setGameController(this);
	}

	@Override
	public void setMapSize(int rows, int cols) {
		// TODO Auto-generated method stub
		this.map.setMapSize(rows, cols);
	}

	@Override
	public void setEvolutionTimes(int evolutionTimes) {
		// TODO Auto-generated method stub
		this.gameTimer.setEvolutionTimes(evolutionTimes);
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		this.gameTimer.start(map);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.gameTimer.stop();
	}

	@Override
	public void initMap() {
		// TODO Auto-generated method stub
		this.logic.initMap(map);
		this.gameGUI.update(map);
	}

	@Override
	public IMap getMap() {
		// TODO Auto-generated method stub
		return this.map;
	}

	@Override
	public ILogic getLogic() {
		// TODO Auto-generated method stub
		return this.logic;
	}

	@Override
	public IGameTimer getGameTimer() {
		return this.gameTimer;
	}
	
	
	
	@Override
	public IGameGUI getGameGUI() {
		// TODO Auto-generated method stub
		return this.gameGUI;
	}
	

	/**
     * 获取当前的演化状态
     * @return 若正在演化中，返回true
     */
	@Override
	public boolean getEvolutionState() {
    	return this.gameTimer.getEvolutionState();
	}

	public static void main(String[] args) {
		IGameController game = new GameController();
		game.getGameGUI().setShowGUI(true);
	}
}
