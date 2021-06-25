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

    void setRows(int rows);

    /**
     *
     * @return 地图元素列数
     */
    int getCols();

    void setCols(int cols);

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

    /**
     * 按照行数和列数，重新构造地图
     */
    void reCreateMap();
}
