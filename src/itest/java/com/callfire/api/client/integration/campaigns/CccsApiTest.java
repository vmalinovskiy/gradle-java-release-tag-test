package com.callfire.api.client.integration.campaigns;

import com.callfire.api.client.api.campaigns.CccsApi;
import com.callfire.api.client.api.campaigns.model.*;
import com.callfire.api.client.api.campaigns.model.Broadcast.Status;
import com.callfire.api.client.api.campaigns.model.Question.ResponseType;
import com.callfire.api.client.api.campaigns.model.request.AgentInviteRequest;
import com.callfire.api.client.api.campaigns.model.request.FindCccBroadcastsRequest;
import com.callfire.api.client.api.common.model.Page;
import com.callfire.api.client.api.common.model.ResourceId;
import com.callfire.api.client.integration.AbstractIntegrationTest;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Date;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * integration tests for /campaigns/cccs api endpoint
 */
public class CccsApiTest extends AbstractIntegrationTest {
    @Rule
    public ExpectedException ex = ExpectedException.none();

    @Test
    @Ignore("callfire is not ready to run this")
    public void testCrudOperations() throws Exception {
        // TODO vmikhailov fix tests when ccc will be ready
        CccsApi api = new CccsApi(null);
        //        CccsApi api = getCallfireClient().cccsApi();

        // create
        CccCampaign campaign = new CccCampaign();
        campaign.setName("ccc-campaign-test");
        campaign.setLabels(asList("ccc-test"));
        campaign.setRecorded(true);
        Script script = new Script();
        script.setContent("script-content-test");
        Question question1 = new Question();
        question1.setQuestion("question1");
        question1.setResponseType(ResponseType.STRING);
        question1.setChoices(asList("choice1", "choice2"));
        Question question2 = new Question();
        question2.setQuestion("question2");
        question2.setChoices(asList("choice1", "choice2"));
        question2.setResponseType(ResponseType.STRING);
        script.setQuestions(asList(question1, question2));
        campaign.setScript(script);
        ResourceId id = api.create(campaign);
        // get by id
        CccCampaign savedCampaign = api.get(id.getId(), "id,name,script");
        System.out.println(savedCampaign);
        assertNull(savedCampaign.getStatus());
        assertNull(savedCampaign.getRecorded());
        assertEquals(id.getId(), savedCampaign.getId());
        assertNotNull(savedCampaign.getScript());
        assertEquals(script.getContent(), savedCampaign.getScript().getContent());
        assertEquals(2, savedCampaign.getScript().getQuestions().size());
        // TODO vmikhailov seem to be a bug, doesn't return choices
        //        assertEquals(2, savedCampaign.getScript().getQuestions().get(0).getChoices().size());
        assertEquals(id.getId(), savedCampaign.getId());
        // update
        savedCampaign.setName("updated_name");
        api.update(savedCampaign, true);
        // find
        FindCccBroadcastsRequest request = FindCccBroadcastsRequest.create()
            .name("updated_name")
            .build();
        Page<CccCampaign> page = api.find(request);
        assertEquals(Long.valueOf(1L), page.getTotalCount());
        assertEquals("updated_name", page.getItems().get(0).getName());
        // delete
        api.delete(id.getId());
        savedCampaign = api.get(id.getId(), "status");
        assertEquals(Status.ARCHIVED, savedCampaign.getStatus());
    }

    @Test
    @Ignore("callfire is not ready to run this")
    public void testStartStopArchiveCampaign() throws Exception {
        CccsApi api = new CccsApi(null);
        //        CccsApi api = getCallfireClient().cccsApi();
        CccCampaign campaign = api.get(8L);
        System.out.println(campaign);
        assertNotNull(campaign);
        // start
        api.start(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Status.RUNNING, campaign.getStatus());
        // stop
        api.stop(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Status.STOPPED, campaign.getStatus());
        // archive
        api.archive(campaign.getId());
        campaign = api.get(campaign.getId(), "id,status");
        assertEquals(Status.ARCHIVED, campaign.getStatus());
    }

    @Test
    @Ignore("callfire is not ready to run this")
    public void testAgentsCrudOperations() throws Exception {
        CccsApi api = new CccsApi(null);
        //        CccsApi api = getCallfireClient().cccsApi();
        FindCccBroadcastsRequest findRequest = FindCccBroadcastsRequest.create()
            .build();
        Page<CccCampaign> page = api.find(findRequest);
        System.out.println(page);
        assertThat(page.getItems(), not(empty()));

        // add
        Long campaignId = page.getItems().get(0).getId();
        AgentInviteRequest addAgentsRequest = AgentInviteRequest.create()
            .agentEmails(asList("dev+agent@callfire.com"))
            .campaignId(campaignId)
            .sendEmail(false)
            .build();
        Map<String, String> map = api.addAgents(addAgentsRequest);
        System.out.println(map);

        // get
        List<Agent> agents = api.getAgents(campaignId);
        assertThat(agents, not(empty()));
        System.out.println(agents);

        // agent invite uri
        String agentInviteUri = api.getAgentInviteUri(campaignId);
        assertThat(agentInviteUri, not(isEmptyOrNullString()));
        System.out.println(agentInviteUri);
        // remove agent
        api.removeAgent(campaignId, agents.get(0).getId());
        List<Agent> updatedAgents = api.getAgents(campaignId);
        assertEquals(0, updatedAgents.size());
    }

    @Test
    @Ignore("callfire is not ready to run this")
    public void testAgentGroupsCrudOperations() throws Exception {
        CccsApi api = new CccsApi(null);
        //        CccsApi api = getCallfireClient().cccsApi();
        FindCccBroadcastsRequest findRequest = FindCccBroadcastsRequest.create()
            .build();
        Page<CccCampaign> page = api.find(findRequest);
        System.out.println(page);
        assertThat(page.getItems(), not(empty()));
        Long campaignId = page.getItems().get(0).getId();

        // get
        List<AgentGroup> groups = api.getAgentGroups(campaignId);
        System.out.println(groups);

        // add
        String name = "ccc-group-" + new Date().getTime();
        AgentGroup agentGroup = new AgentGroup();
        agentGroup.setName(name);
        ResourceId resourceId = getCallfireClient().agentGroupsApi().create(agentGroup);
        Long groupId = resourceId.getId();
        api.addAgentGroups(campaignId, asList(groupId));

        // get
        List<AgentGroup> updatedGroups = api.getAgentGroups(campaignId);
        System.out.println(updatedGroups);
        assertEquals(groups.size() + 1, updatedGroups.size());
        assertThat(updatedGroups, hasItem(Matchers.<AgentGroup>hasProperty("name", equalTo(name))));

        // remove
        api.removeAgentGroup(campaignId, groupId);
        groups = api.getAgentGroups(campaignId);
        System.out.println(groups);
        assertEquals(updatedGroups.size() - 1, groups.size());

        getCallfireClient().agentGroupsApi().delete(groupId);
    }
}
