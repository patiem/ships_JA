package model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Ship {

    private static final int START_SIZE = 1;

    private final Mast[] masts;
    private final int length;
    private int buildLength;


    public Ship(Mast mast) {
        length = mast.getShipLength();
        masts = new Mast[length];
        masts[0] = mast;
        buildLength = START_SIZE;
    }

    boolean isShipDone() {
        return buildLength == length;
    }

    void addMast(Mast mast) {
        if (!isShipDone()) masts[buildLength++] = mast;
    }

    public List<Position> possibleMastsPositions() {
        return Arrays.stream(masts).map(m -> m.position()).collect(Collectors.toList());
    }

    public Set<Position> calculateShipBoundariesPositions() {
        Set<Position> boundaries = new HashSet<>();
        for (Mast mast : masts) {
            boundaries.addAll(new BoundariesPosition(mast).countBoundariesForMast());
        }
        boundaries.removeAll(possibleMastsPositions());
        return boundaries;
    }

    public Mast[] getMasts() {
        return masts;
    }
}
