//package com.wechat;
//
//import com.wechat.business.dao.MemorandumDao;
//import com.wechat.business.entity.Memorandum;
//import com.wechat.util.MyBatisUtil;
//import com.wechat.util.QuertzUtil;
//import com.wechat.util.TimeUtil;
//import org.apache.ibatis.session.SqlSession;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//
//import java.util.Date;
//import java.util.List;
//
//import static org.quartz.TriggerBuilder.newTrigger;
//
///**
// * Created by Lovelyz
// * on 2019-04-15 08:54
// */
//public class Quertztest {
//    public static void main(String[] args) throws SchedulerException {
//        System.out.println("启动时间"+new Date());
//        //通过schedulerFactory获取一个调度器
//        SchedulerFactory schedulerfactory = new StdSchedulerFactory();
//        Scheduler scheduler = null;
//        try {
//            // 通过schedulerFactory获取一个调度器
//            scheduler = schedulerfactory.getScheduler();
//            // 创建jobDetail实例，绑定Job实现类
//            // 指明job的名称，所在组的名称，以及绑定job类
//            JobDetail job = JobBuilder.newJob(QuertzUtil.class).withIdentity("JobName",
//                    "JobGroupName").build();
//            // 定义调度触发规则
//            Memorandum memorandum = new Memorandum();
//            MemorandumDao memorandumDao = new MemorandumDao();
//            SqlSession session = MyBatisUtil.getInstance().getSqlSession();
//            List<Memorandum> memorandumList = memorandumDao.selectByMemorandumList(session, memorandum);
//            Trigger trigger = null;
//            int b = 500;
//            for (int a = 0;a<5;a++){
//                b++;
//                Date now = new Date();
//                Date date = new Date(now.getTime() + 9000+b);  //执行时间
//                String cron = TimeUtil.getCron(date);
//                trigger = newTrigger().withIdentity("JobName"+a, "JobGroupName")
//                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))
//                        .startNow().build();
//                scheduler.scheduleJob(job, trigger);
//            }
////            for (int a = 0; a < memorandumList.size(); a++) {
////                job.getJobDataMap().put("memorandum", memorandumList.get(a));
////                Date date = memorandumList.get(a).getmTime();  //执行时间
////                String cron=TimeUtil.getCron(date);
////                System.out.println(cron);
////                ;
////                //  corn表达式  每五秒执行一次
////                 trigger = newTrigger().withIdentity("JobName", "JobGroupName")
////                        .withSchedule(CronScheduleBuilder.cronSchedule(cron))
////                        .startNow().build();
////                // 把作业和触发器注册到任务调度中
////                scheduler.scheduleJob(job, trigger);
////            }
//            // 启动调度
//            scheduler.start();
////            Thread.sleep(10000);
////            // 停止调度
////            scheduler.shutdown();
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    /**
//     * 添加方法时，向Scheduler中注册任务
//     * @return
//     */
////    public int  insert(){
////        //            其他地方调用
////        CronTrigger trigger =  newTrigger().withIdentity("CronTrigger1", "CronTriggerGroup")
////                .withSchedule(CronScheduleBuilder.cronSchedule("*/5 * * * * ?"))
////                .startNow().build();
////        Date ft = GetSchedual.getSchedual().scheduleJob(job, trigger);//向Scheduler中注册任务
////        return 1;
////    }
//}
//
//
