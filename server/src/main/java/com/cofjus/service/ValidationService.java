package com.cofjus.service;

import com.cofjus.handler.BaseHandler;
import com.cofjus.handler.HandlerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cofjus.pojo.CommonRequest;
import com.cofjus.pojo.ErrorCode;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
public class ValidationService {

    @Autowired
    private HandlerFactory handlerFactory;

    public Boolean validate(List<String> keyList, Map<String, String> requestParams) {
        BaseHandler chain = handlerFactory.generateChain(keyList);
        printChain(chain);
        CommonRequest request = new CommonRequest(requestParams);
        ErrorCode errorCode = Objects.nonNull(chain) ? chain.execute(request) : ErrorCode.SUCCESS;
        return ErrorCode.SUCCESS.equals(errorCode);
    }

    public void writeBack(List<String> keyList, Map<String, String> requestParams) {
        BaseHandler chain = handlerFactory.generateChain(keyList);
        CommonRequest request = new CommonRequest(requestParams);
        if (Objects.nonNull(chain)) {
            chain.writeBack(request);
        }
    }

    public static void printChain(BaseHandler handler) {
        StringBuilder sb = new StringBuilder();
        BaseHandler cur = handler;
        while (cur != null) {
            sb.append(cur.getClass().getSimpleName()).append(" -> ");
            cur = cur.getNext();
        }
        log.info(sb.toString());
    }
}
