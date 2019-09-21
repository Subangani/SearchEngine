package com.search.test;

import com.json.search.document.Organization;
import com.json.search.service.OrganizationSearchService;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class OrganizationSearchServiceTest {

    static TextIO textIO = TextIoFactory.getTextIO();

    static OrganizationSearchService organizationSearchService = new OrganizationSearchService(textIO);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void searchOrgServiceWithValidIdTest(){
        List<Organization> organizationList = organizationSearchService.searchForOrganizationData("_id","101");
        Assert.assertEquals(1,organizationList.size());
        Organization org = organizationList.get(0);
        Assert.assertEquals(101,org.get_id());
        Assert.assertEquals("http://initech.tokoin.io.com/api/v2/organizations/101.json",org.getUrl());
        Assert.assertEquals("Enthaze",org.getName());
        Assert.assertEquals(4,org.getDomain_names().size());
        Assert.assertEquals("2016-05-21T11:10:28 -10:00",org.getCreated_at());
        Assert.assertEquals("MegaCorp",org.getDetails());
        Assert.assertEquals(4,org.getTags().size());
    }

    @Test
    public void searchUserServiceWithInvalidIdTest(){
        List<Organization> organizationList = organizationSearchService.searchForOrganizationData("_id","999");
        Assert.assertEquals(0,organizationList.size());
    }

    @Test
    public void searchUserServiceWithInValidfieldTest(){
        List<Organization> user = organizationSearchService.searchForOrganizationData("status","false");
        Assert.assertEquals(0,user.size());
    }

    @Test
    public void searchUserServiceWithValidfieldTest(){
        List<Organization> organizationList = organizationSearchService.searchForOrganizationData("shared_tickets","false");
        Assert.assertEquals(15,organizationList.size());
    }

    @Test
    public void searchUserWithValidDataTest(){
        organizationSearchService.search("_id","101");
    }

    @Test
    public void searchUserWithInValidDataTest(){
        organizationSearchService.search("details","sddsdfgcom");
    }

}
