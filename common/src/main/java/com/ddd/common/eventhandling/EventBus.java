package com.ddd.common.eventhandling;


import com.ddd.common.event.Event;

import java.util.List;

/**
 * event bus
 *
 * @see Event
 * @see EventHandler
 **/
public interface EventBus {

    /**
     * 同步发布事件
     *
     * @param event 事件
     */
    void publish(Event event);

    /**
     * 异步发布事件
     *
     * @param event 事件
     */
    void asyncPublish(Event event);

    /**
     * 注册EventListener(not threadsafe)
     *
     * @param handler EventHandler
     * @param <T>     Event
     */
    <T extends Event> void registerHandler(EventHandler<T> handler);

    /**
     * 注册多个事件处理器(not threadsafe)
     *
     * @param handlers EventHandler
     * @param <T>      Event
     */
    <T extends Event> void registerHandlers(List<EventHandler<T>> handlers);
}
