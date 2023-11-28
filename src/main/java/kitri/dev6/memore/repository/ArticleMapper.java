package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {

    @Select("select * from article")
    List<Article> findAll();
    @Select("select * from article where member_id = #{memberId}")
    List<Article> findByMemberId(Long memberId);
    @Select("select * from article where id = #{id}")
    Optional<Article> findById(Long id);

    @Insert("insert into article (member_id, book_id, title, content, is_done, start_date, end_date, rating_score, is_hide) " +
            " values ( #{memberId}, #{bookId}, #{title}, #{content}, #{isDone}, #{startDate}, #{endDate}, #{ratingScore}, #{isHide})")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    Long insert(Article article);

    @Update("update article set member_id=#{memberId}, book_id=#{bookId}, title = #{title}, content = #{content}, is_done = #{isDone}, start_date = #{startDate}, end_date = #{endDate}, " +
            "rating_score = #{ratingScore}, is_hide = #{isHide}" +
            " where id = #{id}")
    Long updateById(Article article);

    @Delete("delete from article where id = #{id}")
    Long deleteById(Long id);

}
