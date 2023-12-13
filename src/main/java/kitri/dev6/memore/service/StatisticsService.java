package kitri.dev6.memore.service;

import kitri.dev6.memore.dto.statistics.ByMonthlyDto;
import kitri.dev6.memore.dto.statistics.ByYearlyDto;
import kitri.dev6.memore.repository.StatisticsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {
    private final StatisticsMapper statisticsMapper;
    public List<ByMonthlyDto> countGroupByMonth(Long memberId){
        return statisticsMapper.countGroupByMonth(memberId);
    }

    public List<ByYearlyDto> countGroupByYear(Long memberId) {
        return statisticsMapper.countGroupByYear(memberId);
    }
}
