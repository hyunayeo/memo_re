package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Wish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface WishMapper {

    @Select("select * from wish")
    List<Wish> findAll();

    @Select("select * from wish where id = #{id}")
    Wish findById(Long id);
}
