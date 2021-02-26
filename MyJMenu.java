package learn.heima.game;

import javax.swing.*;

public class MyJMenu extends JMenu {

    MyJMenuItem[] myJMenuItems = new MyJMenuItem[5];

    //行为函数
    public void createJMenu(){

        //创建内容对象
        myJMenuItems[0] = new MyJMenuItem("重新游戏");
        myJMenuItems[1] = new MyJMenuItem("联系作者");


    }

    //构造函数
    public MyJMenu() {
        this.createJMenu();
    }

    public MyJMenu(String s) {
        super(s);
        this.createJMenu();
    }

    public MyJMenu(Action a) {
        super(a);
        this.createJMenu();
    }

    public MyJMenu(String s, boolean b) {
        super(s, b);
        this.createJMenu();
    }
}
