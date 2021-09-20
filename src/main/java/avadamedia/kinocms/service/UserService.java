package avadamedia.kinocms.service;

import avadamedia.kinocms.model.User;

public interface UserService {

    void createUser(User user);

    void changeUser(User user);

    void deleteUserById(Integer id);

    Iterable<User> getAllUsers();

    User getUserById(Integer id);

    void deleteAllUsers();

    Integer getMaxId();

}
