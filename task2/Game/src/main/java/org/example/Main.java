package org.example;

import org.example.controllers.RootController;
import org.example.view.RootViewComponent;

public class Main {
    public static void main(String[] args) {
        RootController RootController = new RootController();
        RootViewComponent ViewComponent = new RootViewComponent();
        RootController.register(ViewComponent);
        ViewComponent.register(RootController);
        RootController.Start();
    }


}