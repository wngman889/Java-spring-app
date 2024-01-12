package com.restapi.services;

import com.restapi.models.User;
import com.restapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements CustomService<User> {
    private UserRepository _userRepository;

    @Autowired
    public UserServiceImpl(UserRepository _userRepository) {
        this._userRepository = _userRepository;
    }
    @Override
    public List<User> findAll() {
        return _userRepository.findAll();
    }

    @Override
    public User findById(int id) {
        Optional<User> result = _userRepository.findById(id);

        User user = null;

        if(result.isPresent()) {
            user = result.get();
        }else {
            throw new RuntimeException("Did not find any user with that id: " + id);
        }
        return user;
    }

    @Override
    public User save(User saveInDB) {
        return _userRepository.save(saveInDB);
    }

    @Override
    public void deleteById(int id) {
        _userRepository.deleteById(id);
    }
}
