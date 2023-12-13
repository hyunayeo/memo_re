package kitri.dev6.memore.dto.statistics;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ByCategoryDto {
    private String name;
    private Integer count;
}
