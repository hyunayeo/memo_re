package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Book;
import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface BookMapper {

    @SelectProvider(type = Sql.class, method = "count")
    int count(SearchDto params);

    @SelectProvider(type = Sql.class, method = "findAll")
    List<Book> findAll(SearchDto searchDto);

    @SelectProvider(type = Sql.class, method = "findAll")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "link", column = "link"),
            @Result(property = "author", column = "author"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "modifiedAt", column = "modified_at"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "articles", column = "id", many = @Many(select = "kitri.dev6.memore.repository.ArticleMapper.findAllWithMemberByBookId", fetchType = FetchType.EAGER)),
    })
    List<Book> findAllWithArticles(SearchDto params);

    @Select("select * from book where id = #{id}")
    Book findById(Long id);

    @Select("select * from book where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "link", column = "link"),
            @Result(property = "author", column = "author"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "modifiedAt", column = "modified_at"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "articles", column = "id", many = @Many(select = "kitri.dev6.memore.repository.ArticleMapper.findAllWithMemberByBookId", fetchType = FetchType.EAGER)),
    })
    Book findWithArticlesById(Long id);

    @Select("select * from book where isbn = #{isbn}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "isbn", column = "isbn"),
            @Result(property = "viewCount", column = "view_count"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "link", column = "link"),
            @Result(property = "author", column = "author"),
            @Result(property = "publisher", column = "publisher"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "modifiedAt", column = "modified_at"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "categoryId", column = "category_id"),
            @Result(property = "articles", column = "id", many = @Many(select = "kitri.dev6.memore.repository.ArticleMapper.findAllWithMemberByBookId", fetchType = FetchType.EAGER)),
    })
    Book findWithArticlesByIsbn(String isbn);

    @Select("select * from book where member_id=#{memberID}")
    List<Book> findByMemberId(Long memberId);

    @Insert("insert into book (category_id, member_id, title, isbn, cover, link, description, author, publisher, published_date, approved) " +
            "values (#{categoryId}, #{memberId}, #{title}, #{isbn}, #{cover}, #{link}, " +
            "#{description}, #{author}, #{publisher}, #{publishedDate}, #{approved})")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    Long insert(Book book);

    @Update("update book set deleted_at = now() where id = #{id}")
    void delete(Long id);

    @Update("update book set category_id=#{categoryId}, member_id=#{memberId}, title=#{title}, isbn=#{isbn}," +
            "cover=#{cover}, link=#{link}, description=#{description}, author=#{author}, publisher=#{publisher}," +
            "published_date=#{publishedDate}, approved=#{approved}, modified_at=now() where id = #{id}")
    void update(Book book);

<<<<<<< HEAD


    // 기타
    // @Select("select name from category where #{id} = code")
=======
    @Select("select distinct `1depth` from aladin_category where cid = #{categoryId}")
    String findFirstDepthByCid(Long categoryId);

    @Select("select code from category where #{name} = name")
    Long findCategoryIdByName(String name);

>>>>>>> feature/#27-category
}
