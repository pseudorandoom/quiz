package com.flopez.web;

import com.flopez.model.Message;
import com.flopez.service.MessageCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Quiz endpoint
 */
@RestController
@RequestMapping("/v1/messages")
public class GreetingRest {

    @Autowired
    MessageCreator messageCreator;

    @GetMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Message getMessage(final @PathVariable("id") String id) {
        return messageCreator.getMessageById(id);
    }
}
