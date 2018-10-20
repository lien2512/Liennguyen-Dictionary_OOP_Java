package sample;

public class Word {

    private String word_target, word_explain;

    public Word(String s)
    {
        String [] a = s.split("\\s", 2);
        this.word_target = a[0];
        this.word_explain = a[1];
    }

    public Word(){
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    @Override
    public String toString() {
        return this.word_target;
    }
}
