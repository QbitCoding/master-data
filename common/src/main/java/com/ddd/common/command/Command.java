package com.ddd.common.command;

import com.ddd.common.exception.BizException;
import com.ddd.common.result.BaseResult;
import com.ddd.common.validator.CommandValidator;


import java.io.Serializable;

/**
 * 命令
 *
 * @author zhengwei
 * @date 2019/3/12 3:40 PM
 **/
public interface Command<R extends BaseResult> extends Serializable {

    /**
     * 检查参数是否正确
     *
     * @param validator 验证器
     * @throws IllegalArgumentException
     * @throws BizException
     */
    void validate(CommandValidator<Command<R>> validator) throws IllegalArgumentException, BizException;

    /**
     * 结果类型
     *
     * @return
     */
    Class<R> resultType();
}
