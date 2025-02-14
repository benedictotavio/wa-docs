package br.com.wa_docs.user.services;

import org.springframework.stereotype.Service;

import br.com.wa_docs.user.domains.User;
import br.com.wa_docs.user.exceptions.UserNotFoundException;
import br.com.wa_docs.user.repositories.UserRepository;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User " + id + " not found."));
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User " + email + " not found."));
    }
}
