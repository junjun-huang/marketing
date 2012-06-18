package com.dp.basicoperations;

import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterion;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionOperation;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionReturnValue;
import com.google.api.ads.adwords.axis.v201109_1.cm.AdGroupCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201109_1.cm.Criterion;
import com.google.api.ads.adwords.axis.v201109_1.cm.Operator;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServices;
import com.google.api.ads.common.lib.auth.ClientLoginTokens;

public class DeleteKeyword
{
//    Long adGroupId;
//    Long criterionId;
    
    void deleteKw(Long adGroupId, Long criterionId) throws Exception
    {
     // Get a ClientLogin AuthToken.
        String clientLoginToken = new ClientLoginTokens.Builder().forApi(ClientLoginTokens.Api.ADWORDS).fromFile().build().requestToken();

        // Construct an AdWordsSession.
        AdWordsSession session = new AdWordsSession.Builder().fromFile().withClientLoginToken(clientLoginToken).build();
        
     // Get the AdGroupCriterionService.
        AdGroupCriterionServiceInterface adGroupCriterionService =
            new AdWordsServices().get(session, AdGroupCriterionServiceInterface.class);

        // Create base class criterion to avoid setting keyword specific fields.
        Criterion criterion = new Criterion();
        criterion.setId(criterionId);

        // Create ad group criterion.
        AdGroupCriterion adGroupCriterion = new AdGroupCriterion();
        adGroupCriterion.setAdGroupId(adGroupId);
        adGroupCriterion.setCriterion(criterion);

        // Create operations.
        AdGroupCriterionOperation operation = new AdGroupCriterionOperation();
        operation.setOperand(adGroupCriterion);
        operation.setOperator(Operator.REMOVE);

        AdGroupCriterionOperation[] operations = new AdGroupCriterionOperation[] {operation};

        // Delete ad group criteria.
        /*AdGroupCriterionReturnValue result = */adGroupCriterionService.mutate(operations);
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
//    public Long getCriterionId()
//    {
//        return criterionId;
//    }
//
//    public void setCriterionId(Long criterionId)
//    {
//        this.criterionId = criterionId;
//    }
    

}
