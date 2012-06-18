package com.dp.basicoperations;

import com.google.api.ads.adwords.axis.v201109_1.cm.Ad;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAd;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAdOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAdReturnValue;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupAdServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class DeleteAd
{
//  Long adGroupId;
//  Long adId;
    
    void delete(Long adGroupId, Long adId) throws Exception
    {
     // Get a ClientLogin AuthToken.
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
        
     // Get the AdGroupAdService.
        AdGroupAdServiceInterface adGroupAdService =
            new AdWordsServices().get(session, AdGroupAdServiceInterface.class);

        // Create base class ad to avoid setting type specific fields.
        Ad ad = new Ad();
        ad.setId(adId);

        // Create ad group ad.
        AdGroupAd adGroupAd = new AdGroupAd();
        adGroupAd.setAdGroupId(adGroupId);
        adGroupAd.setAd(ad);

        // Create operations.
        AdGroupAdOperation operation = new AdGroupAdOperation();
        operation.setOperand(adGroupAd);
        operation.setOperator(Operator.REMOVE);

        AdGroupAdOperation[] operations = new AdGroupAdOperation[] {operation};

        // Delete ad.
        /*AdGroupAdReturnValue result = */adGroupAdService.mutate(operations);
    }

//  public Long getAdGroupId()
//  {
//      return adGroupId;
//  }
//
//  public void setAdGroupId(Long adGroupId)
//  {
//      this.adGroupId = adGroupId;
//  }
//
//  public Long getAdId()
//  {
//      return adId;
//  }
//
//  public void setAdId(Long adId)
//  {
//      this.adId = adId;
//  }
    
}
