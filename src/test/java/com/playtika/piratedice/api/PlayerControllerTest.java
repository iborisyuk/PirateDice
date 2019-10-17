package com.playtika.piratedice.api;

import com.google.gson.Gson;
import com.playtika.piratedice.PirateDiceApplication;
import com.playtika.piratedice.util.Response;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
@ContextConfiguration(classes = PirateDiceApplication.class)
public class PlayerControllerTest {

    private final static String REQUEST_URL = "/player/";
    private final static Gson gson = new Gson();

    private int playerId = 0;
    private String playerName = "raccoon";
    private int playerMoney = 999;

    @Autowired
    private MockMvc mvc;

//    @Test
//    public void mustCorrectAddNewPlayer() {
//        Response resp = getRequest("add?name=%s&money=%d", playerName, playerMoney);
//        String playerId = resp.getValue("playerId");
//
//        assertTrue(resp.isStatus());
//        assertNotNull(playerId);
//
//        getRequest("remove?id=%s", playerId);
//    }
//
//    @Test
//    public void mustCorrectGetPlayer() {
//        Response resp = getRequest("add?name=%s&money=%d", playerName, playerMoney);
//
//        int playerId = Integer.parseInt(resp.getValue("playerId"));
//        Response respPlayer = getRequest("get?id=%d", playerId);
//
//        String playerJson = respPlayer.getValue("player");
//
//        Player player = gson.fromJson(playerJson, Player.class);
//
//        assertTrue(respPlayer.isStatus());
//        assertNotNull(playerJson);
//        assertEquals(player.getId(), playerId);
//        assertEquals(player.getName(), playerName);
//        assertEquals(player.getMoney(), playerMoney);
//
//        getRequest("remove?id=%d", playerId);
//    }
//
//    @Test
//    public void mustCorrectRemovePlayer() {
//        Response resp = getRequest("add?name=%s&money=%d", playerName, playerMoney);
//        int playerId = Integer.parseInt(resp.getValue("playerId"));
//
//        Response respRemove = getRequest("remove?id=%d", playerId);
//
//        assertTrue(respRemove.isStatus());
//
//        Response respRemove2 = getRequest("remove?id=%d", playerId);
//
//        assertFalse(respRemove2.isStatus());
//        assertNotNull(respRemove2.getValue("error"));
//    }

    private Response getRequest(String format, Object... args) {
        String urlTemplate = String.format(REQUEST_URL + format, args);

        String content = null;
        try {
            MvcResult result = mvc.perform(get(urlTemplate)
                    .contentType("application/json"))
                    .andExpect(status().isOk()).andReturn();

            content = result.getResponse().getContentAsString();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }

        return gson.fromJson(content, Response.class);
    }
}