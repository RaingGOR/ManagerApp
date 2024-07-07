package raingor.ru.managerapp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raingor.ru.managerapp.domain.Authority;
import raingor.ru.managerapp.repositories.RaingorUserRepository;

@Service
@RequiredArgsConstructor
public class RaingorUserDetailService implements UserDetailsService {

    private final RaingorUserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByName(username).map(user -> User.builder()
                .username(user.getName())
                .password(user.getPassword())
                .authorities(user.getAuthorities().stream()
                        .map(Authority::getAuthority)
                        .map(SimpleGrantedAuthority::new).toList()).build()
        ).orElseThrow(() -> new UsernameNotFoundException("User %s not found".formatted(username)));
    }
}
