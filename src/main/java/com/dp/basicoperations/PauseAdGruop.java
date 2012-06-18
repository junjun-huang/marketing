package com.dp.basicoperations;

import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroup;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupReturnValue;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupStatus;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class PauseAdGruop
{
//    Long adGroupId;
    
    void pauseAdGrp(Long adGroupId) throws Exception
    {
     // Get a ClientLogin AuthToken.
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
      
     // Get the AdGroupService.
        AdGroupServiceInterface adGroupService =
            new AdWordsServices().get(session, AdGroupServiceInterface.class);

        // Create ad group with updated status.
        AdGroup adGroup = new AdGroup();
        adGroup.setId(adGroupId);
        adGroup.setStatus(AdGroupStatus.PAUSED);

        // Create operations.
        AdGroupOperation operation = new AdGroupOperation();
        operation.setOperand(adGroup);
        operation.setOperator(Operator.SET);

        AdGroupOperation[] operations = new AdGroupOperation[] {operation};

        // Update ad group.
        /*AdGroupReturnValue result = */adGroupService.mutate(operations);
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
    
}
