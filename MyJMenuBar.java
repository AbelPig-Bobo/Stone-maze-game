package learn.heima.game;

import javax.swing.*;

public class MyJMenuBar extends JMenuBar {

    MyJMenu[] myJMenus = new MyJMenu[5];

    //构造函数
    public MyJMenuBar() {

        //添加菜单
        myJMenus[0] = new MyJMenu("功能");
        this.add(myJMenus[0]);
        myJMenus[0].add(myJMenus[0].myJMenuItems[0]);

        //联系作者
        myJMenus[1] = new MyJMenu("联系作者");
        this.add(myJMenus[1]);
        myJMenus[1].add(myJMenus[1].myJMenuItems[1]);
    }

}
