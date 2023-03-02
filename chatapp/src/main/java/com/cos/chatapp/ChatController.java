package com.cos.chatapp;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ChatController {

    private final MongoTemplate mongoTemplate;
    private final ChatRepository chatRepository;
    private final ChatStaticRepository chatStaticRepository;

    // 귓속말 할때 사용
    @CrossOrigin
    @GetMapping(value = "/sender/{sender}/receiver/{receiver}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> getMsg(@PathVariable String sender, @PathVariable String receiver) {
        String senderDecode = URLDecoder.decode(sender, StandardCharsets.UTF_8);
        String receiverDecode = URLDecoder.decode(receiver, StandardCharsets.UTF_8);
        return chatRepository.mFindBySender(sender, receiver)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @CrossOrigin
    @GetMapping(value = "/chat/roomNum/{roomNum}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Chat> findByUsername(@PathVariable String roomNum) {
        return chatRepository.mFindByRoomNum(roomNum)
                .subscribeOn(Schedulers.boundedElastic());
    }

    @CrossOrigin
    @PostMapping("/chat")
    public Mono<Chat> setMsg(@RequestBody Chat chat) {
        chat.setCreatedAt(LocalDateTime.now());
        return chatRepository.save(chat);
    }

    @CrossOrigin
    @DeleteMapping("/chat")
    public void deleteMsg(@RequestBody DeleteChatDto deleteChatDto) throws InterruptedException {
        String sender = deleteChatDto.getSender();
        String receiver = deleteChatDto.getReceiver();
       /* mongoTemplate.remove(Query.query(Criteria.where("sender").is(sender)
                .and("receiver").is(receiver)),Chat.class);*/
        chatStaticRepository.deleteRoom(deleteChatDto.getSender(), deleteChatDto.getReceiver());
    }
}
