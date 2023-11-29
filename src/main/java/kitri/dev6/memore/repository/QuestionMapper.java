package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;


@Mapper
public interface QuestionMapper {
    @SelectProvider(type = Sql.class, method = "findQuestions")
    List<Question> findAll(SearchDto searchDto);

    @Select("select * from question where member_id=#{memberId}")
    List<Question> findByMemberId(Long memberId);

    @Select("select * from question where id = #{id}")
    Optional<Question> findById(Long id);

    @Update("update set title=#{title}, content=#{content} where id = #{id}")
    Question updateById(Question Question);


    @Insert("insert into question (member_id, title, content) values (#{memberId}, #{title}, #{content})")
    void insert(Question Question);

    // 찜 삭제
    @Delete("delete from question where id= #{id}")
    void delete(Long id);

    @Select("select count(*) from question")
    int count();
}
