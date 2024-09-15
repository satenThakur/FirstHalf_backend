package com.firsthalf.service;

import com.firsthalf.api.payload.ResponseData;
import com.firsthalf.entity.User;
import com.firsthalf.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.firsthalf.utility.Constants.FAILED;
import static com.firsthalf.utility.Constants.SUCCESS;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
    public ResponseData getUserDetails(String phone){
       List<User> users=userRepository.findAll();
       User foundUser = null;
        String msg="";
       int status=SUCCESS;
       try{
           for(int i=0;i<users.size();i++){
               if(phone.equalsIgnoreCase(users.get(i).getPhone())){
                   foundUser= users.get(i);
               }
           }
           if(foundUser==null){
                msg="User does not exist for "+phone+" Please Provide correct phone number "+phone+" or Sign Up ";
               status=FAILED;
           }
       }catch (Exception e){
           e.printStackTrace();
       }finally {
           return  new ResponseData(status,msg,foundUser);
       }

    }
    public Boolean checkIfUserAlreadyExist(String phone){
        boolean isUserExist=false;
        List<User> users=userRepository.findAll();
        try{
            for(int i=0;i<users.size();i++){
                if(phone.equalsIgnoreCase(users.get(i).getPhone())){
                    isUserExist= true;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return isUserExist;
        }

    }

    public void createUser(User user){
       userRepository.save(user);
    }

    public void updateUser(User user){
        userRepository.save(user);
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }
}
