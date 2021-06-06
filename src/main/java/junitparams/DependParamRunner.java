package junitparams;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

import junitparams.internal.DependParameterisedTestClassRunner;

/**
 *
 * @author mohamed
 */
public class DependParamRunner extends JUnitParamsRunner {

    DependencyListener dependencyListener;

    public DependParamRunner(Class<?> klass) throws InitializationError {
        super(klass);
        super.setParameterisedRunner(new DependParameterisedTestClassRunner(getTestClass()));
    }

    @Override
    protected Statement childrenInvoker(final RunNotifier notifier) {
        // inject dependency listener
        dependencyListener = new DependencyListener(((DependParameterisedTestClassRunner) super.getParameterisedRunner()).getDependencyGraph());
        notifier.addListener(dependencyListener);
        return super.childrenInvoker(notifier);
    }
}
