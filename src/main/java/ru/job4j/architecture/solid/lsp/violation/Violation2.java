package ru.job4j.architecture.solid.lsp.violation;

public class Violation2 {
    class BigMoney {
        private int money;

        public BigMoney(int money) {
            this.money = money;
        }

        public int getMoney() {
            return money;
        }

        public boolean checkMoney() {
            return money > 100;
        }
    }

    class SmallMoney extends BigMoney {
        public SmallMoney(int money) {
            super(money);
        }

        @Override
        public boolean checkMoney() {
            return this.getMoney() < 100;
        }

    }
}
