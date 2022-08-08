package classify.webserver.controller;

import classify.webserver.entity.Picture;
import classify.webserver.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.MalformedURLException;
import java.net.URL;

@Controller
public class TransferController {

    private final PictureService pictureService;


    public TransferController(PictureService pictureService) {
        this.pictureService = pictureService;
    }



    @GetMapping("/transfer")
    public String transfer() throws MalformedURLException {
        //전송하기
        URL url = new URL("http://httpbin.org/post");


        return "redirect:/";
    }
}
