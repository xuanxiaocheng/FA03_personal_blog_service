package app.example.blogBE.controller;

import app.example.blogBE.bean.ResultBean;
import app.example.blogBE.bean.PaginationHelper;
import app.example.blogBE.service.UserService;
import app.example.blogBE.entity.user.User;
import app.example.blogBE.utils.JWTUtils;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户管理")
@RestController()
@RequestMapping("/manage")
@Slf4j
public class UserController {


    @Resource(name = "userService")
    UserService userService;

    @ApiOperation("获取用户列表")
    @PostMapping("/queryUserList")
    public ResultBean<PaginationHelper> queryUserList(@RequestBody PaginationHelper pageInfo) {
        PaginationHelper resultPagination = new PaginationHelper<>();
        ResultBean<PaginationHelper> result = new ResultBean<>();
        if (pageInfo == null) {
            pageInfo = new PaginationHelper();
        }
        if (pageInfo.getOptions() == null) {
            resultPagination.setOptions(new User());
        }
        resultPagination.setData(userService.queryUserList(pageInfo));
        result.setData(resultPagination);
        result.setCode(200);
        result.setMsg("success");
        return result;
    }


    @GetMapping("/auth")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "string", paramType = "query", required = true)})
    public Map<String, Object> login(User user) {
        log.info("用户名：[{}]", user.getUsername());
        log.info("密码：[{}]", user.getPassword());

        Map<String, Object> map = new HashMap<>();

        try {
            User userDB = userService.login(user);
            Map<String, String> payload = new HashMap<>();
            payload.put("id", userDB.getId().toString());
            payload.put("username", userDB.getUsername());
            //生成JWT令牌
            String token = JWTUtils.getToken(payload);
            map.put("state", true);
            map.put("token", token);
            map.put("msg", "认证成功");
        } catch (NullPointerException e) {
            map.put("state", false);
            map.put("msg", "账号或密码错误");
        } catch (Exception e) {
            map.put("state", false);
            map.put("msg", e.getMessage());
        }
        return map;
    }

    @GetMapping("/test")
    public Map<String, Object> test(String token) {
        Map<String, Object> map = new HashMap<>();
        map.put("state", true);
        map.put("msg", "请求成功");
        return map;
    }

}
