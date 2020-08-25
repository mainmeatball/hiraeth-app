package com.hiraeth.backend.controller;

import com.hiraeth.backend.entity.NameAwareEntity;
import com.hiraeth.backend.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BackendController {

    private final TestService testService;

    @Autowired
    public BackendController(final TestService testService) {
        this.testService = testService;
    }

    @GetMapping(value = "/backend")
    public String showBackend(final Model model) {
        model.addAttribute("entity", new NameAwareEntity());
        return "backend";
    }

    @GetMapping(value = "/backend", params = "id")
    public String findById(@RequestParam(value = "id") long id, Model model) {
        model.addAttribute("entity", new NameAwareEntity());
        final NameAwareEntity foundEntity = testService.findById(id);
        System.out.println("Name: " + foundEntity.getName());
        return "backend";
    }

    @GetMapping(value = "/backend", params = "name")
    public String findByName(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("entity", new NameAwareEntity());
        final List<NameAwareEntity> entities = testService.findBy("name", name);
        System.out.println("entities = " + entities.toString());
        return "backend";
    }

    @PostMapping("/backend")
    public String saveEntity(@ModelAttribute NameAwareEntity entity, Model model) {
        model.addAttribute("entity", entity);
        final NameAwareEntity testEntity = testService.save(entity);
        System.out.println("Id: " + testEntity.getId());
        System.out.println("Name: " + testEntity.getName());
        return "backend";
    }

    @PostMapping(value = "/backend", params = "id")
    public String updateEntity(@ModelAttribute NameAwareEntity entity, Model model) {
        model.addAttribute("entity", entity);
        final NameAwareEntity testEntity = testService.save(entity);
        System.out.println("Id: " + testEntity.getId());
        System.out.println("Name: " + testEntity.getName());
        return "backend";
    }
}
