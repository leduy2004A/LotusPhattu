package quanliphattu.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@ToString
@Table(name="phattu")
public class phattu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="phattuid")
    private int phattuid;
    @Lob
    @Column(name ="anhchup",columnDefinition = "longblob")
    private byte[] anhchup;
    @Column(name ="dahoantuc")
    private boolean dahoantuc;
    @Column(name ="email")
    private String email;
    @Column(name ="gioitinh")
    private int gioitinh;
    @Column(name ="ho")
    private String ho;
    @Column(name ="ngaycapnhap")
    private Date ngaycapnhap;
    @Column(name ="ngayhoantuc")
    private Date ngayhoantuc;
    @Column(name ="ngaysinh")
    private Date ngaysinh;
    @Column(name ="ngayxuatgia")
    private Date ngayxuatgia;
    @Column(name ="password")
    private String password;
    @Column(name ="phapdanh")
    private String phapdanh;
    @Column(name ="sodienthoai")
    private String sodienthoai;
    @Column(name ="ten")
    private String ten;
    @Column(name ="tendem")
    private String tendem;
    @Column(name ="isactive")
    private boolean isActive;
    @Column(name ="matkhaugoc")
    private String matkhaugoc;
    @Column(name ="chuaid",insertable = false,updatable = false)
    private Integer chuaid;
    @Column(name ="kieuthanhvienid",insertable = false,updatable = false)
    private int kieuthanhvienid;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "phattu")
    @JsonManagedReference
    private List<phattudaotrangs> phattudaotrangsList;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "phattu")
    @JsonManagedReference
    private List<dondangkys> dondangkysList;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "phattu")
    @JsonManagedReference
    private List<token> tokenList;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "kieuthanhvienid")
    private kieuthanhviens kieuthanhviens;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name ="chuaid")
    private chuas chuas;

}

