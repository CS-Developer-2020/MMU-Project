package com.hit.algorithm;

public interface IAlgoCache<K, V>
{
    V getElement(final K p0);
    
    V putElement(final K p0, final V p1);
    
    void removeElement(final K p0);
}