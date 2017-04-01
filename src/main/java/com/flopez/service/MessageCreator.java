package com.flopez.service;

import com.flopez.model.Message;

/**
 * Message Creator Service
 */
public interface MessageCreator {

    default Message getMessageById(String id) {
        return new Message(id, getDefaultMessage());
    }

    String getDefaultMessage();
}
