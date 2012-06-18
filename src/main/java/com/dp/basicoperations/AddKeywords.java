package com.dp.basicoperations;

import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionReturnValue;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.BidSource;
import com.google.api.ads.adwords.axis.v201109_1.cm.BiddableAdGroupCriterion;
import com.google.api.ads.adwords.axis.v201109_1.cm.Keyword;
import com.google.api.ads.adwords.axis.v201109_1.cm.KeywordMatchType;
import com.google.api.ads.adwords.axis.v201109_1.cm.ManualCPCAdGroupCriterionBids;
import com.google.api.ads.adwords.axis.v201109_1.cm.ManualCPMAdGroupCriterionBids;
import com.google.api.ads.adwords.axis.v201109_1.cm.NegativeAdGroupCriterion;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.axis.v201109_1.cm.PercentCPAAdGroupCriterionBids;
import com.google.api.ads.adwords.axis.v201109_1.cm.UserStatus;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class AddKeywords
{
    private String keywords;
    private String matchType;
    private String userStatus;
    private String destUrl;
    private String bid;
    private String bidSource;
    private String bidSet;
    private boolean enhancedCpcEnabled = false;
//    Long adGroupId;
    private String criterionPN = "POSITIVE";
    

    void addKws(Long adGroupId) throws Exception {
     // Get a ClientLogin AuthToken.
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
        
        AdGroupCriterionServiceInterface adGroupCriterionService =
            new AdWordsServices().get(session, AdGroupCriterionServiceInterface.class);
        
        String[] keyWordList = keywords.split(",");
//        KeywordMatchType.
//        UserStatus.f
//        BidSource.f
        Object keywordBids = null;
        if (bid == "ManualCPC")
        {
            ManualCPCAdGroupCriterionBids bids = new ManualCPCAdGroupCriterionBids();
            bids.setBidSource(BidSource.fromValue(bidSource)); //  This field is read only and should not be set
            bids.setEnhancedCpcEnabled(enhancedCpcEnabled);
//            bids.setMaxCpc(setMaxCpc(bidSet));        // TO BE CONTINUE...
            keywordBids = bids;
        }
        else if (bid == "ManualCPM")
        {
            ManualCPMAdGroupCriterionBids bids = new ManualCPMAdGroupCriterionBids();
            bids.setBidSource(BidSource.fromValue(bidSource)); //  This field is read only and should not be set
//          bids.setMaxCpm(setMaxCpm(bidSet));        // TO BE CONTINUE...
            keywordBids = bids;
        }
        else if (bid == "PercentCPA")
        {
            PercentCPAAdGroupCriterionBids bids = new PercentCPAAdGroupCriterionBids();
            bids.setSource(BidSource.fromValue(bidSource)); //  This field is read only and should not be set
//          bids.setPercentCPA(setPercentCPA(bidSet));        // TO BE CONTINUE...
            keywordBids = bids;
        }
//        else
//        {
//            String bids = null;
//            keywordBids = bids;
//        }
        
        
        
        for (int i = 0; i < keyWordList.length; i++)
        {
            Keyword keyword = new Keyword();
            keyword.setText(keyWordList[i]);
            keyword.setMatchType(KeywordMatchType.fromValue(matchType));
            
            if (criterionPN == "POSITIVE")
            {
                BiddableAdGroupCriterion bagc = new BiddableAdGroupCriterion();
                bagc.setAdGroupId(adGroupId);
                bagc.setCriterion(keyword);
                
                if (userStatus != null || userStatus != "" || userStatus != "null")
                {
                    bagc.setUserStatus(UserStatus.fromValue(userStatus));
                }
                
                if (destUrl != null || destUrl != "" || destUrl != "null")
                {
                    bagc.setDestinationUrl(destUrl);
                }
                
                if (bid == "ManualCPC")
                {
                    bagc.setBids((ManualCPCAdGroupCriterionBids)keywordBids);
                }
                else if (bid == "ManualCPM")
                {
                    bagc.setBids((ManualCPMAdGroupCriterionBids)keywordBids);
                }
                else if (bid == "PercentCPA")
                {
                    bagc.setBids((PercentCPAAdGroupCriterionBids)keywordBids);
                }
                
             // Create AdGroupCriterionOperation.
                AdGroupCriterionOperation agco = new AdGroupCriterionOperation();
                agco.setOperand(bagc);
                agco.setOperator(Operator.ADD);
                
                AdGroupCriterionOperation[] operations = new AdGroupCriterionOperation[] {agco};

                // Add keywords.
                /*AdGroupCriterionReturnValue result = */adGroupCriterionService.mutate(operations);
            }
            else
            {
                NegativeAdGroupCriterion bagc = new NegativeAdGroupCriterion();
                bagc.setAdGroupId(adGroupId);
                bagc.setCriterion(keyword);
                
             // Create AdGroupCriterionOperation.
                AdGroupCriterionOperation agco = new AdGroupCriterionOperation();
                agco.setOperand(bagc);
                agco.setOperator(Operator.ADD);
                
                AdGroupCriterionOperation[] operations = new AdGroupCriterionOperation[] {agco};

                // Add keywords.
                /*AdGroupCriterionReturnValue result = */adGroupCriterionService.mutate(operations);
            }
                
            
        }
       
        
    }


    public String getKeywords()
    {
        return keywords;
    }

    public void setKeywords(String keywords)
    {
        this.keywords = keywords;
    }


    public String getMatchType()
    {
        return matchType;
    }

    public void setMatchType(String matchType)
    {
        this.matchType = matchType;
    }


    public String getUserStatus()
    {
        return userStatus;
    }

    public void setUserStatus(String userStatus)
    {
        this.userStatus = userStatus;
    }


    public String getDestUrl()
    {
        return destUrl;
    }

    public void setDestUrl(String destUrl)
    {
        this.destUrl = destUrl;
    }


    public String getBid()
    {
        return bid;
    }

    public void setBid(String bid)
    {
        this.bid = bid;
    }


    public String getBidSource()
    {
        return bidSource;
    }

    public void setBidSource(String bidSource)
    {
        this.bidSource = bidSource;
    }


    public String getBidSet()
    {
        return bidSet;
    }

    public void setBidSet(String bidSet)
    {
        this.bidSet = bidSet;
    }


    public boolean isEnhancedCpcEnabled()
    {
        return enhancedCpcEnabled;
    }

    public void setEnhancedCpcEnabled(boolean enhancedCpcEnabled)
    {
        this.enhancedCpcEnabled = enhancedCpcEnabled;
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


    public String getCriterionPN()
    {
        return criterionPN;
    }

    public void setCriterionPN(String criterionPN)
    {
        this.criterionPN = criterionPN;
    } 
    
}
