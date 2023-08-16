package DeadlockByAlishev;

class Account {
    private int money;

    Account(int money) {
        this.money = money;
    }

    public int getBalance() {
        return money;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void takeMoney(int amount) {
        money -= amount;
    }

    public static void transferMoneyFromAccountOneToAccountTwo(Account accountOne, Account accountTwo, int amount) {
        accountOne.takeMoney(amount);
        accountTwo.addMoney(amount);
    }
}
