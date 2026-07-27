package org.example.jpa3.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa3.dto.PhoneFormDTO;
import org.example.jpa3.service.PhoneService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    private final PhoneService phoneService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("phones", phoneService.findAll());
        return "index";
    }

    @PostMapping
    public String create(@ModelAttribute PhoneFormDTO dto) {
        phoneService.save(dto.toEntity());
        return "redirect:/";
    }
}
