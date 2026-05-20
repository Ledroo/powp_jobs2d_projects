package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.logger.TrackingLoggerDriver;
import edu.kis.powp.jobs2d.drivers.packet_composite.CompositeDriver;
import edu.kis.powp.jobs2d.drivers.visitor.VisitableDriver;
import edu.kis.powp.observer.Publisher;

import java.util.ArrayList;
import java.util.List;

/**
 * Driver manager provides means to setup the driver. It also enables other
 * components and features of the application to react on configuration changes.
 */
public class DriverManager {

    private VisitableDriver coreDriver = new TrackingLoggerDriver();
    private CompositeDriver extensionsComposite = new CompositeDriver("Extensions");
    private Publisher changePublisher = new Publisher();

    public synchronized void setCurrentDriver(VisitableDriver driver) {
        coreDriver = driver;
        changePublisher.notifyObservers();
    }

    public synchronized void addExtension(VisitableDriver extension) {
        extensionsComposite.addDriver(extension);
        changePublisher.notifyObservers();
    }

    public synchronized void removeExtension(VisitableDriver extension) {
        extensionsComposite.removeDriver(extension);
        changePublisher.notifyObservers();
    }

    public synchronized VisitableDriver getCurrentDriver() {
        if (extensionsComposite.getDriverCount() == 0) {
            return coreDriver;
        }

        List<DriverDecorator> decorators = new ArrayList<>();
        List<VisitableDriver> parallel = new ArrayList<>();

        for (VisitableDriver extension : extensionsComposite.getDrivers()) {
            if (extension instanceof DriverDecorator) {
                decorators.add((DriverDecorator) extension);
            } else {
                parallel.add(extension);
            }
        }
        VisitableDriver chain = coreDriver;
        for (DriverDecorator decorator : decorators) {
            decorator.setInnerDriver(chain);
            chain = decorator;
        }
        if (parallel.isEmpty()) {
            return chain;
        }
        CompositeDriver activeDriver = new CompositeDriver(coreDriver.toString());
        activeDriver.addDriver(chain);
        for (VisitableDriver ext : parallel) {
            activeDriver.addDriver(ext);
        }
        return activeDriver;
    }

    public synchronized VisitableDriver getCoreDriver() {
        return coreDriver;
    }

    /**
     * @return changePublisher.
     */
    public Publisher getChangePublisher() {
        return changePublisher;
    }
}
