package com.antra.phonepad.combination.dao;

import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Repository("WordSetDAO")
public class WordSetDAO {
    private Set<String> wordsSet;
    public Map<String,String> phone;



    public WordSetDAO() throws IOException
    {
        Path path = Paths.get("src/main/resources/words.txt");
        byte[] readBytes = Files.readAllBytes(path);
        String wordListContents = new String(readBytes, "UTF-8").toLowerCase();
        String[] words = wordListContents.split("\n");
        wordsSet = new HashSet<>();
        Collections.addAll(wordsSet, words);

         phone = new HashMap<String, String>() {{
            put("0", "ab");
            put("1", "cd");
            put("2", "ed");
            put("3", "gh");
            put("4", "ij");
            put("5", "kl");
            put("6", "mno");
            put("7", "pqrs");
            put("8", "tuv");
            put("9", "wxyz");
        }};
    }


    public boolean isValidWord(String word)
    {
        return wordsSet.contains(word);
    }

    public List<List<String>> findValidCombination(String[] tokens , List<String> output,Map<String, String> phone)  {
        List<List<String>> findOutput = new ArrayList<>();
        for(String s: tokens){
            backtrack("",s,output,phone);
            List<String> innerList = new ArrayList<>();
            for(String str: output){
                if(isValidWord(str)){
                    innerList.add(str);
                }
            }
            if(innerList.isEmpty()){
                innerList.add(s);
            }
            findOutput.add(new ArrayList<>(innerList));
            output.clear();
        }
        return  findOutput;
    }

    public void backtrack(String combination, String next_digits, List<String> output, Map<String, String> phone) {
        // if there is no more digits to check
        if (next_digits.length() == 0) {
            // the combination is done
            output.add(combination);
        }
        // if there are still digits to check
        else {
            // iterate over all letters which map
            // the next available digit
            String digit = next_digits.substring(0, 1);
            String letters = phone.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                String letter = phone.get(digit).substring(i, i + 1);
                // append the current letter to the combination
                // and proceed to the next digits
                backtrack(combination + letter, next_digits.substring(1),output,phone);
            }
        }
    }
    public void dfs(StringBuilder sb, int level, List<List<String>> findOutput, List<String> result){
        if(level == findOutput.size()){
            result.add(sb.toString().substring(0, sb.length() - 1));
            return;
        }
        List<String> curString = findOutput.get(level);
        for(int i = 0; i <  curString.size(); i++){
            sb.append(curString.get(i));
            sb.append("-");
            dfs(sb, level + 1, findOutput,result);
            sb.setLength(sb.length() - (curString.get(i).length() + 1));
        }

    }

}
