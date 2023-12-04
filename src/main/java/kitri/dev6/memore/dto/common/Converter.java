package kitri.dev6.memore.dto.common;

import kitri.dev6.memore.domain.*;
import kitri.dev6.memore.dto.open_api.AladinBookVO;
import kitri.dev6.memore.dto.open_api.NaverBookVO;
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
    public static List<Object> openApiListTodtoList(List<? extends Object> apiRespList) {
        if (apiRespList.isEmpty()) return null;

        if (apiRespList.get(0) instanceof NaverBookVO) {
            return apiRespList.stream().map(resp -> {
                return new BookResponseDto((NaverBookVO) resp);
            }).collect(Collectors.toList());
        }

        if (apiRespList.get(0) instanceof AladinBookVO) {
            return apiRespList.stream().map(resp -> {
                return new BookResponseDto((AladinBookVO) resp);
            }).collect(Collectors.toList());
        }
        return null;
    }

    public static List<Object> toDto(List<? extends Object> original) {
        List<Object> result = null;
        result = domainListTodtoList(original);
        if (result == null) {
            result = openApiListTodtoList(original);
        }
        return result;
    }
}
