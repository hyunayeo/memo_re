package kitri.dev6.memore.dto.common;

import kitri.dev6.memore.domain.Article;
import kitri.dev6.memore.dto.response.ArticleResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {
    public static List<Object> domainListTodtoList(List<? extends Object> domains) {
        if (domains.isEmpty()) return null;

        if (domains.get(0) instanceof Article) {
            return domains.stream().map(domain -> {
                return new ArticleResponseDto((Article) domain);
            }).collect(Collectors.toList());
        }
        return null;
    }
}
