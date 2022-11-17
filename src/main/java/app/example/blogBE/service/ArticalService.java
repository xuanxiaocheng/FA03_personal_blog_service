package app.example.blogBE.service;

import app.example.blogBE.bean.PaginationHelper;
import app.example.blogBE.entity.Article;
import app.example.blogBE.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import app.example.blogBE.Dao.ArticleDao;
import java.util.List;


@Service("articalService")
public class ArticalService  {

    @Autowired(required = false)
    ArticleDao articleDao;

    @Cacheable(cacheNames = "article=60", key = "'pageNum=' + #pageInfo.pageNum + '&pageSize=' + #pageInfo.pageSize")
    public List<Article> queryArticleData(PaginationHelper pageInfo) {
        return articleDao.selectArticleData(pageInfo);
    }
    public List<Question> queryQuestionData(Integer curPage, Integer pageSize) {
        return articleDao.selectQestionData(curPage, pageSize);
    }
}
