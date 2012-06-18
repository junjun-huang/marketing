package com.dp.basicoperations;


import org.joda.time.DateTime;

import com.dp.dao.CampaignDao;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdServingOptimizationStatus;
import com.google.api.ads.adwords.axis.v201109_1.cm.Budget;
import com.google.api.ads.adwords.axis.v201109_1.cm.BudgetBudgetDeliveryMethod;
import com.google.api.ads.adwords.axis.v201109_1.cm.BudgetBudgetPeriod;
import com.google.api.ads.adwords.axis.v201109_1.cm.Campaign;
import com.google.api.ads.adwords.axis.v201109_1.cm.CampaignOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.CampaignReturnValue;
import com.google.api.ads.adwords.axis.v201109_1.cm.CampaignServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.CampaignStatus;
import com.google.api.ads.adwords.axis.v201109_1.cm.FrequencyCap;
import com.google.api.ads.adwords.axis.v201109_1.cm.GeoTargetTypeSetting;
import com.google.api.ads.adwords.axis.v201109_1.cm.GeoTargetTypeSettingPositiveGeoTargetType;
import com.google.api.ads.adwords.axis.v201109_1.cm.KeywordMatchSetting;
import com.google.api.ads.adwords.axis.v201109_1.cm.Level;
import com.google.api.ads.adwords.axis.v201109_1.cm.ManualCPC;
import com.google.api.ads.adwords.axis.v201109_1.cm.ManualCPM;
import com.google.api.ads.adwords.axis.v201109_1.cm.Money;
import com.google.api.ads.adwords.axis.v201109_1.cm.NetworkSetting;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.axis.v201109_1.cm.Setting;
import com.google.api.ads.adwords.axis.v201109_1.cm.TimeUnit;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class AddCampaign
{
    CampaignDao campaignDao;

    CampaignReturnValue addCamp() throws Exception 
    {
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
        
     // Get the CampaignService.
        CampaignServiceInterface campaignService = new AdWordsServices().get(session, CampaignServiceInterface.class);

        // Create campaign.
        Campaign campaign = new Campaign();
        campaign.setName(campaignDao.getCampaignName());
        campaign.setStatus(campaignDao.getCampaignStatus());
        campaign.setBiddingStrategy(campaignDao.getBiddingStrategy());

        // You can optionally provide these field(s).
        
        // 系统当前时间+1（+30）天
        campaign.setStartDate(campaignDao.getStartDate());
//        campaign.set
//        campaign.setStartDate(new DateTime().plusDays(30).toString("yyyyMMdd"));
        // ROTATE 表示广告均匀地轮流投放，若选择 OPTIMIZE 将根据CTR对广告系列的投放进行优化
        campaign.setAdServingOptimizationStatus(campaignDao.getAdServingOptimizationStatus());
        /* A frequency cap is the maximum number of times an ad (or some set of ads) can be shown to a user 
        over a particular time period. */
        campaign.setFrequencyCap(campaignDao.getFrequencyCap());

        // Create budget.
//        Budget budget = new Budget();
//        budget.setPeriod(BudgetBudgetPeriod.DAILY);
//        budget.setAmount(new Money(null, 50000000L));
//        budget.setDeliveryMethod(BudgetBudgetDeliveryMethod.STANDARD);
        campaign.setBudget(campaignDao.getBudget());

        // Set the campaign network options to Search and Search Network.
//        NetworkSetting networkSetting = new NetworkSetting();
//        networkSetting.setTargetGoogleSearch(true);
//        networkSetting.setTargetSearchNetwork(true);
//        networkSetting.setTargetContentNetwork(false);
//        networkSetting.setTargetContentContextual(false);
//        networkSetting.setTargetPartnerSearchNetwork(false);
        campaign.setNetworkSetting(campaignDao.getNetworkSetting());

        // Set options that are not required.
        
        // 地理定位geotarget，包括LOP(Location of Presence)和AOI(Area of Interest)，分 positive 和  negative 两种
        // positive 定位广告将投放到LOP和（或）AOI适合的人群，  negative 定位则将广告不投放到LOP和（或）AOI适合的人群
        // DONT_CARE 表示LOP或AOI适合皆可
//        GeoTargetTypeSetting geoTarget = new GeoTargetTypeSetting();
//        geoTarget.setPositiveGeoTargetType(GeoTargetTypeSettingPositiveGeoTargetType.DONT_CARE);
        /* mproved exact and phrase match option includes misspellings, plurals, 
         * and other close variants of your keywords. Default value set to true to include close variants.
         */
//        KeywordMatchSetting keywordMatch = new KeywordMatchSetting();
//        keywordMatch.setOptIn(Boolean.FALSE);
        campaign.setSettings(new Setting[] {campaignDao.getGeoTarget(), campaignDao.getKeywordMatch()});
        
     // Create operations.
        CampaignOperation operation = new CampaignOperation();
        operation.setOperand(campaign);
        operation.setOperator(Operator.ADD);
        
        CampaignOperation[] operations = new CampaignOperation[] {operation};

        // Add campaigns.
        CampaignReturnValue result = campaignService.mutate(operations);
        
        return result;
    
    }
    
    public CampaignDao getCampaignDao()
    {
        return campaignDao;
    }

    public void setCampaignDao(CampaignDao campaignDao)
    {
        this.campaignDao = campaignDao;
    }

}
