package com.sud.user.action;

import com.sud.common.dto.BaseRes;
import com.sud.user.entity.User;
import com.sud.user.repository.UserRepository;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.util.Date;

@RestController
@RequestMapping("/user")
@Api(description = "用户相关控制类")
public class UserAction {
    @Autowired
    UserRepository userRepository;
    @ApiOperation(value="用户登录", notes="用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户登录名", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户密码", required = true, dataType = "String",paramType = "query")
    })
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseRes login(HttpServletRequest request, @RequestBody User user){
        BaseRes result = new BaseRes();
        String loginName = request.getParameter("loginName");
        String password = request.getParameter("password");

        if(loginName == null){
            loginName = user.getLoginName();
            password = user.getPassword();
        }

        HttpSession session;
        user = userRepository.findByLoginNameAndPassword(loginName,password);
        if(user != null){
            session = request.getSession();
            session.setAttribute("username",user.getName());
            session.setAttribute("loginName",loginName);
            session.setAttribute("user",user);
            session.setMaxInactiveInterval(30 * 6000000);

            result.setStatus(1);
            result.setData(user);
        }else {
            result.setStatus(0);
            result.setMessage("用户名或密码错误");
        }
        return result;
    }

    @ApiOperation(value="注册", notes="用户注册")
    @RequestMapping(value = "/login/register", method = RequestMethod.POST)
    public BaseRes register(HttpServletRequest request, @RequestBody User user){

        BaseRes res = new BaseRes();
        Date date = new Date();
        user.setCreateTime(date);
        user.setUpdateTime(date);
        try {
            User user1 = userRepository.findByLoginName(user.getLoginName());
            if(user1 != null ){
                res.setStatus(0);
                res.setMessage("注册失败，登录名已存在，请更换登录名");
                return res;
            }
            userRepository.save(user);
            HttpSession session = request.getSession();
            session.setAttribute("username",user.getName());
            session.setAttribute("loginName",user.getLoginName());
            session.setAttribute("user",user);
            session.setMaxInactiveInterval(30 * 6000000);
            res.setStatus(1);
            res.setData(user);
        }catch (Exception e){
            res.setStatus(0);
            e.printStackTrace();
        }
        res.setMessage("注册成功");
        return res;
    }

    @ApiOperation(value="获取当前用户信息", notes="获取当前用户信息")
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public BaseRes getUser(HttpServletRequest request, HttpServletResponse httpResponse){
        BaseRes result = new BaseRes();
        HttpSession session = request.getSession();
        User user = null;

        if(session.getAttribute("user") != null){
            user = (User) session.getAttribute("user");
            result.setStatus(1);
            result.setData(user);
        }else {
            result = null;
            try {
                OutputStream out = httpResponse.getOutputStream();
                httpResponse.setHeader("Content-type", "text/html;charset=utf-8");
                BaseRes baseRes = new BaseRes();
                baseRes.setMessage("你还没有登录");
                out.write("4".getBytes());

                out.flush();
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return result;
    }

    @ApiOperation(value="退出当前用户", notes="退出当前用户")
    @RequestMapping(value = "/loginOut", method = RequestMethod.GET)
    public JSONObject loginOut(HttpServletRequest request){
        JSONObject result = new JSONObject();
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        session.removeAttribute("loginName");
        session.removeAttribute("user");
        result.put("message","loginOut success");
        return result;
    }
    @ApiOperation(value="退出当前用户", notes="退出当前用户")
    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public JSONObject getUsers(@RequestBody User user){
        JSONObject result = new JSONObject();
        result.put("data",userRepository.findByRole(user.getRole()));
        return result;
    }
}
