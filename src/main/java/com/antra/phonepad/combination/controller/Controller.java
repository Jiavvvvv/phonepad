package com.antra.phonepad.combination.controller;

import com.antra.phonepad.combination.dao.WordSetDAO;
import com.antra.phonepad.combination.service.NumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import static java.lang.Character.toLowerCase;

@RestController
public class Controller {

    @Autowired
    NumberService numberService;

    @RequestMapping(value = "/numbers/{userInput}", method = RequestMethod.GET)
    public List<String> convertNumberToChar(@PathVariable("userInput") String userInput){
        //This method converts phone number to a combination of valid word
        // by converting numbers between each dash to an valid word and assuming users enter valid input

        return numberService.convertToString(userInput);

    }




    @RequestMapping(value = "/strings/{userInput}", method = RequestMethod.GET)
    public StringBuilder convertCharToNumber(@PathVariable("userInput") String userInput){
        //This method converts strings to phone numbers
        Map<Character, Character> map = new HashMap<>();
        map.put('a','2');
        map.put('b','2');
        map.put('c','2');
        map.put('d','3');
        map.put('e','3');
        map.put('f','3');
        map.put('g','4');
        map.put('h','4');
        map.put('i','4');
        map.put('j','5');
        map.put('k','5');
        map.put('l','5');
        map.put('m','6');
        map.put('n','6');
        map.put('o','6');
        map.put('p','7');
        map.put('q','7');
        map.put('r','7');
        map.put('s','7');
        map.put('t','8');
        map.put('u','8');
        map.put('v','8');
        map.put('w','9');
        map.put('x','9');
        map.put('y','9');
        map.put('z','9');

        StringBuilder result = new StringBuilder();
        for(int i = 0; i < userInput.length();i++){
            char chara = userInput.charAt(i);
           if(Character.isUpperCase(chara)){
               chara = toLowerCase(chara);
           }
            Character fromMap = map.get(chara);
            if(fromMap != null){
                result.append(fromMap);
            }else{
                result.append(chara);
            }
        }
        return result;
    }

}
