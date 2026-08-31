package utils;

import io.qameta.allure.Attachment;

public class AllureLogger {
    @Attachment(value = "{0}", type = "text/plain")
    public static String logToAllure(String messageName, String messageBody) {
        return messageBody;
    }
}
