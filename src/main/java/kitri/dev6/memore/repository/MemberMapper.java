package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Member;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    @SelectProvider(type = Sql.class, method = "count")
    int count(SearchDto params);

    @SelectProvider(type = Sql.class, method = "findAll")
    List<Member> findAll(SearchDto searchDto);

    @SelectProvider(type = Sql.class, method = "findAll")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "number", column = "number"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "createdAt", column = "createdAt"),
            @Result(property = "modifiedAt", column = "modifiedAt"),
            @Result(property = "deletedAt", column = "deletedAt"),
            @Result(property = "articles", column = "id", many = @Many(select = "kitri.dev6.memore.repository.ArticleMapper.findAllByMemberId", fetchType = FetchType.EAGER)),
            @Result(property = "questions", column = "id", many = @Many(select = "kitri.dev6.memore.repository.QuestionMapper.findAllByMemberId", fetchType = FetchType.EAGER)),
            @Result(property = "wishes", column = "id", many = @Many(select = "kitri.dev6.memore.repository.WishMapper.findAllByMemberId", fetchType = FetchType.EAGER)),
    })
    List<Member> findAllWithArticlesAndWishesAndQuestions(SearchDto params);

    @Select("select * from member where id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "email", column = "email"),
            @Result(property = "number", column = "number"),
            @Result(property = "name", column = "name"),
            @Result(property = "password", column = "password"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "createdAt", column = "createdAt"),
            @Result(property = "modifiedAt", column = "modifiedAt"),
            @Result(property = "deletedAt", column = "deletedAt"),
            @Result(property = "articles", column = "id", many = @Many(select = "kitri.dev6.memore.repository.ArticleMapper.findAllByMemberId", fetchType = FetchType.EAGER)),
            @Result(property = "questions", column = "id", many = @Many(select = "kitri.dev6.memore.repository.QuestionMapper.findAllByMemberId", fetchType = FetchType.EAGER)),
            @Result(property = "wishes", column = "id", many = @Many(select = "kitri.dev6.memore.repository.WishMapper.findAllByMemberId", fetchType = FetchType.EAGER)),
    })
    Member findById(Long id);

    @Insert("insert into member(email, number, name, password, picture) values (#{email}, #{number}, #{name}, #{password}, #{picture})")
    @SelectKey(statement = "select last_insert_id()", keyColumn = "id", keyProperty = "id", before = false, resultType = Long.class)
    void insert(Member member);

    @Delete("delete from member where id = #{id}")
    void delete(Long id);

    @Update("update member set name=#{name}, number=#{number}, password=#{password}, picture=#{picture}, modified_at=now() where id = #{id}")
    Long update(Member member);

    @Select("select * from member where email=#{email}")
    Optional<Member> findByEmail(String email);
    @Insert("insert into member (email, name, picture, role, social) values (#{email}, #{name}, #{picture}, #{role}, #{social}) on duplicate key update name=#{name}, picture=#{picture}")
    void save(Member member);

}
