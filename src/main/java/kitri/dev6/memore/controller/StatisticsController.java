package kitri.dev6.memore.controller;

import kitri.dev6.memore.configuration.auth.LoginUser;
import kitri.dev6.memore.configuration.auth.dto.SessionUser;
import kitri.dev6.memore.dto.statistics.ByCategoryDto;
import kitri.dev6.memore.dto.statistics.ByMonthlyDto;
import kitri.dev6.memore.dto.statistics.ByYearlyDto;
import kitri.dev6.memore.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.Session;
import java.util.List;

@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class StatisticsController {
    private final StatisticsService statisticsService;

    @GetMapping("/month/{id}")
    public ResponseEntity<List<ByMonthlyDto>> monthlyRecord(@PathVariable Long id) {
        return new ResponseEntity<>(statisticsService.countGroupByMonth(id), HttpStatus.OK);
    }

    @GetMapping("/year/{id}")
    public ResponseEntity<List<ByYearlyDto>> yearlyRecord(@PathVariable Long id) {
        return new ResponseEntity<>(statisticsService.countGroupByYear(id), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ByCategoryDto>> categoryRecord(@PathVariable Long id) {
        return new ResponseEntity<>(statisticsService.countGroupByCategory(id), HttpStatus.OK);
    }
    
    
    // Todo: 개발 마지막에 회원 확인하는 로직 추가
//    @GetMapping("/category/{id}")
//    public ResponseEntity<List<ByCategoryDto>> categoryRecord(@PathVariable Long id, @LoginUser SessionUser user) {
//        if (user.getId().equals(id)) {
//            return new ResponseEntity<>(statisticsService.countGroupByCategory(id), HttpStatus.OK);
//        } else return null;
//    }
}
