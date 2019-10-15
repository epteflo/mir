package hu.imsi.mir.spring.hibernate.service;


import hu.imsi.mir.util.ApplicationContextProvider;

import static java.lang.System.exit;

public class MirServiceHelper {

    public static MirService getMirService(){
        MirService mirService = ApplicationContextProvider.getApplicationContext().getBean("mirService", MirService.class);
        if(mirService==null){
            System.out.println("MirService not available! Exit!");
            exit(-1);
        }
        return mirService;
    }
}
