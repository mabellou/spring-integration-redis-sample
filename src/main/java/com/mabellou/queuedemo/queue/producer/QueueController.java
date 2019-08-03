package com.mabellou.queuedemo.queue.producer;

import com.mabellou.queuedemo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("queue")
public class QueueController {

    @Autowired
    private QueueMessageGateway messagingGateway;

    @GetMapping
    public String get() {
        messagingGateway.sendMessage(new Student(1L, "test", 16));
        return "hello";
    }
}
