package prbrios.nfeapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import prbrios.nfeapi.entities.UserEntity;
import prbrios.nfeapi.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    private PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public List<UserEntity> list() {
        return this.repository.findAll();
    }

    @Override
    public UserEntity insert(UserEntity user) {
        if (user.getId() != null)
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário invalido");

        Optional<UserEntity> userTemp = this.repository.findByUsername(user.getUsername());
        if (userTemp.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario ja existe");
        }

        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        return this.repository.save(user);
    }

    @Override
    public UserEntity update(UserEntity user, Long id) {
        if (user.getId() == null || user.getId() != id)
            new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário encontrado");

        UserEntity u = this.repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário encontrado"));

        user.setUsername(u.getUsername());
        user.setPassword(this.passwordEncoder().encode(user.getPassword()));
        return this.repository.save(user);
    }

    @Override
    public void delete(Long id) {
        UserEntity user = this.repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário encontrado"));
        this.repository.delete(user);
    }

    @Override
    public UserEntity find(Long id) {
        return this.repository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário encontrado"));
    }

}
