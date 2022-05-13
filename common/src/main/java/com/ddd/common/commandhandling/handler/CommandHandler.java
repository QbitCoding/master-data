package com.ddd.common.commandhandling.handler;

import com.ddd.common.command.Command;
import com.ddd.common.result.BaseResult;

/**
 * 命令处理器
 *
 **/
public interface CommandHandler<C extends Command<R>, R extends BaseResult> {

    /**
     * 是否支持当前命令
     *
     * @return 类型
     */
    Class<C> supportCommand();

    /**
     * 处理command
     *
     * @param command command
     * @return 结果
     */
    R handle(C command);

}
