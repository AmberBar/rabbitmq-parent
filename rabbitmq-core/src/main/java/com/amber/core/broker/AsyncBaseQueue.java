package com.amber.core.broker;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AsyncBaseQueue {

    private static final int THREAD_SIZE = Runtime.getRuntime().availableProcessors();

    private static final int QUEUE_SIZE = 10000;

    private static ExecutorService senderAsync = new ThreadPoolExecutor(THREAD_SIZE, THREAD_SIZE,
            60L, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_SIZE),
            (r) -> {
                Thread t = new Thread(r);
                t.setName("rabbitmq_client_async_sender");
                return t;
            },
            (r, executor) -> log.error("async sender is error reject , runable : {}, executor: {}", r,executor));

    public static void submit( Runnable runnable) {
        senderAsync.submit(runnable);
    }
}
