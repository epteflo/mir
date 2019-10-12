package hu.imsi.mir.services;

import java.util.HashSet;
import java.util.Set;

public class MirJsonApplication  extends AbstractApplication {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> classes = new HashSet<>();

        classes.add(JsonMIRAPIv1.class);

        return classes;
    }
}
