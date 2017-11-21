package com.oxilo.oioindia.retrofit;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by manuelvicnt on 11/11/2016.
 */

public class MockInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        addDelay();

        return new Response.Builder()
                .code(200)
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_0)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .body(ResponseBody.create(MediaType.parse("application/json"), "{}"))
                .build();
    }

    private void addDelay() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
