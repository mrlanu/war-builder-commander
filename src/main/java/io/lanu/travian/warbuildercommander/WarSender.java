package io.lanu.travian.warbuildercommander;

import io.lanu.travian.warbuildercommander.models.Item;
import io.lanu.travian.warbuildercommander.models.Order;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WarSender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private FanoutExchange fanout;

    @Scheduled(fixedDelay = 15000, initialDelay = 1000)
    public void send() {
        Order order = getOrder();
        template.convertAndSend(fanout.getName(), "", order);
        System.out.println(" [x] Sent order - " + order.toString());
    }

    private Order getOrder()
    {
        List<Item> orderItemList = new ArrayList<>();
        Item item1 = new Item("Phone", 1);
        orderItemList.add(item1);

        Item item2 = new Item("SIM", 1);
        orderItemList.add(item2);

        Item item3 = new Item("Charger", 1);
        orderItemList.add(item3);

        return new Order("Chathuranga Tennakoon", new Date(), "www.springbootdev.com", orderItemList);
    }
}
