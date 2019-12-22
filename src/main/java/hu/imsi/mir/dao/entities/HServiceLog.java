package hu.imsi.mir.dao.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "service_logs")
@Data
public class HServiceLog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "message")
    private String message;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "source_modul")
    private String sourceModul;

    @Column(name = "source_method")
    private String sourceMethod;

    @Column(name = "severity")
    private String severity;

    @Column(name = "trace")
    private String trace;
}
