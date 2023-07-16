package com.firsthalf.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import org.springframework.stereotype.Service;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static com.firsthalf.common.ApiKeys.*;

@Service
public class OtpService {


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
    public String generateOTP(String key,String phoneNumber){
        Random random = new Random();
        int otp = 100000 + random.nextInt(999999);
        otpCache.put(key, otp);
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        String msg="your verification code is : "+otp;
        Message message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber(FROM_NUMBER),msg).create();
        return msg;
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
