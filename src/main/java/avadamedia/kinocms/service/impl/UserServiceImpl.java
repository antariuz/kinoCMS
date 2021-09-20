package avadamedia.kinocms.service.impl;

import avadamedia.kinocms.model.User;
import avadamedia.kinocms.repository.UserRepository;
import avadamedia.kinocms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public void createUser(User user) {
        repository.save(user);
    }

    @Override
    public void updateUser(User user) {
        repository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void deleteAllUsers() {
        repository.deleteAll();
    }

    @Override
    public Long getMaxId() {
        return repository.getMaxId();
    }
}
