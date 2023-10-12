package com.envify.back.service.configFileParser;

import com.envify.back.dto.finaldto.PackageObjectDto;
import com.envify.back.dto.finaldto.PackagePropertiesObjectDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigFileParser {
    private static String folderPath = "./src/main/resources/fileConfigTemplate/%s/";
    private String packageName;
    private PackageObjectDto packageObjectDto;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public PackageObjectDto getPackageObjectDto() {
        return packageObjectDto;
    }

    public void setPackageObjectDto(PackageObjectDto packageObjectDto) {
        this.packageObjectDto = packageObjectDto;
    }

    public ConfigFileParser(String packageName, PackageObjectDto packageObjectDto) {
        this.packageName = packageName;
        this.packageObjectDto = packageObjectDto;
    }

    private String configFolderPath() {
        return String.format(folderPath, packageName);
    }


    private String findPatternAndReplace(String line) {
        List<PackagePropertiesObjectDto> properties = packageObjectDto.getPackageProperties();
        StringBuilder newLine = new StringBuilder();
        boolean found = false;

        for (PackagePropertiesObjectDto property : properties) {
            String patternToReplace = "$" + property.getField();
            String replacementPattern = property.getValue();

            Pattern pattern = Pattern.compile("\\"+patternToReplace);
            Matcher matcher = pattern.matcher(line);
            boolean matchFound = matcher.find();

            if (matchFound) {
                found = true;
                newLine.append(line.replace(patternToReplace, replacementPattern));
            }
        }

        return found ? newLine.toString() : line;
    }

    public String parseFile() {
        try {
            File file = getFirstFileFromFolder();
            StringBuilder newConfFile = new StringBuilder();
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                newConfFile.append(findPatternAndReplace(scanner.nextLine())).append("\n");
            }
            scanner.close();

            return newConfFile.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFirstFileFromFolder() throws IOException {
        File folder = new File(configFolderPath());

        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();

            if (files != null && files.length > 0) {
                return files[0];
            }
        }

        return folder;
    }
}
