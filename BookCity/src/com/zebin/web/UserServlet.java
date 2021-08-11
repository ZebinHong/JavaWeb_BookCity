package com.zebin.web;

import com.google.gson.Gson;
import com.zebin.pojo.User;
import com.zebin.service.impl.UserServiceImpl;
import com.zebin.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserServiceImpl usi = new UserServiceImpl();

    /**
     * 处理登录的请求
     * @param req
     * @param resp
     */
    public void regist(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        UserServiceImpl usi = new UserServiceImpl();
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
//        User user = new User(name,password,email);
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //获取session中验证码
        String token = (String)req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        System.out.println(token);
        //删除session中的验证码,以防止表单重复提交
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        if(token!=null && token.equalsIgnoreCase(code)){
            //二维码正确
            if(!usi.existsUserName(user.getName())){
                //不存在该用户，可以注册
                if(usi.regist(user)){
                    System.out.println("注册成功");
                    req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req,resp);
                }else{
                    req.setAttribute("msg","注册失败");
                    req.setAttribute("username",name);
                    req.setAttribute("email",email);
                    System.out.println("注册失败");
                    req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
                }
            }else{
                System.out.println("用户名存在");
                req.setAttribute("msg","存在该用户,注册失败");
                req.setAttribute("username",name);
                req.setAttribute("email",email);
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
            }
        }else{
            System.out.println("验证码[" + code + "]错误");
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",name);
            req.setAttribute("email",email);
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req,resp);
        }
    }

    /**
     * 处理登录的请求
     * @param req
     * @param resp
     */
    public void login(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //获取用户
        User user = new User(username,password,null);
        if((user=usi.login(user))!=null){
            //登录成功
            req.getSession().setAttribute("user",user);
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else{
            //登录失败
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            System.out.println("登录失败");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    public void existUserName(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");
        boolean isExist = usi.existsUserName(name);
        //将结果封装成map，以json字符串格式传回
        Map<String,Boolean> map = new HashMap<>();
        map.put("isExist",isExist);
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        //传回
        resp.getWriter().write(jsonStr);
    }
}
