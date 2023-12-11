package kitri.dev6.memore.repository.sql;

import kitri.dev6.memore.dto.common.SearchDto;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class Sql {
    public String findArticles(SearchDto params) {
        String searchType = params.getSearchType();
        String keyword = params.getSearchKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortType();

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
            } else {
                query.WHERE(QueryUtils.like("title", keyword));
                query.OR();
                query.WHERE(QueryUtils.like("content", keyword));
            }
        }
        query.ORDER_BY(QueryUtils.sortAs(sortType, sortOrder));
        // 페이징
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();
    }

    public String findBooks(SearchDto params) {
        String searchType = params.getSearchType();
        String keyword = params.getSearchKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortType();

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
        String keyword = params.getSearchKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortType();

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
        String keyword = params.getSearchKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortType();

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
        String keyword = params.getSearchKeyword();
        String sortOrder = params.getSortAs();
        String sortType = params.getSortType();

        SQL query = new SQL() {
            {
                SELECT("*");
                FROM("wish");
            }
        };
        if (!StringUtils.isEmpty(params.getSearchKeyword())) {
            if (!StringUtils.isEmpty(params.getSearchType())) {
                query.WHERE(QueryUtils.procSearchInput(searchType, keyword));
            }
        }
        query.ORDER_BY(QueryUtils.sortAs(sortType, sortOrder));
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        return query.toString();
    }

    public SQL filter(SearchDto params, boolean isCount) {
        SQL query = new SQL() {
            {
                if (params.getFilter().equals("category")) {
                    if (isCount) {
                        SELECT("count(*)");
                    } else {
                        SELECT("a.id id, a.title title, content, view_count, is_done, is_hide, rating_score, start_date, end_date, a.created_at created_at, book_id, a.member_id member_id");
                    }
                    FROM("article a");
                    JOIN("book b on a.book_id = b.id");
                    JOIN("category c on b.category_id = c.code");
                    JOIN("aladin_category ac on c.name = ac.name");
                    WHERE(QueryUtils.procSearchInput("ac.name", params.getFilterKeyword()));
                }
            }
        };
        System.out.println(query);
        return query.toString().isEmpty() ? null : query.getSelf();
    };
    public String findAll(SearchDto params) {
        SQL query = filter(params, false);

        if (query == null) {
            query = new SQL() {
                {
                    SELECT("*");
                    FROM(params.getDomainType());
                }
            };
        }

        if (!StringUtils.isEmpty(params.getSearchKeyword())) {
            if (!StringUtils.isEmpty(params.getSearchType())) {
                query.WHERE(QueryUtils.procSearchInput(params.getSearchType(), params.getSearchKeyword()));
            }
        }
        query.ORDER_BY(QueryUtils.sortAs(params.getSortType(), params.getSortAs()));
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();
    }

    public String findAllWithFetchJoin(SearchDto params) {
        SQL query = new SQL() {
            {
                SELECT("a.id, a.title, a.content, a.view_count, a.rating_score,a.is_done, a.is_hide");
                SELECT("a.start_date, a.end_date,a.book_id, a.member_id, a.created_at, a.modified_at, a.deleted_at");
                SELECT("b.id, b.category_id, b.member_id, b.title, b.isbn13, b.cover, b.link, b.description, b.author");
                SELECT("b.publisher, b.published_date, b.approved, m.id, m.name, m.picture");
                FROM("article a");
                INNER_JOIN("book b on a.book_id = b.id");
                INNER_JOIN("member m on a.member_id = m.id");
            }
        };

        if (!StringUtils.isEmpty(params.getSearchKeyword())) {
            if (!StringUtils.isEmpty(params.getSearchType())) {
                query.WHERE(QueryUtils.procSearchInput(params.getSearchType(), params.getSearchKeyword()));
            }
        }
        query.ORDER_BY(QueryUtils.sortAs(params.getSortType(), params.getSortAs()));
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();

    }

    public String count(SearchDto params) {
//        SQL query = new SQL() {
//            {
//                SELECT("count(*)");
//                FROM(params.getDomainType());
//            }
//        };
        SQL query = filter(params, true);

        if (query == null) {
            query = new SQL() {
                {
                    SELECT("count(*)");
                    FROM(params.getDomainType());
                }
            };
        }
        if (!StringUtils.isEmpty(params.getSearchKeyword())) {
            if (!StringUtils.isEmpty(params.getSearchType())) {
                query.WHERE(QueryUtils.procSearchInput(params.getSearchType(), params.getSearchKeyword()));
            }
        }
        System.out.println(query);
        return query.toString();
    }

}
