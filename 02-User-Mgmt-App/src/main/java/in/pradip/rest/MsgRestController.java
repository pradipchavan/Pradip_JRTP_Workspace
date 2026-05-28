package in.pradip.rest;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class MsgRestController {

    //it is optional if we use @Slf4j annotation
   // private Logger logger = LoggerFactory.getLogger(MsgRestController.class);

    @GetMapping("/welcome")
    public String getWelcomeMsg() {
        //logger.info("getWelcomeMsg() - started");
        log.info("getWelcomeMsg() - started");
        //logger.warn("getWelcomeMsg() - executing");
        log.warn("getWelcomeMsg() - executing");
        String str = "Welocome to Pradip!!";
        //logger.warn("getWelcomeMsg() - completed");
        log.warn("getWelcomeMsg() - completed");
        return str;
    }

    @GetMapping("/greet")
    public String getGreetMsg() {
        //logger.info("getGreetMsg() - started");
        log.info("getGreetMsg() - started");
        //logger.warn("getGreetMsg() - executing");
        log.warn("getGreetMsg() - executing");
        String str = "Greet Message!!";
        //logger.warn("getGreetMsg() - completed");
        log.warn("getGreetMsg() - completed");
        return str;
    }
}
