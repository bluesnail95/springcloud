package gdut.ff.util;

/**
 * 調用者
 * 2018-11-20
 * liuffei
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void execute() {
        command.execute();
    }
}
