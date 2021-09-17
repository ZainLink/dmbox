package com.zkzy.zyportal.system.api.entity.set;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/13.
 */
public class AdvanceParam implements Serializable {

    private Integer Clarity;

    private Integer Brightness;

    private Integer MaskScore;

    private Integer FacePitch;

    private Integer FaceYaw;

    private Integer FaceRoll;


    public Integer getClarity() {
        return Clarity;
    }

    public void setClarity(Integer clarity) {
        Clarity = clarity;
    }

    public Integer getBrightness() {
        return Brightness;
    }

    public void setBrightness(Integer brightness) {
        Brightness = brightness;
    }

    public Integer getMaskScore() {
        return MaskScore;
    }

    public void setMaskScore(Integer maskScore) {
        MaskScore = maskScore;
    }

    public Integer getFacePitch() {
        return FacePitch;
    }

    public void setFacePitch(Integer facePitch) {
        FacePitch = facePitch;
    }

    public Integer getFaceYaw() {
        return FaceYaw;
    }

    public void setFaceYaw(Integer faceYaw) {
        FaceYaw = faceYaw;
    }

    public Integer getFaceRoll() {
        return FaceRoll;
    }

    public void setFaceRoll(Integer faceRoll) {
        FaceRoll = faceRoll;
    }
}
