package com.klid.api.logs.common.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CsvDownloadReqDTO {
    private String filename;
    private List<String> headers;
    private List<List<String>> rows;
    private String searchOptions;
    private String reason;

    @Override
    public String toString() {
        return "CsvDownloadReqDTO{" +
                "filename='" + filename + '\'' +
                ", headers=" + (headers != null ? headers.size() : 0) +
                ", rows=" + (rows != null ? rows.size() : 0) +
                ", searchOptions='" + searchOptions + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
