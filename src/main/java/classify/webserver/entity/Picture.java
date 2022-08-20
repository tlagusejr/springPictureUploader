package classify.webserver.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.SecureRandom;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String filepath;
    @Column
    private String fileName;
    @Column
    private String serverSaveFileName;
    @Column
    private String state;

    private LocalDateTime createDate;
}