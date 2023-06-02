package db;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import org.apache.commons.lang3.StringUtils;
import org.testng.ITestResult;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class DataBaseMethod {
    private static final ISettingsFile environment = new JsonSettingsFile("dbData.json");
    private static List<Integer> idList = new ArrayList<>();
    private static int i = 0;


    public static int setValueAuthor() {
        int checkId = checkAuthor(environment.getValue("/nameAuthor").toString(),
                environment.getValue("/login").toString(),
                environment.getValue("/email").toString());
        if (checkId != -1) {
            return checkId;
        } else {
            String sql = "INSERT INTO author (name, login, email) VALUES (?, ?, ?)";
            try {
                PreparedStatement stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, environment.getValue("/nameAuthor").toString());
                stmt.setString(2, environment.getValue("/login").toString());
                stmt.setString(3, environment.getValue("/email").toString());
                stmt.executeUpdate();
                return getId(stmt);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static int setProject() {
        int checkId = checkProject(environment.getValue("/nameProject").toString());
        if (checkId != -1) {
            return checkId;
        } else {
            String sql = "INSERT INTO project (name) VALUES (?)";
            try {
                PreparedStatement stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, environment.getValue("/nameProject").toString());
                stmt.executeUpdate();
                return getId(stmt);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static int setSession() {
        String sql = "INSERT INTO session (session_key, created_time, build_number) VALUES (?, ?, ?)";
        Random random = new Random();
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd H:m:s");
        String formattedDateTime = currentDateTime.format(formatter);
        try {
            PreparedStatement stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Integer.toString(random.nextInt(99999999)));
            stmt.setString(2, formattedDateTime);
            stmt.setString(3, Integer.toString(random.nextInt(9) + 1));
            stmt.executeUpdate();
            return getId(stmt);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getIdStatus(ITestResult result) {
        String sql = "SELECT * FROM status WHERE name = ?";
        String status = null;
        try {
            PreparedStatement stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
            switch (result.getStatus()) {
                case ITestResult.SUCCESS -> status = "PASSED";
                case ITestResult.FAILURE -> status = "FAILED";
                case ITestResult.SKIP -> status = "SKIPPED";
            }
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();
            return (rs.next()) ? rs.getInt("id") : -1;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int setTestData(String name, int statusId, String methodName, int projectId, int sessionId, String startTime, String endTime, String env, String browser, int authorId) {
        String sql = "INSERT INTO test (name, status_id, method_name, project_id, session_id, start_time, end_time, env, browser, author_id ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = null;
        try {
            stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
            stmt.setInt(2, statusId);
            stmt.setString(3, methodName);
            stmt.setInt(4, projectId);
            stmt.setInt(5, sessionId);
            stmt.setString(6, startTime);
            stmt.setString(7, endTime);
            stmt.setString(8, env);
            stmt.setString(9, browser);
            stmt.setInt(10, authorId);
            stmt.executeUpdate();
            return getId(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int updateTestData(int id, int statusId, int projectId, int sessionId, String startTime, String endTime, String env, String browser, int authorId) {
        String sql = "UPDATE test SET status_id = ?, project_id = ?, session_id = ?, start_time = ?, end_time = ?, env = ?, browser=?, author_id=? WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, statusId);
            stmt.setInt(2, projectId);
            stmt.setInt(3, sessionId);
            stmt.setString(4, startTime);
            stmt.setString(5, endTime);
            stmt.setString(6, env);
            stmt.setString(7, browser);
            stmt.setInt(8, authorId);
            stmt.setInt(9, id);
            stmt.executeUpdate();
            return getId(stmt);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int deleteTestData(int id) {
        String sql = "DELETE FROM test WHERE id= ?";
        PreparedStatement stmt = null;
        try {
            stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int getRandomId() {
        Random random = new Random();

        String num = StringUtils.repeat(String.valueOf(random.nextInt(9) + 1), 2);
        String sql = "SELECT id FROM test";
        Statement stmt = null;
        try {
            stmt = DataBaseConnection.getInstance().getConnection().createStatement();
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                String id = String.valueOf(resultSet.getInt("id"));
                if (id.contains(num)) {
                    idList.add(Integer.valueOf(id));
                }
            }
            Collections.shuffle(idList, new Random());
            ++i;
            return idList.get(i-1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ResultSet getTestData(int id) {
        String sql = "SELECT * FROM test WHERE id = ?";
        PreparedStatement stmt = null;
        try {
            stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            return stmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TestResultModel testResultModelSql(int id) {
        ResultSet resultSet = getTestData(id);
        TestResultModel testResultModel = new TestResultModel();
        try {
            while (resultSet.next()) {
                testResultModel.setTestModelDatas(resultSet.getString("name"),
                        resultSet.getInt("status_id"),
                        resultSet.getString("method_name"),
                        resultSet.getInt("project_id"),
                        resultSet.getInt("session_id"),
                        resultSet.getString("start_time"),
                        resultSet.getString("end_time"),
                        resultSet.getString("env"),
                        resultSet.getString("browser"),
                        resultSet.getInt("author_id"));
            }
            return testResultModel;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getLastId() {
        String sql = "SELECT MAX(id) FROM test";
        try {
            Statement stmt = DataBaseConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;
    }

    private static int checkAuthor(String name, String login, String email) {
        String sql = "SELECT * FROM author WHERE name = ? AND login = ? AND email = ?";
        try {
            PreparedStatement stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, login);
            stmt.setString(3, email);
            ResultSet rs = stmt.executeQuery();
            return (rs.next()) ? rs.getInt("id") : -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static int checkProject(String name) {
        String sql = "SELECT * FROM project WHERE name = ?";
        try {
            PreparedStatement stmt = DataBaseConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            return (rs.next()) ? rs.getInt("id") : -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private static int getId(PreparedStatement stmt) {
        ResultSet rs = null;
        try {
            rs = stmt.getGeneratedKeys();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return -1;

    }


}
