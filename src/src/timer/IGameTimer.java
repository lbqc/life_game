package timer;

import logic.ILogic;
import ui.IGameGUI;

/**
 * 游戏计时器
 * 用于以固定的频率刷新游戏地图
 */
public interface IGameTimer {

    long DEFAULT_DELAY_TIME = 200l;
    long DEFAULT_PERIOD_TIME = 500l;

    void setGameLogic(ILogic logic);
    void setGameGUI(IGameGUI gameGUI);

    /**
     * 设置演化次数
     * @param steps 演化次数
     */
    void setSteps(int steps);

    int getSteps();

    /**
     * 继续演化
     */
    void process();

    /**
     * 停止演化
     */
    void stop();
}
