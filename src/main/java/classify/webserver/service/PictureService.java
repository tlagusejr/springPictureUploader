package classify.webserver.service;

import classify.webserver.entity.Picture;
import classify.webserver.repository.PictureRepository;
import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    public String saveImage(List<MultipartFile> mfs){

        String uploadFolder = "C:\\Users\\##\\Downloads\\project\\";
        String dbsave =  "C:\\Users\\##\\Downloads\\webserver\\src\\main\\resources\\static\\img\\";

        System.out.println(mfs.size());
        for (MultipartFile mf : mfs) {
            String original = mf.getOriginalFilename();
            String uploadPath = uploadFolder + original;
            dbsave = dbsave+ original;
            System.out.println("originFileName : " + original);
            System.out.println("fileSize : " + mf.getSize());
            //이미지 썸네일생성

           //썸네일과 원본저장
            try {
                mf.transferTo(new File(uploadPath));
                Thumbnails.of(uploadPath)
                        .size(160, 160)
                        .toFile(uploadFolder + "thumbnail\\" + original);


            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();;
            }

            // DB저장 부분
            Picture picture = new Picture();
            picture.setFilepath(dbsave);
            picture.setFileName(original);
            picture.setCreateDate(LocalDateTime.now());
            picture.setState("업로드 성공!");
            this.pictureRepository.save(picture);

        }
        return ""; // 에러 나중에?
    }
    public Page<Picture> searchPictureAll(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10,Sort.by(sorts));

        return this.pictureRepository.findAll(pageable);
    }
    public Picture pictureSearchById(String id){
        Optional<Picture> byId = pictureRepository.findById(Integer.valueOf(id));
        Picture pic_one = byId.get();
        return pic_one;
    }
    public void deletePicture(String id){
        pictureRepository.deleteById(Integer.valueOf(id));

    }
    public Picture detailFindImg(String id){
        Optional<Picture> byId = pictureRepository.findById(Integer.valueOf(id));
        Picture pic_one = byId.get();
        return pic_one;
    }
}