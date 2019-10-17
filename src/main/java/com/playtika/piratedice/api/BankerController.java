package com.playtika.piratedice.api;

import com.google.gson.Gson;
import com.playtika.piratedice.core.database.BankersRepository;
import com.playtika.piratedice.core.database.model.Bankers;
import com.playtika.piratedice.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@RestController
@RequestMapping("/banker")
public class BankerController {

    @Autowired
    private BankersRepository bankersRepository;

    private static final Gson gson = new Gson();

    @GetMapping("/add")
    public Response add(String name, int money) {

        Bankers bankers = bankersRepository.save(new Bankers(name, money));

        return new Response()
                .add("bankerId", bankers.getId());
    }

    @GetMapping("/get")
    public Response add(int id) {
        Bankers bankers = bankersRepository.findById(id).orElse(null);

        if (bankers == null)
            return new Response(false).add("error", "banker not found");


        return new Response()
                .add("banker", gson.toJson(bankers.getBanker()));
    }

    @GetMapping("/list")
    public Response list() {
        return new Response().add("bankers", gson.toJson(bankersRepository.findAll()));
    }

    @GetMapping("/remove")
    public Response remove(int id) {
        Bankers bankers = bankersRepository.findById(id).orElse(null);

        if (bankers == null)
            return new Response(false).add("error", "banker not found");

        bankersRepository.deleteById(id);

        return new Response();
    }
}

