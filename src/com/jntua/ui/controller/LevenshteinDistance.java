package com.jntua.ui.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

public class LevenshteinDistance {

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public static int computeDistance(CharSequence str1,
            CharSequence str2) {

        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++){
            distance[i][0] = i;
        }
        for (int j = 0; j <= str2.length(); j++){
            distance[0][j] = j;
        }
        for (int i = 1; i <= str1.length(); i++){
            for (int j = 1; j <= str2.length(); j++){
                distance[i][j] = minimum(
                    distance[i - 1][j] + 1,
                    distance[i][j - 1] + 1,
                    distance[i - 1][j - 1]
                        + ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
            }
        }
        int result = distance[str1.length()][str2.length()];
        //log.debug("distance:"+result);
        return result;
    }

    public static int getChance(String fileName,String sent1)
    {
    	int ch=Integer.MAX_VALUE;
    	try {
    		URL fileUrl = LevenshteinDistance.class.getResource(fileName);
			File file = new File(fileUrl.getFile());
			System.out.println(file.getAbsolutePath());
			FileReader fileReader = new FileReader(file);
		
			BufferedReader bufferedReader = new BufferedReader(fileReader);
		
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				int ch2=computeDistance(sent1.toUpperCase().trim(), line.toUpperCase().trim());
				ch=ch2<ch?ch2:ch;
				
			}
			fileReader.close();
			System.out.println("Contents of file:");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	return ch;
    }

    public static void main(String[] args) {
        String sent1="Who is head of the dept of CSE.";
        String sent2="Who is head of the dept of CSE.";       
        String sent3="Alice and Bobe are not my classmates.";
        String sent4="Some totally different sentence.";

    System.out.println("Distance between \n'"+sent1+"' \nand '"+sent2+"': \n"+computeDistance(sent1, sent2));
    System.out.println("Distance between \n'"+sent1+"' \nand '"+sent3+"': \n"+computeDistance(sent1, sent3));
    System.out.println("Distance between \n'"+sent1+"' \nand '"+sent4+"': \n"+computeDistance(sent1, sent4));

        }
}
