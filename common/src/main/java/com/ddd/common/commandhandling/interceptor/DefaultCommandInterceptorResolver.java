package com.ddd.common.commandhandling.interceptor;


import com.ddd.common.command.Command;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.lang3.Validate;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DefaultCommandInterceptorResolver
 *
 * @author zhengwei
 * @date 2019-11-06 13:03
 **/
@Slf4j
public class DefaultCommandInterceptorResolver implements CommandInterceptorResolver {

    /**
     * 全局拦截器
     */
    private List<CommandInterceptor> globalInterceptors = new ArrayList<>();

    /**
     * 命令拦截器
     * <p>
     * key=Command Class
     */
    private Map<Class, List<CommandInterceptor>> interceptorsMapping = new HashMap<>();

    public void init() {
    }

    private void register0(CommandInterceptor interceptor) {
        Validate.notNull(interceptor);

        if (interceptor instanceof GlobalCommandInterceptor) {
            globalInterceptors.add(interceptor);
            return;
        }

        if (interceptor.supportCommandType() == null) {
            throw new RuntimeException("The CommandInterceptor's supportCommandType cannot be null, '" + interceptor.getClass().getName() + "'");
        }

        final Map<Class, List<CommandInterceptor>> interceptorMapping = this.interceptorsMapping;
        if (interceptorMapping.containsKey(interceptor.supportCommandType())) {
            List<CommandInterceptor> commandInterceptors = interceptorMapping.get(interceptor.supportCommandType());
            commandInterceptors.add(interceptor);
        } else {
            interceptorMapping.putIfAbsent(interceptor.supportCommandType(), new ArrayList<>());
            List<CommandInterceptor> commandInterceptors = interceptorMapping.get(interceptor.supportCommandType());
            commandInterceptors.add(interceptor);
        }
    }

    @Override
    public List<CommandInterceptor> resolveInterceptors(Class<? extends Command> aCommandClass) {
        List<CommandInterceptor> commandInterceptors = interceptorsMapping.get(aCommandClass);
        if (CollectionUtils.isEmpty(this.globalInterceptors)) {
            return commandInterceptors;
        }

        if (CollectionUtils.isEmpty(commandInterceptors)) {
            return this.globalInterceptors;
        }

        int size = commandInterceptors.size() + this.globalInterceptors.size();
        List<CommandInterceptor> finalCommandInterceptors = new ArrayList<>(size);
        finalCommandInterceptors.addAll(this.globalInterceptors);
        finalCommandInterceptors.addAll(commandInterceptors);
        return finalCommandInterceptors;

    }

    @Override
    public void registerCommandInterceptor(CommandInterceptor interceptor) {
        this.register0(interceptor);
    }

}
