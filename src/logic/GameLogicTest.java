package logic;

import static org.junit.Assert.*;

import org.junit.Test;

import map.GameMap;
import map.IMap;

public class GameLogicTest {

	/**
	 * 测试GameLogic的initMap()方法
	 * initMap()按照一定的概率随机生成活细胞
	 */
	@Test
	public void testInitMap() {
		IMap map = new GameMap(); // 游戏地图
		ILogic logic = new GameLogic(); // 游戏逻辑
		int count = 0; // 活细胞数量
		int allCount = map.getCols() * map.getRows();  // 地图总大小
		logic.initMap(map);
		for(int row = 0; row < map.getRows(); row++) {
			for(int col = 0; col < map.getCols(); col++) {
				if(map.getCellState(row, col)) {
					// 统计活细胞数量
					count++;
				}
			}
		}
		double probability = count*1.0 / allCount; // 当前生成活细胞的概率
		System.out.println(probability);
		// 属于指定值+-0.1即正确
		assertEquals(probability < ILogic.PROBABILITY + 0.1 
				&& probability > ILogic.PROBABILITY - 0.1, true);
		// 当地图为null的时候
		logic.initMap(null);
	}

	/**
	 * 测试ILogic的updateMap()方法
	 */
	@Test
	public void testUpdateMap() {
		IMap map = new GameMap(); // 游戏地图
		ILogic logic = new GameLogic(); // 游戏逻辑
		// (0, 0) 为死细胞，且周围有3个活细胞
		map.setCellState(0, 0, false);
		map.setCellState(0, 1, true);
		map.setCellState(1, 0, true);
		map.setCellState(1, 1, true);
		// (row, col)为活细胞，且周围有2个活细胞
		int row = map.getRows()-1;
		int col = map.getCols()-1;
		map.setCellState(row, col, true);
		map.setCellState(row-1, col, true);
		map.setCellState(row, col-1, true);
		// (1, col)为活细胞，且周围有4个活细胞
		map.setCellState(1, col, true);
		map.setCellState(0, col, true);
		map.setCellState(1, col-1, true);
		map.setCellState(0, col-1, true);
		map.setCellState(2, col, true);
		// (row, 0)为死细胞，且周围有2个死细胞
		map.setCellState(row, 0, false);
		map.setCellState(row-1, 0, true);
		map.setCellState(row+1, 0, true);
		
		logic.update(map);
		// (0,0)活
		assertEquals(map.getCellState(0, 0), true);
		// (row, col)活
		assertEquals(map.getCellState(row, col), true);
		// (1, col)死
		assertEquals(map.getCellState(1, col), false);
		// (row, 0)死
		assertEquals(map.getCellState(row, 0), false);
	}

}
