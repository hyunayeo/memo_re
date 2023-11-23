package kitri.dev6.memore.repository;
import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.domain.Wish;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.*;

@Mapper
public interface ArticleMapper {

    @Select("select * from article")
    List<Article> findAll();

    @Select("select * from article where id = #{id}")
    Article findById(Long id);

    @Insert("insert into article (member_id, book_id, title, content, done, start_date, end_date, rating_score, is_hide) " +
            " values ( #{memberId}, #{bookId}, #{title}, #{content}, #{done}, #{startDate}, #{endDate}, #{ratingScore}, #{isHide})")
//    @Options(useGeneratedKeys=true, keyProperty="id")
    Long insert(Article article);

    @Update("update article set title = #{title}, content = #{content}, done = #{done}, start_date = #{startDate}, end_date = #{endDate}, rating_score = #{ratingScore}, is_hide = #{isHide}" +
            " where id = #{id}")
    Long update(Article article);

    @Delete("delete from article where id = #{id}")
    Long delete(Long id);

}
