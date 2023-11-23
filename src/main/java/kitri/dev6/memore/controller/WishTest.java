package kitri.dev6.memore.controller;

import kitri.dev6.memore.entity.Wish;
import kitri.dev6.memore.repository.WishMapper;
import kitri.dev6.memore.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wish")
@RequiredArgsConstructor
public class WishTest {
    private final WishService wishService;

    @GetMapping("/{id}")
    public Wish findById(@PathVariable String id){
        return wishService.findById(Long.parseLong(id));
    }
}