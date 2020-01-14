package io.lanu.travian.warbuildercommander.services;

import io.lanu.travian.warbuildercommander.models.CommandMessage;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class WarSenderServiceImpl implements WarSenderService{

    private RabbitTemplate template;
    private FanoutExchange fanoutExchange;

    public WarSenderServiceImpl(RabbitTemplate template,
                                FanoutExchange fanoutExchange) {
        this.template = template;
        this.fanoutExchange = fanoutExchange;
    }

    @Override
    public void send(CommandMessage commandMessage) {
        template.convertAndSend(fanoutExchange.getName(), "attack", commandMessage);
        System.out.println(" [x] Sent attack - " + commandMessage.toString());
    }
}
