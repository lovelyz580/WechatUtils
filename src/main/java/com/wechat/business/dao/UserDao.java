package com.wechat.business.dao;

import com.wechat.business.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * 操作用户
 */
@Repository
public class UserDao {
    private Logger logger = Logger.getLogger(UserDao.class);
    /**
     * 根据User实体添加
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    public int insertByUser(SqlSession session, User user) {
        int num = 0;
        try {
            logger.info("UserDao");
            num = session.insert("com.wechat.business.dao.mapper.User.insertByUser", user);
        } catch (Exception e) {
            logger.error("UserDao--insertByUser--error:" + e.getMessage());
        }
        return num;
    }
    /**
     * 根据User实体更新
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    public int updateByUser(SqlSession session, User user) {
        int num = 0;
        try {
            logger.info("UserDao");
            num = session.update("com.wechat.business.dao.mapper.User.updateByUser", user);
        } catch (Exception e) {
            logger.error("UserDao--updateByUser--error:" + e.getMessage());
        }
        return num;
    }
    /**
     * 查询单个实体
     * @param session
     * @param user  实体
     * @return
     */
    public User selectByUserone(SqlSession session, User user) {
        User userone = new User();
        try {
            logger.info("UserDao");
            userone = session.selectOne("com.wechat.business.dao.mapper.User.selectByUserone", user);
        } catch (Exception e) {
            logger.error("UserDao--selectByPrimaryKey--error:" + e.getMessage());
        }
        return userone;
    }
    /**
     * 根据User精准实体查询数量
     * 查询数量  int
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    public int selectCountByUser(SqlSession session, User user) {
        int num = 0;
        try {
            logger.info("UserDao");
            num = session.selectOne("com.wechat.business.dao.mapper.User.selectCountByUser", user);
        } catch (Exception e) {
            logger.error("UserDao--selectCountByUser--error:" + e.getMessage());
        }
        return num;
    }

    /**
     * 根据User精准实体查询
     * 可以进行分页查询
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     * pageSize 每页的数据量
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    public List<User> selectByUserList(SqlSession session, User user) {
        List<User> userList = new ArrayList<User>();
        try {
            logger.info("UserDao");
            userList = session.selectList("com.wechat.business.dao.mapper.User.selectByUserList", user);
        } catch (Exception e) {
            logger.error("UserDao--selectByUser--error:" + e.getMessage());
        }
        return userList;
    }
    /**
     * 根据User实体模糊查询
     * 查询数量
     * @param user
     * @return
     *
     * @author Lovelyz on 2019/03/21
     */
    public int selectCountBySelectData(SqlSession session, User user) {
        int num = 0;
        try {
            logger.info("UserDao");
            num = session.selectOne("com.wechat.business.dao.mapper.User.selectCountBySelectData", user);
        } catch (Exception e) {
            logger.error("UserDao--selectCountBySelectData--error:" + e.getMessage());
        }
        return num;
    }
    /**
     * 根据User实体模糊查询
     * 可以进行分页查询
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     * pageSize 每页的数据量
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    public List<User> selectBySelectData(SqlSession session, User user) {
        List<User> user1List = new ArrayList<User>();
        try {
            logger.info("UserDao");
            user1List = session.selectList("com.wechat.business.dao.mapper.User.selectBySelectData", user);
        } catch (Exception e) {
            logger.error("UserDao--selectBySelectData--error:" + e.getMessage());
        }
        return user1List;
    }

    /**
     * 根据实体id删除(假删、更改删除状态)
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    public int updateByUserDeleteState(SqlSession session, User user) {
        int num = 0;
        try {
            logger.info("UserDao");
            num = session.update("com.wechat.business.dao.mapper.User.updateByUserDeleteState", user);
        } catch (Exception e) {
            logger.error("UserDao--updateByUserDeleteState--error:" + e.getMessage());
        }
        return num;
    }
    /**
     * 根据User实体单条删除
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    public int deleteByUser(SqlSession session, User user) {
        int num = 0;
        try {
            logger.info("UserDao");
            num = session.delete("com.wechat.business.dao.mapper.User.deleteByUser", user);
        } catch (Exception e) {
            logger.error("UserDao--deleteByUser--error:" + e.getMessage());
        }
        return num;
    }

    /**
     * 根据User实体多条条删除
     * @param id
     * @return
     * @author Lovelyz on 2019/03/21
     */
    public int deleteByidlist(SqlSession session, String id) {
        int num = 0;
        try {
            logger.info("UserDao");
            num = session.delete("com.wechat.business.dao.mapper.User.deleteByidlist", id);
        } catch (Exception e) {
            logger.error("UserDao--deleteByPrimaryKey--error:" + e.getMessage());
        }
        return num;
    }
}