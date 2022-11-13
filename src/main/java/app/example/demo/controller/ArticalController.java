package app.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import app.example.demo.entity.Article;
import app.example.demo.entity.Question;
import app.example.demo.Service.ArticalService;
import app.example.demo.dto.response.ResultBean;

@RestController()
@RequestMapping("api/user")
public class ArticalController {

    @Resource(name = "articalService")
    ArticalService articalService;

    @GetMapping("/getQuestion")
    public ResultBean<List<Question>> queryQuestion(HttpServletRequest request){
        Integer curPage = Integer.parseInt(request.getParameter("curPage"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<Question> list = articalService.queryQuestionData(curPage-1, pageSize);
        return new ResultBean<List<Question>>(list);
    }

    @GetMapping("/getArticle")
    public ResultBean<List<Article>> goodText(HttpServletRequest request){
        Integer curPage = Integer.parseInt(request.getParameter("curPage"));
        Integer pageSize = Integer.parseInt(request.getParameter("pageSize"));
        List<Article> list = articalService.queryArticleData(curPage-1, pageSize);
        return new ResultBean<List<Article>>(list);
    }
}
