package com.htl.controller;

import com.htl.entity.Admin;
import com.htl.entity.Reader;
import com.htl.service.LoginService;
import com.htl.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    /**
     * 处理登录的业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 设置字符集
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        // 接收来自浏览器传过来的三个key。
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        // 用object接收 loginService传过来的数据。
        Object object = loginService.login(username,password,type);
        // 判断object是否为null，是则登录失败，不是则登录成功。
        if(object != null){
            // 创建Session。
            HttpSession session = req.getSession();
            switch (type){
                case "reader":
                    // 要强转为Reader。
                    Reader reader = (Reader) object;
                    // 用session存储reader。
                    session.setAttribute("reader",reader);
                    // 跳转到读者首页（重定向）（默认第1页，需要初始化参数page=1）
                    resp.sendRedirect("/book?page=1");
                    break;
                case "admin":
                    // 要强转为Admin。
                    Admin admin = (Admin) object;
                    // 用session存储Admin。
                    session.setAttribute("admin",admin);
                    // 跳转到管理员首页（重定向）
                    // 这边 page 默认设置 = 1。这里的page要传到AdminServlet里面的。（为了页面初始化使用。）
                    resp.sendRedirect("/admin?method=findAllBorrow&page=1");
                    break;
            }
        }else{
            // 跳转到登录页面（重定向）
            PrintWriter out = resp.getWriter();
            out.write("<script>");
            out.write("alert('登录失败！');");
            out.write("location.href='login.jsp'");
            out.write("</script>");
        }
    }

}

