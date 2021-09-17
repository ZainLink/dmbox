package com.zkzy.zyportal.system.api.service.register;

import com.zkzy.zyportal.system.api.entity.dm.CaptureInfoRequest;
import com.zkzy.zyportal.system.api.entity.dm.HeartbeatRequest;
import com.zkzy.zyportal.system.api.entity.dm.RegisterRequest;

/**
 * Created by Thinkpad-W530 on 2021/3/30.
 */
public interface RegisterService {

    Boolean register(RegisterRequest registerRequest);

    Boolean heart(HeartbeatRequest heartbeatRequest);

    Boolean capture(CaptureInfoRequest captureInfoRequest);

}
