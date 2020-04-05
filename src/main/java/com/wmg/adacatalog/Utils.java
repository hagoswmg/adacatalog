package com.wmg.adacatalog;

import com.fasterxml.jackson.databind.JsonNode;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public final class Utils {

    public static String trimString(String str) {
        if (str == null || str.trim().equals("")) {
            return null;
        }
        return str.trim();
    }

    public static List<String> getCleansedString(String input, String delim) {
        // remove duplicates and trim excess white space
        return Collections.list(new StringTokenizer(input, delim))
                .stream()
                .map(token -> trimString((String) token))
                .distinct()
                .collect(Collectors.toList());
    }

    public static Map<String, JsonNode> processJsonNode(JsonNode node) {
        Map<String, JsonNode> map = new LinkedHashMap<>();
        Iterator<String> fields = node.fieldNames();

        while (fields.hasNext()) {
            String field = fields.next();
            map.put(field, node.get(field));
        }

        return map;
    }

    public static String getAttribute(Map<String, JsonNode> map, String str) {
        return (!isNull(map.get(str))) ? trimString(map.get(str).asText()) : null;
    }

    /**
     * @param str - input string
     * @return - a string with all white space removed
     */
    public static String trimAndSqueezeWs(String str) {
        return str != null ? str.
                replaceAll("\r", "").
                replaceAll("\n", "").
                replaceAll("\\s+", " ").trim() : "";
    }

    public static String getDate(String transactionDate) {
        if (trimString(transactionDate) == null) {
            return null;
        }
        final String INPUT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

        SimpleDateFormat format = new SimpleDateFormat(INPUT_DATE_FORMAT);
        try {
            Date date = format.parse(transactionDate);
            return new SimpleDateFormat(Constants.DATE_FORMAT).format(new Timestamp(date.getTime()));
        } catch (ParseException e) {
            return null;
        }
    }
}
