package com.tencent.wxcloudrun.mapper;

import com.tencent.wxcloudrun.model.Bill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 35793
* @description 针对表【bill(账单表)】的数据库操作Mapper
* @createDate 2024-03-19 23:38:06
* @Entity com.tencent.wxcloudrun.model.Bill
*/
@Mapper
public interface BillMapper extends BaseMapper<Bill> {

}




