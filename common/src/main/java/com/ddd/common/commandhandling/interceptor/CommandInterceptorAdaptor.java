package com.ddd.common.commandhandling.interceptor;

import com.ddd.common.command.Command;
import com.ddd.common.result.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 命令拦截器
 * <p>
 * 支持排序
 *
 * @param <R> Result
 * @see org.springframework.core.annotation.Order
 * @see org.springframework.core.Ordered
 */
public abstract class CommandInterceptorAdaptor<C extends Command<R>, R extends BaseResult> implements CommandInterceptor<C, R> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * 前置处理
     *
     * @param command 命令
     */
    @Override
    public void beforeHandle(C command) {

    }

    /**
     * 后置处理
     *
     * @param command 命令
     * @param result  结果
     */
    @Override
    public void afterHandle(C command, R result) {

    }
}
