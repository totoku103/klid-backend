package com.klid.api.logs.common.service;

import com.klid.api.logs.common.dto.InstitutionSummaryGridResDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class InstitutionAggregateService {

    private Map<String, Integer> getDictionary(final int depth, final List<InstitutionSummaryGridResDTO> data) {
        return data.stream()
                .filter(row -> row.getDepthLevel() == depth)
                .collect(Collectors
                        .groupingBy(InstitutionSummaryGridResDTO::getParentInstitutionCode,
                                Collectors.summingInt(InstitutionSummaryGridResDTO::getTotalCount)));
    }

    private void setCountData(final int depth, final Map<String, Integer> dic, final List<InstitutionSummaryGridResDTO> data) {
        data.stream()
                .filter(row -> row.getDepthLevel() == depth)
                .forEach(row -> {
                    final String code = row.getInstitutionCode();
                    final Integer i = dic.getOrDefault(code, 0);
                    row.setTotalCount(i);
                });
    }

    public List<InstitutionSummaryGridResDTO> aggregate(final List<InstitutionSummaryGridResDTO> data) {
        final Map<String, Integer> depth3Dic = getDictionary(3, data);
        setCountData(2, depth3Dic, data);

        final Map<String, Integer> depth2Dic = getDictionary(2, data);
        setCountData(1, depth2Dic, data);
        return data;
    }
}
