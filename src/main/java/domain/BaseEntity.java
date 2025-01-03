package domain;

public abstract class BaseEntity { //sozusagen Unterklasse die die Grundteile für alle Entitäten festlegt, also
                                    //jede Entität braucht ID
    private Long id;

    public BaseEntity(Long id) {
        setId(id);
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        if (id==null || id>=0){
            this.id = id;
        }else {
            throw new InvalidValueException("Entität-ID muss größer gleich 0 sein");
        }
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                '}';
    }
}
