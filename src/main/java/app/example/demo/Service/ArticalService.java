package app.example.demo.Service;

import app.example.demo.entity.Article;
import app.example.demo.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.example.demo.Dao.ArticleDao;
import java.util.List;


@Service("articalService")
public class ArticalService  {

    @Autowired(required = false)
    ArticleDao articleDao;

    public List<Article> queryArticleData(Integer curPage, Integer pageSize) {
        return articleDao.selectArticleData(curPage, pageSize);
    }
    public List<Question> queryQuestionData(Integer curPage, Integer pageSize) {
        return articleDao.selectQestionData(curPage, pageSize);
    }
}
