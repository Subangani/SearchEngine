package com.search.test;

import com.json.search.document.User;
import com.json.search.service.UserSearchService;
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
public class UserSearchServiceTest {

    static TextIO textIO = TextIoFactory.getTextIO();

    static UserSearchService userSearchService = new UserSearchService(textIO);

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void searchUserServiceWithValidIdTest() {
        List<User> user = userSearchService.searchForUserData("_id","70");
        Assert.assertEquals(1,user.size());
        Assert.assertEquals(70, user.get(0).get_id());
        Assert.assertEquals("af6ff47c-1ca0-45b1-b7d9-598ff5147a0d", user.get(0).getExternal_id());
        Assert.assertEquals("Nash Rivers", user.get(0).getName());
        Assert.assertEquals("Mr Alexandria", user.get(0).getAlias());
        Assert.assertEquals("2016-01-20T11:08:56 -11:00", user.get(0).getCreated_at());
        Assert.assertEquals(false, user.get(0).getActive());
        Assert.assertEquals(false, user.get(0).getVerified());
        Assert.assertEquals(false, user.get(0).getShared());
        Assert.assertEquals("en-AU", user.get(0).getLocale());
        Assert.assertEquals("Cote D'Ivoire (Ivory Coast)", user.get(0).getTimezone());
        Assert.assertEquals("2013-12-30T11:36:49 -11:00", user.get(0).getLast_login_at());
        Assert.assertEquals("alexandriarivers@flotonic.com", user.get(0).getEmail());
        Assert.assertEquals("9015-682-949", user.get(0).getPhone());
        Assert.assertEquals("Don't Worry Be Happy!", user.get(0).getSignature());
        Assert.assertEquals(116, user.get(0).getOrganization_id());
        Assert.assertEquals(4, user.get(0).getTags().size());
        Assert.assertEquals(false, user.get(0).getShared());
        Assert.assertEquals("end-user", user.get(0).getRole());
    }

    @Test
    public void searchUserServiceWithInvalidIdTest(){
        List<User> user = userSearchService.searchForUserData("_id","254");
        Assert.assertEquals(0,user.size());
    }

    @Test
    public void searchUserServiceWithInValidfieldTest(){
        List<User> user = userSearchService.searchForUserData("status","false");
        Assert.assertEquals(0,user.size());
    }

    @Test
    public void searchUserServiceWithValidfieldTest(){
        List<User> user = userSearchService.searchForUserData("active","false");
        Assert.assertEquals(36,user.size());
    }

    @Test
    public void searchUserWithValidDataTest(){
        userSearchService.search("_id","71");
    }

    @Test
    public void searchUserWithInValidDataTest(){
        userSearchService.search("email","sddsdfg@xcvb.com");
    }
}
