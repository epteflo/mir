package hu.imsi.mir.util;

import hu.imsi.mir.services.model.inner.Museum;
import hu.imsi.mir.services.model.ws.WSMuseum;

import java.util.HashMap;
import java.util.Map;

public class MapPairs {

    public static final Map<Class, Class> MAPPER_DISPATCHER = new HashMap<>();
    static {
        //IN
        MAPPER_DISPATCHER.put(WSMuseum.class, Museum.class);

        //OUT
        MAPPER_DISPATCHER.put(Museum.class, WSMuseum.class);
    }
}
