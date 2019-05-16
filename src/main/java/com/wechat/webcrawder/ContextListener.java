package com.wechat.webcrawder;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.util.*;

/**
 * 定时器
 *
 * @author Lovelyz on 2019/04/19
 */

public class ContextListener extends HttpServlet implements ServletContextListener {
    private Timer timer = null; // 定时器

    /**
     * 构造函数
     */
    public ContextListener() {

    }

    /**
     * 初始化
     * 监听器
     */
    @Override
    public void contextInitialized(ServletContextEvent event) {
        timer = new Timer(true); // 创建定时器
//        Memorandum memorandum = new Memorandum();
//        memorandum.setPagenumber(-1);
//        memorandum.setVersion("1.1");
//        MemorandumDao memorandumDao = new MemorandumDao();
//        SqlSession session = MyBatisUtil.getInstance().getSqlSession();
//        // 查询数据
//        List<Memorandum> memorandumList = memorandumDao.selectByMemorandumList(session, memorandum);
//        event.getServletContext().log("自动推送定时器--已启动");
//        memorandum.setmName("大明");
//        Autopush autopush = new Autopush(memorandum);
//        Date now = new Date();
//        Date afterDate = new Date(now.getTime() + 120000);
//        System.out.println(now + "现在时间");
//        System.out.println(afterDate + "定时推送时间");
//        memorandum.setmTime(afterDate);
//        timer.schedule(autopush, memorandum.getmTime());  //创建定时任务
//        for (int a = 0; a < memorandumList.size(); a++) {
//            // timer.schedule(定时任务, 执行时间)
//            // timer.schedule(autopush, 2000L, 1000L);
//            timer.schedule(autopush, memorandumList.get(a).getmTime());  //创建定时任务
//
//        }
        event.getServletContext().log("自动验收定时器--已经将任务添加到任务调度表");

    }

    /**
     * 销毁
     */
    @Override
    public void contextDestroyed(ServletContextEvent event) {
        timer.cancel(); // 销毁
        event.getServletContext().log("自动验收定时器--已销毁");
    }
}


