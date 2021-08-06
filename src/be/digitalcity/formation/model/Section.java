package be.digitalcity.formation.model;

public class Section {

    private long id;
    private String name;
    private int delegateId;

    public Section() {
    }

    public Section(long id, String name, int delegateId) {
        this.id = id;
        this.name = name;
        this.delegateId = delegateId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDelegateId() {
        return delegateId;
    }

    public void setDelegateId(int delegateId) {
        this.delegateId = delegateId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Section{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", delegateId=").append(delegateId);
        sb.append('}');
        return sb.toString();
    }
}
