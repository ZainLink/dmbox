package com.zkzy.portal.base.admin.api.service;

public interface RabbitMqService {
    void insertErrorData(String queueName, Object obj, String reason);
}
