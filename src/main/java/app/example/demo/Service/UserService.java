package app.example.demo.Service;

import app.example.demo.Dao.UserDao;
import app.example.demo.dto.request.PaginationBean;
import app.example.demo.dto.response.ResultPaginationBean;
import app.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserService {
    @Autowired(required = false)
    UserDao userDao;

    public List<User> queryUserList(PaginationBean paginationBean) {
        return userDao.selectUserData(paginationBean);
    }
}
