package com.sushil.moderator.services;

import com.sushil.moderator.model.APIResponse;
import com.sushil.moderator.model.ValidationRequest;
import com.sushil.moderator.util.BlockedWordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Sushil Saha
 *
 * created on 2019-09-20
 */

@Service
public class ValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationService.class);

    @Value("${black.listed}")
    private String words;

    private BlockedWordUtil blockedWordUtil;

    public ValidationService(BlockedWordUtil blockedWordUtil){
        this.blockedWordUtil = blockedWordUtil;
    }

    public APIResponse isValidContent(ValidationRequest request) {
        debug("validation string :: {}", request.getMessage());
        String message = blockedWordUtil.checkBlockedWords(request.getMessage());

        if(null == message)
            return new APIResponse(HttpStatus.OK);
        else
            return new APIResponse(HttpStatus.OK, message);
    }

    private void debug(String message, String... ob){
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(message, ob);
        }
    }

}