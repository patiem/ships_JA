package gui.act;

import events.YouHitEvent;
import javafx.scene.control.TextField;

public class HitInstruction implements Instruction {

    @Override
    public void perform(TextField dispatcher) {
        dispatcher.fireEvent(new YouHitEvent());
    }
}
