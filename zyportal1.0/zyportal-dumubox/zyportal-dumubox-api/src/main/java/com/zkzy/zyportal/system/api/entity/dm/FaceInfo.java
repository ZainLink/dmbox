package com.zkzy.zyportal.system.api.entity.dm;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/3/31.
 */
public class FaceInfo implements Serializable {

    private Integer FaceId;

    private Integer FaceQuality;

    private String FacePosition;


    public Integer getFaceId() {
        return FaceId;
    }

    public void setFaceId(Integer faceId) {
        FaceId = faceId;
    }

    public Integer getFaceQuality() {
        return FaceQuality;
    }

    public void setFaceQuality(Integer faceQuality) {
        FaceQuality = faceQuality;
    }

    public String getFacePosition() {
        return FacePosition;
    }

    public void setFacePosition(String facePosition) {
        FacePosition = facePosition;
    }
}
