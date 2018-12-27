package org.wm.apilab.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.wm.apilab.utils.Const;
import org.wm.apilab.utils.JSONUtils;

@RestController
public class ExceptionController {
    
    @RequestMapping(value="/403", method=RequestMethod.GET)
    public String path403(){
        return JSONUtils.fillResultString(Const.RC_903, null);
    }
    
    @RequestMapping(value="/500", method=RequestMethod.GET)
    public String path500(){
        return JSONUtils.fillResultString(Const.RC_999, null);
    }
    
}
