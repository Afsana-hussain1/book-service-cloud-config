package com.company.bookservice.ServiceLayer;

import com.company.bookservice.dao.BookDao;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BookServiceLayer {

    @Autowired
    BookDao bookDao;


    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY_CREATE = "note.queue.create";
    public static final String ROUTING_KEY_UPDATE = "note.queue.update";
    public static final String ROUTING_KEY_DELETE = "note.queue.delete";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public BookServiceLayer(RabbitTemplate rabbitTemplate){this.rabbitTemplate = rabbitTemplate;}

}
