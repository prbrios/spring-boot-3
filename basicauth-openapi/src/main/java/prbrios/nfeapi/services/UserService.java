package prbrios.nfeapi.services;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import prbrios.nfeapi.entities.UserEntity;

import java.util.List;

public interface UserService {

    List<UserEntity> list();
    UserEntity insert(UserEntity user);
    UserEntity update(UserEntity user, Long id);
    void delete(Long id);
    UserEntity find(Long id);
}
