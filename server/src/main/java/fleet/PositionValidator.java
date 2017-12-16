package fleet;

class PositionValidator {
    private static final int MIN_POSITION_INDEX = 0;
    private static final int MAX_POSITION_INDEX = 99;
    private final CustomFleet fleet;

    public PositionValidator(CustomFleet fleet) {
        this.fleet = fleet;
    }

    boolean isValid(int position) {
        if(position < MIN_POSITION_INDEX || position > MAX_POSITION_INDEX){
            return false;
        } else if(fleet.getFleetPositions().contains(position)){
            return false;
        }

        return true;
    }
}
