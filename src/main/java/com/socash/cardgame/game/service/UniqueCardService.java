package com.socash.cardgame.game.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UniqueCardService {

    public String findUniqueCard(LinkedList<String> cardValues, Map<String, Integer> cardCountMap) {
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
