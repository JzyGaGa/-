package com.nowcoder.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.sound.midi.SysexMessage;

@Controller
public class JiaController {


    @RequestMapping(path = "/demo1",method = {RequestMethod.GET})
    @ResponseBody
    public String demo1(@PathVariable("code") String code,
                        @RequestParam(value = "code1",required = false,defaultValue = "20") String code1,
                        @RequestParam(value = "code1",required = false,defaultValue = "20") int code2
                        ){
        System.out.print("6666");
        return  String.format("%s,%s",code,code1,code2);
    }


}
