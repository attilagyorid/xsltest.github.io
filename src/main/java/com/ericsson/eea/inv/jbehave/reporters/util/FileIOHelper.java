package com.ericsson.eea.inv.jbehave.reporters.util;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * Created by eattgyo on 2015.11.03..
 */
@Component
public class FileIOHelper {

    public void deleteAndRecreateFolder (String folderPath) throws IOException {
        FileUtils.deleteDirectory(new File(folderPath));
        FileUtils.forceMkdir(new File(folderPath));
    }

}
