package com.cofjus.handler;

import com.cofjus.enums.HandlerEnum;
import com.cofjus.util.SpringBeanUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class HandlerFactory {

    @Nullable
    public BaseHandler selectHandler(String name) {
        if (HandlerEnum.BLACKLIST.getName().equals(name)) {
            return getBlacklistHandler();
        } else if (HandlerEnum.FREQUENCY.getName().equals(name)) {
            return getFrequencyHandler();
        }
        return null;
    }

    private BlacklistHandler getBlacklistHandler() {
        return SpringBeanUtil.getApplicationContext().getBean("blacklistHandler", BlacklistHandler.class);

    }

    private FrequencyHandler getFrequencyHandler() {
        return SpringBeanUtil.getApplicationContext().getBean("frequencyHandler", FrequencyHandler.class);
    }

    @Nullable
    public BaseHandler generateChain(List<String> keyList) {
        BaseHandler first = null, cur = null;
        for (String key : keyList) {
            BaseHandler handler = selectHandler(key);
            // skip if failed
            if (Objects.isNull(handler)) {
                continue;
            }
            if (Objects.isNull(first)) {
                first = handler;
            } else {
                cur.setNext(handler);
            }
            cur = handler;
        }
        return first;
    }
}
