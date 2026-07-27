package org.example.jpa3.controller;

import lombok.RequiredArgsConstructor;
import org.example.jpa3.dto.PhoneFormDTO;
import org.example.jpa3.service.PhoneService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
        phoneService.tx1();
        return "index";
    }

    @GetMapping("/list")
    public String list(
            @PageableDefault(
                    page = 0,
                    size = 5,
                    sort = "id",
                    direction = Sort.Direction.DESC
            )
            Pageable pageable,
            Model model) {
        // page, size, sort
        // page : 0부터 시작하는 현재 페이지 커서 위치
        // size : 한 페이지에 보여줄 데이터의 개수
        // sort/direction
        // sort=(속성명,방향) -> name,asc
        model.addAttribute("phones", phoneService.findAll(pageable).toList());
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
