package org.camel.utilslibrary.interfaces;

public interface Action<R, P> {

    R call(P p);
}