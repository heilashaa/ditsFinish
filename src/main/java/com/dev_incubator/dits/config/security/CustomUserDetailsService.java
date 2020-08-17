package com.dev_incubator.dits.config.security;

import com.dev_incubator.dits.persistence.entity.User;
import com.dev_incubator.dits.persistence.repository.UserRepository;
import com.dev_incubator.dits.util.MessageSourceFacade;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final MessageSourceFacade messageSource;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (isNull(user)) {
            throw new UsernameNotFoundException(messageSource.getMessage("user.not.found"));
        }
        return new CustomUserDetails(user);
    }
}
