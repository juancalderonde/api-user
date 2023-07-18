package com.juancalderondev.apiuser.modelsDTO;

import java.util.Date;

public class TokenProperties {

    private Date startTime;
    private Date endTime;
    private Long userId;

    public TokenProperties(Date startTime, Date endTime, Long userId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
