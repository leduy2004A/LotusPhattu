package quanliphattu.database.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "daotrangs")
public class daotrangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="daotrangid")
    private int daotrangid;
    @Column(name="daketthuc")
    private boolean daketthuc;
    @Column(name="noidung")
    private String noidung;
    @Column(name="noitochuc")
    private String noitochuc;
    @Column(name="sothanhvienthamgia")
    private int sothanhvienthamgia;
    @Column(name="thoigiantochuc")
    private Date thoigiantochuc;
    @Column(name="nguoitrutri")
    private int nguoitrutri;
    @OneToMany(fetch = FetchType.LAZY,mappedBy ="daotrangs")
    @JsonManagedReference
    private List<dondangkys> dondangkysList;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "daotrangs")
    @JsonManagedReference
    private List<phattudaotrangs> phattudaotrangsList;


}

