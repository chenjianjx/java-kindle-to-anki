package com.chenjianjx.k2a.ui;

import com.chenjianjx.k2a.service.Kindle2Anki;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Kindle2AnkiMain {

    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            System.out.println("Params:  kindleHighlightsHtmlFile [targetDir]");
            return;
        }

        List<String> argList = new ArrayList<String>(Arrays.asList(args));

        File htmlFile = new File( argList.get(0));

        File outputDir = htmlFile.getParentFile();
        if (argList.size() > 1) {
            outputDir = new File(argList.get(1));
            outputDir.mkdirs();
        }

        File targetFile = new File(outputDir, FilenameUtils.getBaseName(htmlFile.getName()) + ".csv");


        Kindle2Anki k2a = new Kindle2Anki();

        String kindleHighlights = FileUtils.readFileToString(htmlFile, "UTF8");
        String ankiCsv =  k2a.convert(kindleHighlights);

        FileUtils.writeStringToFile(targetFile, ankiCsv, "UTF8");

        System.out.println("Done.  Please check " + targetFile.getAbsolutePath());
    }
}
