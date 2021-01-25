package com.socash.cardgame.game.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@Builder
public class Player {

    List<String> cards;

}
