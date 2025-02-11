package com.example.crud.controller;

import com.example.crud.entity.SubEntity;
import com.example.crud.service.SubEntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subentities")
public class SubEntityController {

    private final SubEntityService subEntityService;

    public SubEntityController(SubEntityService subEntityService) {
        this.subEntityService = subEntityService;
    }

    @PostMapping
    public String saveSubEntity(@RequestParam Long entityId, @RequestParam String value) {
        SubEntity subEntity = new SubEntity();
        subEntity.setValue(value);
        subEntityService.saveSubEntity(subEntity, entityId);
        return "redirect:/entities/view/" + entityId;
    }

    @PostMapping("/del/{id}")
    public String deleteSubEntity(@PathVariable Long id, @RequestParam Long entityId) {
        subEntityService.deleteSubEntity(id);
        return "redirect:/entities/view/" + entityId;
    }

    @GetMapping("/edit/{id}")
    public String editSubEntityForm(@PathVariable Long id,
                                    @RequestParam Long entityId,
                                    Model model) {
        SubEntity subEntity = subEntityService.getSubEntityById(id);
        model.addAttribute("subEntity", subEntity);
        model.addAttribute("entityId", entityId);
        return "subentities/form";
    }

    @PostMapping("/edit/{id}")
    public String updateSubEntity(@PathVariable Long id,
                                  @RequestParam Long entityId,
                                  @RequestParam String value) {
        subEntityService.updateSubEntity(id, value);
        return "redirect:/entities/view/" + entityId;
    }
}
