package hu.imsi.mir.services.model.inner;


import hu.imsi.mir.services.model.ws.WSParam;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

public class Params {

    protected List<Param> param;

    public List<Param> getParam() {
        if (param == null) {
            param = new ArrayList<>();
        }
        return this.param;
    }

}
