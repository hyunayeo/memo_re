package kitri.dev6.memore.repository.sql;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class QueryUtils {
    static HashSet<String> likeCompareSet =
            new HashSet<>(List.of(new String[]{"title", "content"}));
    static HashSet<String> exactCompareSet =
            new HashSet<>(List.of(new String[]{"id", "member_id", "book_id", "category_id", "view_count", "rating_score"}));
    static HashSet<String> exactCompareWithQuotesSet =
            new HashSet<>(List.of(new String[]{"name", "c.name", "email" }));

    public static String like(String searchType, String searchKeyword) {
        return searchType + " LIKE CONCAT('%', '" + searchKeyword + "', '%')";
    }
    public static String sortAs(String searchType, String searchKeyword) {
        return searchType + " " + searchKeyword;
    }
    public static String equals(String searchType, String searchKeyword) {
        return searchType + "=" + searchKeyword;
    }
    public static String equalsWithQuotes(String searchType, String searchKeyword) {
        return searchType + "='" + searchKeyword + "'";
    }
    public static String procSearchInput(String searchType, String searchKeyword) {
        if (likeCompareSet.stream().anyMatch((word) -> word.equals(searchType))) return like(searchType, searchKeyword);
        if (exactCompareSet.stream().anyMatch((word) -> word.equals(searchType))) return equals(searchType, searchKeyword);
        if (exactCompareWithQuotesSet.stream().anyMatch((word) -> word.equals(searchType))) return equalsWithQuotes(searchType, searchKeyword);
        return "true";
    }

}
