package ui;

import logic.GameLogic;
import logic.ILogic;
import map.GameMap;
import map.IMap;
import timer.GameTimer;
import timer.IGameTimer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 游戏窗口
 */
public class GameGUI extends JFrame implements IGameGUI {

    public static int GAME_AREA_WIDTH = 600; // 游戏区域的宽度
    public static int GAME_AREA_HEIGHT = 600; // 游戏区域的高度
    public static int CONTROL_AREA_WIDTH = 200; // 控制区域宽度
    public static int CONTROL_AREA_HEIGHT = 600; // 控制区域高度
    public static int TITLE_FRAME_HEIGHT = 50; // 标题框的大小
    public static int CONTROL_ELEM_START_X = 25; //
    public static int CONTROL_ELEM_START_Y = 20;
    public static int CONTROL_ELEM_HEIGHT = 30;

    private IMap map; // 游戏地图
    private ILogic logic; // 游戏逻辑
    private IGameTimer gameTimer; // 游戏计时器
    private Graphics graphics; // 绘图工具
    private boolean state;
    private int x;
    private int y;

    private JPanel gamePanel; // 游戏panel
    private JPanel controlPanel; // 控制画板
    private JLabel rowLbl; // 行数
    private JLabel colLbl; // 列数
    private JTextField rowTF; // 行数文本框
    private JTextField colTF; // 列数文本框
    private JButton modifyBtn; // 修改行列数的按钮
    private JLabel stepsLbl; // 演化步数
    private JTextField stepsTF; // 演化步数文本
    private JButton modifyStepBtn; // 确认修改步数按钮
    private JButton processBtn; // 继续演化按钮
    private JButton stopBtn; // 停止演化按钮
    private JButton initBtn; // 随机初始化按钮

    public GameGUI(){
        super("生命游戏");

        this.map = new GameMap();
        this.logic = new GameLogic(this.map);
        this.gameTimer = new GameTimer(logic, this);

        this.setLayout(null);
        // 游戏区域
        gamePanel = new GamePanel(this.map);
        gamePanel.setSize(GameGUI.GAME_AREA_WIDTH, GameGUI.GAME_AREA_HEIGHT);
        gamePanel.setLocation(0,0);
        gamePanel.addMouseListener(new GamePanelMouseListener());
        this.add(gamePanel);

        // 控制区域
        controlPanel = new JPanel();
        controlPanel.setSize(GameGUI.CONTROL_AREA_WIDTH, GameGUI.CONTROL_AREA_HEIGHT);
        controlPanel.setLocation(GameGUI.GAME_AREA_WIDTH, 0);
        controlPanel.setLayout(null);
        initControlPanel();
        this.add(controlPanel);

        // 整个界面
        this.setSize(this.gamePanel.getWidth() + this.controlPanel.getWidth(),
                this.gamePanel.getHeight()+GameGUI.TITLE_FRAME_HEIGHT);
        this.setLocationRelativeTo(null); // 屏幕中间显示
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 关闭窗口
        this.setResizable(false); // 不能调整窗口的大小
        this.setVisible(true);

        x = this.getX();
        y = this.getY() + 26;

        graphics = gamePanel.getGraphics();
        gamePanel.paint(graphics);
        notProcessState();
    }

    /**
     * 重新展示游戏地图
     */
    @Override
    public void reShow() {
        gamePanel.paint(graphics);
    }

    /**
     * 初始化控制画板区域
     */
    private void initControlPanel(){
        // 行数label
        rowLbl = new JLabel("行数：");
        rowLbl.setSize(50,30);
        rowLbl.setLocation(CONTROL_ELEM_START_X, 0);
        controlPanel.add(rowLbl);
        // 行数text field
        rowTF = new JTextField();
        rowTF.setSize(60,20);
        rowTF.setLocation(80,5);
        rowTF.setText(""+IMap.DEFAULT_ROWS);
        controlPanel.add(rowTF);
        // 列数label
        colLbl = new JLabel("列数：");
        colLbl.setSize(50,30);
        colLbl.setLocation(CONTROL_ELEM_START_X,CONTROL_ELEM_HEIGHT*1);
        controlPanel.add(colLbl);
        // 列数text field
        colTF = new JTextField();
        colTF.setSize(60,20);
        colTF.setLocation(80,35);
        colTF.setText(""+IMap.DEFAULT_COLS);
        controlPanel.add(colTF);
        // 确认修改行列数button
        modifyBtn = new JButton("修改行列数");
        modifyBtn.setSize(120,30);
        modifyBtn.setLocation(CONTROL_ELEM_START_X,60);
        modifyBtn.addActionListener(new ModifyButtonActionListener());
        controlPanel.add(modifyBtn);
        // steps label
        stepsLbl = new JLabel("演化次数");
        stepsLbl.setSize(50,30);
        stepsLbl.setLocation(CONTROL_ELEM_START_X, 120);
        controlPanel.add(stepsLbl);
        // steps text field
        stepsTF = new JTextField();
        stepsTF.setSize(60, 20);
        stepsTF.setLocation(80,125);
        stepsTF.setText("" + ILogic.MAX_COUNT);
        controlPanel.add(stepsTF);
        // 修改演化次数 button
        modifyStepBtn = new JButton("修改演化次数");
        modifyStepBtn.setSize(120,30);
        modifyStepBtn.setLocation(CONTROL_ELEM_START_X, 150);
        modifyStepBtn.addActionListener(new ModifyStepsButtonActionListener());
        controlPanel.add(modifyStepBtn);
        // 继续演化按钮
        processBtn = new JButton("继续演化");
        processBtn.setSize(120,30);
        processBtn.setLocation(CONTROL_ELEM_START_X, 210);
        processBtn.addActionListener(new ProcessButtonActionListener());
        controlPanel.add(processBtn);
        // 停止演化按钮
        stopBtn = new JButton("停止演化");
        stopBtn.setSize(120,30);
        stopBtn.setLocation(CONTROL_ELEM_START_X, 250);
        stopBtn.addActionListener(new StopButtonActionListener());
        controlPanel.add(stopBtn);
        // 随机初始化按钮
        initBtn = new JButton("随机初始化");
        initBtn.setSize(120,30);
        initBtn.setLocation(CONTROL_ELEM_START_X, 290);
        initBtn.addActionListener(new InitButtonActionListener());
        controlPanel.add(initBtn);
    }

    private void processState(){
        modifyBtn.setEnabled(false);
        modifyStepBtn.setEnabled(false);
        processBtn.setEnabled(false);
        stopBtn.setEnabled(true);
        initBtn.setEnabled(false);
        state = true;
    }

    private void notProcessState(){
        modifyBtn.setEnabled(true);
        modifyStepBtn.setEnabled(true);
        processBtn.setEnabled(true);
        stopBtn.setEnabled(false);
        initBtn.setEnabled(true);
        state = false;
    }

    /**
     * 游戏区域的画板
     * 重写了paint()方法
     */
    private class GamePanel extends JPanel{

        private IMap map;  // 游戏地图
        private int cellHeight;  // 一个细胞的长度
        private int cellWidth;  // 一个细胞的宽度
        private int xStart; // 开始绘图的横坐标
        private int yStart; // 开始绘图的纵坐标

        public GamePanel(IMap map){
            super();
            this.map = map;

            xStart = 0;
            yStart = 0;
        }

        @Override
        public void paint(Graphics g){
            this.cellHeight = GameGUI.GAME_AREA_HEIGHT / map.getRows();
            this.cellWidth = GAME_AREA_WIDTH / map.getCols();
            // 在游戏区绘制图像
            for(int row = 0; row < map.getRows(); row++){
                for(int col = 0; col < map.getCols(); col++){
                    if(map.getCellState(row, col)){
                        // 如果为活细胞
                        g.setColor(Color.BLACK);
                        g.fillRect(xStart+col*cellWidth, yStart+row*cellHeight, cellWidth, cellHeight);
                        g.setColor(Color.WHITE);
                        g.drawRect(xStart+col*cellWidth, yStart+row*cellHeight, cellWidth, cellHeight);
                    } else{
                        // 死细胞
                        g.setColor(Color.WHITE);
                        g.fillRect(xStart+col*cellWidth, yStart+row*cellHeight, cellWidth, cellHeight);
                        g.setColor(Color.BLACK);
                        g.drawRect(xStart+col*cellWidth, yStart+row*cellHeight, cellWidth, cellHeight);
                    }
                    /*g.fillRect(xStart+col*cellWidth, yStart+row*cellHeight, cellWidth, cellHeight);
                    g.setColor(Color.BLACK);
                    g.drawRect(xStart+col*cellWidth, yStart+row*cellHeight, cellWidth, cellHeight);*/
                }
            }
        }
    }

    /**
     * 修改行列数按钮监听
     */
    private class ModifyButtonActionListener implements ActionListener{
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            // 修改行列数按钮
            // 重新创建地图
            int rows = Integer.parseInt(rowTF.getText());
            int cols = Integer.parseInt(colTF.getText());
            map.setRows(rows);
            map.setCols(cols);
            map.reCreateMap();
            // 重新初始化地图
            // logic.initMap();
            // 重新显示地图
            gamePanel.paint(graphics);
            // 重新显示当前行列数
            rowTF.setText("" + map.getRows());
            colTF.setText("" + map.getCols());
        }
    }

    /**
     * 修改演化次数按钮监听
     */
    private class ModifyStepsButtonActionListener implements ActionListener{
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            int steps = Integer.parseInt(stepsTF.getText());
            gameTimer.setSteps(steps);
            stepsTF.setText("" + gameTimer.getSteps());
        }
    }

    /**
     * 继续演化按钮监听
     */
    private class ProcessButtonActionListener implements ActionListener{
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if(gameTimer.getSteps() == 1){
                gameTimer.process();
                return;
            }
            gameTimer.process();
            processState();
        }
    }

    /**
     * 停止演化按钮监听
     */
    private class StopButtonActionListener implements ActionListener{
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            gameTimer.stop();
            notProcessState();
        }
    }

    /**
     * 随机初始化按钮
     */
    private class InitButtonActionListener implements ActionListener{
        /**
         * Invoked when an action occurs.
         *
         * @param e the event to be processed
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            logic.initMap();
            reShow();
        }
    }

    /**
     * 画板鼠标事件监听
     */
    private class GamePanelMouseListener implements MouseListener{
        /**
         * Invoked when the mouse button has been clicked (pressed
         * and released) on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {

        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            if(state) return;
            // 由MouseEvent可以获得鼠标的相对位置
            // Point point = MouseInfo.getPointerInfo().getLocation();
            int row = e.getY()/ (GameGUI.GAME_AREA_HEIGHT / map.getRows());
            int col = e.getX()/ (GameGUI.GAME_AREA_WIDTH / map.getCols());
            map.setCellState(row, col, !map.getCellState(row,col));
            reShow();
        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * Invoked when the mouse exits a component.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
