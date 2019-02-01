import gdut.ff.Main;
import gdut.ff.rabbitmq.SinkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Main.class})
@WebAppConfiguration
public class MainTest {

    @Autowired
    private SinkSender sinkSender;

    @Test
    public void contextLoads() {

        sinkSender.output().send(MessageBuilder.withPlyload("From SinkSender").build());
    }

}
