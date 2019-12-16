package hu.imsi.mir.services.model.ws;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "params",
        propOrder = {
        "param"
})
public class WSParams {

    protected List<WSParam> param;

    @XmlElement
    public List<WSParam> getParam() {
        if (param == null) {
            param = new ArrayList<>();
        }
        return this.param;
    }

}
