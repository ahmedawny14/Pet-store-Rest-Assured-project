package base;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import utilities.AllureAttachments;

import java.lang.reflect.Field;

public class BaseTest {

    @AfterMethod(alwaysRun = true)
    public void attachFailureDetails(ITestResult result) {
        Object testInstance = result.getInstance();

        try {
            Field responseField = findField(testInstance.getClass(), "response");
            if (responseField == null) {
                AllureAttachments.attachFailureDetails(result, null);
                return;
            }

            responseField.setAccessible(true);
            Object response = responseField.get(testInstance);

            AllureAttachments.attachFailureDetails(
                    result,
                    response instanceof io.restassured.response.Response
                            ? (io.restassured.response.Response) response
                            : null
            );

            responseField.set(testInstance, null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Could not read test response for Allure attachment", e);
        }
    }

    private Field findField(Class<?> type, String fieldName) {
        Class<?> current = type;
        while (current != null && current != Object.class) {
            try {
                return current.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
                current = current.getSuperclass();
            }
        }
        return null;
    }
}
