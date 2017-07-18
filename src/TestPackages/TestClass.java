package TestPackages;
import AnalyticsPackage.WordAnalytics;
import SourcePackages.HashTableClass;
import java.io.*;

public class TestClass {
    public static void main(String[] args)throws IOException {
       HashTableClass a =  new HashTableClass("D:\\Programing " +
               "practice\\JavaProj\\StringHashTableAssignment\\res\\Demo.txt");
       a.printTop10(WordAnalytics.top10Numbers(WordAnalytics.convertToArrayList(a.getHtable())));
    }
}
