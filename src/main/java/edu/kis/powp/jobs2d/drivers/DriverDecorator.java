package edu.kis.powp.jobs2d.drivers;

import edu.kis.powp.jobs2d.drivers.visitor.VisitableDriver;

/**
 * Abstract base class for driver decorators.
 * Provides common innerDriver field with getter and setter
 * to avoid code duplication in all implementations.
 */
public abstract class DriverDecorator implements VisitableDriver {

    protected VisitableDriver innerDriver;

    protected DriverDecorator(VisitableDriver innerDriver) {
        this.innerDriver = innerDriver;
    }

    public VisitableDriver getInnerDriver() {
        return innerDriver;
    }

    public void setInnerDriver(VisitableDriver driver) {
        this.innerDriver = driver;
    }
}
