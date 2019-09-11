package com.introductory.controller;
import com.introductory.entity.Speciality;
import com.introductory.entity.User;
import com.introductory.repository.SpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {
    @Autowired
    private SpecialityRepository specialityRepository;

//    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Speciality> messages = specialityRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            messages = specialityRepository.findBySpecialityName(filter);
        } else {
            messages = specialityRepository.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Speciality speciality,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        speciality.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("speciality", speciality);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                File uploadDir = new File(uploadPath);

                if (!uploadDir.exists()) {
                    uploadDir.mkdir();
                }

                String uuidFile = UUID.randomUUID().toString();
                String resultFilename = uuidFile + "." + file.getOriginalFilename();

                file.transferTo(new File(uploadPath + "/" + resultFilename));

                speciality.setFilename(resultFilename);
            }

            model.addAttribute("speciality", null);

            specialityRepository.save(speciality);
        }

        Iterable<Speciality> messages = specialityRepository.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }
}


//import com.introductory.entity.Speciality;
//import com.introductory.entity.User;
//import com.introductory.repository.SpecialityRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.Map;
//
//@Controller
//public class MainController {
//    @Autowired
//    private SpecialityRepository specialityRepository;
//
//    @GetMapping("/")
//    public String greeting(Map<String, Object> model) {
//        return "greeting";
//    }
//
//    @GetMapping("/main")
//    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
//        Iterable<Speciality> messages = specialityRepository.findAll();
//
//        if (filter != null && !filter.isEmpty()) {
//            messages = specialityRepository.findBySpecialityName(filter);
//        } else {
//            messages = specialityRepository.findAll();
//        }
//        model.addAttribute("messages", messages);
//        model.addAttribute("filter", filter);
//
//        return "main";
//    }
//
//    @PostMapping("/main")
//    public String add(
//            @AuthenticationPrincipal User user,
//            @RequestParam String description,
//            @RequestParam String specialityName,
//            Map<String, Object> model) {
//        Speciality message = new Speciality(description, specialityName, user);
//
//        specialityRepository.save(message);
//
//        Iterable<Speciality> messages = specialityRepository.findAll();
//
//        model.put("messages", messages);
//
//        return "main";
//    }
//}