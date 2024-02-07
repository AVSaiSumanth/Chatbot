package com.jntua.dao.model;

import java.io.File;
import java.io.FileInputStream;

import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.PlainTextDictionary;

import org.apache.lucene.search.spell.SpellChecker;

import org.apache.lucene.store.Directory;

import org.apache.lucene.store.FSDirectory;

 

public class SimpleSuggestionService {

     

    public static void main(String[] args) throws Exception {

         

        File dir = new File("d:/spellchecker/");

         

        Directory directory = FSDirectory.getDirectory(dir);
         

        SpellChecker spellChecker = new SpellChecker(directory);

         
        
        spellChecker.indexDictionary(new PlainTextDictionary(new FileInputStream(new File("d:\\q1.txt"))));


        String wordForSuggestions = "ho ma dept ae tre in jntua";

         

        int suggestionsNumber = 5;

 

        String[] suggestions = spellChecker.

            suggestSimilar(wordForSuggestions, suggestionsNumber);

 

        if (suggestions!=null && suggestions.length>0) {

            for (String word : suggestions) {

                System.out.println("Did you mean:" + word);

            }

        }

        else {

            System.out.println("No suggestions found for word:"+wordForSuggestions);

        }

             

    }

 

}