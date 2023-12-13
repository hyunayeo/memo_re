package kitri.dev6.memore.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CategoryMapper {
    private final BookMapper bookMapper;
    public static HashMap<String, String> categoryMapper = new HashMap<>();
    static {
        categoryMapper.put("소설/시/희곡", "문학");
        categoryMapper.put("고전", "문학");
        categoryMapper.put("인문학", "문학");
        categoryMapper.put("에세이", "문학");
        categoryMapper.put("장르소설", "문학");
        categoryMapper.put("컴퓨터/모바일", "기술과학");
        categoryMapper.put("경제경영", "사회과학");
        categoryMapper.put("자기계발", "사회과학");
        categoryMapper.put("사회과학", "사회과학");
        categoryMapper.put("외국어", "언어");
        categoryMapper.put("역사", "역사");
        categoryMapper.put("예술/대중문화", "예술");
        categoryMapper.put("가정/요리/뷰티", "예술");
        categoryMapper.put("과학", "자연과학");
        categoryMapper.put("종교/역학", "종교");
    }

    public Long findCategoryId(Long cid) {
        String firstDepth = bookMapper.findFirstDepthByCid(cid);
        String categoryName = categoryMapper.get(firstDepth);
        if (categoryName == null) return 0L;
        return bookMapper.findCategoryIdByName(categoryName);
    }

}
