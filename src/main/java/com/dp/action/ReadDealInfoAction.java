

package com.dp.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dp.dao.DealDao;

import jxl.Cell;
import jxl.CellType;
import jxl.LabelCell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ReadDealInfoAction
{
    String filename = "D://dp_data//投放的单子.xls";
    List<DealDao> dealDaoList;

    public String readDealInfo()
    {
        String[] title = null;
        String data;

        try
        {
            Workbook workbook = Workbook.getWorkbook(new File(filename));
            Sheet sheet = workbook.getSheet(0);
            int rows = sheet.getRows();
            Cell[] titleCells = sheet.getRow(0);
            int count = 0;
            title = new String[titleCells.length];
            dealDaoList = new ArrayList<DealDao>(rows - 1);
            String cityCashe = null;

            for (Cell cell : titleCells)
            {
                if (cell.getType() == CellType.LABEL)
                {
                    LabelCell labelCell = (LabelCell) cell;
                    title[count++] = labelCell.getString();
                }
            }
            for (int i = 1; i < rows; i++)
            {
                Cell[] dataCells = sheet.getRow(i);
                DealDao deal = new DealDao();                
                
                for (int j = 0; j < titleCells.length; j++)
                {
                    data = dataCells[j].getContents();
                    
                    if (title[j].equals("城市"))
                    {
                        if (data == null || data == "" || data =="null")
                        {
                            data = cityCashe;
                        }
                        deal.setCity(data);
                        cityCashe = data;
                    }
                    else if (title[j].equals("商户名"))
                    {
                        deal.setComodityName(data);
                    }
                    else if (title[j].equals("团购链接"))
                    {
                        deal.setTgUrl(data);
                    }
                    else if (title[j].equals("套餐内容"))
                    {
                        deal.setContent(data);
                    }
                    else if (title[j].equals("原价"))
                    {
                        deal.setListPrice(data);
                    }
                    else if (title[j].equals("现价"))
                    {
                        deal.setPrice(data);
                    }
                    else if (title[j].equals("上线时间"))
                    {
                        deal.setDateOnline(data);
                    }
                    else if (title[j].equals("下线时间"))
                    {
                        deal.setDateOffline(data);
                    }
                    else if (title[j].equals("一级分类"))
                    {
                        deal.setCls(data);
                    }
                    else if (title[j].equals("二级分类"))
                    {
                        deal.setSubCls(data);
                    }
                    else if (title[j].equals("投放优势"))
                    {
                        deal.setSuperiority(data);
                    }    
                }
                dealDaoList.add(deal);
            } 

        }
        catch (BiffException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return "SUCCESS";
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public List<DealDao> getDealDaoList()
    {
        return dealDaoList;
    }

    public void setDealDaoList(List<DealDao> dealDaoList)
    {
        this.dealDaoList = dealDaoList;
    }
    

}
