package com.envify.back.service.configFileParser;

import com.envify.back.dto.ReceivedPackageDto;
import com.envify.back.dto.ReceivedPackagePropertiesDto;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConfigFileParser {
    private static final String folderPath = "./src/main/resources/fileConfigTemplate/%s/";
    private String packageName;
    private ReceivedPackageDto receivedPackageDto;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ReceivedPackageDto getPackageObjectDto() {
        return receivedPackageDto;
    }

    public void setPackageObjectDto(ReceivedPackageDto receivedPackageDto) {
        this.receivedPackageDto = receivedPackageDto;
    }

    public ConfigFileParser(String packageName, ReceivedPackageDto receivedPackageDto) {
        this.packageName = packageName;
        this.receivedPackageDto = receivedPackageDto;
    }

    private String configFolderPath() {
        return String.format(folderPath, packageName.toLowerCase());
    }


    private String findPatternAndReplace(String line) {
        List<ReceivedPackagePropertiesDto> properties = receivedPackageDto.getPackageProperties();
        StringBuilder newLine = new StringBuilder();
        boolean found = false;

        for (ReceivedPackagePropertiesDto property : properties) {
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

    public File getFirstFileFromFolder() throws IOException {
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
