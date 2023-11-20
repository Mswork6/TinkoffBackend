package edu.project1;

import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {
    char[] state();

    int attempt();

    @NotNull String message();

    record StartState(String answer, int maxAttempt) implements GuessResult {
        @Override
        public char[] state() {
            char[] word = new char[answer.length()];
            Arrays.fill(word, '*');
            return word;
        }

        @Override
        public int attempt() {
            return maxAttempt;
        }

        @Override
        @NotNull
        public String message() {
            return "";
        }

    }

    record Defeat(@NotNull String message) implements GuessResult {
        @Override
        public char[] state() {
            return new char[] {};
        }

        @Override
        public int attempt() {
            return 0;
        }

        @Override
        @NotNull
        public String message() {
            return message;
        }
    }

    record Win(@NotNull String message) implements GuessResult {
        @Override
        public char[] state() {
            return new char[] {};
        }

        @Override
        public int attempt() {
            return 0;
        }

        @Override
        @NotNull
        public String message() {
            return message;
        }
    }

    record SuccessfulGuess(char[] state, int attempts, @NotNull String message) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempts;
        }

        @Override
        @NotNull
        public String message() {
            return message;
        }
    }

    record FailedGuess(char[] state, int attempts, @NotNull String message) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempts;
        }

        @Override
        @NotNull
        public String message() {
            return message;
        }
    }
}
