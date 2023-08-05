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
@Table(name = "token")
public class token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="stoken")
    private String stoken;
    @Column(name ="phattuid",insertable = false,updatable = false)
    private int phattuid;
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "phattuid")
    private phattu phattu;

}
