package kz.epam.java.text.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 09.07.2017.
 */
public class Composite implements Component {

    private List<Component> components = new ArrayList();

    public void addComponent(Component composite){
        components.add(composite);
    }

    public List<Component> getComponents() {
        return components;
    }
    @Override
    public String toString() {
        String text = "";
        for(Component component : components){
            text += component;
        }
        return text;
    }
}
