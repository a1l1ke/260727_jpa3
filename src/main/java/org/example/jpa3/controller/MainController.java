package org.example.jpa3.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa3.dto.PhoneFormDTO;
import org.example.jpa3.service.PhoneService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/list")
    public String list(
            Pageable pageable,
            Model model) {
        model.addAttribute("phones", phoneService.findAll(pageable));
        return "index";
    }

    @PostMapping
    public String create(@ModelAttribute PhoneFormDTO dto) {
        phoneService.save(dto.toEntity());
        return "redirect:/";
    }

    @PostMapping("/{id}/name")
    public String changeName(@ModelAttribute PhoneFormDTO dto, @PathVariable Long id) {
        phoneService.changeName(id, dto.name());
        return "redirect:/";
    }
}
