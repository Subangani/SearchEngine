package com.search.test;

import com.json.search.document.Ticket;
import com.json.search.service.TicketSearchService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TicketSearchServiceTest {

    static TextIO textIO = TextIoFactory.getTextIO();

    static TicketSearchService ticketSearchService = new TicketSearchService(textIO);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void searchTicketServiceWithValidIdTest() {
        List<Ticket> ticketList = ticketSearchService.searchForTickerData("_id","436bf9b0-1147-4c0a-8439-6f79833bff5b");
        Assert.assertEquals(1,ticketList.size());
        Ticket ticket = ticketList.get(0);
        Assert.assertEquals("436bf9b0-1147-4c0a-8439-6f79833bff5b", ticketList.get(0).get_id());
        Assert.assertEquals("9210cdc9-4bee-485f-a078-35396cd74063", ticket.getExternal_id());
        Assert.assertEquals("http://initech.tokoin.io.com/api/v2/tickets/436bf9b0-1147-4c0a-8439-6f79833bff5b.json", ticket.getUrl());
        Assert.assertEquals("2016-04-28T11:19:34 -10:00", ticket.getCreated_at());
        Assert.assertEquals("incident", ticket.getType());
        Assert.assertEquals("A Catastrophe in Korea (North)", ticket.getSubject());
        Assert.assertEquals("Nostrud ad sit velit cupidatat laboris ipsum nisi amet laboris ex exercitation amet et proident. Ipsum fugiat aute dolore tempor nostrud velit ipsum.", ticket.getDescription());
        Assert.assertEquals("high", ticket.getPriority());
        Assert.assertEquals("pending", ticket.getStatus());
        Assert.assertEquals(38, ticket.getSubmitter_id());
        Assert.assertEquals(24, ticket.getAssignee_id());
        Assert.assertEquals(116, ticket.getOrganization_id());
        Assert.assertEquals(4, ticket.getTags().size());
        Assert.assertEquals(false, ticket.getHas_incidents());
        Assert.assertEquals("2016-07-31T02:37:50 -10:00", ticket.getDue_at());
        Assert.assertEquals("web", ticket.getVia());

    }

    @Test
    public void searchTicketServiceWithInvalidIdTest(){
        List<Ticket> ticketList = ticketSearchService.searchForTickerData("_id","2547878878787878787");
        Assert.assertEquals(0,ticketList.size());
    }

    @Test
    public void searchTicketServiceWithInValidfieldTest(){
        List<Ticket> ticketList = ticketSearchService.searchForTickerData("domain_names","xxxxxx");
        Assert.assertEquals(0,ticketList.size());
    }

    @Test
    public void searchTicketServiceWithValidfieldTest(){
        List<Ticket> ticketList = ticketSearchService.searchForTickerData("status","hold");
        Assert.assertEquals(37,ticketList.size());
    }

    @Test
    public void searchTicketWithValidDataTest(){
        ticketSearchService.search("_id","1a227508-9f39-427c-8f57-1b72f3fab87c");
    }

    @Test
    public void searchTicketWithInValidDataTest(){
        ticketSearchService.search("subject","sddsdfg@xcvb.com");
    }

    @After
    public void after(){
        textIO.dispose();
    }
}
