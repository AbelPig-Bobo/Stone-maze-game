package learn.heima.game;

import javax.swing.*;

public class MyJMenuItem extends JMenuItem {

    //行为函数
    public void createJMenuItem(){
    }

    //构造函数
    public MyJMenuItem() {
        this.createJMenuItem();
    }

    public MyJMenuItem(Icon icon) {
        super(icon);
        this.createJMenuItem();
    }

    public MyJMenuItem(String text) {
        super(text);
        this.createJMenuItem();
    }

    public MyJMenuItem(Action a) {
        super(a);
        this.createJMenuItem();
    }

    public MyJMenuItem(String text, Icon icon) {
        super(text, icon);
        this.createJMenuItem();
    }

    public MyJMenuItem(String text, int mnemonic) {
        super(text, mnemonic);
        this.createJMenuItem();
    }
}
