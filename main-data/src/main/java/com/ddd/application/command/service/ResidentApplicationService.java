package com.ddd.application.command.service;

import com.ddd.application.command.dto.CreateResidentCommand;
import com.ddd.application.command.handle.CreateResidentCommandHandler;
import com.ddd.common.commandhandling.CommandBus;
import com.ddd.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class ResidentApplicationService {

    @Autowired
    private CommandBus commandBus;

    @Autowired
    private CreateResidentCommandHandler createResidentCommandHandler;

    @PostConstruct
    public void init() {
        commandBus.registerCommandHandler(createResidentCommandHandler);

    }

    @Transactional
    public Result<String> createResident(CreateResidentCommand command) {
        return commandBus.dispatch(command);
    }


}
