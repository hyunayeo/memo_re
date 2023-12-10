package kitri.dev6.memore.service;

import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.dto.common.Pagination;
import kitri.dev6.memore.dto.common.PagingResponse;
import kitri.dev6.memore.dto.common.SearchDto;
import kitri.dev6.memore.dto.request.WishRequestDto;
import kitri.dev6.memore.repository.WishMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishService {
    private final WishMapper wishMapper;

    // 전체 찜 조회
    public PagingResponse<Wish> findAll(SearchDto params) {
// 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
        params.setDomainType("wish");
        int count = wishMapper.count(params);
        if (count < 1) {
            return new PagingResponse<>(Collections.emptyList(), null);
        }

        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
        Pagination pagination = new Pagination(count, params);
        params.setPagination(pagination);

        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
        List<Wish> list = wishMapper.findAll(params);
        // domain -> dto
//        List<WishResponseDto> convertedList = (List<WishResponseDto>) (Object) Converter.domainListTodtoList(list);

        return new PagingResponse<>(list, pagination);
    }
    public Wish findById(Long id) {
        return wishMapper.findById(id);
    }

    // 찜 등록 : 회원의 찜 목록에 book정보를 담아야 한다.
    public void insert(WishRequestDto wishRequestDto) {
        wishMapper.insert(wishRequestDto.toDomain());
    }

    // 찜 삭제 : 찜 목록에 담긴 id를 회원의 찜 목록에서 삭제해야 한다.
    public void delete(Long id, Long memberId) {
        wishMapper.delete(id, memberId);
    }
}
