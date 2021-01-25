package com.socash.cardgame.game.helper;

import com.socash.cardgame.game.models.Player;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WinnerConditionHelper {

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

        IntStream.range(0, players.size()).forEach(i -> {
            Player player = players.get(i);
            int sum = player.getCards()
                    .stream()
                    .mapToInt(cardValues::indexOf).sum();
            sumMap.put(i, sum);
        });

        boolean isSameSum = sumMap.values().stream().distinct().count() != sumMap.size();
        Map.Entry<Integer, Integer> maximum = Collections
                .max(sumMap.entrySet(), Comparator.comparingInt(Map.Entry::getValue));

        if (!isSameSum) {
            return maximum.getKey();
        } else {
            Integer maximumValue = maximum.getValue();
            List<Player> filteredPlayer = sumMap.entrySet()
                    .stream()
                    .filter(entry -> Objects.equals(maximumValue, entry.getValue()))
                    .map(entry -> players.get(entry.getKey()))
                    .collect(Collectors.toList());
        }
        return 0;
    }
}
