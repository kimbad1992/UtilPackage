package util;

import org.springframework.http.server.PathContainer;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

// 스프링용 Path Parser
public class PathUtils {

    private static final PathPatternParser PARSER = new PathPatternParser();

    private PathUtils() {}

    public static boolean containsPath(List<String> uriList, String uri) {
        PathContainer pathContainer = PathContainer.parsePath(uri);
        return uriList.stream()
                .map(PARSER::parse)
                .anyMatch(pattern -> pattern.matches(pathContainer));
    }
}
