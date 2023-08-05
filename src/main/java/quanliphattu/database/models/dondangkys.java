package quanliphattu.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="dondangkys")
public class dondangkys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="dondangkyid")
    private int dondangkyid;
    @Column(name="ngayguidon")
    private Date ngayguidon ;
    @Column(name="ngayxuly")
    private Date ngayxuly;
    @Column(name="nguoixuly")
    private int nguoixuly;
    @Column(name="trangthaidon")
    private int trangthaidon;
    @Column(name="daotrangid",updatable = false,insertable = false)
    private Integer daotrangid;
    @Column(name="phattuid",updatable = false,insertable = false)
    private int phattuid;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="daotrangid")
    private daotrangs daotrangs;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="phattuid")
    private phattu phattu;


}
