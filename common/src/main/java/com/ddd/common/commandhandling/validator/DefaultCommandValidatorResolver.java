package com.ddd.common.commandhandling.validator;


import com.ddd.common.command.Command;
import com.ddd.common.result.Result;
import com.ddd.common.validator.CommandValidator;
import com.ddd.common.validator.GlobalCommandValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.Validate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认的CommandValidator解析器
 *
 **/
@Slf4j
public class DefaultCommandValidatorResolver implements CommandValidatorResolver {

    private Map<Class, CommandValidator> commandValidatorMapping = new ConcurrentHashMap<>();

    private List<GlobalCommandValidator> globalValidators = new ArrayList<>();

    @Override
    public <T extends Result> CommandValidator<Command<T>> resolve(Command<T> command) {
        Validate.notNull(command);
        return commandValidatorMapping.get(command.getClass());
    }

    @Override
    public <T extends Result> void registerValidator(CommandValidator<Command<T>> validator) {
        if (validator instanceof GlobalCommandValidator) {
            this.globalValidators.add((GlobalCommandValidator) validator);
            return;
        }

        Class requestType = validator.supportType();
        if (requestType == null) {
            throw new RuntimeException("The Validator's support type is required, '" + validator + "'");
        }

        if (commandValidatorMapping.containsKey(requestType)) {
            throw new RuntimeException("The Validator duplicated, '" + requestType + "'");
        }

        this.commandValidatorMapping.put(validator.supportType(), validator);
    }

    @Override
    public List<GlobalCommandValidator> getGlobalValidators() {
        return this.globalValidators;
    }

    @PostConstruct
    public void init() {

    }

}
