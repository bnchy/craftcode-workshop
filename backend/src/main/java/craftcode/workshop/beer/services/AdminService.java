package craftcode.workshop.beer.services;

import craftcode.workshop.beer.model.Admin;
import craftcode.workshop.beer.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepository adminRepository;

    public Admin findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }
}
