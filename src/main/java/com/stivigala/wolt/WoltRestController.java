package com.stivigala.wolt;

import org.springframework.web.bind.annotation.*;

@RestController
public class WoltRestController {

    @GetMapping("/admin")
    public String adminPage() {
        return "<p>This is an ADMIN site.</p><a href='/'>Back to main site</a>";
    }

    @GetMapping("/customer")
    public String customerPage() {
        return "<p>This is a CUSTOMER site.</p><a href='/'>Back to main site</a>";
    }

    @GetMapping("/manager")
    public String managerPage() {
        return "<p>This is a MANAGER site.</p><a href='/'>Back to main site</a>";
    }

}
