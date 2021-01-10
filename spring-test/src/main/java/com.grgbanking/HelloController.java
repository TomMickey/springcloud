package com.grgbanking;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @RequestMapping("/encrypt")
    public String encrypt(@RequestParam("encrypt") String encrypt){
        log.info("------------------");
        log.info(encrypt);
        String result = DES3Utils.encrypt(encrypt);
        return result;
    }

    @RequestMapping("/decrypt")
    public String decrypt(@RequestParam("decrypt") String decrypt){
        log.info("------------------");
        log.info(decrypt);
        String result = DES3Utils.decrypt(decrypt);
        return result;
    }
}
