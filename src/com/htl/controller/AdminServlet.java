package com.htl.controller;

import com.htl.entity.Admin;
import com.htl.entity.Borrow;
import com.htl.service.BookService;
import com.htl.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method == null){
            method = "findAllBorrow";
        }
        HttpSession session = req.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        switch (method){
            /** 进入管理员对【借书请求进行操作】的页面。（同意、拒绝）*/
            case "findAllBorrow":
                // 获取当前页号。（默认 page=1）
                String pageStr = req.getParameter("page");
                // 强转。
                Integer page = Integer.parseInt(pageStr);
                // 把页号（page）传到findAllBorrowByState(Integer state,Integer page)方法里。（获取展示信息）
                List<Borrow> borrowList = bookService.findAllBorrowByState(0,page);
                // 返回展示信息。（给admin.jsp页面的forEach用）
                req.setAttribute("list",borrowList);
                // 每页6条数据。（给admin.jsp页面用）
                req.setAttribute("dataPrePage",6);
                // 【当前页号】。（给admin.jsp页面用，初始化为1，点击下一页会+1增加。）
                req.setAttribute("currentPage",page);
                // 通过BookServiceImpl类里面的getBorrowPagesByState方法，
                // 获取【总页数】。（给admin.jsp页面用）（state 默认为0，因为调的是未审核的。）
                req.setAttribute("pages",bookService.getBorrowPagesByState(0));
                req.getRequestDispatcher("admin.jsp").forward(req,resp);
                break;
            case "handle":
                /** 管理员对借书请求进行（同意、拒绝、判断是否归还）的操作。*/
                // 获取图书id。
                String idStr = req.getParameter("id");
                // 获取处理方式id。
                String stateStr = req.getParameter("state");
                // 强转。
                Integer id = Integer.parseInt(idStr);
                // 强转。
                Integer state = Integer.parseInt(stateStr);
                // 传入三个数据：图书编号、处理方式id、管理员id。
                bookService.handleBorrow(id,state,admin.getId());
                if(state == 1 || state == 2){
                    resp.sendRedirect("/admin?page=1");
                }
                if(state == 3){
                    resp.sendRedirect("/admin?method=getBorrowed&page=1");
                }
                break;
            case "getBorrowed":
                /** 管理员进入【还书管理】页面。*/
                // 获取当前页号。（默认 page=1）
                pageStr = req.getParameter("page");
                // 强转。
                page = Integer.parseInt(pageStr);
                // 这边查询的是已经审核通过的记录，因为这里要进行还书管理。
                // 所有state = 1。（1表示审核通过。）
                borrowList = bookService.findAllBorrowByState(1,page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                // 通过BookServiceImpl类里面的getBorrowPagesByState方法，
                // 获取【总页数】。（给return.jsp页面用）（state 默认为1，因为调的是审核通过的。）
                req.setAttribute("pages",bookService.getBorrowPagesByState(1));
                // 转发。
                req.getRequestDispatcher("return.jsp").forward(req,resp);
                break;
        }
    }
}

