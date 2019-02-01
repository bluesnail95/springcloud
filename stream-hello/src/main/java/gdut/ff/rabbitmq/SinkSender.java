package gdut.ff.rabbitmq;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface SinkSender {

    @Output
    MessageChannel output();
}
