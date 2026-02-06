package com.klid.api.hist.smsemail.persistence;

import com.klid.api.hist.smsemail.dto.SmsEmailHistMgmtDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SmsEmailHistMgmtMapper {
    List<SmsEmailHistMgmtDTO> selectSmsHist(@Param("date1") String date1,
                                            @Param("date2") String date2,
                                            @Param("time1") String time1,
                                            @Param("time2") String time2);

    List<SmsEmailHistMgmtDTO> selectEmailHist(@Param("date1") String date1,
                                              @Param("date2") String date2,
                                              @Param("time1") String time1,
                                              @Param("time2") String time2);
}
