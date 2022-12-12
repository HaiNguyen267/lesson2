package ex1_textfile;

public abstract class Action extends MenuItem{
    public Action(String name) {
        super(name);
    }

    public abstract void run();
}
