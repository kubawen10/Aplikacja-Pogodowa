package com;

import Menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        Csi csi = new Csi();
        MainMenu menu = new MainMenu(csi);
        menu.run();
    }
}
