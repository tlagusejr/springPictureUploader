package classify.webserver.controller;

import classify.webserver.entity.Picture;
import classify.webserver.service.PictureService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Controller
public  class MainController {

    // 메인 웹 사이트 에 관련된 컨트롤러
    private final PictureService pictureService;

    public MainController(PictureService pictureService) {
        this.pictureService = pictureService;
    }

    @GetMapping("/")
    public String index(Model model,@RequestParam(value="page", defaultValue="0") int page) {
        Page<Picture> paging = this.pictureService.searchPictureAll(page);
        model.addAttribute("paging", paging);
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

    @DeleteMapping("/delete/{id}")
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

    @GetMapping("/detail/image")
    public String detailImage(Model model,@RequestParam String id) {
        Picture picture = pictureService.detailFindImg(id);
        model.addAttribute("picture", picture);
        System.out.println("picture.getFileName() = " + picture.getFileName());
        return "detailPicture";
    }

}