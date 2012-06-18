package com.dp.dao;

import com.google.api.ads.adwords.axis.v201109_1.cm.AdServingOptimizationStatus;
import com.google.api.ads.adwords.axis.v201109_1.cm.BiddingStrategy;
import com.google.api.ads.adwords.axis.v201109_1.cm.Budget;
import com.google.api.ads.adwords.axis.v201109_1.cm.CampaignStatus;
import com.google.api.ads.adwords.axis.v201109_1.cm.FrequencyCap;
import com.google.api.ads.adwords.axis.v201109_1.cm.GeoTargetTypeSetting;
import com.google.api.ads.adwords.axis.v201109_1.cm.KeywordMatchSetting;
import com.google.api.ads.adwords.axis.v201109_1.cm.NetworkSetting;

public class CampaignDao
{
    String campaignName;
    CampaignStatus campaignStatus;
    BiddingStrategy biddingStrategy;
    
    //optional
    String startDate;
    AdServingOptimizationStatus adServingOptimizationStatus;
    FrequencyCap frequencyCap;
    
    Budget budget;
    NetworkSetting networkSetting;
    GeoTargetTypeSetting geoTarget;
    KeywordMatchSetting keywordMatch;
    
    
    public String getCampaignName()
    {
        return campaignName;
    }
    public void setCampaignName(String campaignName)
    {
        this.campaignName = campaignName;
    }
    public CampaignStatus getCampaignStatus()
    {
        return campaignStatus;
    }
    public void setCampaignStatus(CampaignStatus campaignStatus)
    {
        this.campaignStatus = campaignStatus;
    }
    public BiddingStrategy getBiddingStrategy()
    {
        return biddingStrategy;
    }
    public void setBiddingStrategy(BiddingStrategy biddingStrategy)
    {
        this.biddingStrategy = biddingStrategy;
    }
    public String getStartDate()
    {
        return startDate;
    }
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }
    public AdServingOptimizationStatus getAdServingOptimizationStatus()
    {
        return adServingOptimizationStatus;
    }
    public void setAdServingOptimizationStatus(AdServingOptimizationStatus adServingOptimizationStatus)
    {
        this.adServingOptimizationStatus = adServingOptimizationStatus;
    }
    public FrequencyCap getFrequencyCap()
    {
        return frequencyCap;
    }
    public void setFrequencyCap(FrequencyCap frequencyCap)
    {
        this.frequencyCap = frequencyCap;
    }
    public Budget getBudget()
    {
        return budget;
    }
    public void setBudget(Budget budget)
    {
        this.budget = budget;
    }
    public NetworkSetting getNetworkSetting()
    {
        return networkSetting;
    }
    public void setNetworkSetting(NetworkSetting networkSetting)
    {
        this.networkSetting = networkSetting;
    }
    public GeoTargetTypeSetting getGeoTarget()
    {
        return geoTarget;
    }
    public void setGeoTarget(GeoTargetTypeSetting geoTarget)
    {
        this.geoTarget = geoTarget;
    }
    public KeywordMatchSetting getKeywordMatch()
    {
        return keywordMatch;
    }
    public void setKeywordMatch(KeywordMatchSetting keywordMatch)
    {
        this.keywordMatch = keywordMatch;
    }
    
    

}
