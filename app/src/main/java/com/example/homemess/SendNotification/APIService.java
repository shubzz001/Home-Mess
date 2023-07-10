package com.example.homemess.SendNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAA6EbkLv0:APA91bGd9U_MfYlhBpmwyD0C1XH8R9whlZPhYqLleXe6hlJ_BJBm_yQi9KYgz2Ti7TsWhjsfBSkPEpn3VJDlTIX4yIGZKPfmr3NssDVKPg0jNJh30jX1BnYKkYY857aaqlTszWg1bdkC"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body NotificationSender body);
}
