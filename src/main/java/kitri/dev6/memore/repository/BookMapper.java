package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {

    @SelectProvider(type = Sql.class, method = "count")
    int count(SearchDto params);

    @SelectProvider(type = Sql.class, method = "findAll")
    List<Book> findAll(SearchDto searchDto);

    @Select("select * from book where id = #{id}")
    Optional<Book> findById(Long id);

    @Select("select * from book where id = #{id}")
    Book findById2(@Param("book_id") Long id);

    @Select("select * from book where isbn13 = #{isbn}")
    Optional<Book> findByIsbn(Long isbn);

    @Select("select * from book where member_id=#{memberID}")
    List<Book> findAllByMemberId(Long memberId);

    @Insert("insert into book (category_id, member_id, title, isbn, isbn13, cover, link, description, author, publisher, published_date, approved) " +
            "values (#{categoryId}, #{memberId}, #{title}, #{isbn}, #{isbn13}, #{cover}, #{link}, " +
            "#{description}, #{author}, #{publisher}, #{publishedDate}, #{approved})")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    Long insert(Book book);

//    @Delete("delete from book where id = #{id}")
    @Update("update book set deleted_at = now() where id = #{id}")
    void deleteById(Long id);

    @Update("update book set category_id=#{categoryId}, member_id=#{memberId}, title=#{title}, isbn=#{isbn}," +
            "isbn13=#{isbn13}, cover=#{cover}, link=#{link}, description=#{description}, author=#{author}, publisher=#{publisher}," +
            "published_date=#{publishedDate}, approved=#{approved}, modified_at=now() where id = #{id}")
    void updateById(Book book);

}
