
package classify.webserver.repository;

import classify.webserver.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PictureRepository extends JpaRepository<Picture, Integer>{

    Page<Picture> findAll(Pageable pageable);

}
