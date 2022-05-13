package com.ddd.common.commandhandling.interceptor;

import com.ddd.common.command.Command;
import com.ddd.common.commandhandling.validator.CommandValidatorResolver;
import com.ddd.common.result.BaseResult;
import com.ddd.common.validator.GlobalCommandValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;


import java.util.List;

/**
 * 验证command
 *
 **/
@Slf4j
public class ValidateCommandInterceptor implements GlobalCommandInterceptor {

    /**
     * 命令验证器解析器
     */
    private CommandValidatorResolver commandValidatorResolver;

    @Override
    public void beforeHandle(Command command) {
        log.debug("CommandValidateInterceptor.beforeHandle start, command={}", command);

        List<GlobalCommandValidator> globalValidators = commandValidatorResolver.getGlobalValidators();
        if (!CollectionUtils.isEmpty((globalValidators)) ){
            for (GlobalCommandValidator globalValidator : globalValidators) {
                if (globalValidator.supportCommand(command)) {
                    globalValidator.validate(command);
                }
            }
        }

        command.validate(commandValidatorResolver.resolve(command));
        log.debug("CommandValidateInterceptor.beforeHandle end, command={}", command);
    }

    @Override
    public void afterHandle(Command command, BaseResult result) {

    }

    public CommandValidatorResolver getCommandValidatorResolver() {
        return commandValidatorResolver;
    }

    public void setCommandValidatorResolver(CommandValidatorResolver commandValidatorResolver) {
        this.commandValidatorResolver = commandValidatorResolver;
    }
}
