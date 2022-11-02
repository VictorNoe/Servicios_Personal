package mx.edu.utez.personal4b.model.position;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BeanPosition {
    Long id;
    String position;
    String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
