package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed interface GuessResult {
    char[] state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record StartState(int attempt) implements GuessResult {
        @Override
        public char[] state() {
            return new char[] {};
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return 0;
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
        public int maxAttempts() {
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
        public int maxAttempts() {
            return 0;
        }

        @Override
        @NotNull
        public String message() {
            return message;
        }
    }

    record SuccessfulGuess(char[] state, int attempt, int maxAttempts, @NotNull String message) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        @NotNull
        public String message() {
            return message;
        }
    }

    record FailedGuess(char[] state, int attempt, int maxAttempts, @NotNull String message) implements GuessResult {
        @Override
        public char[] state() {
            return state;
        }

        @Override
        public int attempt() {
            return attempt;
        }

        @Override
        public int maxAttempts() {
            return maxAttempts;
        }

        @Override
        @NotNull
        public String message() {
            return message;
        }
    }
}
