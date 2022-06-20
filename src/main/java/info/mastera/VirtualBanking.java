package info.mastera;

import info.mastera.console.IConsoleController;
import info.mastera.dependencyInjection.DependencyInjection;

public class VirtualBanking {

    public static void main(String[] args) {
        DependencyInjection.getInstance().getSingletonObject(IConsoleController.class).run();
    }
}
