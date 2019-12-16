package hu.imsi.mir.services.model.inner;

public class Param {

    protected String key;

    protected String value;

    protected String ref;

    public Param() {
    }

    public Param(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String value) {
        this.key = value;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRef() {
        return this.ref;
    }

    public void setRef(String value) {
        this.ref = value;
    }
}

