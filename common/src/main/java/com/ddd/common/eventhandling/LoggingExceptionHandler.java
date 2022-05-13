package com.ddd.common.eventhandling;

import com.ddd.common.event.Event;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认只是打印日志
 *
 **/
@Slf4j
public class LoggingExceptionHandler implements EventExceptionHandler {

    @Override
    public void onException(EventHandler listener, Event event, Throwable t) {
        log.error("execute listener error, listener={}, event={}", listener, event, t);
    }
}
