package life.majiang.community.community.Controller;

import life.majiang.community.community.dto.accessTokenDTO;
import life.majiang.community.community.dto.githubUser;
import life.majiang.community.community.pprovider.githubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {
    @Autowired
    private githubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;


    @GetMapping("/callback")
    public String callBack(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request){
        accessTokenDTO accessTokenDTO = new accessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        githubUser user = githubProvider.getUser(accessToken);

//        System.out.println(user.getName());
        if(user != null){
            request.getSession().setAttribute("user",user);
            return "redirect:/";

        }else {
            return "redirect:/";
        }

    }
}
