package com.ddd.application.command.handle;

import com.ddd.application.command.dto.CreateResidentCommand;
import com.ddd.common.commandhandling.handler.BaseCommandHandler;
import com.ddd.common.eventhandling.EventBus;
import com.ddd.common.result.Result;
import com.ddd.domain.entity.People.Resident;
import com.ddd.domain.event.ResidentCreatedEvent;
import com.ddd.domain.repository.PeopleRepository;
import com.ddd.domain.repository.ResidentRepository;
import com.ddd.domain.service.ResidentDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateResidentCommandHandler extends BaseCommandHandler<CreateResidentCommand, Result> {

    @Autowired
    private ResidentRepository residentRepository;

    @Autowired
    private ResidentDomainService residentDomainService;

    @Autowired
    private EventBus eventBus;



    @Override
    protected Result doHandle(CreateResidentCommand command) {
        // 转换领域对象
        Resident resident =  new Resident(residentDomainService.nextId(),command);

        // 保存数据
        residentRepository.saveResident(resident);

        // 发布领域事件
        eventBus.publish(new ResidentCreatedEvent(resident.getResidentId()));

        return Result.success(resident.getResidentId());
    }

    @Override
    public Class<CreateResidentCommand> supportCommand() {
        return CreateResidentCommand.class;
    }
}
