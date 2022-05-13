package com.ddd.common.commandhandling.validator;



import com.ddd.common.command.Command;
import com.ddd.common.result.Result;
import com.ddd.common.validator.CommandValidator;
import com.ddd.common.validator.GlobalCommandValidator;

import java.util.List;

/**
 * 命令验证器解析器
 *
 **/
public interface CommandValidatorResolver {

    /**
     * 找到对应命令验证器
     *
     * @param command 命令
     * @return Validator 命令验证器
     */
    <T extends Result> CommandValidator<Command<T>> resolve(Command<T> command);

    /**
     * 注册(运行时注册注意并发问题)
     *
     * @param validator 命令验证器
     * @param <C>
     */
    <T extends Result> void registerValidator(CommandValidator<Command<T>> validator);

    /**
     *
     * 获取全局的验证器
     *
     * @return 全局的验证器
     */
    List<GlobalCommandValidator> getGlobalValidators();
}
