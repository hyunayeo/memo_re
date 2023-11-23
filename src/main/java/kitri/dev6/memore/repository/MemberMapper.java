package kitri.dev6.memore.repository;

import kitri.dev6.memore.entity.Member;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Select("select * from member")
    List<Member> findAll();

    @Select("select * from member where id = #{id}")
    Member findById(Long id);

    @Insert("insert into member(email, number, name, password, picture) values (#{email}, #{number}, #{name}, #{password}, #{picture})")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    void insert(Member member);

    @Delete("update member set deleted_at = now() where id = #{id}")
    void deleteById(Long id);

    @Update("update member set name=#{name}, number=#{number}, password=#{password}, picture=#{picture} where id = #{id}")
    Long updateById(Member member);
}
