package beans.abstractions;

import beans.interfaces.IElement;

public abstract class AbstractElement implements IElement {
    @Override
    public Enum<?> getElement(String s) {
        return null;
    }

    @Override
    public boolean found(String s) {
        return false;
    }
}
