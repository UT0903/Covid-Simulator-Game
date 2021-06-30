package panels.DetailPanelComponent.Component;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ChangeBtnAdapter extends MouseAdapter{
    @Override
    public void mouseEntered(MouseEvent e) {
        e.getComponent().setBackground(Color.darkGray);
        //StateManager.setGameState(S);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        e.getComponent().setBackground(Color.gray);
    }
}
