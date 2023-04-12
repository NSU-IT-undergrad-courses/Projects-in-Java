package org.example;

import org.example.controller.ChessGameController;
import org.example.view.viewcomponent.ChessViewComponent;

class Main {
    public static void main(String[] args) {
        ChessGameController Controller = new ChessGameController();
        ChessViewComponent ViewComponent = new ChessViewComponent();
        Controller.register(ViewComponent);
        ViewComponent.register(Controller);
        Controller.Start();
        Controller.StartGame();
    }
}