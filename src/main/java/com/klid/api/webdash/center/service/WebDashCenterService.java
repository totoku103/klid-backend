package com.klid.api.webdash.center.service;

import com.klid.api.webdash.center.dto.WebDashCenterDTO;
import com.klid.api.webdash.center.persistence.WebDashCenterMapper;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class WebDashCenterService {

    private final WebDashCenterMapper mapper;

    public List<WebDashCenterDTO> getAttNationTop5(final Map<String, Object> paramMap) {
        return mapper.selectAttNationTop5(paramMap);
    }

    public List<Map<String, Integer>> getTypeChart(final Map<String, Object> paramMap) {
        final List<WebDashCenterDTO> dbList = mapper.selectTypeChart(paramMap);
        List<Map<String, Integer>> listMap = new ArrayList<>();
        listMap = regHhListReturn(dbList, listMap);
        return listMap;
    }

    public Map<String, List<Map<String, Integer>>> getEvtChart(final Map<String, Object> paramMap) {
        final List<WebDashCenterDTO> dbList = mapper.selectEvtChart(paramMap);
        Map<String, List<Map<String, Integer>>> listMap = new HashMap<>();
        listMap = dayTypeListReturn(dbList, listMap);
        return listMap;
    }

    public Map<String, List<Map<String, Integer>>> getEvtAllChart(final Map<String, Object> paramMap) {
        final List<WebDashCenterDTO> dbList = mapper.selectEvtAllChart(paramMap);
        Map<String, List<Map<String, Integer>>> listMap = new HashMap<>();
        listMap = dayTypeListReturn(dbList, listMap);
        return listMap;
    }

    private List<Map<String, Integer>> regHhListReturn(final List<WebDashCenterDTO> dbList, List<Map<String, Integer>> listMap) {
        if (!CollectionUtils.isEmpty(dbList)) {
            final Map<String, Integer> amap = new HashMap<>();
            final Map<String, Integer> bmap = new HashMap<>();
            final Map<String, Integer> cmap = new HashMap<>();
            final Map<String, Integer> dmap = new HashMap<>();
            final Map<String, Integer> emap = new HashMap<>();
            final Map<String, Integer> fmap = new HashMap<>();
            final Map<String, Integer> gmap = new HashMap<>();
            final Map<String, Integer> hmap = new HashMap<>();
            final Map<String, Integer> imap = new HashMap<>();
            final Map<String, Integer> jmap = new HashMap<>();
            final Map<String, Integer> kmap = new HashMap<>();
            final Map<String, Integer> lmap = new HashMap<>();
            final Map<String, Integer> mmap = new HashMap<>();
            final Map<String, Integer> nmap = new HashMap<>();
            final Map<String, Integer> omap = new HashMap<>();
            final Map<String, Integer> pmap = new HashMap<>();
            final Map<String, Integer> qmap = new HashMap<>();
            final Map<String, Integer> rmap = new HashMap<>();
            final Map<String, Integer> smap = new HashMap<>();
            final Map<String, Integer> tmap = new HashMap<>();
            final Map<String, Integer> umap = new HashMap<>();
            final Map<String, Integer> vmap = new HashMap<>();
            final Map<String, Integer> wmap = new HashMap<>();
            final Map<String, Integer> xmap = new HashMap<>();

            for (final WebDashCenterDTO re : dbList) {
                switch (re.getRegHh()) {
                    case "0": regHHJsonReturn(amap, re); break;
                    case "1": regHHJsonReturn(bmap, re); break;
                    case "2": regHHJsonReturn(cmap, re); break;
                    case "3": regHHJsonReturn(dmap, re); break;
                    case "4": regHHJsonReturn(emap, re); break;
                    case "5": regHHJsonReturn(fmap, re); break;
                    case "6": regHHJsonReturn(gmap, re); break;
                    case "7": regHHJsonReturn(hmap, re); break;
                    case "8": regHHJsonReturn(imap, re); break;
                    case "9": regHHJsonReturn(jmap, re); break;
                    case "10": regHHJsonReturn(kmap, re); break;
                    case "11": regHHJsonReturn(lmap, re); break;
                    case "12": regHHJsonReturn(nmap, re); break;
                    case "13": regHHJsonReturn(mmap, re); break;
                    case "14": regHHJsonReturn(omap, re); break;
                    case "15": regHHJsonReturn(pmap, re); break;
                    case "16": regHHJsonReturn(qmap, re); break;
                    case "17": regHHJsonReturn(rmap, re); break;
                    case "18": regHHJsonReturn(smap, re); break;
                    case "19": regHHJsonReturn(tmap, re); break;
                    case "20": regHHJsonReturn(umap, re); break;
                    case "21": regHHJsonReturn(vmap, re); break;
                    case "22": regHHJsonReturn(wmap, re); break;
                    case "23": regHHJsonReturn(xmap, re); break;
                }
            }
            listMap.add(amap); listMap.add(bmap); listMap.add(cmap); listMap.add(dmap);
            listMap.add(emap); listMap.add(fmap); listMap.add(gmap); listMap.add(hmap);
            listMap.add(imap); listMap.add(jmap); listMap.add(kmap); listMap.add(lmap);
            listMap.add(mmap); listMap.add(nmap); listMap.add(omap); listMap.add(pmap);
            listMap.add(qmap); listMap.add(rmap); listMap.add(smap); listMap.add(tmap);
            listMap.add(umap); listMap.add(vmap); listMap.add(wmap); listMap.add(xmap);
        }
        return listMap;
    }

    private void regHHJsonReturn(final Map<String, Integer> map, final WebDashCenterDTO re) {
        final JSONObject jsonObj = new JSONObject(re.getSumJson());
        final Iterator<String> keys = jsonObj.keys();
        while (keys.hasNext()) {
            final String key = keys.next();
            final int value = jsonObj.getInt(key);
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
        }
        map.put("regHh", Integer.parseInt(re.getRegHh()));
    }

    private Map<String, List<Map<String, Integer>>> dayTypeListReturn(final List<WebDashCenterDTO> dbList, Map<String, List<Map<String, Integer>>> listMap) {
        final List<Map<String, Integer>> todayListMap = new ArrayList<>();
        final List<Map<String, Integer>> yesterdayListMap = new ArrayList<>();
        final List<Map<String, Integer>> lastWeekListMap = new ArrayList<>();

        final Map<String, Integer> amap = new HashMap<>(), aamap = new HashMap<>(), aaamap = new HashMap<>();
        final Map<String, Integer> bmap = new HashMap<>(), bbmap = new HashMap<>(), bbbmap = new HashMap<>();
        final Map<String, Integer> cmap = new HashMap<>(), ccmap = new HashMap<>(), cccmap = new HashMap<>();
        final Map<String, Integer> dmap = new HashMap<>(), ddmap = new HashMap<>(), dddmap = new HashMap<>();
        final Map<String, Integer> emap = new HashMap<>(), eemap = new HashMap<>(), eeemap = new HashMap<>();
        final Map<String, Integer> fmap = new HashMap<>(), ffmap = new HashMap<>(), fffmap = new HashMap<>();
        final Map<String, Integer> gmap = new HashMap<>(), ggmap = new HashMap<>(), gggmap = new HashMap<>();
        final Map<String, Integer> hmap = new HashMap<>(), hhmap = new HashMap<>(), hhhmap = new HashMap<>();
        final Map<String, Integer> imap = new HashMap<>(), iimap = new HashMap<>(), iiimap = new HashMap<>();
        final Map<String, Integer> jmap = new HashMap<>(), jjmap = new HashMap<>(), jjjmap = new HashMap<>();
        final Map<String, Integer> kmap = new HashMap<>(), kkmap = new HashMap<>(), kkkmap = new HashMap<>();
        final Map<String, Integer> lmap = new HashMap<>(), llmap = new HashMap<>(), lllmap = new HashMap<>();
        final Map<String, Integer> mmap = new HashMap<>(), mmmap = new HashMap<>(), mmmmap = new HashMap<>();
        final Map<String, Integer> nmap = new HashMap<>(), nnmap = new HashMap<>(), nnnmap = new HashMap<>();
        final Map<String, Integer> omap = new HashMap<>(), oomap = new HashMap<>(), ooomap = new HashMap<>();
        final Map<String, Integer> pmap = new HashMap<>(), ppmap = new HashMap<>(), pppmap = new HashMap<>();
        final Map<String, Integer> qmap = new HashMap<>(), qqmap = new HashMap<>(), qqqmap = new HashMap<>();
        final Map<String, Integer> rmap = new HashMap<>(), rrmap = new HashMap<>(), rrrmap = new HashMap<>();
        final Map<String, Integer> smap = new HashMap<>(), ssmap = new HashMap<>(), sssmap = new HashMap<>();
        final Map<String, Integer> tmap = new HashMap<>(), ttmap = new HashMap<>(), tttmap = new HashMap<>();
        final Map<String, Integer> umap = new HashMap<>(), uumap = new HashMap<>(), uuumap = new HashMap<>();
        final Map<String, Integer> vmap = new HashMap<>(), vvmap = new HashMap<>(), vvvmap = new HashMap<>();
        final Map<String, Integer> wmap = new HashMap<>(), wwmap = new HashMap<>(), wwwmap = new HashMap<>();
        final Map<String, Integer> xmap = new HashMap<>(), xxmap = new HashMap<>(), xxxmap = new HashMap<>();

        if (!CollectionUtils.isEmpty(dbList)) {
            for (final WebDashCenterDTO re : dbList) {
                switch (re.getDayType()) {
                    case "today":
                        processHourlyData(re, amap, bmap, cmap, dmap, emap, fmap, gmap, hmap, imap, jmap, kmap, lmap, mmap, nmap, omap, pmap, qmap, rmap, smap, tmap, umap, vmap, wmap, xmap);
                        break;
                    case "yesterday":
                        processHourlyData(re, aamap, bbmap, ccmap, ddmap, eemap, ffmap, ggmap, hhmap, iimap, jjmap, kkmap, llmap, mmmap, nnmap, oomap, ppmap, qqmap, rrmap, ssmap, ttmap, uumap, vvmap, wwmap, xxmap);
                        break;
                    case "lastWeek":
                        processHourlyData(re, aaamap, bbbmap, cccmap, dddmap, eeemap, fffmap, gggmap, hhhmap, iiimap, jjjmap, kkkmap, lllmap, mmmmap, nnnmap, ooomap, pppmap, qqqmap, rrrmap, sssmap, tttmap, uuumap, vvvmap, wwwmap, xxxmap);
                        break;
                }
            }
        }

        todayListMap.add(amap); yesterdayListMap.add(aamap); lastWeekListMap.add(aaamap);
        todayListMap.add(bmap); yesterdayListMap.add(bbmap); lastWeekListMap.add(bbbmap);
        todayListMap.add(cmap); yesterdayListMap.add(ccmap); lastWeekListMap.add(cccmap);
        todayListMap.add(dmap); yesterdayListMap.add(ddmap); lastWeekListMap.add(dddmap);
        todayListMap.add(emap); yesterdayListMap.add(eemap); lastWeekListMap.add(eeemap);
        todayListMap.add(fmap); yesterdayListMap.add(ffmap); lastWeekListMap.add(fffmap);
        todayListMap.add(gmap); yesterdayListMap.add(ggmap); lastWeekListMap.add(gggmap);
        todayListMap.add(hmap); yesterdayListMap.add(hhmap); lastWeekListMap.add(hhhmap);
        todayListMap.add(imap); yesterdayListMap.add(iimap); lastWeekListMap.add(iiimap);
        todayListMap.add(jmap); yesterdayListMap.add(jjmap); lastWeekListMap.add(jjjmap);
        todayListMap.add(kmap); yesterdayListMap.add(kkmap); lastWeekListMap.add(kkkmap);
        todayListMap.add(lmap); yesterdayListMap.add(llmap); lastWeekListMap.add(lllmap);
        todayListMap.add(mmap); yesterdayListMap.add(mmmap); lastWeekListMap.add(mmmmap);
        todayListMap.add(nmap); yesterdayListMap.add(nnmap); lastWeekListMap.add(nnnmap);
        todayListMap.add(omap); yesterdayListMap.add(oomap); lastWeekListMap.add(ooomap);
        todayListMap.add(pmap); yesterdayListMap.add(ppmap); lastWeekListMap.add(pppmap);
        todayListMap.add(qmap); yesterdayListMap.add(qqmap); lastWeekListMap.add(qqqmap);
        todayListMap.add(rmap); yesterdayListMap.add(rrmap); lastWeekListMap.add(rrrmap);
        todayListMap.add(smap); yesterdayListMap.add(ssmap); lastWeekListMap.add(sssmap);
        todayListMap.add(tmap); yesterdayListMap.add(ttmap); lastWeekListMap.add(tttmap);
        todayListMap.add(umap); yesterdayListMap.add(uumap); lastWeekListMap.add(uuumap);
        todayListMap.add(vmap); yesterdayListMap.add(vvmap); lastWeekListMap.add(vvvmap);
        todayListMap.add(wmap); yesterdayListMap.add(wwmap); lastWeekListMap.add(wwwmap);
        todayListMap.add(xmap); yesterdayListMap.add(xxmap); lastWeekListMap.add(xxxmap);

        listMap.put("today", todayListMap);
        listMap.put("yesterday", yesterdayListMap);
        listMap.put("lastWeek", lastWeekListMap);

        return listMap;
    }

    private void processHourlyData(final WebDashCenterDTO re, final Map<String, Integer>... hourMaps) {
        switch (re.getRegHh()) {
            case "0": regHHJsonReturn(hourMaps[0], re); break;
            case "1": regHHJsonReturn(hourMaps[1], re); break;
            case "2": regHHJsonReturn(hourMaps[2], re); break;
            case "3": regHHJsonReturn(hourMaps[3], re); break;
            case "4": regHHJsonReturn(hourMaps[4], re); break;
            case "5": regHHJsonReturn(hourMaps[5], re); break;
            case "6": regHHJsonReturn(hourMaps[6], re); break;
            case "7": regHHJsonReturn(hourMaps[7], re); break;
            case "8": regHHJsonReturn(hourMaps[8], re); break;
            case "9": regHHJsonReturn(hourMaps[9], re); break;
            case "10": regHHJsonReturn(hourMaps[10], re); break;
            case "11": regHHJsonReturn(hourMaps[11], re); break;
            case "12": regHHJsonReturn(hourMaps[13], re); break;
            case "13": regHHJsonReturn(hourMaps[12], re); break;
            case "14": regHHJsonReturn(hourMaps[14], re); break;
            case "15": regHHJsonReturn(hourMaps[15], re); break;
            case "16": regHHJsonReturn(hourMaps[16], re); break;
            case "17": regHHJsonReturn(hourMaps[17], re); break;
            case "18": regHHJsonReturn(hourMaps[18], re); break;
            case "19": regHHJsonReturn(hourMaps[19], re); break;
            case "20": regHHJsonReturn(hourMaps[20], re); break;
            case "21": regHHJsonReturn(hourMaps[21], re); break;
            case "22": regHHJsonReturn(hourMaps[22], re); break;
            case "23": regHHJsonReturn(hourMaps[23], re); break;
        }
    }
}
