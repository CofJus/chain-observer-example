package com.cofjus.handler;

import com.cofjus.enums.HandlerEnum;
import lombok.Getter;
import lombok.Setter;
import com.cofjus.pojo.CommonRequest;
import com.cofjus.pojo.ErrorCode;

@Getter
@Setter
public abstract class BaseHandler implements BaseObserver {

    protected BaseHandler next;

    protected abstract ErrorCode handle(CommonRequest request);

    public ErrorCode execute(CommonRequest request) {
        ErrorCode ret = this.handle(request);
        if (ret != ErrorCode.SUCCESS) {
            return ret;
        } else if (next == null) {
            return ErrorCode.SUCCESS;
        }
        return this.next.execute(request);
    }

    public boolean writeBack(CommonRequest request) {

        this.update(request);
        if (next == null) {
            return true;
        }
        return this.next.writeBack(request);
    }

}
