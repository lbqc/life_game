package timer;

import map.IMap;

/**
 * 游戏计时器
 * 用于以固定的频率刷新游戏地图
 * 
 */
public interface IGameTimer {

    long DEFAULT_DELAY_TIME = 200L;  // 演化开始前置时间
    long DEFAULT_PERIOD_TIME = 500L;  // 计时器周期
    int DEFAULT_MAX_EVOLUTION_TIMES = 250; // 最大演化次数
    

    /**
     * 计时器开始
     * @param map 游戏地图
     */
    void start(IMap map);

    /**
     * 停止演化
     */
    void stop();
    
    /**
     * 设置演化次数
     * @param evolutionTimes 演化次数
     */
    void setEvolutionTimes(int evolutionTimes);
    
    int getEvolutionTimes();
    
    /**
     * 增加刷新任务
     * @param task 刷新任务
     */
    void addGameTimerTask(IGameTimerTask task);
    
    /**
     * 设置当前是否在演化中
     * @param isEvolution
     * @param sender，事件的发起者，用来校验
     */
    void setEvolutionState(boolean isEvolution, UpdateTimerTask sender);
    
    /**
     * 获取当前的演化状态
     * @return 若正在演化中，返回true
     */
    boolean getEvolutionState();
}
