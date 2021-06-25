package ui;

import controller.IGameController;
import timer.IGameTimerTask;

/**
 * 游戏窗口
 */
public interface IGameGUI extends IGameTimerTask{

	/**
	 * 设置是否显示GUI
	 * @param show 若为true，则显示
	 */
    void setShowGUI(boolean show);
    
    /**
     * 设置游戏控制类
     * @param gameController
     */
    void setGameController(IGameController gameController);
}
