package com.practice.basic_auth.services.impl;

import com.practice.basic_auth.entities.User;
import com.practice.basic_auth.payloads.OutputResponse;
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
import org.springframework.http.ResponseEntity;
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
  public OutputResponse<UserDto> createUser(User user) {



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
      return new OutputResponse<>(true, userDto, "Successfully created the user... PLEASE LOGIN!!!!!");
    }else{
      return new OutputResponse<>(false, null,"The specified Email id is already present");
    }
  }

  @Override
  public OutputResponse<UserDto> getUser(String email, String password) {

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
        return new OutputResponse<>(true,  userDto, "User logged in successfully.");

      }
      else{
        return new OutputResponse<>(false, null, "Entered Password is wrong");
      }
    }else
      return new OutputResponse<>(false, null, "Entered Email ID does not exist");
  }

  public User getLoggedInUser(Principal principal){
    return userRepo.findByEmail(principal.getName()).get();
  }

  public static final String[] adminAccess= {"ROLE_ADMIN", "ROLE_USER"};
  public List<String> getRolesOfLoggedInUser(Principal principal){
    String roles = getLoggedInUser(principal).getRole();
    List<String> assignRoles = Arrays.stream(roles.split(",")).collect(Collectors.toList());
    if(assignRoles.contains("ROLE_ADMIN")) {
      return Arrays.stream(adminAccess).collect(Collectors.toList());
    }
    else
      return List.of(new String[]{"ROLE_USER"});
  }
  public String giveAccess(String email, String userRole, Principal principal){
    User user = userRepo.findByEmail(email).get();
    List<String> activeRoles = getRolesOfLoggedInUser(principal);
    String newRole ="";
    if(activeRoles.contains(userRole)){
      newRole= user.getRole()+","+userRole;
      user.setRole(newRole);
    }
    userRepo.save(user);
    return "Hello!!! "+user.getEmail()+". New Role has been assigned to you by "+principal.getName()+"i.e., "+user.getRole();
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
  public OutputResponse<UserDto> update(User user, Principal principal) {
    User loggedinUser = userRepo.findByEmail(principal.getName()).get();
    Optional<User> ifPresent = userRepo.findByEmail(user.getEmail());
    if(ifPresent.isEmpty()){
      loggedinUser.setEmail(user.getEmail());
      loggedinUser.setFirstName(user.getFirstName());
      loggedinUser.setLastName(user.getLastName());
      loggedinUser.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepo.save(loggedinUser);

    }
    else{
      return new OutputResponse<>(false, null,"Given mail id is already present...!!!!");

    }
    return new OutputResponse<>(true, new UserDto(user),"The user details are successfully updated...");

  }


}
