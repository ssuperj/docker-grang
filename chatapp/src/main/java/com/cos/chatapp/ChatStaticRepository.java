package com.cos.chatapp;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ChatStaticRepository extends MongoRepository<Chat, String> {

    @Query(value = "{$or: [{sender:  ?0, receiver:?1},{sender:  ?1, receiver:?0}]}", delete = true)
    void deleteRoom(String sender, String receiver);
}
