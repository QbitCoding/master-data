package com.ddd.application.command.validator;

import com.ddd.application.command.dto.CreateResidentCommand;
import com.ddd.common.exception.BizException;
import com.ddd.common.validator.CommandValidator;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

@Component
public class CreateResidentCommandValidator implements CommandValidator<CreateResidentCommand> {

    @Override
    public Class<CreateResidentCommand> supportType() {
        return CreateResidentCommand.class;
    }

    @Override
    public void validate(CreateResidentCommand command) throws IllegalArgumentException, BizException {
        Validate.notNull(command);
        Validate.notNull(command.getName());
        Validate.isTrue(command.getName().length() <= 10);
    }
}
