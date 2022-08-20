package classify.webserver.controller;

import classify.webserver.service.PictureService;
import classify.webserver.service.TransferService;
import classify.webserver.service.TransferService.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@AllArgsConstructor
@Controller
public class TransferController {
    // 파이썬 inference 서버랑 통신하기 위한 컨트롤러

    private final TransferService transferService;

    @GetMapping("/transfer/{id}")
    public String transfer(@PathVariable int id) throws IOException {
        //전송하기
        String url = ("https://flask.run-asia-northeast1.goorm.io/api");
        transferService.setState(id);
        transferService.transferImg(id, url);
        return "redirect:/";
    }

}
