package com.ddd.common.commandhandling;


import com.ddd.common.command.Command;
import com.ddd.common.commandhandling.handler.CommandHandler;
import com.ddd.common.commandhandling.interceptor.CommandInterceptor;
import com.ddd.common.result.BaseResult;

/**
 * 命令总线
 *
 * @see Command
 * @see BaseResult
 * @see CommandHandler
 * @see CommandInterceptor
 **/
public interface CommandBus {

    /**
     * 分发命令
     *
     * @param command command
     * @return 结果
     */
    <T extends BaseResult> T dispatch(Command<T> command);

    /**
     * 注册命令处理器(not threadsafe)
     *
     * @param commandHandler 命令处理器
     */
    void registerCommandHandler(CommandHandler commandHandler);

    /**
     * 注册命令拦截器(not threadsafe)
     *
     * @param interceptor
     */
    void registerCommandInterceptor(CommandInterceptor interceptor);

}
