package com.tencent.wxcloudrun.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tencent.wxcloudrun.config.ApiResponse;
import com.tencent.wxcloudrun.model.dto.StockData;
import com.tencent.wxcloudrun.utils.HttpUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liusheng
 * @date 2024/4/222:48
 * @desc TODO
 */
@RestController
@RequestMapping("/stocks")
public class StocksController {

    @Value("${api.xawj.url}")
    private String host;
    @Value("${api.xawj.appcode}")
    private String appcode;

    private final ObjectMapper objectMapper;

    public StocksController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * 获取股票信息排行
     * @param sort 排序字段
     * @param asc 排序方式
     * @param page 页码
     * @param limit 每页条数
     * @param market 市场
     * @return ApiResponse
     */
    @GetMapping("/hs/rank")
    @ResponseBody
    public ApiResponse getStocksRank(@RequestParam("sort") String sort,
                                     @RequestParam("asc") String asc,
                                     @RequestParam("page") Integer page,
                                     @RequestParam("limit") Integer limit,
                                     @RequestParam("market") String market) {
        String path = "/hs/rank";
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("sort", sort);
        querys.put("asc", asc);
        querys.put("page", page.toString());
        querys.put("limit", limit.toString());
        querys.put("market", market);
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            String entity = EntityUtils.toString(response.getEntity());
            Map map = objectMapper.readValue(entity, Map.class);
            return ApiResponse.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("获取股票信息失败");
        }
    }

    @GetMapping("/hs/kline")
    @ResponseBody
    public ApiResponse getStocksKline(@RequestParam("symbol") String symbol,
                                      @RequestParam("type") String type,
                                      @RequestParam("limit") Integer limit,
                                      @RequestParam("ma") String ma) {
        String path = "/hs/kline";
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("symbol", "sz000002");
        querys.put("type", "5");
        querys.put("limit", "10");
        querys.put("ma", "5");
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println(response.toString());
            Map map = objectMapper.readValue(EntityUtils.toString(response.getEntity()), Map.class);
            return ApiResponse.ok(map);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.error("获取股票K线图失败");
        }
    }

}
