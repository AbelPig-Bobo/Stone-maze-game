package learn.heima.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class MyJFrame extends JFrame implements KeyListener, ActionListener {
    //属性
    //创建菜单栏
    MyJMenuBar myJMenuBar = new MyJMenuBar();
    //决定变量
    int[] temp;
    //地址数组
    String[] string;
    //图片数组
    ImageIcon[] imageIcon;
    //图片标签数组
    JLabel[] jLabel;
    //设置关键字
    int key;
    //统计步数
    int step = 0;


    //Action接口实现
    //重新开始游戏
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == myJMenuBar.myJMenus[0].myJMenuItems[0]){
            //步数清零
            step = 0;

            //设置地址
            string = setStr();

            key = searchZero(temp);

            //重新绘制
            this.setImage(string);
        }
        else if (e.getSource() == myJMenuBar.myJMenus[1].myJMenuItems[1]){
            //弹出二维码
            JDialog jDialog = new JDialog();
            ImageIcon ia = new ImageIcon("D:\\Program\\StudyProject\\StoneMazeGame\\src\\learn\\heima\\game\\image\\puyunbo.jpg");
            JLabel ja = new JLabel(ia);
            ja.setBounds(0,0,430,430);
            jDialog.add(ja);
            jDialog.setSize(430,430);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setVisible(true);
        }



    }

    //Key接口实现
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        Random r = new Random();
        int n = r.nextInt(5);
        switch (n){
            case 0:
                System.out.println("加油");
                break;
            case 1:
                System.out.println("你正在接近最终目标");
                break;
            case 2:
                System.out.println("马上就要成功了");
                break;
            case 3:
                System.out.println("越来越接近胜利了");
                break;
            case 4:
                System.out.println("太棒了！");
        }
    }

    //松开按键时处罚，重点使用
    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        string = move(keyCode,string);
        this.setImage(string);
        step++;
    }

    private String[] move(int keyCode,String[] str) {
        //左键(空格右边的数字向左移)
        if (keyCode == 37){
            if(key % 4 == 3) return str;
            else{
                String mid = str[key];
                str[key] = str[key+1];
                str[key+1] = mid;
                key+=1;
            }
        }

        //上键
        else if(keyCode == 38){
            if(key >= 12) return str;
            else{
                String mid = str[key];
                str[key] = str[key+4];
                str[key+4] = mid;
                key+=4;
            }
        }

        //右键
        else if(keyCode == 39){
            if(key % 4 == 0) return str;
            else{
                String mid = str[key];
                str[key] = str[key-1];
                str[key-1] = mid;
                key-=1;
            }
        }

        //下键
        else if (keyCode == 40){
            if(key <= 3) return str;
            else{
                String mid = str[key];
                str[key] = str[key-4];
                str[key-4] = mid;
                key-=4;
            }
        }

        //一键作弊:w
        else if (keyCode == 87){
            for (int i = 0; i < temp.length; i++) {
                if(i == temp.length-1)
                    //空在最后生成
                    str[i] = "D:\\Program\\StudyProject\\StoneMazeGame\\src\\learn\\heima\\game\\image\\" + 0 + ".png";
                else
                    //顺序生成
                    str[i] = "D:\\Program\\StudyProject\\StoneMazeGame\\src\\learn\\heima\\game\\image\\" + (i+1) + ".png";
                key = 15;
            }
        }

        //其他
        else{
            System.out.println("只能按上下左右");
        }

        return str;
    }


    //行为函数
    //判断胜利
    public boolean isVictor(String[] str){
        for (int i = 0; i < 15; i++) {
            if (!str[i].equals("D:\\Program\\StudyProject\\StoneMazeGame\\src\\learn\\heima\\game\\image\\" + (i+1) + ".png"))
                return false;
        }
        System.out.println("胜利了");
        return true;
    }


    //查0函数
    public int searchZero(int[] temp){
        int key = 0;
        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == 0)
                key = i;
        }
        return key;
    }

    //随机读取地址
    public String[] setStr(){

        //地址字符串数组
        String[] str = new String[16];

        //设置随机数组
        this.temp = new int[16];

        for (int i = 0; i < 16; i++) {
            //赋初值
            temp[i] = i;

        }

        for (int i = 0; i < temp.length; i++) {
            //生成随机数
            Random random = new Random();
            int randomNumber = random.nextInt(16);

            //实现随机交换
            int mid = temp[i];
            temp[i] = temp[randomNumber];
            temp[randomNumber] = mid;

        }

        for (int i = 0; i < temp.length; i++) {
            //赋随机图片
            str[i] = "D:\\Program\\StudyProject\\StoneMazeGame\\src\\learn\\heima\\game\\image\\" + temp[i] + ".png";
        }

        return str;
    }

    public void setImage(String[] str){//设置图片
        //删除原先界面中所有图片
        this.getContentPane().removeAll();

        //添加记步功能
        JLabel jStep = new JLabel("步数" + step);
        jStep.setBounds(50,20,100,20);
        this.add(jStep);

        //判断是否胜利
        if(isVictor(string)){
            JLabel jl = new JLabel(new ImageIcon("D:\\Program\\StudyProject\\StoneMazeGame\\src\\learn\\heima\\game\\image\\win.png"));
            jl.setBounds(514/2-266/2,230,266,88);
            this.add(jl);
        }

        //图片数组
        imageIcon = new ImageIcon[str.length];

        //设置标签
        jLabel = new JLabel[str.length];

        //载入图片
        //初始偏移量调整位置
        int x = 50;//横坐标
        int y = -10;//纵坐标
        for (int i = 0; i < str.length; i++) {
            //创建图片标签
            jLabel[i] = new JLabel(new ImageIcon(str[i]));

            //设置图片位置
            x = ( i % 4 ) * 100 + 50;
            if(i % 4 == 0)
                y += 100;
            jLabel[i].setBounds(x,y,100,100);

            //加入图片
            this.add(jLabel[i]);
        }

        //设置背景
        ImageIcon background = new ImageIcon("D:\\Program\\StudyProject\\StoneMazeGame\\src\\learn\\heima\\game\\image\\background.png");
        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(26,30,450,484);
        this.add(backgroundLabel);

        //重新绘制界面
        this.getContentPane().repaint();
    }

    public void createJFrame(){//设置窗体
        //设置窗体大小
        this.setSize(514,595);

        //设置窗体标题
        this.setTitle("石头迷阵单机版 v1.0 by 蒲云博");

        //设置默认关闭方式
        this.setDefaultCloseOperation(3);

        //设置显示位置
        this.setLocationRelativeTo(null);

        //使窗体始终置顶显示
        this.setAlwaysOnTop(true);

        //创建菜单栏
        //MyJMenuBar myJMenuBar = new MyJMenuBar();
        myJMenuBar.myJMenus[0].myJMenuItems[0].addActionListener(this::actionPerformed);
        myJMenuBar.myJMenus[1].myJMenuItems[1].addActionListener(this::actionPerformed);
        //添加菜单栏
        this.setJMenuBar(myJMenuBar);

        //取消默认居中放置
        this.setLayout(null);

        //设置地址
        string = setStr();

        key = searchZero(temp);

        //添加图片
        this.setImage(string);

        //添加键盘监听
        this.addKeyListener(this);

        //显示窗体
        this.setVisible(true);
    }

    //构造函数
    public MyJFrame(){
        this.createJFrame();
    }

    public MyJFrame(GraphicsConfiguration gc) {
        super(gc);
        this.createJFrame();
    }

    public MyJFrame(String title) throws HeadlessException {
        super(title);
        this.createJFrame();
    }

    public MyJFrame(String title, GraphicsConfiguration gc) {
        super(title, gc);
        this.createJFrame();
    }
}
