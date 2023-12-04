package kitri.dev6.memore.dto.open_api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.List;

// items에 들어갈 VO

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AladinBookVO {
    private String title;
    private String link;
    private String author;
    private String pubDate;
    private String description;
    private String isbn;
    private String isbn13;
    private Long itemId;
    private Long priceSales;
    private Long priceStandard;
    private String mallType;
    private String cover;
    private Long categoryId;
    private String categoryName;
    private String publisher;
    private Long customerReviewRank;
}
