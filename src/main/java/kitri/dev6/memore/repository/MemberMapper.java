package kitri.dev6.memore.repository;
import kitri.dev6.memore.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Select("select * from member")
    List<Member> findAll();

}
