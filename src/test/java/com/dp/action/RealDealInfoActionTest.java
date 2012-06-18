package com.dp.action;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dp.dao.DealDao;

public class RealDealInfoActionTest
{
    ReadDealInfoAction readAction;
    
    @Test
    public void testReadDealInfo()
    {
        readAction.readDealInfo();
        for (DealDao dealDao : readAction.dealDaoList)
        {
            System.out.println(dealDao.getCity() + " || "
                               + dealDao.getComodityName() + " || "
                               + dealDao.getContent() + " || "
                               + dealDao.getListPrice() + " || "
                               + dealDao.getPrice() + " || "
                               + dealDao.getDateOnline() + " || "
                               + dealDao.getDateOffline() + " || "
                               + dealDao.getTgUrl() + " || "
                               + dealDao.getCls() + " || "
                               + dealDao.getSubCls() + " || "
                               + dealDao.getSuperiority());
        }
        
    }
    
    @BeforeClass
    public void beforeClass()
    {
        readAction = new ReadDealInfoAction();
    }

}
