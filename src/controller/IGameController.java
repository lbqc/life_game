package controller;

import logic.ILogic;
import map.IMap;
import timer.IGameTimer;
import ui.IGameGUI;

/**
 * 游戏控制类接口
 * 世界上也是和UI交互的接口
 * 向其中添加UI的每个功能对应的方法
 */
public interface IGameController {
	
	/**
     * 重新设置地图的大小
     * @param rows 行数
     * @param cols 列数
     */
	void setMapSize(int rows, int cols);
	
	/**
     * 设置演化次数
     * @param evolutionTimes 演化次数
     */
	void setEvolutionTimes(int evolutionTimes);
	
	/**
	 * 开始演化
	 */
	void start();
	
	/**
	 * 停止演化
	 */
	void stop();
	
	/**
	 * 初始化地图
	 */
	void initMap();
	
	/**
	 * 获取游戏地图
	 * @return 游戏地图
	 */
	IMap getMap();
	
	/**
	 * 获取游戏逻辑
	 * @return 游戏逻辑
	 */
	ILogic getLogic();
	
	/**
	 * 获取游戏时钟
	 * @return 游戏时钟
	 */
	IGameTimer getGameTimer();
	
	/**
	 * 获得gameGUI
	 * @return 游戏UI
	 */
	IGameGUI getGameGUI();
	
	/**
     * 获取当前的演化状态
     * @return 若正在演化中，返回true
     */
    boolean getEvolutionState();
}
