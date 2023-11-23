package kitri.dev6.memore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticlesController {
    @GetMapping("/articles/list")
    public String articlesList(){
        return "articles-list";
    }

    @GetMapping("/articles/create")
    public String articlesCreate(){
        return "articles-create";
    }

    @GetMapping("/articles/{articles_id}")
    public String articlesListDetail(){
        return "articles-list-detail";
    }

    @GetMapping("/articles/{articles_id}")
    public String articlesModify(){
        return "articles-modify";
    }

    @GetMapping("/articles/{articles_id}")
    public String articlesDelete(){
        return "articles-delete";
    }

}
