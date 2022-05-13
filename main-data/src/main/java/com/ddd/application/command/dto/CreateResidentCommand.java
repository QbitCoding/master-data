package com.ddd.application.command.dto;

import com.ddd.common.command.AbstractCommand;
import com.ddd.common.result.Result;
//import com.ddd.domain.entity.space.Area;
import lombok.Data;

import java.util.List;

@Data
public class CreateResidentCommand extends AbstractCommand<Result> {

    private Integer Id;

    private String name;

    private Integer age;

    private String sex;

    List<String> listArea;


    @Override
    public Class<Result> resultType() {
        return Result.class;
    }
}
