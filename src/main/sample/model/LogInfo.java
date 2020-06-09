package main.sample.model;

import java.util.Date;

public class LogInfo {

    private Integer userId;
    private String log;
    private Date createTime;

    public LogInfo(Integer userId, String log) {
        this.createTime = new Date();
        this.log = log;
        this.userId = userId;
    }

    /*
     * Getters & Setters
     */
    public Date getCreateTime() { return createTime; }
    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public String getLog() { return log; }
    public void setLog(String log) { this.log = log; }

    /*
     * Entity Basics
     */
    @Override
    public String toString() {
        return "{" +
                "\"userId\": \"" + userId + "\"," +
                "\"log\": \"" + log + "\"," +
                "\"createTime\": \"" + createTime + "\"" +
                "}";
    }
}
