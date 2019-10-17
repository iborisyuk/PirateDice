package com.playtika.piratedice.api;

import com.google.gson.Gson;
import com.playtika.piratedice.core.database.PlayersRepository;
import com.playtika.piratedice.core.database.model.Players;
import com.playtika.piratedice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayersRepository playersRepo;

    private static final Gson gson = new Gson();

    @GetMapping("/add")
    public Response add(String name, int money) {

        Players players = playersRepo.save(new Players(name, money));

        return new Response()
                .add("playerId", players.getId());
    }

    @GetMapping("/get")
    public Response get(int id) {
        Players players = playersRepo.findById(id).orElse(null);

        if (players == null)
            return new Response(false).add("error", "player not found");


        return new Response()
                .add("player", gson.toJson(players.getPlayer()));
    }

    @GetMapping("/remove")
    public Response remove(int id) {
        Players players = playersRepo.findById(id).orElse(null);

        if (players == null)
            return new Response(false).add("error", "player not found");

        playersRepo.deleteById(id);

        return new Response();
    }
}
