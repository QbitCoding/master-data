package com.ddd.application.command.handle;

import com.ddd.domain.repository.ResidentRepository;
import com.ddd.infrastructure.persistence.DO.ResidentDO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/*
 * 这里相对于新增操作,给出了一个修改操作代码.ddd方法是使用领域驱动设计的写法,mvc方式是传统的MVC模式的写法.其差别如下
 * MVC会有Service来处理业务(在这里我把service代码直接合并进mvc方法),而ddd是由领域层处理的,所以可以没有Service
 * MVC中调用Mapper方法获取与表结构一致的Entity,然后直接调用setter来赋值,其setter给的值就是getter获得的值,而ddd中getter是有业务逻辑的
 *
 */
public class DomainEvent1EnterPoint {
    public static class ResidentModule{

    }
    @Data
    public static class ModifyCommand{
        private Integer age;
    }
    @Autowired
    private ResidentRepository residentRepository;
    @PutMapping("/api/resident/{id}")
    void ddd(@PathVariable("id")Integer id, @RequestBody ModifyCommand command){
        var domain=residentRepository.findResident(id);
        domain.modify(command);
        residentRepository.saveResident(domain);
    }

    interface ResidentMapper{

        ResidentDO selectById(Integer id);

        void update(ResidentDO po);
    }
    private ResidentMapper mapper;
    @PutMapping("/api/resident/{id}")
    void mvc(@PathVariable("id")Integer id, @RequestBody ModifyCommand command){

        ResidentDO po=mapper.selectById(id);
        po.setAge(command.getAge());
        switch (po.getSex()){
            case "男":
                po.setOldEnoughForMarriage(po.getAge()>=22);
                break;
            case "女":
                po.setOldEnoughForMarriage(po.getAge()>=20);
                break;
            default:
                throw new RuntimeException();
        }
        mapper.update(po);
    }
}
