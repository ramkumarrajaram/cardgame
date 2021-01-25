package com.socash.cardgame.game;

import com.socash.cardgame.game.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class CardGameCommandLine implements CommandLineRunner {

    private GameService gameService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("===== Welcome to Card game =======");
        System.out.println("Enter number of Players in digits");
        Scanner scanner = new Scanner(System.in);
        int numberOfPlayers;
        try {
            numberOfPlayers = Integer.parseInt(scanner.nextLine());
        } catch (Exception ex) {
            System.out.println("Wrong number, so setting to 4 players as default");
            numberOfPlayers = 4;
        }

        System.out.println("The winner is : " + gameService.determineWinner(numberOfPlayers));

    }
}
