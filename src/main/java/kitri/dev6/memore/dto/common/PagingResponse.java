package kitri.dev6.memore.dto.common;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class PagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination;

    public PagingResponse(List<T> list, Pagination pagination) {
        if (list != null) this.list.addAll(list);
        this.pagination = pagination;
    }

}