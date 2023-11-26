package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Book;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BookMapper {

    @Select("select * from book where id = #{id}")
    Book findById(Long id);

    @Select("select * from book")
    List<Book> findAll();

    @Insert("insert into book (category_id, member_id, title, isbn, isbn13, cover, link, description, author, publisher, published_date, approved) " +
            "values (#{category_id}, #{member_id}, #{title}, #{isbn}, #{isbn13}, #{cover}, #{link}, " +
            "#{description}, #{author}, #{publisher}, #{published_date}, #{approved})")
    void create(Book book);
}
