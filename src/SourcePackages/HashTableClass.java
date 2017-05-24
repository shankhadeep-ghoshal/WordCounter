package SourcePackages;

import AnalyticsPackage.WordAnalytics;
import org.apache.commons.io.IOUtils;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Echo01 on 5/19/2017.
 */
public class HashTableClass {
    private HashNodeClass[] Htable;
    String location;
    String[] catchString;
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

}
