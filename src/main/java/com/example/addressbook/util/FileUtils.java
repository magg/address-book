package com.example.addressbook.util;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;


/**
 * Created by magg on 11/17/17.
 */
public class FileUtils {

    private static final Logger log = LogManager.getLogger(FileUtils.class.getName());

    /**
     * Read file content to string.
     *
     * @param filePath
     *            the file path
     * @return the string
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static String readFileContentToString(String filePath)
            throws IOException {

        URL url = Resources.getResource(filePath);
        String result = Resources.toString(url, Charsets.UTF_8);

        return result;
    }
}
