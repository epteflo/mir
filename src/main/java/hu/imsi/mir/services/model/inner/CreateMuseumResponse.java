package hu.imsi.mir.services.model.inner;

public class CreateMuseumResponse extends Response{

    Museum museum;

    public Museum getMuseum() {
        return museum;
    }

    public void setMuseum(Museum museum) {
        this.museum = museum;
    }
}
