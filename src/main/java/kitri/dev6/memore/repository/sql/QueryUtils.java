package kitri.dev6.memore.repository.sql;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class QueryUtils {
    static HashSet<String> likeCompareSet =
            new HashSet<>(List.of(new String[]{"title", "content"}));
    static HashSet<String> exactCompareSet =
            new HashSet<>(List.of(new String[]{"id", "memberId", "bookId", "viewCount", "ratingScore"}));

    public static String like(String searchType, String searchKeyword) {
        return searchType + " LIKE CONCAT('%', '" + searchKeyword + "', '%')";
    }
    public static String sortAs(String searchType, String searchKeyword) {
        return searchType + " " + searchKeyword;
    }
    public static String equals(String searchType, String searchKeyword) {
        return searchType + "=" + searchKeyword;
    }
    public static String procSearchInput(String searchType, String searchKeyword) {
        if (!likeCompareSet.add(searchType)) return like(searchType, searchKeyword);
        if (!exactCompareSet.add(searchType)) return equals(searchType, searchKeyword);
        return "";
    }
}
