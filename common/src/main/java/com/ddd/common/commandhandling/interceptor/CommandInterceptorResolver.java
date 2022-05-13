package com.ddd.common.commandhandling.interceptor;

import com.ddd.common.command.Command;

import java.util.List;

/**
 * CommandInterceptorResolver
 *
 **/
public interface CommandInterceptorResolver {

    /**
     * 找到命令对应的拦截器
     *
     * @param aCommandClass 命令类型
     * @return 拦截器
     */
    List<CommandInterceptor> resolveInterceptors(Class<? extends Command> aCommandClass);

    /**
     * 注册命令拦截器(not threadsafe)
     *
     * @param interceptor
     */
    void registerCommandInterceptor(CommandInterceptor interceptor);
}
