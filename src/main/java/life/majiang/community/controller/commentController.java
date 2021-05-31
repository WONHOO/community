package life.majiang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class commentController {
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    public Object post(){

        return null;

    }

}
