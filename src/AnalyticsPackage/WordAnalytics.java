package AnalyticsPackage;

import SourcePackages.HashNodeClass;
import SourcePackages.LLNode;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Echo01 on 5/24/2017.
 */
public class WordAnalytics implements java.io.Serializable {
    @Nullable
    @Contract(pure = true)
    public static ArrayList<LLNode> convertToArrayList(HashNodeClass[] input){
        ArrayList<LLNode> temp = new ArrayList<>();
        for(int i=0;i<input.length;i++){
            if(input[i]!=null){
                for(LLNode a : input[i].getLLNEntry()){
                    temp.add(a);
                }
            }
        }
        return temp;
    }
    @Nullable
    @Contract(pure = true)
    public static LLNode[] top10Numbers(ArrayList<LLNode> input){
        List<LLNode> temp = new ArrayList<>(input);
        Collections.sort(temp);
        Collections.reverse(temp);
        LLNode[] nodeArray = new LLNode[10];
        for(int i=0;i<10;i++){
            nodeArray[i] = temp.get(i);
        }
        return nodeArray;
    }
}
