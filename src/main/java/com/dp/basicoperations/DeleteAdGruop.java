package com.dp.basicoperations;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroup;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupStatus;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class DeleteAdGruop
{
//    Long adGroupId;
    
    void deleteAdGrp(Long adGroupId) throws Exception
    {
     // Get a ClientLogin AuthToken.
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
      
     // Get the AdGroupService.
        AdGroupServiceInterface adGroupService =
            new AdWordsServices().get(session, AdGroupServiceInterface.class);

        // Create ad group with DELETED status.
        AdGroup adGroup = new AdGroup();
        adGroup.setId(adGroupId);
        adGroup.setStatus(AdGroupStatus.DELETED);
        // We recommend including the original name when renaming before delete.
//        adGroup.setName("Deleted on " + new SimpleDateFormat("yyMMDD HH:mm:ss.S").format(new Date())); //TO BE CONTINUE...

        // Create operations.
        AdGroupOperation operation = new AdGroupOperation();
        operation.setOperand(adGroup);
        operation.setOperator(Operator.SET);

        AdGroupOperation[] operations = new AdGroupOperation[] {operation};

        // Delete ad group.
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
