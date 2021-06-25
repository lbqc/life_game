package logic;

import map.IMap;

import java.util.Arrays;
import java.util.Random;

/**
 * 游戏逻辑类
 */
public class GameLogic implements ILogic{

    private IMap map; // 处理的游戏地图对象
    private int[][] aroundLives; // 周围的活细胞的数目
    private int count; // 游戏演化次数

    public GameLogic(IMap map){
        this.setMap(map);
    }

    /**
     * 设置处理的地图对象
     *
     * @param map 地图对象
     */
    @Override
    public void setMap(IMap map) {
        this.map = map;
        this.aroundLives = new int[map.getRows()][map.getCols()];
    }

    /**
     * 初始化地图
     */
    @Override
    public void initMap() {

        // 判断游戏地图是否为空
        if(this.map == null){
            // 异常处理
            return;
        }
        Random random = new Random();
        int temp;
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                temp = random.nextInt(10);
                map.setCellState(row, col, temp > 7);
            }
        }
    }

    /**
     * 更新游戏地图
     */
    @Override
    public void updateMap() {
        // 求取每个细胞周围的活细胞数目
        // 初始化
        if(map.getRows() != this.aroundLives.length
                || map.getCols() != this.aroundLives[0].length){
            reCreateAliveCellArray(map.getRows(), map.getCols());
        }
        for (int[] aroundLive : this.aroundLives) {
            Arrays.fill(aroundLive, 0);
        }
        // 求取周围活细胞数目
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                if (map.getCellState(row, col)) {
                    this.addAroundLives(row-1, col-1);
                    this.addAroundLives(row, col-1);
                    this.addAroundLives(row-1, col);
                    this.addAroundLives(row+1, col+1);
                    this.addAroundLives(row, col+1);
                    this.addAroundLives(row+1, col);
                    this.addAroundLives(row+1, col-1);
                    this.addAroundLives(row-1,col+1);
                }
            }
        }
        int num;
        // 更新地图中的细胞的状态
        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getCols(); col++) {
                // System.out.print(this.getAroundLives(row, col) + "\t");
                // 周围活细胞数目超过3，成为死细胞
                num = getAroundLives(row, col);
                if (num < 2) {
                    // 周围活细胞数目小于2，死细胞
                    this.map.setCellState(row, col, false);
                } else if(num == 3){
                    // 周围有三个活细胞，变成活细胞
                    this.map.setCellState(row,col,true);
                }
                else if(num > 3){
                    // 三个以上细胞，变成死细胞
                    this.map.setCellState(row, col, false);
                }
            }
            // System.out.println("");
        }
        this.count++;
    }

    /**
     * 判断游戏是否可以结束
     *
     * @return 若游戏可以结束，则返回true；否则，返回false
     */
    @Override
    public boolean isEnd() {
        // 条件一：演化次数超过最大次数
        if(this.count > ILogic.MAX_COUNT){
            return true;
        }

        return false;
    }

    /**
     * 增加一个细胞的周围活细胞数目
     * @param row 行坐标
     * @param col 列坐标
     */
    private void addAroundLives(int row, int col){
        if(row >= 0 && row < this.aroundLives.length
        && col >= 0 && col < this.aroundLives[row].length){
            this.aroundLives[row][col]++;
        }
    }

    /**
     * 获取指定坐标的细胞的周围活细胞的数目
     *
     * @param row 行坐标
     * @param col 列坐标
     * @return 周围的活细胞的数目，若坐标越界，则返回0
     */
    private int getAroundLives(int row, int col){
        if(row < 0 || row >= this.aroundLives.length) return 0;
        if(col < 0 || col >= this.aroundLives[row].length) return 0;
        return this.aroundLives[row][col];
    }

    /**
     * 重建周围活细胞数组
     * @param rows 行数
     * @param cols 列数
     */
    private void reCreateAliveCellArray(int rows, int cols){
        this.aroundLives = new int[rows][cols];
    }
}
