package quanliphattu.database.dto;

import lombok.*;
import org.springframework.context.annotation.Bean;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class InfoToken {
    private String gmail;
    private String token;
}
