package com.ddd.common.commandhandling.handler;

import com.ddd.common.command.Command;
import com.ddd.common.result.BaseResult;

/**
 * 异常处理器
 *
 */
public interface CommandExceptionHandler {

    /**
     * 处理异常
     *
     * @param command command
     * @param result  result
     * @param t       异常
     */
    <T extends BaseResult> void onException(Command<T> command, T result, Throwable t);

}
