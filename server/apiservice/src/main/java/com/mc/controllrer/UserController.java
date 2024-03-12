package com.mc.controllrer;

import com.mc.common.api.CommonResult;
import com.mc.dto.OrderListDTO;
import com.mc.dto.OrderStatusEnum;
import com.mc.dto.UserInfoDTO;
import com.mc.dto.param.UserInfoParam;
import com.mc.dto.param.UserOrderListParam;
import com.mc.po.Order;
import com.mc.po.User;
import com.mc.service.OrdersService;
import com.mc.service.UserService;
import com.mc.util.IPAddrUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Value("${img.request-path}")
    private String imgReqPath; // 请求地址

    @Value("${img.local-path}")
    private String imgLocPath; // 本地存放资源目录的绝对路径

    @Value("${server.port}")
    private int port;
    @Value("${server.ip}")
    private String ipAddr;

    @Autowired
    UserService userService;

    @Autowired
    OrdersService ordersService;

    /**
     * 用户登录验证
     *
     * @param userParam
     * @return
     */
    @PostMapping("/userLogin")
    public CommonResult userLogin(@RequestBody User userParam) {
//        System.out.println(userParam);
        User user = userService.checkUser(userParam.getName(), userParam.getPassword());
        if (user != null) {
            return CommonResult.success();
        } else {
            return CommonResult.failed("用户登录失败");
        }
    }

    //@PostMapping("/userRegister")
    //public CommonResult userRegister(@RequestBody User userParam, @RequestParam("avatar") MultipartFile avatar) {
    //    userService.saveUser(userParam);
    //    return CommonResult.success();
    //}

    /**
     * 用户注册及上传头像
     *
     * @param request
     * @param avatar
     * @return
     */
    @PostMapping("/userRegister")
    public CommonResult userRegister(HttpServletRequest request, @RequestParam("avatar") MultipartFile avatar) throws UnknownHostException, FileNotFoundException {
        User user = new User();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        user.setName(name);
        user.setPassword(password);
        //获取当前主机的IP地址
//        InetAddress localHost = InetAddress.getLocalHost();
        //System.out.println("localHost:" + localHost.getHostAddress() + ":" + port);
        //String uploadPath = "d:\\tmp";
//        String localHostAddr = IPAddrUtils.getIpAddress();
//        System.out.println("本机地址：" + localHostAddr);
        //目录不存在就创建
//        File uploadDir = new File(uploadPath);
//        if (!uploadDir.exists()) {
//            uploadDir.mkdir();
//        }
        //获取原来文件名称的扩展名
        String fileName = avatar.getOriginalFilename();
        //构建文件路径
        String filePath = imgLocPath + File.separator + fileName;
        String fileUrl = "http://" + ipAddr + ":" + port + "/" + fileName;
        user.setAvatar(fileUrl);
        //保存文件
        try {
            FileUtils.writeByteArrayToFile(new File(filePath), avatar.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //spring封装的图片存储方法
        //try {
        //    avatar.transferTo(new File(filePath));
        //} catch (IOException e) {
        //    throw new RuntimeException(e);
        //}
        User saveUser = userService.saveUser(user);
//        System.out.println(saveUser.toString());
        if (saveUser != null) {
            return CommonResult.success("注册成功");
        } else {
            return CommonResult.failed("注册失败");
        }

    }


    /**
     * 修改订单状态为取消
     *
     * @param userOrderListParam
     * @return
     */
    @PostMapping("/cancelOrder")
    public CommonResult cancelOrder(@RequestBody UserOrderListParam userOrderListParam) {
        Integer cancelOrder = ordersService.changeOrderStatus(OrderStatusEnum.CANCEL, userOrderListParam.getUserId());
        //System.out.println(cancelOrder);
        if (cancelOrder == 1) {
            return CommonResult.success("取消成功");
        } else {
            return CommonResult.failed("取消失败");
        }
    }

    /**
     * 获取用户ID
     *
     * @param userInfoParam
     * @return
     */
    @PostMapping("/getUserInfo")
    public UserInfoDTO getUserId(@RequestBody UserInfoParam userInfoParam) {
        return userService.getUserIdByName(userInfoParam.getName());
    }

    /**
     * 修改用户密码
     *
     * @param userInfoParam
     * @return
     */
    @PostMapping("/updataUserPassword")
    public CommonResult updateUserPassword(@RequestBody UserInfoParam userInfoParam) {
        Integer res = userService.updateUserPassword(userInfoParam.getId(), userInfoParam.getPassword());
        if (res == 1) {
            return CommonResult.success("修改成功");
        } else {
            return CommonResult.failed("修改失败");
        }
    }

}
