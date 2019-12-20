package hu.imsi.mir.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "contents")
@Data
public class HContent {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "content_url")
    private String contentUrl;

}
