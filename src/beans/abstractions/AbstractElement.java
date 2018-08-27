package beans.abstractions;

import beans.interfaces.I_Element;

public abstract class AbstractElement implements I_Element {
    @Override
    public Enum<?> getElement(String s) {
        return null;
    }

    @Override
    public boolean found(String s) {
        return false;
    }
}
