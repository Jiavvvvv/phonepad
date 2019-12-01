package com.antra.phonepad.combination.service;

import com.antra.phonepad.combination.dao.WordSetDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("NumberService")
public class NumberService {
    @Autowired
    private WordSetDAO wordDao;

    public List<String> convertToString(String userInput){
        List<String> output = new ArrayList<String>();
        String[] tokens = userInput.split("-");
        List<List<String>> findOutput = wordDao.findValidCombination(tokens,output, wordDao.phone);
        List<String> result = new ArrayList<>();
        wordDao.dfs(new StringBuilder(""),0, findOutput, result);
        return result;
    }


}
