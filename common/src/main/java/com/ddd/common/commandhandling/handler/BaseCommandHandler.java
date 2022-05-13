package com.ddd.common.commandhandling.handler;

import com.ddd.common.command.Command;
import com.ddd.common.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseCommandHandler
 *
 **/
public abstract class BaseCommandHandler<C extends Command<R>, R extends BaseResult> implements CommandHandler<C, R> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public BaseCommandHandler() {
    }

    @Override
    public R handle(C command) {
        log.debug("command handle start, command={}", command);
        R resp = doHandle(command);
        log.debug("command handle end, command={}, result={}", command, resp);
        return resp;
    }

    /**
     * 由子类实现
     *
     * @param command command
     * @return 结果
     */
    protected abstract R doHandle(C command);
}
