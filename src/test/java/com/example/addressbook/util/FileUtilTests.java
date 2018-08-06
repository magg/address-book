package com.example.addressbook.util;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.net.URL;

import static junit.framework.TestCase.assertTrue;


/**
 * Created by magg on 8/5/18.
 */

@RunWith(JUnit4.class)
public class FileUtilTests {


    @Test
    public void whenReadUsingResources_thenRead() throws IOException {
        String expectedValue = "Wes Jackson";
        String result = FileUtils.readFileContentToString("AddressBook");

        assertTrue(result.contains(expectedValue));
    }
}
