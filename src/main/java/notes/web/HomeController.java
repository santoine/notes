package notes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by User on 11/06/2016.
 */
@Controller
public class HomeController {

    private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/")
    public String home(){
        LOGGER.info("redirect from / to /notes/new");
        return "redirect:/notes/new";
    }
}
