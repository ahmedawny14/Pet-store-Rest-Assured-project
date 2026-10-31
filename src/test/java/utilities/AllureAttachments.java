package utilities;

import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;

public final class AllureAttachments {

    private AllureAttachments() {}

    public static void attachFailureDetails(ITestResult result, Response response) {
        if (result.isSuccess()) {
            return;
        }

        Throwable exception = result.getThrowable();
        if (exception != null) {
            Allure.addAttachment("Assertion Failure Details", "text/plain",
                    exception.toString());

            StringWriter stackTrace = new StringWriter();
            exception.printStackTrace(new PrintWriter(stackTrace));
            Allure.addAttachment("Failure Stack Trace", "text/plain",
                    stackTrace.toString());
        }

        Allure.addAttachment("Failed Test Name", "text/plain",
                result.getMethod().getMethodName());

        if (response != null) {
            Allure.addAttachment("API Response Status", "text/plain",
                    String.valueOf(response.statusCode()));
            Allure.addAttachment("API Response Body", "application/json",
                    response.asPrettyString());
            Allure.addAttachment("API Response Headers", "text/plain",
                    response.getHeaders().toString());
        }
    }
}
