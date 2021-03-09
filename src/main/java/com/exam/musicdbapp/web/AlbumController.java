package com.exam.musicdbapp.web;

import com.exam.musicdbapp.bindingModels.AddAlbumBindingModel;
import com.exam.musicdbapp.model.enums.AlbumGenreEnum;
import com.exam.musicdbapp.model.enums.ArtistNameEnum;
import com.exam.musicdbapp.model.service.AlbumServiceModel;
import com.exam.musicdbapp.services.AlbumService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class AlbumController {

    private final AlbumService albumService;
    private final ModelMapper modelMapper;

    public AlbumController(AlbumService albumService, ModelMapper modelMapper) {
        this.albumService = albumService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/album/add")
    public String albumAdd (Model model, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:login";
        }

        model.addAttribute("genres", AlbumGenreEnum.values());
        model.addAttribute("artists", ArtistNameEnum.values());

        if (!model.containsAttribute("addAlbumBindingModel")) {
            model.addAttribute("addAlbumBindingModel", new AddAlbumBindingModel());
        }
        return "add-album";
    }

    @PostMapping("/album/add")
    public String albumAdd(@Valid @ModelAttribute AddAlbumBindingModel addAlbumBindingModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addAlbumBindingModel", addAlbumBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addAlbumBindingModel",
                    bindingResult);

            return "redirect:/album/add";
        }
        albumService.addAlbum(modelMapper.map(addAlbumBindingModel, AlbumServiceModel.class));
        return "redirect:/";
    }

    @GetMapping("/album/delete/{id}")
    public String albumDelete(@PathVariable String id, HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null) {
            return "redirect:login";
        }
        albumService.deleteAlbumById(id);
        return "redirect:/";
    }
}
