package com.resort.kingfisher.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.resort.kingfisher.model.TourPackage;
import com.resort.kingfisher.service.TourPackageService;

import java.util.List;

@Controller
@RequestMapping("/tourpackage")
public class TourPackageController {

    private final TourPackageService tourPackageService;

    @Autowired
    public TourPackageController(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @GetMapping("/list")
    public String listTourPackages(Model model) {
        List<TourPackage> tourPackages = tourPackageService.getAllTourPackages();
        model.addAttribute("tourpackages", tourPackages);
        return "tourpackage/list";
    }

    @GetMapping("/form")
    public String showTourPackageForm(Model model) {
        TourPackage tourPackage = new TourPackage();
        model.addAttribute("tourpackage", tourPackage);
        return "tourpackage/form";
    }

    @PostMapping("/save")
    public String saveTourPackage(@ModelAttribute("tourpackage") TourPackage tourPackage) {
        tourPackageService.saveTourPackage(tourPackage);
        return "redirect:/tourpackage/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        TourPackage tourPackage = tourPackageService.getTourPackageById(id).get();
        model.addAttribute("tourpackage", tourPackage);
        return "tourpackage/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTourPackage(@PathVariable("id") Long id) {
        tourPackageService.deleteTourPackage(id);
        return "redirect:/tourpackage/list";
    }
}
