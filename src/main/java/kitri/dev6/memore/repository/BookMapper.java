package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Book;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookMapper {

    @Select("select * from book where id = #{id}")
    Optional<Book> findById(Long id);

    @Select("select * from book")
    List<Book> findAll();

    @Select("select * from book where member_id=#{memberID}")
    List<Book> findAllByMemberId(Long memberId);

    @Insert("insert into book (category_id, member_id, title, isbn, isbn13, cover, link, description, author, publisher, published_date, approved) " +
            "values (#{categoryId}, #{memberId}, #{title}, #{isbn}, #{isbn13}, #{cover}, #{link}, " +
            "#{description}, #{author}, #{publisher}, #{publishedDate}, #{approved})")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    void insert(Book book);

//    @Delete("delete from book where id = #{id}")
    @Update("update book set deleted_at = now() where id = #{id}")
    void deleteById(Long id);

    @Update("update book set category_id=#{categoryId}, member_id=#{memberId}, title=#{title}, isbn=#{isbn}," +
            "isbn13=#{isbn13}, cover=#{cover}, link=#{link}, description=#{description}, author=#{author}, publisher=#{publisher}," +
            "published_date=#{publishedDate}, approved=#{approved}, modified_at=now() where id = #{id}")
    void updateById(Book book);
}
