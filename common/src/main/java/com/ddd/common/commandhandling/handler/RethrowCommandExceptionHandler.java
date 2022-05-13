package com.ddd.common.commandhandling.handler;


import com.ddd.common.command.Command;
import com.ddd.common.result.BaseResult;

/**
 * 重新抛出异常
 *
 **/
public class RethrowCommandExceptionHandler implements CommandExceptionHandler {

    @Override
    public <T extends BaseResult> void onException(Command<T> command, T result, Throwable t) {

        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        }
        throw new RuntimeException(t);
    }
}
