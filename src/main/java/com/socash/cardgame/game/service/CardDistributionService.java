package com.socash.cardgame.game.service;

import com.socash.cardgame.game.models.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

@Service
@AllArgsConstructor
public class CardDistributionService {

    private UniqueCardService uniqueCardService;

    public List<Player> getPlayers(int numberOfPlayers, LinkedList<String> cardValues, Map<String, Integer> cardCountMap) {
        return range(0, numberOfPlayers)
                .mapToObj(i -> Player.builder()
                        .index(i)
                        .cards(getCards(cardValues, cardCountMap))
                        .build())
                .collect(Collectors.toList());
    }

    private List<String> getCards(LinkedList<String> cardValues, Map<String, Integer> cardCountMap) {
        //3 cards
        return range(0, 3)
                .mapToObj(index -> uniqueCardService.findUniqueCard(cardValues, cardCountMap))
                .collect(Collectors.toList());
    }
}
