package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.domain.Question;
import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WishMapper {


    @SelectProvider(type = Sql.class, method = "findAll")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "bookId", column = "book_id"),
            @Result(property = "book", column = "book_id", one = @One(select = "kitri.dev6.memore.repository.BookMapper.findById", fetchType = FetchType.EAGER)),
    })
    List<Wish> findByWishMemberId(SearchDto params);

    @SelectProvider(type = Sql.class, method = "count")
    int count(SearchDto params);

    @SelectProvider(type = Sql.class, method = "findAll")
    List<Wish> findAll(SearchDto searchDto);

    @Select("select * from wish where id = #{id}")
    Wish findById(Long id);

    @Select("select * from wish where member_id = #{memberId}")
    List<Wish> findAllByMemberId(Long memberId);

    // 찜 등록
    @Insert("insert into wish (member_id, book_id) values (#{memberId}, #{bookId});")
    void insert(Wish wish);

    // 찜 삭제
    @Delete("delete from wish where member_id = #{memberId} and book_id= #{bookId};")
    void delete(Long bookId, Long memberId);

}
