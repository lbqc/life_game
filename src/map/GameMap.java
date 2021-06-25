package map;

/**
 * 游戏地图
 */
public class GameMap implements IMap{

	private int capacityOfSqrt;  // 地图实际大小的平方根
    private int nRows; // 行数
    private int nCols; // 列数
    private boolean[][] cells; // 逻辑地图

    public GameMap(){
        nRows = IMap.DEFAULT_ROWS;
        nCols = IMap.DEFAULT_COLS;
        this.capacityOfSqrt = Math.max(nRows, nCols);
        cells = new boolean[this.capacityOfSqrt][this.capacityOfSqrt];
    }

    public GameMap(int nRows, int nCols){
        this.nRows = nRows;
        this.nCols = nCols;
        this.capacityOfSqrt = Math.max(nRows, nCols);
        cells = new boolean[this.capacityOfSqrt][this.capacityOfSqrt];
    }

    /**
     * @return 地图元素行数
     */
    @Override
    public int getRows() {
        return this.nRows;
    }
    
    /**
     * 设置地图的行数
     * @param rows (0, n]
     */
    @Override
    public void setRows(int rows) {
        if(rows > 0){
            this.nRows = rows;
        } else{
            this.nRows = IMap.DEFAULT_ROWS;
        }
        this.reCreateMap();
    }

    /**
     * @return 地图元素列数
     */
    @Override
    public int getCols() {
        return this.nCols;
    }

	/**
     * 设置地图的列数
     * @param cols (0, n]
     */
	@Override
    public void setCols(int cols) {
        if(cols > 0){
            this.nCols = cols;
        } else{
            this.nCols = IMap.DEFAULT_COLS;
        }
        this.reCreateMap();
    }

	/**
     * 重新设置地图的大小
     * @param rows 行数
     * @param cols 列数
     */
	@Override
	public void setMapSize(int rows, int cols) {
		this.setRows(rows);
		this.setCols(cols);	
	}

    /**
     * 获得地图实际内存大小的平方根
     * 因为地图实际形状是正方形
     * @return 地图实际大小的平方根
     */
	@Override
	public int getMapCapacityOfSqrt() {
		return this.capacityOfSqrt;
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
     * 地图的实际大小小于行数或者列数的平方时
     * 重新构造地图大小
     * 即只有逻辑地图的边长大于实际地图时，才会重新分配地图的空间
     */
    public void reCreateMap() {
        int maxLength = Math.max(this.nRows, this.nCols);
        if(maxLength > this.capacityOfSqrt) {
        	this.capacityOfSqrt = maxLength;
        	this.cells = new boolean[this.capacityOfSqrt][this.capacityOfSqrt];
        }
    }

    
    
}
