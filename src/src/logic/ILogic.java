package logic;

import map.IMap;

/**
 * 游戏逻辑接口
 */
public interface ILogic {

    int MAX_COUNT = 250; // 最大游戏演化次数

    /**
     * 设置处理的地图对象
     * @param map 地图对象
     */
    void setMap(IMap map);

    /**
     * 初始化地图
     */
    void initMap();

    /**
     * 更新游戏地图
     */
    void updateMap();

    /**
     * 判断游戏是否可以结束
     * @return 若游戏可以结束，则返回true；否则，返回false
     */
    boolean isEnd();
}
