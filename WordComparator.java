package sample;

import java.util.Comparator;

public class WordComparator implements Comparator<Word> {

    public int compare(Word s1, Word s2) {
        return s1.getWord_target().compareTo(s2.getWord_target());
    }
}
