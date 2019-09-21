package com.sushil.moderator.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Sushil Saha
 *
 * created on 2019-09-20
 */

@Slf4j
@Component
public class BlockedWordUtil {

    @Value("${black.listed}")
    private String blockedWords;

    private static Set<String> blockedWordSet = new HashSet<>();

    @PostConstruct
    public void init() {
        try {
            String[] content;

            content = blockedWords.split(",");
            for (int i =0; i< content.length; i++) {
                blockedWordSet.add(content[i].replaceAll(" ", ""));
            }

            log.debug("{} words to filter out", blockedWordSet.size());

        } catch (Exception e) {
            log.error("init()", e);
        }

    }


    /**
     * iterates over input string and checks whether blocked word(s) are found.
     *
     * @param message
     * @return
     */

    public String checkBlockedWords(String message) {

        if (StringUtils.isEmpty(message)) {
            return "input message can not be empty";
        }

        List<String> blockedWordsList = new ArrayList<>();
        message = message.toLowerCase().replaceAll("[^a-zA-Z]", "");
        String wordToCheck;

        if (!blockedWordSet.isEmpty()) {
            for (int start = 0; start < message.length(); start++) {

                for (int offset = 1; offset < (message.length() + 1 - start); offset++) {

                    wordToCheck = message.substring(start, start + offset);
                    if (blockedWordSet.contains(wordToCheck)) {
                        blockedWordsList.add(wordToCheck);
                    }
                }
            }
        }

        if(!blockedWordsList.isEmpty()) {
            return "input contains blocked word(s)";
        }

        return null;
    }

}