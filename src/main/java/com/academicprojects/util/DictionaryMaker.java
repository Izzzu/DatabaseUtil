package com.academicprojects.util;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cookiemonster on 2014-09-16.
 */
public class DictionaryMaker {

    String bigDictionary = "C:\\Users\\Cookiemonster\\workspace\\DbUtilities\\src\\main\\resources\\polimorfologik.txt";
    String smallDictionary = "C:\\Users\\Cookiemonster\\workspace\\DbUtilities\\src\\main\\resources\\slowa-win.txt";

    public void main( ) throws IOException {
        BufferedWriter bw = null;
        BufferedReader big = null;
        BufferedReader small = null;
        PreprocessString ps = new PreprocessString();

        try {
            String line = "";

            big = new BufferedReader(new FileReader(bigDictionary));
            small = new BufferedReader(new FileReader(smallDictionary));
            List<String> listOfSJPWords =  new ArrayList<String>();
            while((line = small.readLine())!=null) {
                listOfSJPWords.add(line);
            }
            System.out.println(listOfSJPWords.size());
            bw = new BufferedWriter(new FileWriter("dictionary.txt"));

            while((line = big.readLine())!=null) {
                String[] cuttedLine = line.split("\t");
                if (listOfSJPWords.contains(ps.replacePolishCharsAndLowerCase(cuttedLine[0]))) {
                    bw.write(line);
                    bw.newLine();
                    for (int i = 0; i <= listOfSJPWords.indexOf(cuttedLine[0]); i++) {
                        listOfSJPWords.remove(i);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            bw.close();
            big.close();
            small.close();
        }


    }


    private void createDictionaryFromFile(File file1) {

    }
}
