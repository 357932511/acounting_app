package com.tencent.wxcloudrun.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tencent.wxcloudrun.model.Bill;
import com.tencent.wxcloudrun.service.BillService;
import com.tencent.wxcloudrun.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author 35793
* @description 针对表【bill(账单表)】的数据库操作Service实现
* @createDate 2024-03-19 23:38:06
*/
@Service
public class BillServiceImpl extends ServiceImpl<BillMapper, Bill>
    implements BillService{


    final BillMapper billMapper;
    
    public BillServiceImpl(@Autowired BillMapper billMapper) {
        this.billMapper = billMapper;
    }

    @Override
    public List<Bill> getBills() {
        return billMapper.selectList(null);
    }
}




