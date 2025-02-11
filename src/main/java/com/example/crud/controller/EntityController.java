package com.example.crud.controller;

import com.example.crud.entity.Entity;
import com.example.crud.entity.SubEntity;
import com.example.crud.service.EntityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/entities")
public class EntityController {
    private final EntityService service;

    public EntityController(EntityService service) {
        this.service = service;
    }

    @GetMapping
    public String getAllEntities(Model model) {
        model.addAttribute("entities", service.getAllEntities());
        return "entities/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("entity", new Entity());
        return "entities/form";
    }

    @PostMapping
    public String saveEntity(@ModelAttribute Entity entity) {
        service.saveEntity(entity);
        return "redirect:/entities";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("entity", service.getEntityById(id));
        return "entities/form";
    }

    @PostMapping("/{id}")
    public String updateEntity(@PathVariable Long id, @ModelAttribute Entity entity) {
        service.updateEntity(id, entity);
        return "redirect:/entities";
    }

    @PostMapping("/del/{id}")
    public String deleteEntity(@PathVariable Long id) {
        service.deleteEntity(id);
        return "redirect:/entities";
    }

    @GetMapping("/view/{id}")
    public String viewEntity(@PathVariable Long id, Model model) {
        Entity entity = service.getEntityById(id);
        model.addAttribute("entity", entity);
        model.addAttribute("subEntity", new SubEntity());
        return "entities/view";
    }
}
