package io.lanu.travian.warbuildercommander;

import io.lanu.travian.warbuildercommander.models.AboutVillageModel;
import io.lanu.travian.warbuildercommander.models.VillageModel;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMqConfig {

    // exchange for RPC
    @Bean(name = "my.rpc.direct")
    public DirectExchange directExchangeRPC() {
        return new DirectExchange("my.rpc.direct");
    }

    //config for Direct
    @Bean(name = "direct")
    public DirectExchange directExchange() {
        return new DirectExchange("my.direct");
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }

    @Bean
    public MessageConverter messageConverter()
    {
        Jackson2JsonMessageConverter jsonMessageConverter = new Jackson2JsonMessageConverter();
        jsonMessageConverter.setClassMapper(classMapper());
        return jsonMessageConverter;
    }

    @Bean
    public DefaultClassMapper classMapper()
    {
        DefaultClassMapper classMapper = new DefaultClassMapper();
        Map<String, Class<?>> idClassMapping = new HashMap<>();
        idClassMapping.put("io.lanu.travian.warbuilder.models.VillageModel", VillageModel.class);
        idClassMapping.put("io.lanu.travian.warbuilder.models.AboutVillageModel", AboutVillageModel.class);
        classMapper.setIdClassMapping(idClassMapping);
        return classMapper;
    }
}
