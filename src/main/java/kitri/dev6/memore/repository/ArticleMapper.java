package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.response.ArticleResponseDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {
    @SelectProvider(type = Sql.class, method = "count")
    int count(SearchDto params);

    @SelectProvider(type = Sql.class, method = "findAll")
    List<Article> findAll(SearchDto searchDto);

//    @SelectProvider(type = Sql.class, method = "findAllWithBookAndMember")
//    @Results({
//            @Result(property = "")
//    })
//    List<ArticleResponseDto> findAllWithBookAndMember(SearchDto params);
//
//    @SelectProvider(type = Sql.class, method = "findAllWithBookAndMember2")
//    @Results({
//            @Result(property = "id", column = "a.id", id = true),
//            @Result(property = "name", column = "name"),
//            @Result(property = "email", column = "id", one = @One(select = "selectUserEmailById", fetchType = FetchType.LAZY)),
//            @Result(property = "telephoneNumbers", column = "id", many = @Many(select = "selectAllUserTelephoneNumberById", fetchType = FetchType.LAZY))
//    })
//    List<ArticleResponseDto> findAllWithBookAndMember2(SearchDto params);

    @Select("select * from article where member_id = #{memberId}")
    List<Article> findByMemberId(Long memberId);

    @Select("select * from article where book_id = #{bookId}")
    List<Article> findByBookId(Long bookId);

    @Select("select * from article where id = #{id}")
    Optional<Article> findById(Long id);

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
