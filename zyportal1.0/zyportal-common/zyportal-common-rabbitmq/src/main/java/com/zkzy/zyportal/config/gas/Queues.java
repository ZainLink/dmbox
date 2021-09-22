package com.zkzy.zyportal.config.gas;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 燃气 队列配置
 */
@Configuration
public class Queues {
    public static final String INITREGISTGASVESSEL = "initRegistGasvessel"; // 初始化登记燃气瓶队列 名
    public static final String CHECKNOTPASS = "checkNotPass"; // 安全检查不过关,报废 队列 名
    public static final String FILLINGCOMPLETED = "fillingCompleted"; // 充装完成 队列 名
    public static final String GASINANDOUTGSS = "gasInAndOutGss"; // 供应站出入库(满/空) 队列 名
    public static final String GASDELIEVERYCOMPLETED = "gasDeliveryCompleted"; // 燃气瓶配送完成 队列 名

    /**
     * 新建队列 topic.messages
     * @return
     */
    @Bean(name = "messages")
    public Queue queueMessages(){
        return new Queue("topic.messages");
    }
    /**
     * 定义交换器
     * @return
     */
    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    /**
     * 交换机与消息队列进行绑定 队列messages绑定交换机with topic.messages
     * @param queueMessages
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessages(@Qualifier("messages") Queue queueMessages, TopicExchange exchange){
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.messages");
    }


    /**
     * 初始化登记燃气瓶队列
     * @return
     */
    @Bean
    public Queue initRegistGasvessel(){
        return new Queue(INITREGISTGASVESSEL);
    }
    /**
     * 安全检查不过关,报废 队名
     * @return
     */
    @Bean
    public Queue checkNotPass(){
        return new Queue(CHECKNOTPASS);
    }
    /**
     *
     * @return
     */
    @Bean
    public Queue fillingCompleted(){
        return new Queue(FILLINGCOMPLETED);
    }

}
