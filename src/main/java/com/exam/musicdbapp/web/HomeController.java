package com.exam.musicdbapp.web;

import com.exam.musicdbapp.services.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final AlbumService albumService;

    public HomeController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "index";
        }
        model.addAttribute("albums", albumService.getAllAlbums());
        model.addAttribute("copies", albumService.copiesCalc());
        return "home";
    }



}
