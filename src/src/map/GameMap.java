package map;

/**
 * 游戏地图
 */
public class GameMap implements IMap{

    private int nRows; // 行数
    private int nCols; // 列数
    private boolean[][] cells; //

    public GameMap(){
        nRows = IMap.DEFAULT_ROWS;
        nCols = IMap.DEFAULT_COLS;
        cells = new boolean[nRows][nCols];
    }

    public GameMap(int nRows, int nCols){
        this.setRows(nRows);
        this.setCols(nCols);
        cells = new boolean[this.nRows][this.nCols];
    }

    /**
     * @return 地图元素行数
     */
    @Override
    public int getRows() {
        return this.nRows;
    }

    @Override
    public void setRows(int rows) {
        if(rows > 0){
            this.nRows = rows;
        } else{
            this.nRows = IMap.DEFAULT_ROWS;
        }
    }

    /**
     * @return 地图元素列数
     */
    @Override
    public int getCols() {
        return this.nCols;
    }

    @Override
    public void setCols(int cols) {
        if(cols > 0){
            this.nCols = cols;
        } else{
            this.nCols = IMap.DEFAULT_COLS;
        }
    }

    /**
     * 获取指定坐标的细胞的状态
     * 超出地图范围的默认为死细胞
     * @param row 行坐标
     * @param col 列坐标
     * @return 若为活细胞，返回true; 否则，返回false
     */
    @Override
    public boolean getCellState(int row, int col) {
        if(row < 0 || row >= this.nRows) return false;
        if(col < 0 || col >= this.nCols) return false;
        return cells[row][col];
    }

    /**
     * 设置指定坐标的细胞的状态
     *
     * @param row   行坐标
     * @param col   列坐标
     * @param state 细胞的新状态：若为活细胞，则为true
     */
    @Override
    public void setCellState(int row, int col, boolean state) {
        if(row >= 0 && row < this.nRows
        && col >= 0 && col < this.nCols){
            cells[row][col] = state;
        }
    }

    /**
     * 按照行数和列数，重新构造地图
     */
    @Override
    public void reCreateMap() {
        if(cells.length != this.nRows
        || cells[0].length != this.nRows){
            cells = new boolean[this.nRows][this.nCols];
        }
    }
}
