package com.htl.service.impl;

import com.htl.entity.Book;
import com.htl.entity.Borrow;
import com.htl.repository.BookRepository;
import com.htl.repository.BorrowRepository;
import com.htl.repository.impl.BookRepositoryImpl;
import com.htl.repository.impl.BorrowRepositoryImpl;
import com.htl.service.BookService;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookServiceImpl implements BookService {

    private BookRepository bookRepository = new BookRepositoryImpl();
    private BorrowRepository borrowRepository = new BorrowRepositoryImpl();
    // 规定每页条6记录。
    private final int LIMIT = 6;

    /**
     *  规律：
     *      每页6条数据记录。
     *      第1页     下标从   0         开始
     *      第2页     下标从   6         开始
     *      第3页     下标从   12        开始
     *      第4页     下标从   18        开始
     *      第n页     下标从   (n-1)*6   开始
     *
     */
    @Override
    public List<Book> findAll(int page) {
        // 通过page=1，获取另一个重要参数。index。
        int index = (page-1)*LIMIT;
        // 通过 findAll(int index,int limit) 方法，使用 index 和 LIMIT 参数来获取展示信息。
        return bookRepository.findAll(index,LIMIT);
    }

    @Override
    // 获取总页数。
    public int getPages() {
        // 先通过count()方法来获取记录的（总条数）。
        int count = bookRepository.count();
        int page = 0;
        // 通过信息总条数，以及每页规定6条记录。来判断总页数。
        if(count % LIMIT == 0){
            page = count/LIMIT;
        }else{
            page = count/LIMIT+1;
        }
        // 返回总页数。
        return page;
    }

    @Override
    public int getBorrowPages(Integer readerid) {
        // 每位用户借书的（总记录数）。
        int count = borrowRepository.count(readerid);
        int page = 0;
        if(count % LIMIT == 0){
            page = count/LIMIT;
        }else{
            page = count/LIMIT+1;
        }
        // 返回总页数。
        return page;
    }

    @Override
    public void addBorrow(Integer bookid, Integer readerid) {
        // 借书时间。
        Date date = new Date();
        // 定义时间规则。
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 使用时间规则来改变date。
        String borrowTime = simpleDateFormat.format(date);
        // 还书时间，借书时间+14天。
        Calendar calendar = Calendar.getInstance();// 创建calendar对象。（可以自动计算日期）
        // 算出当前日期是一年当中的第几天的天数，然后+14。
        int dates = calendar.get(Calendar.DAY_OF_YEAR) + 14;
        // Calendar.DAY_OF_YEAR是转换规则，说明dates的值也是一年当中的第几天，然后存储。
        calendar.set(Calendar.DAY_OF_YEAR, dates);
        // 用calendar调用getTime()方法，拿到14天之后的日期。
        Date date2 = calendar.getTime();
        // 定义并赋值还书时间。
        String returnTime = simpleDateFormat.format(date2);
        // 使用borrowRepository对象来调用BorrowRepositoryImpl类里面的insert()方法。这边的状态state用0表示未审核。审核管理员目前为null。
        borrowRepository.insert(bookid,readerid,borrowTime,returnTime,null,0);
    }

    @Override
    // 通过ReaderId查询查询借书的数据。
    public List<Borrow> findAllBorrowByReaderId(Integer id,Integer page) {
        // 业务：将 page 换算成 index。LIMIT默认=6。
        int index = (page-1)*LIMIT;
        // 将用户id，index，LIMIT三个参数通过borrowRepository对象,
        // 传入到BorrowRepositoryImpl类的findAllByReaderId()方法里面,并返回list集合。
        return borrowRepository.findAllByReaderId(id,index,LIMIT);
    }

    @Override
    // 通过state 的 id 查询所有的借书数据。（state = 0、1）
    public List<Borrow> findAllBorrowByState(Integer state,Integer page) {
        // 将 page 换算成 index。LIMIT默认=6。
        int index = (page-1)*LIMIT;
        // 将state的id，index，LIMIT三个参数通过borrowRepository对象,
        // 传入到BorrowRepositoryImpl类的findAllByState()方法里面,并返回list集合。
        return borrowRepository.findAllByState(state,index,LIMIT);
    }

    @Override
    public int getBorrowPagesByState(Integer state) {
        // 查出 state = 0 的记录数目。也就是未审核的总数目。
        int count = borrowRepository.countByState(state);
        int page = 0;
        if(count % LIMIT == 0){
            page = count/LIMIT;
        }else{
            page = count/LIMIT+1;
        }
        return page;
    }

    @Override
    public void handleBorrow(Integer borrowId, Integer state, Integer adminId) {
        // 调用handel()方法，并传入三个数据：图书编号、处理方式id、管理员id。
        borrowRepository.handle(borrowId,state,adminId);
    }

}

