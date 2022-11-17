package app.example.blogBE.service;

import app.example.blogBE.Dao.UserDao;
import app.example.blogBE.bean.PaginationHelper;
import app.example.blogBE.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {
    @Autowired(required = false)
    UserDao userDao;

    public List<User> queryUserList(PaginationHelper paginationHelper) {
        return userDao.selectUserData(paginationHelper);
    }
    public User login(User user) { return userDao.login(user); }
}
