package com.firsthalf.utils;


import com.firsthalf.common.ApiResponse;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.HashMap;
import java.util.Map;
import static com.firsthalf.common.ApiKeys.*;

public class Utility {

    public static ApiResponse getApiResponse(Object data,String dataIdentifier){
        ApiResponse apiResponse=new ApiResponse();
        Map mapData=new HashMap();
        mapData.put(dataIdentifier,data);
        apiResponse.setData(mapData);
        return apiResponse;
    }

    public static String sendOtp(String phoneNumber){
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
        int min=100000;
        int max=999999;
        int otp=(int)(Math.random()*(max-min+1)+min);
        String msg="your one time Password is : "+otp;
        Message message = Message.creator(new PhoneNumber(phoneNumber),new PhoneNumber(FROM_NUMBER),msg).create();
        System.out.println(""+message.getBody());
        return ""+otp;
    }
}
