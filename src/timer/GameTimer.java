package timer;

import map.IMap;

import java.util.ArrayList;
import java.util.Timer;

/**
 * 游戏计时器
 * 可以使用TimerTask的cancel()方法
 */
public class GameTimer implements IGameTimer{
	
	private Timer timer; // 计时器
	private UpdateTimerTask timertask; // 计时器任务
	private ArrayList<IGameTimerTask> tasks;  // IGameTimer处理对象
	private int evolutionTimes;
	private boolean isEvolution;
	
	public GameTimer() {
		timer = new Timer();
		tasks = new ArrayList<>();
		evolutionTimes = IGameTimer.DEFAULT_MAX_EVOLUTION_TIMES;
	}

	@Override
	public void start(IMap map) {
		// TODO Auto-generated method stub
		if(map == null) return;
		if(this.evolutionTimes == 1) {
			this.evolutionOne(map);
		} else {
			this.timertask = new UpdateTimerTask(tasks, map, this);
			this.timer.schedule(timertask, IGameTimer.DEFAULT_DELAY_TIME, IGameTimer.DEFAULT_PERIOD_TIME);
			isEvolution = true;
		}
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		if(isEvolution) {
			this.timertask.cancel();
			this.timer.purge();
			isEvolution = false;
		}
	}

	@Override
	public void setEvolutionTimes(int evolutionTimes) {
		// TODO Auto-generated method stub
		if(evolutionTimes > 0) {
			this.evolutionTimes = evolutionTimes;
		}
	}

	@Override
	public int getEvolutionTimes() {
		// TODO Auto-generated method stub
		return this.evolutionTimes;
	}

	@Override
	public void addGameTimerTask(IGameTimerTask task) {
		// TODO Auto-generated method stub
		this.tasks.add(task);
	}
	
	public void evolutionOne(IMap map) {
		for(IGameTimerTask task : tasks) {
			// 执行每个计时器任务
			if(task != null) {
				task.update(map);
			}
		}
		
	}

	@Override
	public void setEvolutionState(boolean isEvolution, UpdateTimerTask sender) {
		// TODO Auto-generated method stub
		if(sender != null && sender.getGameTimer().equals(this)){
			this.isEvolution = isEvolution;
		}
	}

	@Override
	public boolean getEvolutionState() {
		// TODO Auto-generated method stub
		return this.isEvolution;
	}
	
}
