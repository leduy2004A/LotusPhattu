package quanliphattu.database.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import quanliphattu.database.Repository.phattuRepo;
import quanliphattu.database.models.phattu;

import java.util.Optional;

@Component
public class phattuUserDetailService implements UserDetailsService {
    @Autowired
    private phattuRepo ptr;

    @Override
    public UserDetails loadUserByUsername(String gmail) throws UsernameNotFoundException {
     Optional<phattu> phattuOp = ptr.findByEmail(gmail);

    return phattuOp.map(phattuUserDetails::new)
             .orElseThrow(() -> new UsernameNotFoundException("user not found"));

    }
}
