package app.example.blogBE.Dao;

import app.example.blogBE.bean.PaginationHelper;
import app.example.blogBE.entity.Article;
import app.example.blogBE.entity.Question;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ArticleDao {
     List<Article> selectArticleData(PaginationHelper pageInfo);
     List<Question> selectQestionData(Integer curPage, Integer pageSize);
}
