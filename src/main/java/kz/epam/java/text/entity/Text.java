package kz.epam.java.text.entity;

/**
 * Created by User on 09.07.2017.
 */
public class Text {

    private Component composite;

    public Text(Component composite){
        this.composite = composite;
    }

    public Component getComposite(){
        return composite;
    }
    @Override
    public String toString() {
        return composite.toString();
    }
}
