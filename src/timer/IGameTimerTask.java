package timer;

import map.IMap;

/**
 * 游戏计时器任务
 * IGameTimer的处理对象接口
 * 含有随IGameTimer刷新的方法的类必须实现
 */
public interface IGameTimerTask {

	/**
	 * 随IGameTimer进行刷新的内容放在这个方法里
	 * @param map 游戏地图
	 */
	public void update(IMap map);
}
