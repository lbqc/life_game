package logic;

import map.IMap;

import java.util.Arrays;
import java.util.Random;

/**
 * 游戏逻辑类
 */
public class GameLogic implements ILogic{

    private int[][] aroundLives; // 周围的活细胞的数目
    
    public GameLogic() {
    	
    }

    /**
     * 初始化地图
     * @param map 地图
     */
    @Override
    public void initMap(IMap map) {
    	if(map == null) {
    		// 可增加异常处理
    		return;
    	}
        Random random = new Random(); // 随机种子
        double numberRandomDouble; // 存储随机数
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
            	// 产生[0.0, 1.0)之间的随机数
                numberRandomDouble = random.nextDouble();
                // 小于概率，则生成活细胞
                map.setCellState(row, col, numberRandomDouble < ILogic.PROBABILITY);
            }
        }
    }

    /**
     * 更新游戏地图
     * @param map 地图
     */
    @Override
    public void updateMap(IMap map) {
    	if(map == null) {
    		// 可增加异常处理
    		return;
    	}
        // 初始化aroundLives数组
        this.initAroundLives(map);
        int rows = map.getRows();
        int cols = map.getCols();
        // 求取周围活细胞数目
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (map.getCellState(row, col)) {
                	// 若该细胞为活细胞，增加它周围细胞的周围活细胞数目
                	this.increaseLivesNumberAroundIt(row, col);
                }
            }
        }
        int num;
        // 更新地图中的细胞的状态
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                num = getAroundLives(row, col);
                if (num < 2) {
                    // 周围活细胞数目小于2，死细胞
                    map.setCellState(row, col, false);
                } else if(num == 3){
                    // 周围有三个活细胞，变成活细胞
                    map.setCellState(row,col,true);
                } else if(num > 3){
                    // 三个以上细胞，变成死细胞
                    map.setCellState(row, col, false);
                }
            }
        }
    }

    /**
     * 判断游戏是否可以结束
     * 拓展功能，王国周的任务
     * @param map 地图
     * @return 若游戏可以结束，则返回true；否则，返回false
     */
    @Override
    public boolean isEnd(IMap map) {
        return false;
    }

    /**
     * 获取指定坐标的细胞的周围活细胞的数目
     *
     * @param row 行坐标
     * @param col 列坐标
     * @return 周围的活细胞的数目，若坐标越界，则返回0
     */
    public int getAroundLives(int row, int col){
        if(row < 0 || row >= this.aroundLives.length) return 0;
        if(col < 0 || col >= this.aroundLives[row].length) return 0;
        return this.aroundLives[row][col];
    }
    
    /**
     * 初始化aroundLives数组
     * @param map 游戏地图
     */
    public void initAroundLives(IMap map) {
    	// 根据map的实际大小，分配aroundLives空间大小
    	if(this.aroundLives == null 
    			|| this.aroundLives.length < map.getMapCapacityOfSqrt()) {
    		this.aroundLives = new int[map.getMapCapacityOfSqrt()][map.getMapCapacityOfSqrt()];
    	}
    	// 初始化每个活细胞周围的活细胞数目为0
    	for (int[] aroundLive : this.aroundLives) {
            Arrays.fill(aroundLive, 0);
        }
    }
    
    /**
     * 若该细胞为活细胞，它周围的细胞的aroundLives增加1
     * @param row 活细胞的行坐标
     * @param col 活细胞的列坐标
     */
    public void increaseLivesNumberAroundIt(int row, int col) {
    	int rowStart = row - 1 >= 0 ? row - 1 : row;
    	int rowEnd = row + 1 < this.aroundLives.length ? row + 1 : row;
    	int colStart = col - 1 >= 0 ? col - 1 : col;
    	int colEnd = col + 1 < this.aroundLives[rowEnd].length ? col + 1 : col;
    	// 自己的周围活细胞数目不会增加，所以需要减一
    	this.aroundLives[row][col]--;
    	for(row = rowStart; row <= rowEnd; row++) {
    		for(col = colStart; col <= colEnd; col++) {
    			// 最多九个细胞的aroundLives都加一
    			this.aroundLives[row][col]++;
    		}
    	}
    }

	@Override
	public void update(IMap map) {
		this.updateMap(map);	
	}
	
	public void printAroudLives(IMap map) {
		for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
            	System.out.print(this.aroundLives[row][col] + "\t");
            }
            System.out.println();
		}
	}
       
}
