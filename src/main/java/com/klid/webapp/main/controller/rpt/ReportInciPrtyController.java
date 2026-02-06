package com.klid.webapp.main.controller.rpt;

import com.klid.common.AppGlobal;
import com.klid.common.HwpmlMaker;
import com.klid.webapp.common.Criterion;
import com.klid.webapp.common.ReturnData;
import com.klid.webapp.main.rpt.reportInciPrty.service.ReportInciPrtyService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RequestMapping("/api/main/rpt/reportInciPrty")
@RestController
@RequiredArgsConstructor
public class ReportInciPrtyController {

	private final ReportInciPrtyService service;

	@RequestMapping(value = "getPrtyList")
	public ReturnData getPrtyList(@RequestParam Map<String, Object> reqMap) {
			return service.getPrtyList(new Criterion(reqMap));
	}

	@RequestMapping(value = "saveHighChartImg",method = RequestMethod.POST)
	public ReturnData saveHighChartImg(@RequestBody Map<String, Object> reqMap, HttpServletRequest request, HttpServletResponse response) {
		try {
			String fname = (String)reqMap.get("fname");
			String imgData = (String)reqMap.get("imgData");
			imgData = imgData.replace("data:image/png;base64,", "");
			byte[] byteData = Base64.decodeBase64(imgData.getBytes());
			String imgpath=AppGlobal.homePath+"/export/"+fname;
			FileUtils.writeByteArrayToFile(new File(imgpath), byteData);
		}catch (IOException e){
			e.printStackTrace();
			return new ReturnData();
		}catch (Exception e){
			e.printStackTrace();
			return new ReturnData();
		}

		return new ReturnData();
	}

	@RequestMapping(value = "exportReportInciPrty",method = RequestMethod.POST)
	public ReturnData exportReportInciPrty(@RequestBody Map<String, Object> reqMap, HttpServletResponse response) {
		String filename = AppGlobal.reportTemplate + "new_report_hml.hml";

		HwpmlMaker hmlMaker = new HwpmlMaker(filename, "##", "##");

		String fname = (String)reqMap.get("fname");
		String imgpath= AppGlobal.homePath+"/export/"+fname;

		hmlMaker.setImage("chart",imgpath);

//		hmlMaker.setParam("report_title", (String)reqMap.get("sTitle"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(new Date());
		String sumDay = (String)reqMap.get("sumDay");
		String sumEndDt = (String)reqMap.get("sumEndDt");
		hmlMaker.setParam("createDate",today);
		hmlMaker.setParam("startDt",sumDay.substring(2,4)+". "+sumDay.substring(4,6)+". "+sumDay.substring(6,8)+".");
		hmlMaker.setParam("endDt",sumEndDt.substring(2,4)+". "+sumEndDt.substring(4,6)+". "+sumEndDt.substring(6,8)+".");

		ArrayList<LinkedHashMap<String, Integer>> typeGrid = (ArrayList) reqMap.get("prtyGrid");
		hmlMaker.insertTableRow("tb1", "row1",typeGrid.size());

		hmlMaker.setParam("report_title", "우선순위");
		hmlMaker.setParam("titleName","우선순위");
		hmlMaker.setParam("title","우선순위");
		hmlMaker.setParam("title2","우선순위");
		hmlMaker.setParam("titleCnt","건수");

		for(int i=0; i< typeGrid.size(); i++){
			hmlMaker.setParam("codeName"+i,String.valueOf(typeGrid.get(i).get("name")));
			hmlMaker.setParam("cnt"+i,String.valueOf(stringToComma(typeGrid.get(i).get("y"))));
		}

		String createTime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmssSSS");

		hmlMaker.saveFile(AppGlobal.homePath+"/export/",createTime+".hwp");
		Map<String, String> resultMap = new HashMap<>();
		resultMap.put("filePath", "/export/" + createTime + ".hwp");
		resultMap.put("fileName", createTime);
		resultMap.put("fileExt", ".hwp");

		return new ReturnData(resultMap);
//		return service.exportReportInciPrty(new Criterion(reqMap));
	}

	public String stringToComma(int req){
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(req);
	}
}
