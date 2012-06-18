package com.dp.dao;

import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupBids;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupStatus;

public class AdGroupDao
{
    String AdGroupName;
    AdGroupStatus adGroupStatus;
//    Long campaignId;
    AdGroupBids adGroupBids;
    
    public String getAdGroupName()
    {
        return AdGroupName;
    }
    public void setAdGroupName(String adGroupName)
    {
        AdGroupName = adGroupName;
    }
    public AdGroupStatus getAdGroupStatus()
    {
        return adGroupStatus;
    }
    public void setAdGroupStatus(AdGroupStatus adGroupStatus)
    {
        this.adGroupStatus = adGroupStatus;
    }
//    public Long getCampaignId()
//    {
//        return campaignId;
//    }
//    public void setCampaignId(Long campaignId)
//    {
//        this.campaignId = campaignId;
//    }
    public AdGroupBids getAdGroupBids()
    {
        return adGroupBids;
    }
    public void setAdGroupBids(AdGroupBids adGroupBids)
    {
        this.adGroupBids = adGroupBids;
    }
       

}
