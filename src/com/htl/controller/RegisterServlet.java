package com.htl.controller;

import com.htl.repository.RegisterRepository;
import com.htl.repository.impl.RegisterRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private RegisterRepository registerRepository = new RegisterRepositoryImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置字符集
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        String method = req.getParameter("method");
        if (method == null){
            method = "gotoRegisterPage";
        }
        switch (method){
            case "gotoRegisterPage":
                // 跳转到注册页面（重定向）
                resp.sendRedirect("register.jsp");
                break;
            case "UserRegister":
                // 把注册信息插入到数据库
                // 接收性别参数强转并判断。
                String genders = req.getParameter("gender");
                int x = Integer.parseInt(genders);
                String sex= null;
                if (x == 1){
                    sex = "男";
                }else {
                    sex = "女";
                }
                String username = req.getParameter("username");
                String name = req.getParameter("name");
                String telephone = req.getParameter("telephone");
                String cardid = req.getParameter("cardid");
                String password = req.getParameter("password");
                registerRepository.RegisterInsert(username,password,name,telephone,cardid,sex);
                PrintWriter out = resp.getWriter();
                out.write("<script>");
                out.write("alert('用户注册成功！');");
                out.write("location.href='login.jsp'");
                out.write("</script>");
                break;
        }
    }
}

