package com.dp.basicoperations;

import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAd;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAdOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAdReturnValue;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAdServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAdStatus;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.axis.v201109_1.cm.TextAd;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class AddTextAd
{
    private String headline;
    private String description1;
    private String description2;
    private String displayUrl;
    private String destUrl;
    private String adGroupAdStatus;
//    Long adGroupId;
    
    void AddText(Long adGroupId) throws Exception 
    {
     // Get a ClientLogin AuthToken.
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
        
     // Get the AdGroupAdService.
        AdGroupAdServiceInterface adGroupAdService = new AdWordsServices().get(session, AdGroupAdServiceInterface.class);

        // Create text ad.
        TextAd textAd = new TextAd();
        textAd.setHeadline(headline);
        textAd.setDescription1(description1);
        textAd.setDescription2(description2);
        textAd.setDisplayUrl(displayUrl);
        textAd.setUrl(destUrl);
        
     // Create ad group ad.
        AdGroupAd textAdGroupAd = new AdGroupAd();
        textAdGroupAd.setAdGroupId(adGroupId);
        textAdGroupAd.setAd(textAd);
        
        if (adGroupAdStatus != null || adGroupAdStatus != "" || adGroupAdStatus != "null")
        {
            textAdGroupAd.setStatus(AdGroupAdStatus.fromValue(adGroupAdStatus));
        }
        
        AdGroupAdOperation textAdGroupAdOperation = new AdGroupAdOperation();
        textAdGroupAdOperation.setOperand(textAdGroupAd);
        textAdGroupAdOperation.setOperator(Operator.ADD);
        
        AdGroupAdOperation[] operations = new AdGroupAdOperation[] {textAdGroupAdOperation};

        // Add ads.
        /*AdGroupAdReturnValue result = */adGroupAdService.mutate(operations);
        
    }
    
    
    public String getDescription1()
    {
        return description1;
    }


    public void setDescription1(String description1)
    {
        this.description1 = description1;
    }


    public String getDescription2()
    {
        return description2;
    }


    public void setDescription2(String description2)
    {
        this.description2 = description2;
    }


    public String getHeadline()
    {
        return headline;
    }
    public void setHeadline(String headline)
    {
        this.headline = headline;
    }
   
    public String getDisplayUrl()
    {
        return displayUrl;
    }
    public void setDisplayUrl(String displayUrl)
    {
        this.displayUrl = displayUrl;
    }
    public String getDestUrl()
    {
        return destUrl;
    }
    public void setDestUrl(String destUrl)
    {
        this.destUrl = destUrl;
    }
    public String getAdGroupAdStatus()
    {
        return adGroupAdStatus;
    }
    public void setAdGroupAdStatus(String adGroupAdStatus)
    {
        this.adGroupAdStatus = adGroupAdStatus;
    }
//    public Long getAdGroupId()
//    {
//        return adGroupId;
//    }
//    public void setAdGroupId(Long adGroupId)
//    {
//        this.adGroupId = adGroupId;
//    }
    
    

}
