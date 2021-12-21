package com.zkzy.controller.face;

import com.zkzy.service.FaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Thinkpad-W530 on 2021/12/13.
 */
@Validated
@RestController
@RequestMapping("/ipc")
@Api(tags = "人脸下发")
public class HikFaceController {

    @Autowired
    private FaceService faceService;


    @PostMapping(value = "/addFace", produces = "application/json; charset=UTF-8")
    @ApiOperation(value = "人脸入库")
    public Map<String, Object> addFace() {
        try {
            faceService.addFace();
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
