package life.majiang.community.community.Controller;

import life.majiang.community.community.Controller.dto.accessTokenDTO;
import life.majiang.community.community.Controller.dto.githubUser;
import life.majiang.community.community.pprovider.githubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private githubProvider githubProvider;

    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        accessTokenDTO accessTokenDTO = new accessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("8a402690f82e31f239bc");
        accessTokenDTO.setClient_secret("8ac227b6fd4112850c7588b5f81fd14b79ccdc0d");
        accessTokenDTO.setRedirect_uri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        githubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return "index";
    }
}
