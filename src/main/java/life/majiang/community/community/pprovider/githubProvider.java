package life.majiang.community.community.pprovider;

import com.alibaba.fastjson.JSON;
import life.majiang.community.community.dto.accessTokenDTO;
import life.majiang.community.community.dto.githubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;


import java.io.IOException;

@Component
public class githubProvider {
    public String getAccessToken1(accessTokenDTO accessTokenDTO){

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] split = string.split("&");
            String tokenstr = split[0];
            String token = tokenstr.split("=")[1];
            return token;
        } catch (Exception e) {
        }
        return null;
    }



    public String getAccessToken(accessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {
        }
        return null;
    }



//    public githubUser getUser(String accessToken){
//        OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url("https://api.github.com/user?access_token="+accessToken)
//                    .build();
//
//        try {
//            Response response = client.newCall(request).execute();
//            String string = response.body().string();
//            System.out.println(string+"String");
//            githubUser githubUser = JSON.parseObject(string, githubUser.class);
////            System.out.println(githubUser.getName()+"ID"+githubUser.getId()+"Bio"+githubUser.getBio());
//            return githubUser;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//
//    }
public githubUser getUser(String accessToken) {
    OkHttpClient client = new OkHttpClient();
    Request request = new Request.Builder()
            .url("https://api.github.com/user")
            .header("Authorization", "token " + accessToken)
            .build();
    try {
        Response response = client.newCall(request).execute();
        String string = response.body().string();
        githubUser githubUser = JSON.parseObject(string, githubUser.class);
        return githubUser;
    } catch (Exception e) {
    }
    return null;
}
}
