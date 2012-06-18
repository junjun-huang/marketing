package com.dp.basicoperations;

import java.rmi.RemoteException;

import com.dp.dao.AdGroupDao;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroup;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupReturnValue;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupStatus;
import com.google.api.ads.adwords.axis.v201109_1.cm.ApiException;
import com.google.api.ads.adwords.axis.v201109_1.cm.Bid;
import com.google.api.ads.adwords.axis.v201109_1.cm.ManualCPCAdGroupBids;
import com.google.api.ads.adwords.axis.v201109_1.cm.Money;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class AddAdGroup
{
    AdGroupDao adGroupDao;
    
    AdGroupReturnValue addAdGrp(Long campaignId) throws Exception {
     // Get a ClientLogin AuthToken.
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
        
     // Get the AdGroupService.
        AdGroupServiceInterface adGroupService = new AdWordsServices().get(session, AdGroupServiceInterface.class);

        // Create ad group.
        AdGroup adGroup = new AdGroup();
        adGroup.setName(adGroupDao.getAdGroupName());
        adGroup.setStatus(adGroupDao.getAdGroupStatus());
        adGroup.setCampaignId(campaignId/*adGroupDao.getCampaignId()*/);

        // Create ad group bid.
//        ManualCPCAdGroupBids adGroupBids = new ManualCPCAdGroupBids();
//        adGroupBids.setKeywordMaxCpc(new Bid(new Money(null, 1000000L)));

        // You can optionally provide these field(s).
//        adGroupBids.setKeywordContentMaxCpc(new Bid(new Money(null, 15000000L)));
        adGroup.setBids(adGroupDao.getAdGroupBids());

        // Create operations.
        AdGroupOperation operation = new AdGroupOperation();
        operation.setOperand(adGroup);
        operation.setOperator(Operator.ADD);
       

        AdGroupOperation[] operations = new AdGroupOperation[] {operation};

        // Add ad groups.
        AdGroupReturnValue result = adGroupService.mutate(operations);
        
        return result;
        
    }

}
