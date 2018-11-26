package gdut.ff;

import gdut.ff.util.Command;
import gdut.ff.util.ConcreteCommand;
import gdut.ff.util.Invoker;
import gdut.ff.util.Receiver;
import org.junit.Test;

public class CommandTest {

    @Test
    public void testCommand() {
        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker(command);
        invoker.execute();
    }
}
