package kitri.dev6.memore.repository.sql;

import kitri.dev6.memore.dto.common.SearchDto;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Sql {
    static String tableAlias = "";
    public SQL filter(SearchDto params, boolean isCount) throws UnsupportedEncodingException {
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
//                    JOIN("aladin_category ac on c.name = ac.name");
                    WHERE(QueryUtils.procSearchInput("c.name", URLDecoder.decode(params.getFilterKeyword(), "UTF-8")));
                }

                if (params.getDomainType().equals("article")) {
                    if (params.getSearchType().equals("writer")) {
                        if (isCount) {
                            SELECT("count(*)");
                        } else {
                            SELECT("article.*");
                        }
                        FROM("article");
                        JOIN("member m on article.member_id = m.id");
                        WHERE(QueryUtils.procSearchInput("m.name", URLDecoder.decode(params.getSearchKeyword(), "UTF-8")));
                    }
                }
            }
        };
        System.out.println(query);
        return query.toString().isEmpty() ? null : query.getSelf();
    };
    public String findAll(SearchDto params) throws UnsupportedEncodingException {
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
                query.AND();
                query.WHERE(QueryUtils.procSearchInput(params.getSearchType2(), params.getSearchKeyword2()));
                // article 뒤에 어떤 키워드가 붙어있는지 확인하여 붙여주기...
            }
        }
        query.ORDER_BY(QueryUtils.sortAs(params.getSortType(), params.getSortAs()));
        query.LIMIT("#{pagination.limitStart}, #{recordSize}");

        System.out.println(query);
        return query.toString();
    }

    public String count(SearchDto params) throws UnsupportedEncodingException {
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
