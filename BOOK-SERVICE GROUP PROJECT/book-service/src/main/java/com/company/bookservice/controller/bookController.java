package com.company.bookservice.controller;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class bookController {
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.#";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public bookController(RabbitTemplate rabbitTemplate){this.rabbitTemplate = rabbitTemplate;}


}
