package SourcePackages;

import org.jetbrains.annotations.NotNull;

/**
 * Created by Echo01 on 5/19/2017.
 */
public class LLNode implements Comparable<LLNode> {
    private String word;
    private int wordCount=0;
    LLNode(String word){
        this.word = word;
        ++this.wordCount;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void incrementWordCount(){
        ++this.wordCount;
    }

    public int getWordCount() {
        return wordCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LLNode)) return false;

        LLNode llNode = (LLNode) o;

        return getWord().equals(llNode.getWord());
    }

    @Override
    public int hashCode() {
        return getWord().hashCode();
    }

    @Override
    public int compareTo(@NotNull LLNode o) {
        int compareWord = o.getWordCount();
        return this.wordCount-compareWord;
    }
}
