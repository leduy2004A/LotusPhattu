package quanliphattu.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phattudaotrangs")
public class phattudaotrangs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="phattudaotrangid")
    private int phattudaotrangid;
    @Column(name ="dathamgia")
    private boolean dathamgia;
    @Column(name ="lydokhongthamgia")
    private String lydokhongthamgia;
    @Column(name ="daotrangid",updatable = false,insertable = false)
    private int daotrangid;
    @Column(name ="phattuid",updatable = false,insertable = false)
    private int phattuid;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="phattuid")
    private phattu phattu;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="daotrangid")
    private daotrangs daotrangs;


}
