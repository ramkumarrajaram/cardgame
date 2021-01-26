package com.socash.cardgame.game.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Player {

    int index;
    List<String> cards;

}
