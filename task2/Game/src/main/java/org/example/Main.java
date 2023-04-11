package org.example;

import org.example.controller.ChessGameController;
import org.example.observer.Observer;
import org.example.view.ChessViewController;
import org.example.view.GameView;

class Main {
    public static void main(String args[]) {
        ChessGameController Controller = new ChessGameController();
        GameView ViewComponent = new ChessViewController();
        Controller.register((Observer) ViewComponent);
        Controller.Start();
    }
}