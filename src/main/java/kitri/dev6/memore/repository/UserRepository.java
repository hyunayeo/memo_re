package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.user.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;
@Mapper
public interface UserRepository {
    @Select("select * from member_oauth where email=#{email}")
    Optional<User> findByEmail(String email);
    @Insert("insert into member_oauth (email, name, picture, role) values (#{email}, #{name}, #{picture}, #{role}) on duplicate key update name=#{name}, picture=#{picture}")
    void save(User user);
}
