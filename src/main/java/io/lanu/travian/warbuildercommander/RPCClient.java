package io.lanu.travian.warbuildercommander;

import io.lanu.travian.warbuildercommander.models.AboutVillageModel;
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

    public AboutVillageModel sendReqForAvailableTroops(String playerId, String villageName) {
        System.out.println(" [x] Requesting all villages...");
        AboutVillageModel response = (AboutVillageModel) template.convertSendAndReceive
                (exchange.getName(), "rpc", new CommandMessage(playerId, CommandsEnum.TROOPS, villageName));
        return response;
    }

    public AboutVillageModel sendReqForAllVillage(String playerId) {
        System.out.println(" [x] Requesting all villages...");
        AboutVillageModel response = (AboutVillageModel) template.convertSendAndReceive
                (exchange.getName(), "rpc", new CommandMessage(playerId, CommandsEnum.ALL_VILLAGES, "null"));
        return response;
    }
}
