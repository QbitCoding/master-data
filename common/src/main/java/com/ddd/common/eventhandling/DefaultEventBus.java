package com.ddd.common.eventhandling;

import com.ddd.common.event.Event;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/**
 * 默认的事件总线
 *
 **/
@Slf4j
@Component
public class DefaultEventBus implements EventBus {

    /**
     * 异步线程池
     * <p>
     * 优先从spring上下文中获取bean name='eventExecutor'的线程池，没有再创建一个
     */
    private ExecutorService eventExecutor;

    /**
     * 异常处理器
     */
    private EventExceptionHandler exceptionHandler;

    /**
     * key=event class type
     */
    private Map<Class, List<EventHandler>> handlerMapping = new HashMap<>();

    @PostConstruct
    public void init() {
        initExceptionHandler();
        log.info("EventBus init end");
    }

    private void initExceptionHandler() {
        if (this.exceptionHandler == null) {
            log.debug("Using LoggingExceptionHandler");
            this.exceptionHandler = new LoggingExceptionHandler();
        }
    }

    @PreDestroy
    public void destroy() {
        if (this.eventExecutor != null) {
            this.eventExecutor.shutdown();
        }
        log.info("EventBus destroy end");
    }

    @Override
    public void publish(Event event) {
        this.publish(event, false);
    }

    @Override
    public void asyncPublish(Event event) {
        this.publish(event, true);
    }

    @Override
    public <T extends Event> void registerHandler(EventHandler<T> eventHandler) {
        Validate.notNull(eventHandler);
        Validate.notNull(eventHandler.supportEventType(), "The EventHandler's support type is required");

        if (handlerMapping.containsKey(eventHandler.supportEventType())) {
            List<EventHandler> eventHandlers = handlerMapping.get(eventHandler.supportEventType());
            eventHandlers.add(eventHandler);
        } else {
            handlerMapping.putIfAbsent(eventHandler.supportEventType(), new ArrayList<>());
            handlerMapping.get(eventHandler.supportEventType()).add(eventHandler);
        }

    }

    @Override
    public <T extends Event> void registerHandlers(List<EventHandler<T>> eventHandlers) {
        Validate.notEmpty(eventHandlers);

        for (EventHandler handler : eventHandlers) {
            this.registerHandler(handler);
        }
    }

    private void publish(final Event event, final boolean async) {
        Validate.notNull(event);

        publish0(event, async);
    }

    private void publish0(Event event, boolean async) {

        log.debug("publish event start, event={}", event);

        long start = System.currentTimeMillis();

        final List<EventHandler> eventHandlers = resolveEventHandlers(event);

        if (CollectionUtils.isEmpty(eventHandlers)) {
            log.info("cannot find any eventHandlers, event={}", event);
            return;
        }

        log.debug("find eventHandlers, count={}, eventHandlers={}, event={}", eventHandlers.size(), eventHandlers, event);

        for (EventHandler eventHandler : eventHandlers) {
            Executor taskExecutor = eventHandler.getExecutor() != null ? eventHandler.getExecutor() : this.getEventExecutor();
            if (async && taskExecutor != null) {
                taskExecutor.execute(() -> invokeHandler(eventHandler, event));
            } else {
                invokeHandler(eventHandler, event);
            }

        }

        long cost = System.currentTimeMillis() - start;
        log.debug("publish event end, cost {} ms, event={}", cost, event);

    }

    private void invokeHandler(EventHandler handler, Event event) {

        EventExceptionHandler exceptionHandler = getExceptionHandler();
        if (exceptionHandler != null) {
            try {
                doInvokeHandler(handler, event);
            } catch (Throwable t) {
                exceptionHandler.onException(handler, event, t);
            }
        } else {
            doInvokeHandler(handler, event);
        }
    }

    private void doInvokeHandler(EventHandler handler, Event event) {
        handler.handle(event);
    }

    private List<EventHandler> resolveEventHandlers(Event event) {
        List<EventHandler> handlers = this.handlerMapping.get(event.getClass());
        if (CollectionUtils.isEmpty(handlers)) {
            return Collections.emptyList();
        }

        List<EventHandler> finalEventHandlers = new ArrayList<>(handlers.size());
        for (EventHandler handler : handlers) {
            if (handler.support(event)) {
                finalEventHandlers.add(handler);
            }
        }

        return finalEventHandlers;
    }

    public ExecutorService getEventExecutor() {
        return eventExecutor;
    }

    public void setEventExecutor(ExecutorService eventExecutor) {
        this.eventExecutor = eventExecutor;
    }

    public EventExceptionHandler getExceptionHandler() {
        return exceptionHandler;
    }

    public void setExceptionHandler(EventExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }
}
