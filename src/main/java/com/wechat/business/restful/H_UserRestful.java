package com.wechat.business.restful;

import com.wechat.business.entity.User;
import com.wechat.business.service.UserService;
import com.wechat.util.Config;
import com.wechat.utilentity.LayuiDataTemplet;
import com.wechat.util.VersionCompare;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * Created by Lovelyz
 * on 2019-04-04 09:25
 */
@Controller
@RequestMapping("/user")
public class H_UserRestful {
    @Autowired
    private UserService userService;
    /**
     * 根据User实体添加
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/insertByUser")
    public LayuiDataTemplet<User> insertByUser(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>();
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 版本号不能为空，验证版本号
        try {
            if (null != user.getVersion() && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }
        //判断登录名不能重复
        User userSelectData = new User();
        userSelectData.setNickName(user.getNickName());//登录名
        User userone = userService.selectByUserone(userSelectData); // 查询数据
        if (userone!=null) {
            returnData.setMsg("该用户已注册、请登录！");
            return returnData;
        }
        // 添加数据
        // 添加
        int count = 0;
        count = userService.insertByUser(user);
        // 返回数据
        if (count == 0) {
            returnData.setMsg("添加失败！");
        } else {
            returnData.setCount(count);
            returnData.setMsg("添加成功！");
        }
        return returnData;
    }

    /**
     * 根据User实体更新
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/updateByUser")
    public LayuiDataTemplet<User> updateByUser(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }
        // 更新判断
        user.setUserId("e74995db-d653-4800-a428-79eb0abfef83");
        if (user.getUserId() == null || user.getUserId() == "") {
            if (user.getUserId() == null || user.getUserId().isEmpty()) {
                returnData.setMsg("缺少更新条件，更新失败！");
                return returnData;
            }
        }
        // 更新数据
        //MD5加密MD5Util.encrypt(user1.getUserpassword();
        // 更新时间
        // 更新
        user.setNickName("小米");
        int count = 0;
        count = userService.updateByUser(user);
        // 返回数据
        if (count == 0) {
            returnData.setMsg("更新失败！");
        } else {
            returnData.setCount(count);
            returnData.setMsg("更新成功！");
        }
        return returnData;
    }

    /**
     * 根据User实体查询 单条数据
     * @param user  单条数据
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/selectByUserid")
    public LayuiDataTemplet<User> selectByUserid(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }
        user.setUserId("e74995db-d653-4800-a428-79eb0abfef83");
        User userone = userService.selectByUserone(user); // 查询数据
        if (userone!=null){
            returnData.setCount(1);
            returnData.setOnedata(userone);
        }else {
            returnData.setCount(0);
            returnData.setMsg("暂无数据");
        }
        return returnData;
    }
    /**
     * 根据User实体联表查询 多条数据
     * 可以进行分页查询
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     * pageSize 每页的数据量
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/selectByUserlist")
    public LayuiDataTemplet<User> selectByUserlist(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }
        // 分页数据
        // 使用limit分页查询，根据偏移量查询
        // 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
        // MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
        if (user.getPagenumber() != null) {
            // 计算偏移量
            if (user.getPagenumber() != -1) {
                if (user.getPagesize() == null) {
                    returnData.setMsg("传递的分页数据(每页数量)错误！");
                    return returnData;
                }
                // 获取传递过来的数据
                int pageNumber = user.getPagenumber();
                int pageSize = user.getPagesize();
                user.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
                user.setPagesize(pageSize); // 每页的数据量
            }
            // 查询数量
            int count = 0;
            count = userService.selectCountByUser(user);
            // 返回数据
            if (count == 0) {
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<User> userList = userService.selectByUserList(user); // 查询数据
                returnData.setData(userList);
            }
        } else {
            returnData.setMsg("传递的分页数据(页数)错误！");
        }
        return returnData;
    }
    /**
     * 根据User实体联表模糊查询
     * 可以进行分页查询
     * pageNumber 当前页数(如果不进行分页，该条数据默认为-1)
     * pageSize 每页的数据量
     * @param user
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/selectBySelectData")
    public LayuiDataTemplet<User> selectBySelectData(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null

        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }

        // 分页数据
        // 使用limit分页查询，根据偏移量查询
        // 第一个参数为第一个返回记录行的偏移量，第二个参数为返回记录行的最大数目
        // MySQL > SELECT * FROM table LIMIT 5, 10; // 查询第6-15行数据
        if (user.getPagenumber() != null) {
            // 计算偏移量
            if (user.getPagenumber() != -1) {
                if (user.getPagesize() == null) {
                    returnData.setMsg("传递的分页数据(每页数量)错误！");
                    return returnData;
                }

                // 获取传递过来的数据
                int pageNumber = user.getPagenumber();
                int pageSize = user.getPagesize();
                user.setPagenumber((pageNumber - 1) * pageSize); // 当前页数(如果不进行分页，该条数据默认为-1)
                user.setPagesize(pageSize); // 每页的数据量
            }
            // 查询数量
            int count = 0;
            count = userService.selectCountBySelectData(user); // 查询数量
            // 返回数据
            if (count == 0) {
                returnData.setMsg("暂无数据！");
            } else {
                returnData.setCount(count);
                returnData.setMsg("查询成功！");
                List<User> user1List = userService.selectBySelectData(user); // 查询数据
                returnData.setData(user1List);
            }
        } else {
            returnData.setMsg("传递的分页数据(页数)错误！");
        }
        return returnData;
    }
    /**
     * 根据User实体删除信息(假删，更改状态)(0:删除、1：未删除)
     * @param user 单条删除
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/updateByUserDel")
    public LayuiDataTemplet<User> updateByUserDeleteState(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }
        // 删除
        int count = 0;
        count = userService.updateByUserDel(user);
        // 返回数据
        if (count == 0) {
            returnData.setCode(500);
            returnData.setMsg("删除失败！");
        } else {
            returnData.setCount(count);
            returnData.setCode(200);
            returnData.setMsg("删除成功！");
        }
        return returnData;
    }
    /**
     * 根据idList删除信息(假删，更改状态)(0:删除、1：未删除)
     * @param user  多条删除
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/updateByUserDellist")
    public LayuiDataTemplet<User> updateByUserDellist(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }
        // 删除
        int count = 0;
        count = userService.updateByUserDeleteState(user.getIdlist());
        // 返回数据
        if (count == 0) {
            returnData.setCode(500);
            returnData.setMsg("删除失败！");
        } else {
            returnData.setCount(count);
            returnData.setCode(200);
            returnData.setMsg("删除成功！");
        }
        return returnData;
    }

    /**
     * 根据id 真删除
     * @param user id 单条
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/deleteByUserid")
    public LayuiDataTemplet<User> deleteByUserid(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }
        // 删除
        int count = 0;
        user.setUserId("e74995db-d653-4800-a428-79eb0abfef83");
        count = userService.deleteByUserid(user);
        // 返回数据
        if (count == 0) {
            returnData.setCode(500);
            returnData.setMsg("删除失败！");
        } else {
            returnData.setCount(count);
            returnData.setCode(200);
            returnData.setMsg("删除成功！");
        }
        return returnData;
    }


    /**
     * 根据idList 真删除
     * @param user  多条
     * @return
     * @author Lovelyz on 2019/03/21
     */
    @ResponseBody
    @RequestMapping("/deleteByUseridList")
    public LayuiDataTemplet<User> deleteByUseridList(@RequestBody User user) {
        LayuiDataTemplet<User> returnData = new LayuiDataTemplet<User>(); // 返回数据
        returnData.setCode(0); // 默认为0
        returnData.setCount(0); // 数据的数量，默认为0
        returnData.setData(null); // 数据List，默认为null
        // 版本号不为空，则验证版本号
        try {
            if (user.getVersion() != null && !user.getVersion().isEmpty()) {
                // 前者大则返回一个正数，后者大返回一个负数，相等则返回0
                int compare = VersionCompare.compare(user.getVersion(), Config.VERSION);
                if (compare < 0) {
                    returnData.setMsg("版本较低，请更新版本！");
                    return returnData;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            returnData.setMsg("版本比较发生异常！");
            return returnData;
        }
        // 删除
        int count = 0;
        count = userService.deleteByIdList(user.getIdlist());
        // 返回数据
        if (count == 0) {
            returnData.setCode(500);
            returnData.setMsg("删除失败！");
        } else {
            returnData.setCount(count);
            returnData.setCode(200);
            returnData.setMsg("删除成功！");
        }
        return returnData;
    }
}
