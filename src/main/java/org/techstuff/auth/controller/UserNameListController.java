package org.techstuff.auth.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.techstuff.auth.service.InvalidUserNameException;
import org.techstuff.auth.service.UserNameValidationResult;
import org.techstuff.auth.service.UserNameValidationService;
import org.techstuff.auth.util.JsonUtil;

import javax.annotation.Resource;

@Controller
@RequestMapping("/userNameService")
public class UserNameListController {
    private static final Logger logger = Logger.getLogger(UserNameListController.class);

    @Resource
    private JsonUtil<UserNameValidationResult> jsonUtil;

    @Resource
    private UserNameValidationService userNameValidationService;

    @RequestMapping(value = "/checkUserName/{userName}", method = RequestMethod.GET)
    public @ResponseBody String checkUserName(@PathVariable String userName) {
        logger.info("Calling checkUserName method");

        UserNameValidationResult result = null;
        try {
            result = userNameValidationService.validate(userName);
        } catch (InvalidUserNameException exception) {
            result = new UserNameValidationResult();
            result.setMessage(exception.getMessage());
        }
        return jsonUtil.toJson(result);
    }
}
