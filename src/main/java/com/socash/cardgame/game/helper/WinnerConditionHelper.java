package com.socash.cardgame.game.helper;

import com.socash.cardgame.game.models.Player;
import com.socash.cardgame.game.service.UniqueCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class WinnerConditionHelper {

    private UniqueCardService uniqueCardService;

    public boolean isAllCardsSame(Player player) {
        return new HashSet<>(player.getCards()).size() == 1;
    }

    public boolean isNumbersInSequence(Player player, LinkedList<String> cardValues) {
        List<String> cards = player.getCards();
        cards.sort(Comparator.comparingInt(cardValues::indexOf));
        return ((cards.get(0) + 1).equals(cards.get(1)))
                || ((cards.get(1) + 1).equals(cards.get(2)));
    }

    public boolean isEqualPairOfCards(Player player, LinkedList<String> cardValues) {
        List<String> cards = player.getCards();
        cards.sort(Comparator.comparingInt(cardValues::indexOf));
        return (cards.get(0).equals(cards.get(1))) || (cards.get(1).equals(cards.get(2)));
    }

    public int getPlayerOfMaximumSum(List<Player> players, LinkedList<String> cardValues) {
        Map<Integer, Integer> sumMap = new HashMap<>();

        for (Player player : players) {
            int sum = player.getCards()
                    .stream()
                    .mapToInt(cardValues::indexOf).sum();
            sumMap.put(player.getIndex(), sum);
        }

        return getPlayerFromMap(players, cardValues, sumMap);
    }

    public int getTiedWinner(LinkedList<String> cardValues, List<Player> filteredPlayers) {
        log.info("There is a tie condition");
        Map<String, Integer> cardCountMap = new HashMap<>();
        int maximum = Integer.MIN_VALUE;

        Map<Integer, Integer> maxMap = new HashMap<>();

        for (Player player : filteredPlayers) {
            String uniqueCard = uniqueCardService.findUniqueCard(cardValues, cardCountMap);
            if (uniqueCard.equals("A")) {
                maxMap.put(player.getIndex(), 14);
            } else {
                maxMap.put(player.getIndex(), cardValues.indexOf(uniqueCard));
            }

            maximum = Math.max(maximum, maxMap.get(player.getIndex()));
        }

        return getPlayerFromMap(filteredPlayers, cardValues, maxMap);
    }

    private int getPlayerFromMap(List<Player> players, LinkedList<String> cardValues, Map<Integer, Integer> sumMap) {
        boolean isSameSum = sumMap.values().stream().distinct().count() != sumMap.size();
        Map.Entry<Integer, Integer> maximum = Collections
                .max(sumMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

        if (!isSameSum) {
            return maximum.getKey();
        } else {
            Integer maximumValue = maximum.getValue();
            List<Player> filteredPlayers = sumMap.entrySet()
                    .stream()
                    .filter(entry -> Objects.equals(maximumValue, entry.getValue()))
                    .map(entry -> players.get(entry.getKey()))
                    .collect(Collectors.toList());

            return getTiedWinner(cardValues, filteredPlayers);
        }
    }
}
