package com.cofjus.handler;

import com.cofjus.contants.RedisKeys;
import com.cofjus.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import com.cofjus.pojo.CommonRequest;
import com.cofjus.pojo.ErrorCode;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * frequency control
 */
@Component
@Slf4j
@Scope("prototype")
public class FrequencyHandler extends BaseHandler {

    @Autowired
    private StringRedisTemplate template;

    @Override
    protected ErrorCode handle(CommonRequest request) {
        try {
            log.info("FrequencyHandler.handle request:{}", JsonUtil.toJson(request));
            Map<String, String> params = request.getParams();
            String userId = params.getOrDefault("userId", "0");
            String key = String.format(RedisKeys.FREQUENCY_CONTROL, userId);
            log.info("FrequencyHandler.handle key:{}", key);
            ErrorCode errorCode = Boolean.TRUE.equals(template.hasKey(key)) ? new ErrorCode(-1, "frequency error") : ErrorCode.SUCCESS;
            log.info("FrequencyHandler.handle errorCode:{}", JsonUtil.toJson(errorCode));
            return errorCode;
        } catch (Exception ignored) {
        }
        return ErrorCode.SUCCESS;
    }

    @Override
    public void update(CommonRequest request) {
        try {
            // async update frequency
            Map<String, String> params = request.getParams();
            String userId = params.getOrDefault("userId", "0");
            String key = String.format(RedisKeys.FREQUENCY_CONTROL, userId);
            template.opsForValue().set(key, String.valueOf(1), 10, TimeUnit.SECONDS);
        } catch (Exception ignored) {

        }
    }
}
