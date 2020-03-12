package lz.cim.api.core.tool;

public class ErrorTool {

    public static String getErrerInfo(Exception ex) {

        StringBuilder stringBuilder = new StringBuilder();
        StackTraceElement stackTraceElement = ex.getStackTrace()[0];

        stringBuilder.append("File=" + stackTraceElement.getFileName());
        stringBuilder.append("\n");
        stringBuilder.append("Line=" + stackTraceElement.getLineNumber());
        stringBuilder.append("\n");

        stringBuilder.append("Method=" + stackTraceElement.getMethodName());
        stringBuilder.append("\n");
        stringBuilder.append("Message=" + ex.getMessage());

        return stringBuilder.toString();

    }
}
