package app.example.demo.controller;

import app.example.demo.dto.request.PaginationBean;
import app.example.demo.dto.response.ResultBean;
import app.example.demo.dto.response.ResultPaginationBean;
import app.example.demo.Service.UserService;
import app.example.demo.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Api(tags = "用户管理")
@RestController()
@RequestMapping("api/user")
public class UserController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Resource(name = "userService")
    UserService userService;

    @ApiOperation("获取用户列表")
    @PostMapping("/queryUserList")
    public ResultBean<ResultPaginationBean> queryUserList(@RequestBody PaginationBean pageInfo){
        ResultPaginationBean resultPagination = new ResultPaginationBean<>();
        ResultBean<ResultPaginationBean> result = new ResultBean<>();
        if(pageInfo == null){
            pageInfo = new PaginationBean();
        }
        if(pageInfo.getOptions() == null){
            resultPagination.setOptions(new User());
        }
        resultPagination.setData(userService.queryUserList(pageInfo));
        result.setData(resultPagination);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }


    @PostMapping("/getmanage")
    public List checkManage(HttpServletRequest request) {
        String sql = "select * from manage WHERE username = ? AND password = ?";
        String username = request.getParameter("userName");
        String password = request.getParameter("password");
        List lst = jdbcTemplate.queryForList(sql, new Object[]{username, password});
        return lst;
    }

    @PostMapping("/checktoken")
    public static String checkToken(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies(); //内部存储的是cookie  通过对象流进行操作
        if (cookies == null) {
            return "{\"status\":null}";
        }
        return "{\"status\":null}";
    }
}
