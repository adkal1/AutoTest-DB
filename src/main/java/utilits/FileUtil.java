package utilits;

import aquality.selenium.core.utilities.ISettingsFile;
import aquality.selenium.core.utilities.JsonSettingsFile;
import aquality.selenium.logging.LocalizedLoggerUtility;
import aquality.selenium.logging.LogLevel;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import static aquality.selenium.browser.AqualityServices.*;

public class FileUtil {
    private static final ISettingsFile environment = new JsonSettingsFile("testData.json");

    public static void uploadFile() {
        StringBuilder currentPath = new StringBuilder(getBrowser().getDownloadDirectory());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

        StringSelection stringSelection = new StringSelection(currentPath.append("\\avatar.png").toString());
        clipboard.setContents(stringSelection,null);

        Robot robot = null;
        try {
            robot = new Robot();
            LocalizedLoggerUtility.logByLevel(LogLevel.INFO,"Instance of the Robot class is created");
        } catch (AWTException e) {
            LocalizedLoggerUtility.logByLevel(LogLevel.ERROR, "Instance of the Robot class isn't created");
            throw new RuntimeException(e);
        }

        robot.delay(Integer.parseInt(environment.getValue("/timeDelay").toString()));
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
