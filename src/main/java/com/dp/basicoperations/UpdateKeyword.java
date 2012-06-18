package com.dp.basicoperations;

import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionReturnValue;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.Bid;
import com.google.api.ads.adwords.axis.v201109_1.cm.BidSource;
import com.google.api.ads.adwords.axis.v201109_1.cm.BiddableAdGroupCriterion;
import com.google.api.ads.adwords.axis.v201109_1.cm.Criterion;
import com.google.api.ads.adwords.axis.v201109_1.cm.ManualCPCAdGroupCriterionBids;
import com.google.api.ads.adwords.axis.v201109_1.cm.ManualCPMAdGroupCriterionBids;
import com.google.api.ads.adwords.axis.v201109_1.cm.Money;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.axis.v201109_1.cm.PercentCPAAdGroupCriterionBids;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class UpdateKeyword
{
    String bid;
//    Long adGroupId;
//    Long keywordId;
    
    void updateKw(Long adGroupId, Long keywordId) throws Exception
    {
     // Get a ClientLogin AuthToken.
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
      
     // Get the AdGroupCriterionService.
        AdGroupCriterionServiceInterface adGroupCriterionService =
            new AdWordsServices().get(session, AdGroupCriterionServiceInterface.class);

        // Create ad group criterion with updated bid.
        Criterion criterion = new Criterion();
        criterion.setId(keywordId);

        BiddableAdGroupCriterion biddableAdGroupCriterion = new BiddableAdGroupCriterion();
        biddableAdGroupCriterion.setAdGroupId(adGroupId);
        biddableAdGroupCriterion.setCriterion(criterion);

        if (bid == "ManualCPC")
        {
            ManualCPCAdGroupCriterionBids bids = new ManualCPCAdGroupCriterionBids();
//            bids.setBidSource(BidSource.fromValue(bidSource)); //  This field is read only and should not be set
//            bids.setEnhancedCpcEnabled(enhancedCpcEnabled);
            
//            bids.setMaxCpc(setMaxCpc(bidSet));        // TO BE CONTINUE...
            biddableAdGroupCriterion.setBids(bids);
        }
        else if (bid == "ManualCPM")
        {
            ManualCPMAdGroupCriterionBids bids = new ManualCPMAdGroupCriterionBids();
//            bids.setBidSource(BidSource.fromValue(bidSource)); //  This field is read only and should not be set
            
//          bids.setMaxCpm(setMaxCpm(bidSet));        // TO BE CONTINUE...
            biddableAdGroupCriterion.setBids(bids);
        }
        else if (bid == "PercentCPA")
        {
            PercentCPAAdGroupCriterionBids bids = new PercentCPAAdGroupCriterionBids();
//            bids.setSource(BidSource.fromValue(bidSource)); //  This field is read only and should not be set
            
//          bids.setPercentCPA(setPercentCPA(bidSet));        // TO BE CONTINUE...
            biddableAdGroupCriterion.setBids(bids);
        }
        
        
        // Create operations.
        AdGroupCriterionOperation operation = new AdGroupCriterionOperation();
        operation.setOperand(biddableAdGroupCriterion);
        operation.setOperator(Operator.SET);

        AdGroupCriterionOperation[] operations = new AdGroupCriterionOperation[] {operation};

        // Update ad group criteria.
        /*AdGroupCriterionReturnValue result = */adGroupCriterionService.mutate(operations);
    }

    public String getBid()
    {
        return bid;
    }

    public void setBid(String bid)
    {
        this.bid = bid;
    }

//    public Long getAdGroupId()
//    {
//        return adGroupId;
//    }
//
//    public void setAdGroupId(Long adGroupId)
//    {
//        this.adGroupId = adGroupId;
//    }
//
//    public Long getKeywordId()
//    {
//        return keywordId;
//    }
//
//    public void setKeywordId(Long keywordId)
//    {
//        this.keywordId = keywordId;
//    }
    

}
