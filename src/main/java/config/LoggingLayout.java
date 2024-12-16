package config;

import java.util.logging.LogRecord;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Logback에서 이미지 Base64 필드 등 너무 긴 값이 로그에 찍히는 경우 생략시키기
 * 정규식 : "...":"..."과 같은 JSON 필드 || ...=....과 같은 특정 자바 객체의 ToString 필드
 */
public class LoggingLayout extends PatternLayout{

    private static final int MAX_LENGTH = 50;
    private static final String REPLACEMENT = "...";
    private static final Pattern PATTERN = Pattern.compile("\"(\\w+)\"\\s*:\\s*\"([^\"]{50,})\"|(\\w+=)([A-za-z0-9+/=]{50,})");

    @Override
    public String doLayout(final ILoggingEvent event) {
        String originalMessage = super.doLayout(event);
        return processMessage(originalMessage);
    }

    private String processMessage(final String originalMessage) {
        StringBuilder sb = new StringBuilder();


        while (true) {
            Matcher matcher = PATTERN.matcher(sb);
            boolean found = false;

            while (matcher.find()) {
                if (matcher.group(1) != null && matcher.group(2) != null) {
                    // JSON Case
                    String key = matcher.group(1);
                    String value = matcher.group(2);
                    int start = matcher.start(2);
                    int end = matcher.end(2);

                    if (value.length() > MAX_LENGTH) {
                        sb.replace(start, end, REPLACEMENT);
                        found = true;
                        break;
                    }
                } else if (matcher.group(3) != null && matcher.group(4) != null) {
                    String key = matcher.group(3);
                    String value = matcher.group(4);
                    int start = matcher.start(4);
                    int end = matcher.end(4);

                    if (value.length() > MAX_LENGTH) {
                        sb.replace(start, end, REPLACEMENT);
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                break;
            }
        }

        return sb.toString();
    }
}
