package com.ddd.common.commandhandling.validator;


import com.ddd.common.command.Command;
import com.ddd.common.exception.BasicErrorCode;
import com.ddd.common.exception.BizException;
import com.ddd.common.validator.GlobalCommandValidator;
import lombok.Getter;
import lombok.Setter;
//import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 注解验证命令参数
 *

 **/
@Setter
@Getter
public class AnnotationParamGlobalCommandValidator implements GlobalCommandValidator<Command> {

    private Validator validator;

    @Override
    public void validate(Command command) throws IllegalArgumentException, BizException {
        Set<ConstraintViolation<Command>> constraintViolations = this.validator.validate(command);
        if (!CollectionUtils.isEmpty(constraintViolations)) {

            List<String> messages = constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

            String message = StringUtils.join(messages, ";");
            throw new BizException(BasicErrorCode.PARAMS_ERROR.getErrorCode(), message, message);
        }
    }
}
