package kitri.dev6.memore.controller;

import kitri.dev6.memore.domain.Wish;
import kitri.dev6.memore.repository.WishMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/wish")
public class WishTest {

    @Autowired
    private WishMapper wishMapper;

    @GetMapping("/{id}")
    public Wish findById(@PathVariable String id){
        return wishMapper.findById(Long.parseLong(id));
    }
}