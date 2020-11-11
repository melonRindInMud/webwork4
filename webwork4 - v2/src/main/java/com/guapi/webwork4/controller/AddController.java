package com.guapi.webwork4.controller;

import com.guapi.webwork4.Data.ContactInfor;
import com.guapi.webwork4.Data.Table;
import com.guapi.webwork4.Service.TableAlters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AddController {

    // 访问添加页面 需要传递一个和前端表单交互数据的提交类
    // 必须要先登录
    @RequestMapping("/add")
    public String showAdd(ContactInfor cont, Model model, HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))
            return "redirect:/login";
        else {
            model.addAttribute("cont", cont);
            return "add";
        }
    }

    // 处理添加的URL 如果不是通过post请求的说明是手动请求的，将跳转回去，否则进行处理
    @GetMapping("/checkadd")
    public String redirectAdd() {
        return "redirect:/add";  // 返回模型redirect:/....  springboot 会自动创建或获取相关Mapping 方法所需的参数
    }

    // 处理添加的URL 如果是通过POST提交的，则进行处理
    @PostMapping("/checkadd")
    public String checkAdd(ContactInfor cont, HttpServletRequest request, Model model) {
        Table t = (Table)request.getSession().getAttribute("table");
        boolean is_valid = TableAlters.checkValidAdd(t, cont);
        if (true == is_valid) {
            t.getTableinfo().addElement(cont);
            return "redirect:/main";
        }
        else {
            cont.setMessage("联系人名称已存在");
            cont.setContactname("");
            return showAdd(cont, model, request);  // 这种需要传递参数的，还是需要调用相关方法
        }
    }
}
