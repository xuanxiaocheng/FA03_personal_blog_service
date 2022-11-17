package app.example.blogBE.Dao;

import app.example.blogBE.bean.PaginationHelper;
import app.example.blogBE.entity.user.User;

import java.util.List;

public interface UserDao {
    List<User> selectUserData(PaginationHelper paginationBean);
    User login(User user);
}
