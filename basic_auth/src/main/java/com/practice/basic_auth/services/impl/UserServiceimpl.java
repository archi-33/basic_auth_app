package com.practice.basic_auth.services.impl;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.ServiceResponse;
import com.practice.basic_auth.payloads.UpdateUserDetailsDto;
import com.practice.basic_auth.payloads.UserDto;
import com.practice.basic_auth.repositories.UserRepo;
import com.practice.basic_auth.services.UserService;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl implements UserService {
  public static final String DEFAULT_ROLE= "ROLE_USER";


  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private UserRepo userRepo;

  @Override
  public ServiceResponse<UserDto> createUser(User user) {



    Optional<User> existingUserOpt = userRepo.findByEmail(user.getEmail());

    if(existingUserOpt.isEmpty()){
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      user.setRole(DEFAULT_ROLE);
      User savedUser = userRepo.save(user);

      UserDto userDto = new UserDto();
      userDto.setEmail(savedUser.getEmail());
      userDto.setFirstName(savedUser.getFirstName());
      userDto.setLastName(savedUser.getLastName());
      userDto.setGender(savedUser.getGender());
      userDto.setId(savedUser.getId());
      return new ServiceResponse<>(true, userDto, "Successfully created the user... PLEASE LOGIN!!!!!");
    }else{
      return new ServiceResponse<>(false, null,"The specified Email id is already present");
    }
  }

  @Override
  public ServiceResponse<UserDto> getUser(String email, String password) {

    Optional<User> userOpt=userRepo.findByEmail(email);

    if (userOpt.isPresent()) {
      User user = userOpt.get();
      if (passwordEncoder.matches(password, user.getPassword())) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setGender(user.getGender());
        userDto.setId(user.getId());
        return new ServiceResponse<>(true,  userDto, "User logged in successfully.");

      }
      else{
        return new ServiceResponse<>(false, null, "Entered Password is wrong");
      }
    }else
      return new ServiceResponse<>(false, null, "Entered Email ID does not exist");
  }

  public User getLoggedInUser(String email){
    return userRepo.findByEmail(email).get();
  }

  public static final String[] adminAccess= {"ROLE_ADMIN", "ROLE_USER"};
  public List<String> getRolesOfLoggedInUser(String email){
    String roles = getLoggedInUser(email).getRole();
    List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
    if(assignRoles.contains("ROLE_ADMIN")) {
      return Arrays.stream(adminAccess).collect(Collectors.toList());
    }
    else
      return List.of(new String[]{"ROLE_USER"});
  }
  public ServiceResponse<User> giveAccess(String email, String userRole, Principal principal){
    User user = userRepo.findByEmail(email).get();
    if(user!=null) {
      List<String> activeRoles = getRolesOfLoggedInUser(email);
      String newRole = "";
      if (!(activeRoles.contains(userRole))) {
        newRole = user.getRole() + "," + userRole;
        user.setRole(newRole);
        userRepo.save(user);
        return new ServiceResponse<>(true, user,
            "Hello!!! " + user.getEmail() + ". New Role has been assigned to you by "
                + principal.getName() + "i.e., " + user.getRole());

      } else {
        return new ServiceResponse<>(false, user, "The user is already " + userRole);
      }
    }
    return new ServiceResponse<>(false, null, "cannot find the user with specified mail..!!!!");

  }

  @Override
  public List<UserDto> loadAll() {
    List<User> list = userRepo.findAll();
    List<UserDto> newList= new ArrayList<>();
    for(int i =0; i<list.size(); i++){
      newList.add(new UserDto(list.get(i)));
    }


    return newList;
  }

  @Override
  public ServiceResponse<UserDto> update(UpdateUserDetailsDto updateUserDetailsDto, Principal principal) {
    User loggedinUser = userRepo.findByEmail(principal.getName()).get();
    Optional<User> checkNewMail = userRepo.findByEmail(updateUserDetailsDto.getEmail());
    if(checkNewMail.isEmpty()){
      loggedinUser.setEmail(updateUserDetailsDto.getEmail());
      loggedinUser.setFirstName(updateUserDetailsDto.getFirstName());
      loggedinUser.setLastName(updateUserDetailsDto.getLastName());
      loggedinUser.setPassword(passwordEncoder.encode(updateUserDetailsDto.getPassword()));
      userRepo.save(loggedinUser);

    }
    else{
      return new ServiceResponse<>(false, null,"Given mail id is already present...!!!!");

    }
    return new ServiceResponse<>(true, new UserDto(updateUserDetailsDto),"The user details are successfully updated...");

  }


}
