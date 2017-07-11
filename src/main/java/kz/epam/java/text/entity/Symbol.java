package kz.epam.java.text.entity;

/**
 * Created by User on 09.07.2017.
 */
public class Symbol implements Component {

    private char ch;

    public Symbol(char ch) {
        this.ch = ch;
    }

    @Override
    public void addComponent(Component composite) {
        throw new UnsupportedOperationException("Impossible action");
    }

    @Override
    public String toString() {
        return String.valueOf(ch);
    }
}



