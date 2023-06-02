package db;

import java.util.Objects;

public class TestResultModel {
    private String name;
    private int statusId;
    private String methodName;
    private int projectId;
    private int sessionId;
    private String startTime;
    private String endTime;
    private String env;
    private String browser;
    private int authorId;

    public void setTestModelDatas(String name, int statusId, String methodName, int projectId, int sessionId, String startTime, String endTime, String env, String browser, int authorId) {
        this.name = name;
        this.statusId = statusId;
        this.methodName = methodName;
        this.projectId = projectId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
        this.browser = browser;
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TestResultModel that)) return false;
        return statusId == that.statusId && projectId == that.projectId && sessionId == that.sessionId && authorId == that.authorId && Objects.equals(name, that.name) && Objects.equals(methodName, that.methodName) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(env, that.env) && Objects.equals(browser, that.browser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, statusId, methodName, projectId, sessionId, startTime, endTime, env, browser, authorId);
    }

    public String getName() {
        return name;
    }

    public int getStatusId() {
        return statusId;
    }

    public String getMethodName() {
        return methodName;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getSessionId() {
        return sessionId;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getEnv() {
        return env;
    }

    public String getBrowser() {
        return browser;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setUpdateTestData(int statusId, int projectId, int sessionId, String startTime, String endTime, String env, String browser, int authorId) {
        this.statusId = statusId;
        this.projectId =projectId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
        this.browser = browser;
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "TestResultModel{" +
                "name='" + name + '\'' +
                ", statusId=" + statusId +
                ", methodName='" + methodName + '\'' +
                ", projectId=" + projectId +
                ", sessionId=" + sessionId +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", env='" + env + '\'' +
                ", browser='" + browser + '\'' +
                ", authorId=" + authorId +
                '}';
    }
}
