package org.example;

import org.example.controller.GameSessionController;
import org.example.view.viewcomponent.ChessViewComponent;

class Main {
    public static void main(String[] args) {
        GameSessionController Controller = new GameSessionController();
        ChessViewComponent ViewComponent = new ChessViewComponent();
        Controller.register(ViewComponent);
        ViewComponent.register(Controller);
        Controller.Start();
    }
}