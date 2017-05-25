package SourcePackages;

import AnalyticsPackage.WordAnalytics;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Echo01 on 5/19/2017.
 */
public class HashTableClass implements java.io.Serializable {
    private HashNodeClass[] Htable;
    private String location;
    private String[] catchString;
    private final Integer size = 1000;
    private static final int FNV_32_INIT = 0x811c9dc5;
    private static final int FNV_32_PRIME = 0x01000193;

    public HashTableClass(String location) throws IOException {
        this.location = location;
        String temp1;
        try(FileInputStream FIS = new FileInputStream(location)){
            temp1= IOUtils.toString(FIS,"UTF-8");
            System.out.print(temp1);
            System.out.println("------------------------------------------------");
            System.out.println();
        }
        String[] tempCatchString = temp1.split("[,?.!;:\\r\\n\\s]+");
        this.catchString=tempCatchString;
        this.Htable = new HashNodeClass[size];
        for(int i=0;i<size;i++){
            this.Htable[i] = null;
        }
        this.insertIntoHashTable(tempCatchString);
        this.printTop10(WordAnalytics.top10Numbers(WordAnalytics.convertToArrayList(this.Htable)));
    }

    public int hash32(final String k) {
        int rv = FNV_32_INIT;
        final int len = k.length();
        for(int i = 0; i < len; i++) {
            char temp = k.toLowerCase().charAt(i);
            rv ^= temp;
            rv *= FNV_32_PRIME;
        }
        if (rv < 0) rv &= ~0x80000000;
        return rv % size;
    }

    public void insertIntoHashTable(String[] input){
        for(int i=0;i<input.length;i++){
            String temp = input[i];
            int obtHashCode = this.hash32(temp);
            if(Htable[obtHashCode]==null){
                Htable[obtHashCode] = new HashNodeClass();
                Htable[obtHashCode].insertNewNode(temp,obtHashCode);
            }else{
                Htable[obtHashCode].updateNodeEntry(temp,obtHashCode);
            }
        }
    }
    public void printTop10(LLNode[] input){
        System.out.println("Top 10 word count");
        for(int i=0;i<10;i++){
            System.out.println(input[i].getWord()+" "+input[i].getWordCount());
        }
    }

    public HashNodeClass[] getHtable() {
        return Htable;
    }

    public void setHtable(HashNodeClass[] htable) {
        Htable = htable;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String[] getCatchString() {
        return catchString;
    }

    public void setCatchString(String[] catchString) {
        this.catchString = catchString;
    }

    public Integer getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "HashTableClass{" +
                "Htable=" + Arrays.toString(Htable) +
                ", location='" + location + '\'' +
                ", catchString=" + Arrays.toString(catchString) +
                ", size=" + size +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof HashTableClass)) return false;

        HashTableClass that = (HashTableClass) o;

        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getHtable(), that.getHtable())) return false;
        if (!getLocation().equals(that.getLocation())) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(getCatchString(), that.getCatchString())) return false;
        return getSize().equals(that.getSize());
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(getHtable());
        result = 31 * result + getLocation().hashCode();
        result = 31 * result + Arrays.hashCode(getCatchString());
        result = 31 * result + getSize().hashCode();
        return result;
    }
}
