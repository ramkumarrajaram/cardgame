package com.socash.cardgame.game.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class GameRunnerTest {

    @InjectMocks
    private GameRunner subject;

    @Test
    public void name() {
        LinkedList<String> strings = subject.buildCardValues();

        assertThat(strings.get(0)).isEqualTo("A");
    }
}