package com.socash.cardgame.game.service;

import com.socash.cardgame.game.helper.WinnerConditionHelper;
import com.socash.cardgame.game.models.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class WInnerService {

    private WinnerConditionHelper helper;

    public int andTheWinnerIs(List<Player> players, LinkedList<String> cardValues) {
        Map<Integer, Integer> playerMap = new HashMap<>();
        Map<String, Integer> conditionCountMap = new HashMap<>();
        for (Player player : players) {
            if(helper.isAllCardsSame(player)) {
                playerMap.put(player.getIndex(), 1);
                if(!conditionCountMap.containsKey("FIRST")) {
                    conditionCountMap.put("FIRST", 1);
                } else {
                    conditionCountMap.put("FIRST", conditionCountMap.get("FIRST") + 1);
                }
            } else if(helper.isNumbersInSequence(player, cardValues)) {
                playerMap.put(player.getIndex(), 2);
                if(!conditionCountMap.containsKey("SECOND")) {
                    conditionCountMap.put("SECOND", 1);
                } else {
                    conditionCountMap.put("SECOND", conditionCountMap.get("SECOND") + 1);
                }
            } else if(helper.isEqualPairOfCards(player, cardValues)) {
                playerMap.put(player.getIndex(), 3);
                if(!conditionCountMap.containsKey("THIRD")) {
                    conditionCountMap.put("THIRD", 1);
                } else {
                    conditionCountMap.put("THIRD", conditionCountMap.get("THIRD") + 1);
                }
            }
        }

        if(playerMap.values().stream().mapToInt(Integer::intValue).sum() == 0) {
            return helper.getPlayerOfMaximumSum(players, cardValues);
        } else {
            if(conditionCountMap.containsKey("FIRST")) {
                Integer value = 1;
                if(conditionCountMap.get("FIRST") > 1) {
                    List<Player> filteredPlayers = playerMap.entrySet()
                            .stream()
                            .filter(entry -> Objects.equals(value, entry.getValue()))
                            .map(entry -> players.get(entry.getKey()))
                            .collect(Collectors.toList());

                    return helper.getTiedWinner(cardValues, filteredPlayers);
                } else {
                    return playerMap.entrySet()
                            .stream()
                            .filter(entry -> value.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst().get();
                }
            } else if(conditionCountMap.containsKey("SECOND")) {
                Integer value = 2;
                if(conditionCountMap.get("SECOND") > 1) {
                    List<Player> filteredPlayers = playerMap.entrySet()
                            .stream()
                            .filter(entry -> Objects.equals(value, entry.getValue()))
                            .map(entry -> players.get(entry.getKey()))
                            .collect(Collectors.toList());

                    return helper.getTiedWinner(cardValues, filteredPlayers);
                } else {
                    return playerMap.entrySet()
                            .stream()
                            .filter(entry -> value.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst().get();
                }
            } else if(conditionCountMap.containsKey("THIRD")) {
                Integer value =3;
                if(conditionCountMap.get("THIRD") > 1) {
                    List<Player> filteredPlayers = playerMap.entrySet()
                            .stream()
                            .filter(entry -> Objects.equals(value, entry.getValue()))
                            .map(entry -> players.get(entry.getKey()))
                            .collect(Collectors.toList());

                    return helper.getTiedWinner(cardValues, filteredPlayers);
                } else {
                    return playerMap.entrySet()
                            .stream()
                            .filter(entry -> value.equals(entry.getValue()))
                            .map(Map.Entry::getKey)
                            .findFirst().get();
                }
            }
        }
        return 0;
    }
}
