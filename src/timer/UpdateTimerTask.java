package timer;

import java.util.ArrayList;
import java.util.TimerTask;

import map.IMap;

/**
 * 计时器任务
 * Timer的处理对象
 */
public class UpdateTimerTask extends TimerTask{
	
	private ArrayList<IGameTimerTask> tasks;  // 游戏计时器任务
	private IMap map; // 游戏地图
	private IGameTimer gameTimer; // 游戏计时器
	private int count; // 记录演化次数
	
	public UpdateTimerTask(ArrayList<IGameTimerTask> tasks, IMap map, IGameTimer gameTimer) {
		this.tasks = tasks;
		this.map = map;
		this.gameTimer = gameTimer;
	}

	@Override
	public void run() {
		if(gameTimer.getEvolutionTimes() == count) {
			// 满足演化次数，任务结束
			this.cancel();
			this.gameTimer.setEvolutionState(false, this);
		}
		for(IGameTimerTask task : tasks) {
			// 执行每个计时器任务
			if(task != null) {
				task.update(map);
			}
		}
		count++;
	}
	
	public IGameTimer getGameTimer() {
		return this.gameTimer;
	}
}
