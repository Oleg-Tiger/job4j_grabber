package ru.job4j.design.isp.menu;

class StubActionDelegate implements ActionDelegate {

    private int count = 0;

    @Override
    public void delegate() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
