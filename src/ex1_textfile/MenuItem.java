package ex1_textfile;

public abstract class MenuItem {
    String name;

    public MenuItem(String name) {
        this.name = name;
    }

    public abstract void run();
}