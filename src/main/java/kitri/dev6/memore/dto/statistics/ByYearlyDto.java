package kitri.dev6.memore.dto.statistics;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ByYearlyDto {
    private String date;
    private Integer count;
}
