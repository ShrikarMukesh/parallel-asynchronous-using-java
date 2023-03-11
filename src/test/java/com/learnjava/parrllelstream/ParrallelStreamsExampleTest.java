package com.learnjava.parrllelstream;

import com.learnjava.util.DataSet;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static com.learnjava.util.CommonUtil.startTimer;
import static com.learnjava.util.CommonUtil.timeTaken;
import static org.junit.jupiter.api.Assertions.*;

class ParrallelStreamsExampleTest {

    @Test
    void stringTransForm() {
        ParrallelStreamsExample parrallelStreamsExample = new ParrallelStreamsExample();
        //given
        List<String> inputList = DataSet.namesList();

        //when
        List<String> result = parrallelStreamsExample.stringTransfrom(inputList);

        //then
        assertEquals(4 , result.size());
        result.forEach(
                name-> assertTrue(name.contains("-"))
        );
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    void stringTransForm_1(boolean isParallel) {

        ParrallelStreamsExample parrallelStreamsExample = new ParrallelStreamsExample();
        //given
        List<String> inputList = DataSet.namesList();

        //when
        //startTimer();
        List<String> result = parrallelStreamsExample.stringTransfrom_1(inputList,isParallel);
        //timeTaken();

        //then
        assertEquals(4 , result.size());
        result.forEach(
                name-> assertTrue(name.contains("-"))
        );
    }
}