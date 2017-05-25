package SourcePackages;

import java.util.LinkedList;

/**
 * Created by Echo01 on 5/19/2017.
 */
public class HashNodeClass implements java.io.Serializable {
    private int HashValue;
    private LinkedList<LLNode> LLNEntry;

    public HashNodeClass() {
        LLNEntry = new LinkedList<>();
        System.out.println("Linked List Empty");
    }
    public void insertNewNode(String input,int HashValue){
        this.HashValue =HashValue;
        LLNEntry.add(new LLNode(input));
        System.out.println("Record from "+this.getHashValue()+" inserted for the first time for "+input);
    }
    public void updateNodeEntry(String input,int HashValue){
        this.HashValue=HashValue;
        LinkedList<LLNode> temporaryList = new LinkedList<>();
        for(LLNode obj : LLNEntry){
            if(input.equalsIgnoreCase(obj.getWord())){
                obj.incrementWordCount();
                System.out.println("Word count increased for " + input);
                System.out.println(obj.getWordCount());
            }
            else {
                temporaryList.add(new LLNode(input));
                System.out.println("Hash algo collision encountered for " + input);
            }
        }
        LLNEntry.addAll(temporaryList);
    }

    public int getHashValue() {
        return HashValue;
    }

    public void setHashValue(int hashValue) {
        HashValue = hashValue;
    }

    public LinkedList<LLNode> getLLNEntry() {
        return LLNEntry;
    }

    public void setLLNEntry(LinkedList<LLNode> LLNEntry) {
        this.LLNEntry = LLNEntry;
    }

    @Override
    public String toString() {
        return "HashNodeClass{" +
                "HashValue=" + HashValue +
                ", LLNEntry=" + LLNEntry +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashNodeClass)) return false;

        HashNodeClass that = (HashNodeClass) o;

        if (getHashValue() != that.getHashValue()) return false;
        return getLLNEntry().equals(that.getLLNEntry());
    }

    @Override
    public int hashCode() {
        int result = getHashValue();
        result = 31 * result + getLLNEntry().hashCode();
        return result;
    }
}
