package kitri.dev6.memore.dto.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.Link;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PagingResponse<T> {

    private List<T> list = new ArrayList<>();
    private Pagination pagination;
    private Link _links;
    public PagingResponse(List<T> list, Pagination pagination) {
        if (list != null) this.list.addAll(list);
        this.pagination = pagination;
    }

}