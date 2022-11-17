package app.example.blogBE.controller;

import app.example.blogBE.bean.PaginationHelper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import app.example.blogBE.entity.Article;
import app.example.blogBE.entity.Question;
import app.example.blogBE.service.ArticalService;
import app.example.blogBE.bean.ResultBean;

@Api(tags = "文章内容")
@RestController()
@RequestMapping("blog/content")
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

    @PostMapping("/queryArticle")
    public ResultBean<PaginationHelper> goodText(@RequestBody PaginationHelper pageInfo){
        if (pageInfo == null) {
            pageInfo = new PaginationHelper();
        }
        if (pageInfo.getOptions() == null) {
            pageInfo.setOptions(new Article());
        }
        pageInfo.setData(articalService.queryArticleData(pageInfo));
        ResultBean<PaginationHelper> result = new ResultBean<>(pageInfo);
        return result;
    }
}
