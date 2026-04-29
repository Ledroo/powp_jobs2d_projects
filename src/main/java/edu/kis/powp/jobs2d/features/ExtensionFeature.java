package edu.kis.powp.jobs2d.features;

import edu.kis.powp.appbase.Application;
import edu.kis.powp.jobs2d.drivers.DriverManager;
import edu.kis.powp.jobs2d.drivers.visitor.VisitableDriver;


public class ExtensionFeature implements IFeature {

    private static Application app;
    private static DriverManager driverManager;

    @Override
    public void setup(Application application) {
        app = application;
        driverManager = DriverFeature.getDriverManager();
        app.addComponentMenu(ExtensionFeature.class, "Extensions");
    }

    @Override
    public String getName() {
        return "Extensions";
    }

    public static void addExtension(String name, VisitableDriver extension) {
        ExtensionToggleListener listener = new ExtensionToggleListener(driverManager, extension);
        app.addComponentMenuElementWithCheckBox(ExtensionFeature.class, name, listener, listener.isEnabled());
    }
}
