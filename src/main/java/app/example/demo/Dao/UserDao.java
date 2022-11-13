package app.example.demo.Dao;

import app.example.demo.dto.request.PaginationBean;
import app.example.demo.entity.User;

import java.util.List;

public interface UserDao {
    List<User> selectUserData(PaginationBean paginationBean);
}
