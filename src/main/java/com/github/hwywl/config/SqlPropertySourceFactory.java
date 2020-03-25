package com.github.hwywl.config;

import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * SQL组装工厂
 *
 * @author huangwenyi
 * @date 2020-3-25 11:03:20
 */
public class SqlPropertySourceFactory implements PropertySourceFactory {

    /**
     * 可执行SQL注入key
     */
    private static final String KEY_LEADING = "--!";
    /**
     * 注释前缀
     */
    private static final String NOTES_AND_LABELS = "--";

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        Deque<Pair> queries = new LinkedList<>();
        new BufferedReader(resource.getReader()).lines().forEach(line -> {
            if (line.startsWith(KEY_LEADING)) {
                queries.addLast(new Pair(line.replaceFirst(KEY_LEADING, "")));
            } else if (line.startsWith(NOTES_AND_LABELS)) {
                //跳过注释
            } else if (!line.trim().isEmpty()) {
                Optional.ofNullable(queries.getLast()).ifPresent(pair -> pair.lines.add(line));
            }
        });

        Map<String, Object> sqlMap = queries.stream()
                .filter(pair -> !pair.lines.isEmpty())
                .collect(Collectors.toMap(pair -> pair.key,
                        pair -> String.join(System.lineSeparator(), pair.lines),
                        (r, pair) -> r, LinkedHashMap::new));
        return new MapPropertySource(resource.toString(), sqlMap);
    }

    private static class Pair {
        private String key;
        private List<String> lines = new LinkedList<>();

        Pair(String key) {
            this.key = key;
        }
    }
}
