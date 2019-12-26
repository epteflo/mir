package hu.imsi.mir.dao.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private HUser user;

    @Column(name = "source_module")
    private String sourceModul;

    @Column(name = "source_method")
    private String sourceMethod;

    @Column(name = "severity")
    private String severity;

    @Column(name = "trace")
    private String trace;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private Date createdAt;
}
