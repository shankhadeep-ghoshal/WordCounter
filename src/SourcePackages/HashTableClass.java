package SourcePackages;

import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.Contract;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
        String temp1,temp2;
        try(FileInputStream FIS = new FileInputStream(location)){
            temp1= IOUtils.toString(FIS,"UTF-8");
            //temp2=temp1.replaceAll("[,?.!;:\\r\\n]"," ");
            System.out.print(temp1);
            System.out.println("------------------------------------------------");
            System.out.println();
        }
        String[] tempCatchString = temp1.split("[,?.!;:\\r\\n\\s]+");
        this.catchString=tempCatchString;
        //this.catchString = tempCatchString;
        this.Htable = new HashNodeClass[size];
        for(int i=0;i<size;i++){
            this.Htable[i] = null;
        }
        for(String x : this.clean(tempCatchString)){
            System.out.printf("%s ",x);
        }
        System.out.println();
        System.out.println("////////////////////////////////////////////");
        this.insertIntoHashTable(this.clean(tempCatchString));
    }
    @Contract(pure = true)
    public static String[] clean(String[] allElements) {
        // 1 : count
        int n = 0;
        for (int i = 0; i < allElements.length; i++)
            if (allElements[i] != "") n++;

        // 2 : allocate new array
        String[] _localAllElements = new String[n];

        // 3 : copy not null elements
        int j = 0;
        for (int i = 0; i < allElements.length; i++)
            if (allElements[i] != "")
                _localAllElements[j++] = allElements[i];

        return _localAllElements;
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

        /*int prime = 31;
        int hash = 0;

        for(int i = 0; i < k.length(); i++)
        {
            hash *= prime;
            hash ^= k.charAt(i);
        }

        if (hash < 0) hash &= ~0x80000000;
            //hash *= -1;

        return hash % size;*/
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

}
