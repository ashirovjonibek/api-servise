package service_1.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service_1.demo.dto.UserDto;
import service_1.demo.entity.User;
import service_1.demo.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(UserDto dto){
        User user=new User(dto.getPassport(),dto.getFullName(),dto.getAddress());
        userRepository.save(user);
    }

    public UserDto findByPassportNum(String passport){
        User byPassport = userRepository.findByPassport(passport);
        UserDto dto=new UserDto(byPassport.getPassport(),byPassport.getFullName(),byPassport.getAddress());
        return dto;
    }
}
