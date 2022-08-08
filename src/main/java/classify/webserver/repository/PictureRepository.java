
package classify.webserver.repository;

import classify.webserver.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface PictureRepository extends JpaRepository<Picture, Integer>{

}
