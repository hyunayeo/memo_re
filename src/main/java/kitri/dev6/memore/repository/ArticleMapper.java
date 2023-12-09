package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @SelectProvider(type = Sql.class, method = "count")
    int count(SearchDto params);

    @SelectProvider(type = Sql.class, method = "findAll")
    List<Article> findAll(SearchDto searchDto);

    @SelectProvider(type = Sql.class, method = "findAll")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "isDone", column = "is_done"),
            @Result(property = "isHide", column = "is_hide"),
            @Result(property = "ratingScore", column = "rating_score"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "book", column = "book_id", one = @One(select = "kitri.dev6.memore.repository.BookMapper.findById", fetchType = FetchType.EAGER)),
            @Result(property = "member", column = "member_id", one = @One(select = "kitri.dev6.memore.repository.MemberMapper.findById", fetchType = FetchType.EAGER))
    })
    List<Article> findAllWithBookAndMember(SearchDto params);

    @Select("select * from article where #{bookId}=book_id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "isDone", column = "is_done"),
            @Result(property = "isHide", column = "is_hide"),
            @Result(property = "ratingScore", column = "rating_score"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "member", column = "member_id", one = @One(select = "kitri.dev6.memore.repository.MemberMapper.findById", fetchType = FetchType.EAGER))
    })
    List<Article> findAllWithMemberByBookId(Long bookId);

    @Select("select * from article where #{memberId}=member_id")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "content", column = "content"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "isDone", column = "is_done"),
            @Result(property = "isHide", column = "is_hide"),
            @Result(property = "ratingScore", column = "rating_score"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "createdAt", column = "created_at"),
    })
    List<Article> findAllByMemberId(Long memberId);

    @Select("select * from article where member_id = #{memberId}")
    List<Article> findByMemberId(Long memberId);

    @Select("select * from article where book_id = #{bookId}")
    List<Article> findByBookId(Long bookId);

    @Select("select * from article where id = #{id}")
    Article findById(Long id);

    @Insert("insert into article (member_id, book_id, title, content, is_done, start_date, end_date, rating_score, is_hide) " +
            " values ( #{memberId}, #{bookId}, #{title}, #{content}, #{isDone}, #{startDate}, #{endDate}, #{ratingScore}, #{isHide})")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    Long insert(Article article);

    @Update("update article set book_id=#{bookId}, title = #{title}, content = #{content}, is_done = #{isDone}, start_date = #{startDate}, end_date = #{endDate}, " +
            "rating_score = #{ratingScore}, is_hide = #{isHide}" +
            " where id = #{id}")
    Long updateById(Article article);

    @Delete("delete from article where id = #{id}")
    Long deleteById(Long id);

}
