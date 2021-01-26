package com.socash.cardgame.game.service;

import com.socash.cardgame.game.helper.WinnerConditionHelper;
import com.socash.cardgame.game.models.Player;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@AllArgsConstructor
public class WInnerService {

    private WinnerConditionHelper helper;

    public int andTheWinnerIs(List<Player> players, LinkedList<String> cardValues) {
        for (Player player : players) {
            //helper.isAllCardsSame()
        }
        return 0;
    }
}
