package map;

/**
 * 游戏地图接口
 *
 */
public interface IMap {
	
    int DEFAULT_ROWS = 20;  // 默认地图行数
    int DEFAULT_COLS = 20;  // 默认地图列数

    /**
     *
     * @return 地图元素行数
     */
    int getRows();

    /**
     * 设置地图的行数
     * @param rows (0, n]
     */
    void setRows(int rows);

    /**
     *
     * @return 地图元素列数
     */
    int getCols();

    /**
     * 设置地图的列数
     * @param cols (0, n]
     */
    void setCols(int cols);
    
    /**
     * 重新设置地图的大小
     * @param rows 行数
     * @param cols 列数
     */
    void setMapSize(int rows, int cols);
    
    /**
     * 获得地图实际内存大小的平方根
     * 因为地图实际形状是正方形
     * @return 地图实际大小的平方根
     */
    int getMapCapacityOfSqrt();

    /**
     * 获取指定坐标的细胞的状态
     * @param row 行坐标
     * @param col 列坐标
     * @return 若为活细胞，返回true; 否则，返回false
     */
    boolean getCellState(int row, int col);

    /**
     * 设置指定坐标的细胞的状态
     * @param row 行坐标
     * @param col 列坐标
     * @param state 细胞的新状态：若为活细胞，则为true
     */
    void setCellState(int row, int col, boolean state);
    
}
