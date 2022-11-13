package app.example.demo.Dao;

import app.example.demo.entity.Article;
import app.example.demo.entity.Question;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleDao {
     List<Article> selectArticleData(Integer curPage, Integer pageSize);
     List<Question> selectQestionData(Integer curPage, Integer pageSize);
}
