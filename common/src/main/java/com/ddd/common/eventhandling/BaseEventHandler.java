package com.ddd.common.eventhandling;

import com.ddd.common.event.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executor;

/**
 **/
public abstract class BaseEventHandler<T extends Event> implements EventHandler<T> {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Executor getExecutor() {
        return null;
    }

    @Override
    public boolean support(Event event) {
        return true;
    }


    @Override
    public final void handle(T event) {
        log.debug("handle Event start, event={}", event);
        long start = System.currentTimeMillis();

        doHandle(event);

        long cost = System.currentTimeMillis() - start;
        log.debug("handle Event event end, cost {} ms, event={}", cost, event);

    }

    /**
     * 子类实现
     *
     * @param event 事件
     */
    protected abstract void doHandle(T event);
}
