package com.ddd.common.eventhandling;


import com.ddd.common.event.Event;

/**
 * 异常处理器
 */
public interface EventExceptionHandler {

	/**
	 * 处理异常
	 *
	 * @param listener listener
	 * @param event event
	 * @param t 异常
	 */
	void onException(EventHandler listener, Event event, Throwable t);

}
