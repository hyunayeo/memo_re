package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Question;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;


@Mapper
public interface QuestionMapper {
    @Select("select * from question")
    List<Question> findAll();

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

}
