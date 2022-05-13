package com.ddd.common.commandhandling.handler;


import com.ddd.common.command.Command;
import com.ddd.common.result.BaseResult;

/**
 * 默认开启事务的CommandHandler
 **/
public interface TransactionCommandHandler<C extends Command<R>, R extends BaseResult> extends CommandHandler<C, R> {

    /**
     * 处理command
     *
     * @param command command
     * @return 结果
     */
    R handleInTransaction(C command);

}
