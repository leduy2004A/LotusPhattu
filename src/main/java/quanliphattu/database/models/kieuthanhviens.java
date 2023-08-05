package quanliphattu.database.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="kieuthanhviens")
public class kieuthanhviens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kieuthanhvienid")
    private int kieuthanhvienid;
    @Column(name="code")
    private String code;
    @Column(name="tenkieu")
    private String tenkieu;
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "kieuthanhviens")
    @JsonManagedReference
    private List<phattu> phattuList;


}
