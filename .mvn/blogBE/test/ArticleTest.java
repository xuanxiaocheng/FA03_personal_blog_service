package app.example.blogBE.test;

import app.example.blogBE.entity.Article;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import app.example.blogBE.Dao.ArticleDao;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import org.apache.ibatis.io.Resources;

public class ArticleTest {
    @Test
    public void test01() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //获取sqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //获取mapper接口对象,帮助创建这个UserMapper的实现类，返回实现类对象
        ArticleDao mapper = sqlSession.getMapper(ArticleDao.class);
        //测试功能
        List<Article> result = mapper.selectArticleData(null);
        //提交事务
        //sqlSession.commit();
        System.out.println("result:"+result);
    }
}
