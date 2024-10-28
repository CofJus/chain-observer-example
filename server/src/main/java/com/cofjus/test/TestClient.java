package com.cofjus.test;

import com.cofjus.service.ValidationService;
import com.cofjus.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class TestClient {

    @Autowired
    private ValidationService validationService;

    public Boolean test(String userId) {
        Pair<List<String>, Map<String, String>> params = generateTestParams(userId);
        // validate
        boolean success = validate(params.getLeft(), params.getRight());

        if (success) {
            // execute code that needs to be validated
            doSomething();
        } else {
            // do nothing
            doNothing();
        }

        // write back
        writeBack(params.getLeft(), params.getRight());
        return success;
    }

    private boolean validate(List<String> keyList, Map<String, String> params) {
        return validationService.validate(keyList, params);
    }

    private void writeBack(List<String> keyList, Map<String, String> params) {
        validationService.writeBack(keyList, params);
    }

    private Pair<List<String>, Map<String, String>> generateTestParams(String userId) {
        List<String> keyList = new ArrayList<>();
        keyList.add("blacklist");
        keyList.add("frequency");
        Map<String, String> params = new HashMap<>();
        params.put("userId", userId);
        log.info("TestValidation.test params key: {}, params: {}", JsonUtil.toJson(keyList), JsonUtil.toJson(params));
        return Pair.of(keyList, params);
    }

    private void doSomething() {
        log.info("TestValidation.doSomething");
    }

    private void doNothing() {
        log.info("TestValidation.doNothing");
    }

}
