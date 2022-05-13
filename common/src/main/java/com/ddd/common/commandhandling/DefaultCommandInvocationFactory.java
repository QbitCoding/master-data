package com.ddd.common.commandhandling;

import com.ddd.common.command.Command;
import com.ddd.common.commandhandling.handler.CommandExceptionHandler;
import com.ddd.common.commandhandling.handler.CommandHandler;
import com.ddd.common.commandhandling.handler.CommandHandlerResolver;

import com.ddd.common.commandhandling.interceptor.CommandInterceptor;
import com.ddd.common.commandhandling.interceptor.CommandInterceptorResolver;
import com.ddd.common.result.BaseResult;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * DefaultCommandInvocationFactory
 *
 */
@Slf4j
public class DefaultCommandInvocationFactory implements CommandInvocationFactory {

    /**
     * 命令处理器解析器
     */
    private CommandHandlerResolver commandHandlerResolver;

    /**
     * 命令异常统一处理器
     */
    private CommandExceptionHandler commandExceptionHandler;

    /**
     * 命令拦截器解析器
     */
    private CommandInterceptorResolver commandInterceptorResolver;

    public DefaultCommandInvocationFactory(CommandHandlerResolver commandHandlerResolver, CommandInterceptorResolver commandInterceptorResolver, CommandExceptionHandler commandExceptionHandler) {
        this.commandExceptionHandler = commandExceptionHandler;
        this.commandHandlerResolver = commandHandlerResolver;
        this.commandInterceptorResolver = commandInterceptorResolver;
    }

    @Override
    public <T extends BaseResult> CommandInvocation<T> createCommandInvocation(Command<T> command) {
        final CommandHandler<Command<T>, T> commandHandler = commandHandlerResolver.resolve(command);

        final List<CommandInterceptor> commandInterceptors = commandInterceptorResolver.resolveInterceptors(command.getClass());
        CommandInvocation ci = new CommandInvocation(command, commandHandler, this.commandExceptionHandler, commandInterceptors);

        return ci;
    }

}
