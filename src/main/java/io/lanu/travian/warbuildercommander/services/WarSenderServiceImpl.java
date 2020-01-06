package io.lanu.travian.warbuildercommander.services;

import io.lanu.travian.warbuildercommander.models.CommandMessage;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class WarSenderServiceImpl implements WarSenderService{

    private RabbitTemplate template;
    private DirectExchange directExchange;

    public WarSenderServiceImpl(RabbitTemplate template,
                                @Qualifier("direct") DirectExchange directExchange) {
        this.template = template;
        this.directExchange = directExchange;
    }

    @Override
    public void send(CommandMessage commandMessage) {
        template.convertAndSend(directExchange.getName(), "attack", commandMessage);
        System.out.println(" [x] Sent attack - " + commandMessage.toString());
    }
}
