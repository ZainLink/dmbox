package com.zkzy.zyportal.system.api.entity.set;

import java.io.Serializable;

/**
 * Created by Thinkpad-W530 on 2021/4/13.
 */
public class CaptureModeParam implements Serializable {
    private Integer CaptureTimes;

    private Integer MaxCaptureTimes;

    private Integer FastCaptureInterval;

    private Integer IntervalTime;

    private Integer FrameCaptureModeIntervalFrames;

    private Integer SingleModeIntervalFrames;


    public Integer getCaptureTimes() {
        return CaptureTimes;
    }

    public void setCaptureTimes(Integer captureTimes) {
        CaptureTimes = captureTimes;
    }

    public Integer getMaxCaptureTimes() {
        return MaxCaptureTimes;
    }

    public void setMaxCaptureTimes(Integer maxCaptureTimes) {
        MaxCaptureTimes = maxCaptureTimes;
    }

    public Integer getFastCaptureInterval() {
        return FastCaptureInterval;
    }

    public void setFastCaptureInterval(Integer fastCaptureInterval) {
        FastCaptureInterval = fastCaptureInterval;
    }

    public Integer getIntervalTime() {
        return IntervalTime;
    }

    public void setIntervalTime(Integer intervalTime) {
        IntervalTime = intervalTime;
    }

    public Integer getFrameCaptureModeIntervalFrames() {
        return FrameCaptureModeIntervalFrames;
    }

    public void setFrameCaptureModeIntervalFrames(Integer frameCaptureModeIntervalFrames) {
        FrameCaptureModeIntervalFrames = frameCaptureModeIntervalFrames;
    }

    public Integer getSingleModeIntervalFrames() {
        return SingleModeIntervalFrames;
    }

    public void setSingleModeIntervalFrames(Integer singleModeIntervalFrames) {
        SingleModeIntervalFrames = singleModeIntervalFrames;
    }
}
