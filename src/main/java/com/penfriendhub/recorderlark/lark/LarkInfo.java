package com.penfriendhub.recorderlark.lark;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotEmpty;

/**
 * The basic configuration of the lark robot,
 * you need to configure the relevant variables in application.yml
 * @see LarkConfig Implementing Bean Injections
 * @author unickcheng
 * @since 2023-03-18
 */

@Slf4j
@Data
@Validated
public class LarkInfo {
    @NotEmpty(message = "The environment variable LARK_TOKEN must not be empty.")
    private String verificationToken;
    @NotEmpty(message = "The environment variable LARK_ENCRYPT_KEY must not be empty.")
    private String encryptKey;
    @NotEmpty(message = "The environment variable LARK_APP_ID must not be empty.")
    private String appId;
    @NotEmpty(message = "The environment variable LARK_APP_SECRET must not be empty.")
    private String appSecret;

    @PostConstruct
    public void validate() {
        log.info("Hi, there. LarkInfo has been loaded successfully.");
    }
}
