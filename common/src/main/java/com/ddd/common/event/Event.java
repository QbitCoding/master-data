package com.ddd.common.event;

import java.util.Date;

public interface Event {

    /**
     * 发生时间
     *
     * @return 发生时间
     */
    Date occurredTime();

    /**
     * 标签
     *
     * @return tag
     */
    String getTag();
}
