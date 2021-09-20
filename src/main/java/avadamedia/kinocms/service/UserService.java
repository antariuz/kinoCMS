package avadamedia.kinocms.service;

import avadamedia.kinocms.model.User;

public interface UserService {

    void createUser(User user);

    void updateUser(User user);

    void deleteUserById(Long id);

    Iterable<User> getAllUsers();

    User getUserById(Long id);

    void deleteAllUsers();

    Integer getMaxId();

}
