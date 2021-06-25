package logic;

import map.IMap;
import timer.IGameTimerTask;

/**
 * 游戏逻辑接口
 */
public interface ILogic extends IGameTimerTask{

    double PROBABILITY = 0.2D; // 生成活细胞的概率

    /**
     * 初始化地图
     * @param map 地图
     */
    void initMap(IMap map);

    /**
     * 更新游戏地图
     * @param map 地图
     */
    void updateMap(IMap map);

    /**
     * 判断游戏是否可以结束
     * 拓展功能
     * @param map 地图
     * @return 若游戏可以结束，则返回true；否则，返回false
     */
    boolean isEnd(IMap map);
}
