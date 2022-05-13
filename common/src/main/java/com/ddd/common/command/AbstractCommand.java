package com.ddd.common.command;

import com.ddd.common.dto.BaseDTO;
import com.ddd.common.exception.BizException;
import com.ddd.common.result.BaseResult;
import com.ddd.common.validator.CommandValidator;



public abstract class AbstractCommand<T extends BaseResult> extends BaseDTO implements Command<T> {

    @Override
    public void validate(CommandValidator<Command<T>> validator) throws IllegalArgumentException, BizException {
        if (validator != null) {
            validator.validate(this);
        }
    }

}
