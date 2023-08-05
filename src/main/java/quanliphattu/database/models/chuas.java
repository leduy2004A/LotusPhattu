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
@Table(name="chuas")
public class chuas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chuaid")
    private int chuaid;
    @Column(name="capnhat")
    private Date capnhat;
    @Column(name="diachi")
    private String diachi;
    @Column(name="ngaythanhlap")
    private Date ngaythanhlap;
    @Column(name="tenchua")
    private String tenchua;
    @Column(name="trutri")
    private int trutri;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "chuas")
    @JsonManagedReference
    private List<phattu> phattuList;


}
