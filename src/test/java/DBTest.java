import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import db.TestResultModel;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static db.DataBaseMethod.*;

public class DBTest {
    private final ISettingsFile config = new JsonSettingsFile("settings.json");
    int currentId;

    private String startTime;
    private TestResultModel testResultModel;

    @BeforeMethod
    public void setUp() {
        List<Integer> listId = new ArrayList<>();
        startTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        testResultModel = testResultModelSql(getRandomId());
        listId.add(setTestData(testResultModel.getName(),
                testResultModel.getStatusId(),
                testResultModel.getMethodName(),
                setProject(),
                testResultModel.getSessionId(),
                testResultModel.getStartTime(),
                testResultModel.getEndTime(),
                testResultModel.getEnv(),
                testResultModel.getBrowser(),
                setValueAuthor()));
    }

    @Test(dataProvider = "data")
    public void simulateUpdateTestData(int id) {
        currentId = id;
        Random random = new Random();
        int statusId = random.nextInt(3) + 1;
        int sessionId = setSession();
        int projectId = setProject();
        String endTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String env = System.getProperty("user.name");
        String browser = config.getValue("/browserName").toString();
        int authorId = setValueAuthor();
        updateTestData(id, statusId, projectId, sessionId, startTime, endTime, env, browser, authorId);

        testResultModel.setUpdateTestData(statusId, projectId, sessionId, startTime, endTime, env, browser, authorId);
        Assert.assertEquals(testResultModel, testResultModelSql(id), "Information doesn't add to SQL");
    }

    @DataProvider(name = "data")
    public Object[][] dataListId() {
        int lastId = getLastId();
        return new Object[][]{{lastId + 1}, {lastId + 2}, {lastId + 3}};
    }

    @AfterMethod
    public void delete() {
        deleteTestData(currentId);
    }
}
