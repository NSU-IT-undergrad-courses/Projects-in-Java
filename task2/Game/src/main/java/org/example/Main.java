package org.example;

import org.example.controller.maincontroller.GameMainController;
import org.example.view.viewcomponent.ChessViewComponent;

import java.io.File;

class Main {
    public static void main(String[] args) {
        GameMainController RootController = new GameMainController();
        ChessViewComponent ViewComponent = new ChessViewComponent();
        RootController.register(ViewComponent);
        ViewComponent.register(RootController);
        RootController.Start();
    }
}