package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Wish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WishMapper {

    // 전체 찜 조회
    @Select("select * from wish")
    List<Wish> findAll();

    // 검색 찜 조회
    @Select("select * from wish where id = #{id}")
    Wish findById(Long id);

    // 찜 등록
    @Insert("insert into wish (member_id, book_id) values (#{member_id}, #{book_id});")
    void create(String member_id, String book_id);

    // 찜 삭제
    @Delete("delete from wish where member_id = #{member_id} and id= #{id};")
    void delete(Long id, String member_id);
}
