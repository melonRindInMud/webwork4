package com.guapi.webwork4.controller;

import com.guapi.webwork4.Data.Table;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class WebController {

    // 访问主界面 如果还没有登录 将被返回到登录界面
    @SneakyThrows
    @GetMapping("/main")
    public String showMain(HttpServletRequest request) {
        Object flag = request.getSession().getAttribute("login");
        if (null != flag) {
            HttpSession session = request.getSession();
            if (null == session.getAttribute("table")) {
                Table table = new Table();
                session.setAttribute("table", table);
            }
            return "main";
        }
        else
            return "redirect:/login";
    }

    // 导航页
    @GetMapping("/*")
    public String showNav(Model model, HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))
            model.addAttribute("message", "请先登录");
        else
            model.addAttribute("message", "欢迎回来");
        return "Navigation";
    }
}
