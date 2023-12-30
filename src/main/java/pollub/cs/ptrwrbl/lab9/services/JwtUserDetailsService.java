package pollub.cs.ptrwrbl.lab9.services;

import pollub.cs.ptrwrbl.lab9.models.UserDAO;
import pollub.cs.ptrwrbl.lab9.models.UserDTO;
import pollub.cs.ptrwrbl.lab9.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userDAO;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        UserDAO user = userDAO.findByUsername(username);
        if (user == null) {
            throw new
                    UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),new ArrayList<>());
    }
    public UserDAO save(UserDTO user) {
        UserDAO newUser = new UserDAO();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userDAO.save(newUser);
    }
}