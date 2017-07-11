package kz.epam.java.text.filtration;

import kz.epam.java.text.entity.Component;
import kz.epam.java.text.entity.Composite;
import kz.epam.java.text.entity.Symbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by User on 11.07.2017.
 */
public class Filter {
        //Вывести слова, которые начинаются и заканчиваются на одну и ту е букву
        List<Component> filteredWords = new ArrayList<>();

    public void printFilteredText(Component composite){
        List<Component> componentsPar = ((Composite) composite).getComponents();
        for(int i = 0; i < componentsPar.size(); i++){
            if(componentsPar.get(i) instanceof Composite){
                List<Component> componentsSen = ((Composite)componentsPar.get(i)).getComponents();
                for(int j = 0; j < componentsSen.size(); j++){
                    if(componentsSen.get(j) instanceof Composite) {
                        List<Component> componentsWord = ((Composite) componentsSen.get(j)).getComponents();
                        for (Component word : componentsWord) {
                            if (word.toString().charAt(0) == word.toString().charAt(word.toString().length() - 1)
                                    & word.toString().length() > 1)
                               if(!containsCheck(word))
                                filteredWords.add(word);
                        }
                    }
                }
            }

        }

        for(Component word : filteredWords){
            System.out.println(word);
        }
    }
    private void secntenceList(){

    }
    private boolean containsCheck(Component word){
        for(Component w : filteredWords){
            String w1 = w.toString();
            String w2 = word.toString();
            if(w1.equals(w2)) {
                return true;
            }
        }
        return false;
    }
}
