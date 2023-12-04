package kitri.dev6.memore.dto.open_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import kitri.dev6.memore.dto.response.BookResponseDto;
import lombok.*;

import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverApiResponseDto {
    private String lastBuildDate;
    private int total;
    private int start;
    private int display;
    private List<NaverBookVO> items;
}
