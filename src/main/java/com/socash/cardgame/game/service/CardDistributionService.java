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

    public List<Player> getPlayers(int numberOfPlayers, LinkedList<String> cardValues, Map<String, Integer> cardCountMap) {
        return range(0, numberOfPlayers)
                .mapToObj(i -> Player.builder()
                        .cards(getCards(cardValues, cardCountMap))
                        .build())
                .collect(Collectors.toList());
    }

    private List<String> getCards(LinkedList<String> cardValues, Map<String, Integer> cardCountMap) {
        //3 cards
        return range(0, 3)
                .mapToObj(index -> findUniqueCard(cardValues, cardCountMap))
                .collect(Collectors.toList());
    }

    private String findUniqueCard(LinkedList<String> cardValues, Map<String, Integer> cardCountMap) {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 13);
        String cardValue = cardValues.get(randomNum);
        if (cardCountMap.containsKey(cardValue)) {
            Integer cardCount = cardCountMap.get(cardValue);
            if (cardCount < 4) {
                cardCountMap.put(cardValue, cardCount + 1);
            } else {
                return findUniqueCard(cardValues, cardCountMap);
            }
        } else {
            cardCountMap.put(cardValue, 1);
        }

        return cardValue;
    }
}
