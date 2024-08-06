package com.swenauk.mainmenu.Classes;

import java.util.Comparator;

/* loaded from: classes3.dex */
public class StreamComparator implements Comparator<StreamClass> {
    @Override // java.util.Comparator
    public int compare(StreamClass streamClass, StreamClass streamClass2) {
        return streamClass.getProvider().compareTo(streamClass2.getProvider());
    }
}
