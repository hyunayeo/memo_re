package kitri.dev6.memore.repository.sql;

import kitri.dev6.memore.dto.common.Pagination;
import kitri.dev6.memore.dto.common.SearchDto;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import javax.management.Query;

public class Sql {
    public String findArticles(SearchDto params) {
        String searchType = params.getSearchType();
        String keyword = params.getKeyword();

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
        query.ORDER_BY("id DESC");
        // 페이징
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        return query.toString();
    }

    public String findAll(SearchDto params) {
        String domainType = params.getDomainType();
        String searchType = params.getSearchType();
        String keyword = params.getKeyword();

        SQL query = new SQL() {
            {
                SELECT("*");
                FROM(domainType);
            }
        };
        if (!StringUtils.isEmpty(keyword)) {
            if (!StringUtils.isEmpty(searchType)) {
                query.WHERE(QueryUtils.like(searchType, keyword));
            }
            query.WHERE(QueryUtils.like("title", keyword));
            query.OR();
            query.WHERE(QueryUtils.like("content", keyword));
        }
        System.out.println(query);
        return query.toString();
    }

    public String count(SearchDto params) {
        String domainType = params.getDomainType();
        SQL query = new SQL() {
            {
                SELECT("COUNT(*)");
                FROM(domainType);
            }
        };
        return query.toString();
    }
}
