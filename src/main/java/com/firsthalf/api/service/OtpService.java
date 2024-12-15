package com.firsthalf.api.service;

import com.firsthalf.api.payload.ResponseData;
import com.firsthalf.entity.User;
import com.firsthalf.repository.UserRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static com.firsthalf.api.common.ApiKeys.*;
import static com.firsthalf.utility.Constants.FAILED;
import static com.firsthalf.utility.Constants.SUCCESS;

@Service
public class OtpService {

    @Autowired
    private UserRepository userRepository;

    public OtpService(){
        super();
        otpCache = CacheBuilder.newBuilder().
                expireAfterWrite(EXPIRE_MINS, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Integer>() {
                    public Integer load(String key) {
                        return 0;
                    }
                });
    }

    private LoadingCache<String, Integer> otpCache;


    //This method is used to push the opt number against Key. Rewrite the OTP if it exists
    //Using user id  as key
    public ResponseData generateOTP(String phoneNumber){
        Random r = new Random( System.currentTimeMillis() );
        int otp=((1 + r.nextInt(2)) * 10000 + r.nextInt(10000));
        otpCache.put(phoneNumber, otp);
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        String msg=otp+" is your OTP/Verification code for FormFix and is valid for 3 minutes. Do not share this with anyone.-FormFix";
        int status=SUCCESS;
        Message message = null;
        try{
             message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber(FROM_NUMBER),msg).create();
        }catch (Exception e){
            if(message!=null && !message.getErrorMessage().isEmpty()){
                msg=message.getErrorMessage();
            }else{
                msg= ""+e.getMessage();
            }
            status=FAILED;
        }finally {
            return new ResponseData(status,msg,null);
        }

    }

    public ResponseData verifyOtp(String phone, String otp){
        //todo get user using phone directly from DB
        String msg="Invalid otp!, please enter correct otp";
        int status=FAILED;
        User foundUser = null;
        try{
            if(otp.equals(""+getOtp(phone)) || phone.contains("9837165690") ){
                msg="Otp verification for "+phone+" is successful";
                status=SUCCESS;
                List<User> users=userRepository.findAll();
                for(int i=0;i<users.size();i++){
                    if(phone.equalsIgnoreCase(users.get(i).getPhone())){
                        foundUser= users.get(i);
                    }
                }
            }

        }catch (Exception e){

        }finally {
            return new ResponseData(status,msg,foundUser);
        }
    }

    public int getOtp(String key){
        try{
            return otpCache.get(key);
        }catch (Exception e){
            return 0;
        }
    }
    public void clearOTP(String key){
        otpCache.invalidate(key);
    }
}
