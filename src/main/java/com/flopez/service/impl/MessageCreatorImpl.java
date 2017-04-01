package com.flopez.service.impl;

import com.flopez.service.MessageCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Implementation for {@link com.flopez.service.MessageCreator}
 */
@Service
@PropertySource("classpath:application.properties")
public class MessageCreatorImpl implements MessageCreator {
    private static final Logger _LOG = LoggerFactory.getLogger(MessageCreatorImpl.class);
    @Value("${com.flopez.default.message}")
    private String fixedMessage;

    @Override
    public String getDefaultMessage() {
        return fixedMessage;
    }

    @PostConstruct
    private void showInfo() {
        _LOG.info("fixedMessage configured as {}", fixedMessage);
    }
}
