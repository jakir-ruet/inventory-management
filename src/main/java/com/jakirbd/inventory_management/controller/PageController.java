package com.jakirbd.inventory_management.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String dashboard(Model model) {
        model.addAttribute("pageTitle", "Welcome to Inventory Management");
        model.addAttribute("content", "dashboard :: content");
        return "layout/layout";
    }
}
