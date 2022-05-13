package com.ddd.common.commandhandling.handler;


import com.ddd.common.command.Command;
import com.ddd.common.result.BaseResult;

/**
 * 命令处理器解析器
 *
 **/
public interface CommandHandlerResolver {

    /**
     * 找到对应处理类
     *
     * @param command 请求
     * @return CommandHandler
     * @throws CannotFindCommandHandlerException
     */
    <C extends Command<R>, R extends BaseResult> CommandHandler<C, R> resolve(Command<R> command) throws CannotFindCommandHandlerException;

    /**
     * 注册命令处理器(not threadsafe)
     *
     * @param commandHandler 命令处理器
     */
    void registerCommandHandler(CommandHandler commandHandler);
}
