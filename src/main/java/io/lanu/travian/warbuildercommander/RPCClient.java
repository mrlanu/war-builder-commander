package io.lanu.travian.warbuildercommander;

import io.lanu.travian.warbuildercommander.models.CommandMessage;
import io.lanu.travian.warbuildercommander.models.CommandsEnum;
import io.lanu.travian.warbuildercommander.models.VillageModel;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RPCClient {

    private RabbitTemplate template;
    private DirectExchange exchange;

    public RPCClient(RabbitTemplate template,
                     @Qualifier("my.rpc.direct") DirectExchange exchange) {
        this.template = template;
        this.exchange = exchange;
    }

    public List<VillageModel> send() {
        System.out.println(" [x] Requesting all villages...");
        List<VillageModel> response = (List<VillageModel>) template.convertSendAndReceive
                (exchange.getName(), "rpc", new CommandMessage(CommandsEnum.UPDATE, null));
        return response;
    }
}
