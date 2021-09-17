package com.zkzy.zyportal.config.gas;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 燃气 队列配置
 */
@Configuration
public class Queues {
    public static final String capture = "capture"; // 抓拍信息

    public static final String pushwarn = "pushwarn"; // 预警

    public static final String imgwarn = "imgwarn"; // 图片生成

    public static final String socketwarn = "socketwarn"; // webcocket

    public static final String warnExChange = "zkzy.warn"; // 预警和socket


    /**
     * 新建队列 topic.messages
     *
     * @return
     */
    @Bean(name = "messages")
    public Queue queueMessages() {
        return new Queue("topic.messages");
    }

    /**
     * 定义交换器
     *
     * @return
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("exchange");
    }


    /**
     * 交换机与消息队列进行绑定 队列messages绑定交换机with topic.messages
     *
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.messages");
    }


    //创建Fanout交换器
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(warnExChange);
    }


    //将对列绑定到Fanout交换器
    @Bean
    Binding bindingExchangeA(Queue socketwarn, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(socketwarn).to(fanoutExchange);
    }

    //将对列绑定到Fanout交换器
    @Bean
    Binding bindingExchangeB(Queue pushwarn, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(pushwarn).to(fanoutExchange);
    }

    //将对列绑定到Fanout交换器
    @Bean
    Binding bindingExchangeC(Queue imgwarn, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(imgwarn).to(fanoutExchange);
    }


    //将对列绑定到Fanout交换器
    @Bean
    Binding bindingExchangeD(Queue capture, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(capture).to(fanoutExchange);
    }


    /**
     * 初始化抓拍队列
     *
     * @return
     */
    @Bean
    public Queue capture() {
        return new Queue(capture);
    }

    //创建队列
    @Bean
    public Queue pushwarn() {
        return new Queue(pushwarn);
    }

    //创建队列
    @Bean
    public Queue socketwarn() {
        return new Queue(socketwarn);
    }

    //创建队列
    @Bean
    public Queue imgwarn() {
        return new Queue(imgwarn);
    }

}
