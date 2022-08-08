package classify.webserver.controller;

import classify.webserver.entity.Picture;
import classify.webserver.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Controller
public  class MainController {

    private final PictureService pictureService;

    public MainController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Picture> pictures = pictureService.searchPictureAll();
        model.addAttribute("pictures", pictures);
        return "index";
    }

    @GetMapping("/uploadpage")
    public String uploadPage() {

        return "uploadpage";
    }

    @PostMapping("/fileupload")
    public String upload(MultipartHttpServletRequest multipartHttpServletRequest) throws InterruptedException {
        List<MultipartFile> fileList = multipartHttpServletRequest.getFiles("files");
        String src = multipartHttpServletRequest.getParameter("src");
        String s = this.pictureService.saveImage(fileList);

        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}" )
    public String delete(@PathVariable("id") String id) {
        System.out.println("id = " + id);
        pictureService.deletePicture(id);
        return "redirect:/";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam String id) {
        Picture picture = pictureService.pictureSearchById(id);
        model.addAttribute("picture", picture);
        return "detail";
    }
}

