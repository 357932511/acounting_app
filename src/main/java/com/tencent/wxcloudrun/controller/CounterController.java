package com.tencent.wxcloudrun.controller;

import com.tencent.wxcloudrun.model.Bill;
import com.tencent.wxcloudrun.model.vo.BillListVo;
import com.tencent.wxcloudrun.service.BillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tencent.wxcloudrun.config.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

/**
 * counter控制器
 */
@RestController
public class CounterController {

  final Logger logger;
  private final BillService billService;

  public CounterController(@Autowired BillService billService) {
    this.billService = billService;
    this.logger = LoggerFactory.getLogger(CounterController.class);
  }

  /**
   * 获取账单
   * @param request 请求
   * @return API response json
   */
  @GetMapping(value = "/api/bills")
    ApiResponse getBills(HttpServletRequest request) {
      //获取请求中的请求头
      String openId = request.getHeader("x-wx-openid");
      logger.info("/api/bills get request openid: {}", openId);
      List<BillListVo> bills = billService.getBills(openId);
      return ApiResponse.ok(bills);
    }

  /**
   * 新增账单
   * @param bill 账单
   * @param request 请求
   * @return API response json
   */
  @PostMapping(value = "/api/putBill")
    ApiResponse putBill(@RequestBody Bill bill, HttpServletRequest request) {
      logger.info("/api/putBill post request, bill: {}", bill);
      String openId = request.getHeader("x-wx-openid");
      billService.putBill(bill, openId);
      return ApiResponse.ok(bill);
    }



//  /**
//   * 获取当前计数
//   * @return API response json
//   */
//  @GetMapping(value = "/api/count")
//  ApiResponse get() {
//    logger.info("/api/count get request");
//    Optional<Counter> counter = counterService.getCounter(1);
//    Integer count = 0;
//    if (counter.isPresent()) {
//      count = counter.get().getCount();
//    }
//
//    return ApiResponse.ok(count);
//  }
//
//
//  /**
//   * 更新计数，自增或者清零
//   * @param request {@link CounterRequest}
//   * @return API response json
//   */
//  @PostMapping(value = "/api/count")
//  ApiResponse create(@RequestBody CounterRequest request) {
//    logger.info("/api/count post request, action: {}", request.getAction());
//
//    Optional<Counter> curCounter = counterService.getCounter(1);
//    if (request.getAction().equals("inc")) {
//      Integer count = 1;
//      if (curCounter.isPresent()) {
//        count += curCounter.get().getCount();
//      }
//      Counter counter = new Counter();
//      counter.setId(1);
//      counter.setCount(count);
//      counterService.upsertCount(counter);
//      return ApiResponse.ok(count);
//    } else if (request.getAction().equals("clear")) {
//      if (!curCounter.isPresent()) {
//        return ApiResponse.ok(0);
//      }
//      counterService.clearCount(1);
//      return ApiResponse.ok(0);
//    } else {
//      return ApiResponse.error("参数action错误");
//    }
//  }
  
}