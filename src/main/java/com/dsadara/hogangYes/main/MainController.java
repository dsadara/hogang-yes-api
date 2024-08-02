package com.dsadara.hogangYes.main;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@ApiIgnore
public class MainController {
    @GetMapping("/")
    public String main() {
        return "this is main page";
    }
}
