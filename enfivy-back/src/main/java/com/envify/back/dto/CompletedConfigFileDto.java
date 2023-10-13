package com.envify.back.dto;

import java.util.Objects;

public class CompletedConfigFileDto {
    private String fileName;
    private String file;

    public CompletedConfigFileDto(String fileName, String file) {
        this.fileName = fileName;
        this.file = file;
    }

    public CompletedConfigFileDto() {
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompletedConfigFileDto that = (CompletedConfigFileDto) o;
        return Objects.equals(fileName, that.fileName) && Objects.equals(file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, file);
    }
}
