package life.majiang.community.community.Controller;

import jdk.nashorn.internal.parser.Token;
import life.majiang.community.community.mapper.UserMapper;
import life.majiang.community.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping("/")
    public String Hello(HttpServletRequest request){
        Cookie[] cookie = request.getCookies();
        for (Cookie cookie1 : cookie) {
            if (cookie1.getName().equals("token")){
                String token = cookie1.getValue();
                User user = userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("user", user);
                }
                break;
            }

        }
        return "index";
    }
}
