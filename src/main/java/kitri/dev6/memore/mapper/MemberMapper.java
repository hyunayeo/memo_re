package kitri.dev6.memore.mapper;

import kitri.dev6.memore.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
    @Select("SELECT * FROM member WHERE id = #{id}")
    Member findById(Long id);
}
