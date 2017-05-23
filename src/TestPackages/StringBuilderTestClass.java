package TestPackages;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Echo01 on 5/19/2017.
 */
public class StringBuilderTestClass {
    public static void main(String[] args)throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\Programing practice\\JavaProj\\StringHashTableAssignment\\res\\Demo.txt"));
        String temp;
        StringBuilder sb = new StringBuilder();
        while((temp = bufferedReader.readLine())!=null){
            String temp2 = temp.replaceAll("[,.!? \n \r]"," ");
            sb.append(temp2);
        }

        String[] c = sb.toString().split(" ");
        String ttt = " ";
        String kkk;
        try(FileInputStream FIS = new FileInputStream("D:\\Programing practice\\JavaProj\\StringHashTableAssignment\\res\\Demo.txt")){
            ttt = IOUtils.toString(FIS,"UTF-8");
        }
        kkk = ttt.replaceAll("[.!,?\"\']"," ");
        //System.out.println("IOUtils result");
        //System.out.println(kkk);

        for(int i = 0 ; i < c.length ; i++){
            System.out.println(c[i]);
        }
    }
}
