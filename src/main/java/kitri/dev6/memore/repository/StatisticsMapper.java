package kitri.dev6.memore.repository;

import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.statistics.ByCategoryDto;
import kitri.dev6.memore.dto.statistics.ByMonthlyDto;
import kitri.dev6.memore.dto.statistics.ByYearlyDto;
import kitri.dev6.memore.repository.sql.Sql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    @Select("select DATE_FORMAT(created_at,'%Y-%m') date, COUNT(*) count from article where member_id=#{memberId} " +
            "group by date order by date desc limit 3;")
    List<ByMonthlyDto> countGroupByMonth(Long memberId);
    @Select("select DATE_FORMAT(created_at,'%Y') date, COUNT(*) count from article where member_id=#{memberId} " +
            "group by date order by date desc limit 3;")
    List<ByYearlyDto> countGroupByYear(Long memberId);
    @Select("select c.name, count(*) count from article a join book b  on a.book_id = b.id " +
            "join category c on b.category_id =c.code " +
            "where a.member_id = #{memberId} group by c.name;")
    List<ByCategoryDto> countGroupByCategory(Long memberId);


}
