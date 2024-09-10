package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.User;
import craftcode.workshop.beer.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }


}
