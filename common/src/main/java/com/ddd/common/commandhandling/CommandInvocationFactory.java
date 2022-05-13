package com.ddd.common.commandhandling;

import com.ddd.common.command.Command;
import com.ddd.common.result.BaseResult;

/**
 * CommandInvocationFactory
 *

 **/
public interface CommandInvocationFactory {

    /**
     * 创建一个CommandInvocation
     *
     * @param command 命令
     * @param <T>     结果类型
     * @return
     */
    <T extends BaseResult> CommandInvocation<T> createCommandInvocation(Command<T> command);
}
