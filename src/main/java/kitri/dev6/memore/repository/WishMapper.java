package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WishMapper {

    @SelectProvider(type = Sql.class, method = "count")
    int count(SearchDto params);

    @SelectProvider(type = Sql.class, method = "findAll")
    List<Wish> findAll(SearchDto searchDto);

    @Select("select * from wish where id = #{id}")
    Optional<Wish> findById(Long id);

    @Select("select * from wish where member_id = #{memberId}")
    List<Wish> findByMemberId(Long memberId);

    // 찜 등록
    @Insert("insert into wish (member_id, book_id) values (#{memberId}, #{bookId});")
    void insert(Wish wish);

    // 찜 삭제
    @Delete("delete from wish where member_id = #{memberId} and id= #{id};")
    void delete(Long id, Long memberId);

}
