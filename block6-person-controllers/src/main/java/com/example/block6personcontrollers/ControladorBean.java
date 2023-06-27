package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class ControladorBean {
    private final Person bean1;
    private final Person bean2;
    private final Person bean3;

    @Autowired
    public ControladorBean(
            @Qualifier("bean1") Person bean1,
            @Qualifier("bean2") Person bean2,
            @Qualifier("bean3") Person bean3
    ) {
        this.bean1 = bean1;
        this.bean2 = bean2;
        this.bean3 = bean3;
    }

    @GetMapping("/bean/{bean}")
    public Person getBean(@PathVariable String bean) {
        switch (bean) {
            case "bean1":
                return bean1;
            case "bean2":
                return bean2;
            case "bean3":
                return bean3;
            default:
                return null;
        }
    }
}
