package com.yang.controller;

import com.yang.constant.Constants;
import com.yang.util.ImageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author admin
 */
@Controller
public class VerificationCodeController {

    @RequestMapping("/")
    public String index(Model model) {
        System.out.println(model.asMap().get("test"));
        return "index";
    }

    @RequestMapping("/get-code")
    public void getCode(HttpServletResponse response, Model model, HttpServletRequest request) throws IOException {
        System.out.println(model.asMap().get("test"));
        Object[] objs = ImageUtil.createImage();
        BufferedImage image = (BufferedImage) objs[1];
        System.out.println(objs[0]);
        Cookie cookie = new Cookie(Constants.verifyCodeName, (String)objs[0]);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
        ImageIO.write(image, "jpeg", response.getOutputStream());
    }

    @RequestMapping("/check-code")
    @ResponseBody
    public Boolean checkCode(HttpServletRequest request, @RequestParam String code) {
        // 获取请求cookie进行验证
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(Constants.verifyCodeName)) {
                if(code.equals(cookie.getValue())) {
                    return true;
                }else {
                    return false;
                }
            }
        }
        return false;
    }
}
