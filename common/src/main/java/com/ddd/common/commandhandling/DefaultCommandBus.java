package com.ddd.common.commandhandling;

import com.ddd.common.command.Command;
import com.ddd.common.commandhandling.handler.*;

import com.ddd.common.commandhandling.interceptor.CommandInterceptor;
import com.ddd.common.commandhandling.interceptor.CommandInterceptorResolver;
import com.ddd.common.commandhandling.interceptor.DefaultCommandInterceptorResolver;
import com.ddd.common.result.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 命令总线
 *
 **/
@Slf4j
@Component
public class DefaultCommandBus implements CommandBus {

    /**
     * 命令处理器解析器
     */
    private CommandHandlerResolver commandHandlerResolver = new DefaultCommandHandlerResolver();

    /**
     * 命令异常统一处理器
     */
    private CommandExceptionHandler commandExceptionHandler;

    /**
     * 命令拦截器解析器
     */
    private CommandInterceptorResolver commandInterceptorResolver = new DefaultCommandInterceptorResolver();

    /**
     * 命令调用信息工厂
     */
    private CommandInvocationFactory commandInvocationFactory;

    @Override
    public <T extends BaseResult> T dispatch(Command<T> command) {
        CommandInvocation<T> commandInvocation = commandInvocationFactory.createCommandInvocation(command);
        return commandInvocation.invoke();
    }

    @Override
    public void registerCommandHandler(CommandHandler commandHandler) {
        this.commandHandlerResolver.registerCommandHandler(commandHandler);
    }

    @Override
    public void registerCommandInterceptor(CommandInterceptor interceptor) {
        this.commandInterceptorResolver.registerCommandInterceptor(interceptor);
    }

    @PostConstruct
    public void init() {
        initCommandExceptionHandler();
        initCommandInvocationFactory();

        log.info("CommandBus init end");
    }

    private void initCommandInvocationFactory() {
        if (this.commandInvocationFactory == null) {
            log.debug("Using DefaultCommandInvocationFactory");
            this.commandInvocationFactory = new DefaultCommandInvocationFactory(this.commandHandlerResolver, this.commandInterceptorResolver, this.commandExceptionHandler);
        }
    }

    private void initCommandExceptionHandler() {
        if (this.commandExceptionHandler == null) {
            log.debug("Using DefaultCommandExceptionHandler");
            this.commandExceptionHandler = new DefaultCommandExceptionHandler();
        }
    }

    public void setCommandHandlerResolver(CommandHandlerResolver commandHandlerResolver) {
        this.commandHandlerResolver = commandHandlerResolver;
    }

    public void setCommandExceptionHandler(CommandExceptionHandler commandExceptionHandler) {
        this.commandExceptionHandler = commandExceptionHandler;
    }

    public void setCommandInterceptorResolver(CommandInterceptorResolver commandInterceptorResolver) {
        this.commandInterceptorResolver = commandInterceptorResolver;
    }

    public void setCommandInvocationFactory(CommandInvocationFactory commandInvocationFactory) {
        this.commandInvocationFactory = commandInvocationFactory;
    }
}
