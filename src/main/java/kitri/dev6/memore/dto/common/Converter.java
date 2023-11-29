package kitri.dev6.memore.dto.common;

import kitri.dev6.memore.domain.*;
import kitri.dev6.memore.dto.response.*;

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
        if (domains.get(0) instanceof Book) {
            return domains.stream().map(domain -> {
                return new BookResponseDto((Book) domain);
            }).collect(Collectors.toList());
        }
        if (domains.get(0) instanceof Member) {
            return domains.stream().map(domain -> {
                return new MemberResponseDto((Member) domain);
            }).collect(Collectors.toList());
        }
        if (domains.get(0) instanceof Question) {
            return domains.stream().map(domain -> {
                return new QuestionResponseDto((Question) domain);
            }).collect(Collectors.toList());
        }
        if (domains.get(0) instanceof Wish) {
            return domains.stream().map(domain -> {
                return new WishResponseDto((Wish) domain);
            }).collect(Collectors.toList());
        }
        return null;
    }
}
