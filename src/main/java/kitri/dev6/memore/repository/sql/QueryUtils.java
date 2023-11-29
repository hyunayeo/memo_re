package kitri.dev6.memore.repository.sql;

public class QueryUtils {
    public static String like(String property, String value) {
        return property + " LIKE CONCAT('%', '" + value + "', '%')";
    }
    public static String sortAs(String property, String value) {
        return property + " " + value;
    }
}