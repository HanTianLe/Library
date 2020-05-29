package com.htl.controller;

import com.htl.entity.Book;
import com.htl.entity.Borrow;
import com.htl.entity.Reader;
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

@WebServlet("/book")
public class BookServlet extends HttpServlet {

    private BookService bookService = new BookServiceImpl();

    /***
     * 加载图书数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        // 设置method 默认为findAll。
        if(method == null){
            method = "findAll";
        }
        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        // 流程控制
        switch (method){
            case "findAll":
                /** 用户进入到【借书主页面】。*/
                // 获取当前页号。（默认 page=1）
                String pageStr = req.getParameter("page");
                // 强转。
                Integer page = Integer.parseInt(pageStr);
                // 把页号（page）传到findAll(int page)方法里。（获取展示信息）
                List<Book> list = bookService.findAll(page);
                // 返回展示信息。（给index.jsp页面的forEach用）
                req.setAttribute("list",list);
                // 每页6条数据。（给index.jsp页面用）
                req.setAttribute("dataPrePage",6);
                // 当前页号。（给index.jsp页面用，初始化为1，点击下一页会+1增加。）
                req.setAttribute("currentPage",page);
                // 获取总页数。（给index.jsp页面用）
                req.setAttribute("pages",bookService.getPages());
                // 传送。
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;
            case "addBorrow":
                /** 用户发出借书请求操作。*/
                String bookidStr = req.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                // 添加借书请求。
                // 通过bookService对象，调用BookServiceImpl类里面的addBorrow()方法。
                bookService.addBorrow(bookid,reader.getId());
                // 重定向。
                resp.sendRedirect("/book?method=findAllBorrow&page=1");
                break;
            case "findAllBorrow":
                /** 用户进入到【借书请求结果】的页面*/
                //pageStr和page上面已经创建了，可以直接使用。
                pageStr = req.getParameter("page");
                page  = Integer.parseInt(pageStr);
                // 展示当前用户的所有借书记录。
                // 通过bookService对象，调用调用BookServiceImpl类里面的findAllBorrowByReaderId()方法，
                // 来调出用户记录，并存入到borrowList集合里面。(reader.getId()得到的就是用户id。)
                List<Borrow> borrowList = bookService.findAllBorrowByReaderId(reader.getId(),page);
                req.setAttribute("list",borrowList);
                // 每页6条数据。（给borrow.jsp页面用）
                req.setAttribute("dataPrePage",6);
                // 当前页号。（给borrow.jsp页面用，初始化为1，点击下一页会+1增加。）
                req.setAttribute("currentPage",page);
                // 通过bookService对象，调用调用BookServiceImpl类里面的getBorrowPages()方法，获取【总页数】。
                // (reader.getId()得到的就是用户id。)
                req.setAttribute("pages",bookService.getBorrowPages(reader.getId()));
                // 传送。
                req.getRequestDispatcher("borrow.jsp").forward(req,resp);
                break;
        }
    }
}

