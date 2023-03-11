package com.learnjava.parrllelstream;

import com.learnjava.util.DataSet;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.learnjava.util.CommonUtil.*;

public class ParrallelStreamsExample {

    public static void main(String[] args) {
        List<String> nameList = DataSet.namesList();
        ParrallelStreamsExample parrallelStreamsExample = new ParrallelStreamsExample();
        startTimer();
        List<String> result = parrallelStreamsExample.stringTransfrom(nameList);
        System.out.println(result);
        timeTaken();
        DataSet.namesList()
                .stream()
                .parallel()
                .map(name-> name.toLowerCase())
                .forEach(
                        str -> System.out.println(str)
                );
    }

    public List<String> stringTransfrom(List<String> namesList){
       return namesList
                //.stream()
                .parallelStream()
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    public List<String> stringTransfrom_1(List<String> namesList, Boolean isParralel){
        Stream<String> nameStream = namesList.stream();
        if(isParralel){
            nameStream.parallel();
        }
        return nameStream
                .map(this::addNameLengthTransform)
                .collect(Collectors.toList());
    }

    private String addNameLengthTransform(String name) {
        delay(500);
        return name.length() + " - "+name ;
    }
}
