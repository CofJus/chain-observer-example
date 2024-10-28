package com.cofjus.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.cofjus.pojo.CommonRequest;
import com.cofjus.pojo.ErrorCode;
import com.cofjus.util.JsonUtil;

import java.util.Map;

@Component
@Slf4j
@Scope("prototype")
public class BlacklistHandler extends BaseHandler {

    // read from config
    private final static String blackList = "12345,54321";

    @Override
    protected ErrorCode handle(CommonRequest request) {
        log.info("BlacklistHandler.handle request:{}", JsonUtil.toJson(request));
        try {
            Map<String, String> params = request.getParams();
            String target = params.get("userId");
            ErrorCode errorCode = !blackList.contains(target) ? ErrorCode.SUCCESS : new ErrorCode(-1, "hit blacklist");
            log.info("BlacklistHandler.handle errorCode:{}", JsonUtil.toJson(errorCode));
        } catch (Exception ignored) {

        }
        return ErrorCode.SUCCESS;
    }

    @Override
    public void update(CommonRequest request) {
        // do nothing
    }
}
