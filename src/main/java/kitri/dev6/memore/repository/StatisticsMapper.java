package kitri.dev6.memore.repository;

import kitri.dev6.memore.dto.statistics.ByMonthlyDto;
import kitri.dev6.memore.dto.statistics.ByYearlyDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    @Select("select DATE_FORMAT(created_at,'%Y-%m') date, COUNT(*) count from article where member_id=#{memberId} " +
            "group by date order by date desc limit 3;")
    List<ByMonthlyDto> countGroupByMonth(Long memberId);
    @Select("select DATE_FORMAT(created_at,'%Y`) date, COUNT(*) count from article where member_id=#{memberId} " +
            "group by date order by date desc limit 3;")
    List<ByYearlyDto> countGroupByYear(Long memberId);
}
