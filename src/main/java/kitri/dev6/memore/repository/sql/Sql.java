package kitri.dev6.memore.repository.sql;

import kitri.dev6.memore.dto.common.SearchDto;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class Sql {
    public String findArticles(SearchDto params) {
        String searchType = params.getSearchType();
        String keyword = params.getKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortFieldType();

        SQL query = new SQL() {
            {
                SELECT("*");
                FROM("article");
            }
        };

        // 검색어
        if (!StringUtils.isEmpty(keyword)) {
            if (!StringUtils.isEmpty(searchType)) {
                query.WHERE(QueryUtils.like(searchType, keyword));
            }
            query.WHERE(QueryUtils.like("title", keyword));
            query.OR();
            query.WHERE(QueryUtils.like("content", keyword));
        }
        query.ORDER_BY(QueryUtils.sortAs(sortType, sortOrder));
        // 페이징
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();
    }

    public String findBooks(SearchDto params) {
        String searchType = params.getSearchType();
        String keyword = params.getKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortFieldType();

        SQL query = new SQL() {
            {
                SELECT("*");
                FROM("book");
            }
        };

        // 검색어
        if (!StringUtils.isEmpty(keyword)) {
            if (!StringUtils.isEmpty(searchType)) {
                query.WHERE(QueryUtils.like(searchType, keyword));
            }
            query.WHERE(QueryUtils.like("title", keyword));
            query.OR();
            query.WHERE(QueryUtils.like("author", keyword));
        }
        query.ORDER_BY(QueryUtils.sortAs(sortType, sortOrder));
        // 페이징
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();
    }

    public String findMembers(SearchDto params) {
        String searchType = params.getSearchType();
        String keyword = params.getKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortFieldType();

        SQL query = new SQL() {
            {
                SELECT("*");
                FROM("member");
            }
        };

        // 검색어
        if (!StringUtils.isEmpty(keyword)) {
            if (!StringUtils.isEmpty(searchType)) {
                query.WHERE(QueryUtils.like(searchType, keyword));
            }
            query.WHERE(QueryUtils.like("name", keyword));
            query.OR();
            query.WHERE(QueryUtils.like("email", keyword));
        }
        query.ORDER_BY(QueryUtils.sortAs(sortType, sortOrder));
        // 페이징
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();
    }

    public String findQuestions(SearchDto params) {
        String searchType = params.getSearchType();
        String keyword = params.getKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortFieldType();

        SQL query = new SQL() {
            {
                SELECT("*");
                FROM("question");
            }
        };

        // 검색어
        if (!StringUtils.isEmpty(keyword)) {
            if (!StringUtils.isEmpty(searchType)) {
                query.WHERE(QueryUtils.like(searchType, keyword));
            }
            query.WHERE(QueryUtils.like("title", keyword));
            query.OR();
            query.WHERE(QueryUtils.like("content", keyword));
        }
        query.ORDER_BY(QueryUtils.sortAs(sortType, sortOrder));
        // 페이징
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();
    }

    public String findWishes(SearchDto params) {
        String searchType = params.getSearchType();
        String keyword = params.getKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortFieldType();

        SQL query = new SQL() {
            {
                SELECT("*");
                FROM("wish");
            }
        };
        // 검색어
        if (!StringUtils.isEmpty(keyword)) {
            if (!StringUtils.isEmpty(searchType)) {
                query.WHERE(QueryUtils.like(searchType, keyword));
            }
            query.WHERE(QueryUtils.like("title", keyword));
            query.OR();
            query.WHERE(QueryUtils.like("content", keyword));
        }
        query.ORDER_BY(QueryUtils.sortAs(sortType, sortOrder));
        // 페이징
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();
    }
}
