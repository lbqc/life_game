package map;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class GameMapTest {
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetRows() {
		// fail("Not yet implemented");
		// map的row值为(0, n)
		// 小于等于0时，变成默认值
		IMap map = new GameMap();
		map.setRows(0);
		assertEquals(map.getRows(), IMap.DEFAULT_ROWS);
		map.setRows(1);
		assertEquals(map.getRows(), 1);
	}

	@Test
	public void testSetCols() {
		// fail("Not yet implemented");
		IMap map = new GameMap();
		map.setCols(0);
		assertEquals(map.getCols(), IMap.DEFAULT_COLS);
		map.setCols(1);
		assertEquals(map.getCols(), 1);
	}

	@Test
	public void testSetMapSize() {
		// fail("Not yet implemented");
		IMap map = new GameMap();
		map.setMapSize(0, 0);
		assertEquals(map.getCols(), IMap.DEFAULT_COLS);
		assertEquals(map.getRows(), IMap.DEFAULT_ROWS);
		map.setMapSize(1, 1);
		assertEquals(map.getRows(), 1);
		assertEquals(map.getCols(), 1);
	}

	@Test
	public void testGetMapCapacityOfSqrt() {
		// fail("Not yet implemented");
		IMap map = new GameMap();
		assertEquals(map.getMapCapacityOfSqrt(), Math.max(IMap.DEFAULT_COLS, IMap.DEFAULT_ROWS));
		map.setMapSize(100, 100);
		assertEquals(map.getMapCapacityOfSqrt(), 100);
		map.setMapSize(50, 50);
		assertEquals(map.getMapCapacityOfSqrt(), 100);
		map.setMapSize(0, 0);
		assertEquals(map.getMapCapacityOfSqrt(), 100);
	}

	@Test
	public void testSetCellState() {
		// fail("Not yet implemented");
		IMap map = new GameMap();
		// 初始化
		for(int row = 0; row < map.getRows(); row++) {
			for(int col = 0; col < map.getCols(); col++) {
				map.setCellState(row, col, true);
			}
		}
		assertEquals(map.getCellState(0, 0), true);
		map.setCellState(-1, 0, true);
		assertEquals(map.getCellState(-1, 0), false);
		map.setCellState(0, -1, true);
		assertEquals(map.getCellState(0, -1), false);
		
	}
}
