package com.socash.cardgame.game.service;

import com.socash.cardgame.game.models.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
@Slf4j
public class GameRunner {

    private CardDistributionService cardDistributionService;
    private WInnerService winnerService;

    public int determineWinner(int numberOfPlayers) {
        LinkedList<String> cardValues = buildCardValues();
        Map<String, Integer> cardCountMap = new HashMap<>();
        List<Player> players = cardDistributionService
                .getPlayers(numberOfPlayers, cardValues, cardCountMap);

        return winnerService.andTheWinnerIs(players, cardValues);
    }

    public LinkedList<String> buildCardValues() {
        String cardString = "A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K";
        String[] cardValues = cardString.split(",");
        return new LinkedList<>(Arrays.asList(cardValues));
    }
}
