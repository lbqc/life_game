package timer;

import logic.ILogic;
import ui.IGameGUI;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 游戏计时器
 */
public class GameTimer implements IGameTimer{

    private ILogic logic;
    private IGameGUI gameGUI;
    private int steps;
    private Timer timer; // 计时器类
    private int count;
    private UpdateTask updateTask;

    public GameTimer(ILogic logic, IGameGUI gameGUI){
        this.setGameGUI(gameGUI);
        this.setSteps(ILogic.MAX_COUNT);
        this.setGameLogic(logic);
    }

    @Override
    public void setGameLogic(ILogic logic) {
        this.logic = logic;
    }

    @Override
    public void setGameGUI(IGameGUI gameGUI) {
        this.gameGUI = gameGUI;
    }

    /**
     * 设置演化次数
     *
     * @param steps 演化次数
     */
    @Override
    public void setSteps(int steps) {
        if(steps > 0)
            this.steps = steps;
        else
            this.steps = ILogic.MAX_COUNT;
    }

    /**
     * 继续演化
     */
    @Override
    public void process() {
        if(this.steps == 1){
            processOnce();
            return;
        }
        count = 0;
        this.timer = new Timer();
        this.updateTask = new UpdateTask();
        timer.schedule(updateTask, IGameTimer.DEFAULT_DELAY_TIME, IGameTimer.DEFAULT_PERIOD_TIME);
    }

    /**
     * 停止演化
     */
    @Override
    public void stop() {
        timer.cancel();
    }

    @Override
    public int getSteps() {
        return this.steps;
    }

    private void processOnce(){
        logic.updateMap();
        gameGUI.reShow();
    }

    /**
     * 更新地图的时钟任务
     */
    private class UpdateTask extends TimerTask{
        /**
         * The action to be performed by this timer task.
         */
        @Override
        public void run() {
            logic.updateMap();
            gameGUI.reShow();
            count++;
            if(count >= steps){
                timer.cancel();
            }
        }
    }
}
