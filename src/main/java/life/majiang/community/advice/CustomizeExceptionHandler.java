package life.majiang.community.advice;

import life.majiang.community.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by codedrinker on 2019/5/28.
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable e,  HttpServletRequest request,Model model) {

        if (e instanceof CustomizeException){

            model.addAttribute("message",e.getMessage());
        }else {

            model.addAttribute("message","服务异常");
        }
            return new ModelAndView("error");
        }

//    private HttpStatus getStatus(HttpServletRequest request) {
//        Integer statusCode = (Integer)request.getAttribute("java.servlet.error.status_code");
//        if (statusCode == null)
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        return HttpStatus.valueOf(statusCode);
//    }

}
