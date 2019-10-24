package com.example.demo;

import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
public class SpringMath {

    @GetMapping("/math/pi")
    public String mathProblem() {
        return "3.141592653589793";
    }

    @GetMapping("/math/calculate")
    public String mathCalculate(
            @RequestParam(required = false, defaultValue = "add") String operation,
            @RequestParam("x") Integer x,
            @RequestParam("y") Integer y) {
        String returnString = "";
        int total = 0;

        if (operation.equals("add")) {
            total = x + y;
            returnString = String.format("%s + %s = %s", x, y, total);
        } else if (operation.equals("subtract")) {
            total = x - y;
            returnString = String.format("%s - %s = %s", x, y, total);
        }
       else if (operation.equals("multiply")) {
            total = x * y;
            returnString = String.format("%s * %s = %s", x, y, total);
        }
       else if(operation.equals("divide")) {
            total = x / y;
            returnString = String.format("%s / %s = %s", x, y, total);
        }
        return returnString;
    }


   @PostMapping("/math/sum")
    public String mathSum(@RequestParam MultiValueMap<String, String> n){
       String additionString = "";
       int sumTotal = 0;

       //returns KeysList from multiValueMap
       Set<String> keysList = n.keySet();

        //for each key in keysList get the whole item [key: list of values]
        for (String item : keysList) {
            List <String> myList = n.get(item);

            // the for each item build strings add total
            for(String number: myList) {
                //build string
                additionString += number + " + ";
                //taking string and parsing to Int and assigning to sumTotal, sumTotal = sumTotal +number
                sumTotal += Integer.parseInt(number);
            }
            //build string that is returned "5 +7 + 9 = 21"
            additionString.substring(0, additionString.length()-3);
            additionString += " = " + sumTotal;
        }

        return additionString;
   }
}
