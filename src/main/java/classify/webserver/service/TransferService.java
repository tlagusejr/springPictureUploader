package classify.webserver.service;

import classify.webserver.entity.Picture;
import classify.webserver.repository.PictureRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;


import lombok.SneakyThrows;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TransferService {

    private final PictureRepository pictureRepository;

    public void setState(int id){
        Optional<Picture> byId = pictureRepository.findById(id);
        Picture picture = byId.get();
        picture.setFilepath(picture.getFilepath());
        picture.setFileName(picture.getFileName());
        picture.setCreateDate(picture.getCreateDate());
        picture.setId(picture.getId());
        picture.setState("연산중");
        pictureRepository.save(picture);
    }
    //https://juntcom.tistory.com/140 참고
    public static String getIp(){
        String result = null;
        try {
            result = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            result = "";
        }
        return result;
    }


    @SneakyThrows
    public void transferImg(int id, String urlpahth) throws IOException {
        Optional<Picture> byId = pictureRepository.findById(id);
        Picture picture = byId.get();
        String ip = getIp();
        String url_file ="http://" + ip + "/img/" + picture.getFileName();

        //1. json 생성후 body에 이미지와 id를 추가.
        JSONObject jsonObject= new JSONObject();
        jsonObject.put("id",id);
        jsonObject.put("url",url_file);
        String stringJson = jsonObject.toString();
        System.out.println("json 객체생성.."+stringJson);
        //2.
        URL url = new URL(urlpahth);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type","applicaiton/json;utf-8");
        con.setRequestProperty("Accept","application/json");
        con.setDoOutput(true);
        //
        try{OutputStream os = con.getOutputStream();
            {
            byte[] input = stringJson.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        //
            try(BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"))){
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while((responseLine = br.readLine()) != null)
                {response.append(responseLine.trim());
        }
                String result = response.toString();
                ObjectMapper mapper = new ObjectMapper();
                try{
                    Map<String, String> map = mapper.readValue(result, Map.class);
                    String animal = map.get("class");
                    String percent = map.get("confidence");
                    float f = Float.parseFloat(percent) * 100.0F;
                    int int_percent = (int) f;
                    picture.setFilepath(picture.getFilepath());
                    picture.setFileName(picture.getFileName());
                    picture.setCreateDate(picture.getCreateDate());
                    picture.setId(picture.getId());
                    picture.setState(animal+"일 확률 "+int_percent+"%");
                    pictureRepository.save(picture);

                } catch (IOException e){e.printStackTrace();

                }


    }

} catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

