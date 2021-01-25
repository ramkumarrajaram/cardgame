package com.socash.cardgame.game.service;

import com.socash.cardgame.game.models.Player;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
@Slf4j
public class GameService {

    private CardDistributionService cardDistributionService;

    public String determineWinner(int numberOfPlayers) {
        LinkedList<String> cardValues = buildCardValues();
        Map<String, Integer> cardCountMap = new HashMap<>();
        List<Player> players = cardDistributionService
                .getPlayers(numberOfPlayers, cardValues, cardCountMap);

        return null;
    }



    public LinkedList<String> buildCardValues() {
        String cardString = "A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K";
        String[] cardValues = cardString.split(",");
        return new LinkedList<>(Arrays.asList(cardValues));
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
