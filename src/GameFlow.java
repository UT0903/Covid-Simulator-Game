import javax.swing.*;

import frame.WindowFrame;
import panels.*;

import java.awt.*;

public class GameFlow {
    private DetailPanel detailPanel;
    private ToolbarPanel toolbarPanel;
    private MapPanel mapPanel;
    private MessagePanel messagePanel;
    private InfoPanel infoPanel;
    private WindowFrame windowFrame;
    private JPanel createPanel(String text){
        JTextArea t = new JTextArea(10, 10);
        t.setText(text);
        JPanel p = new JPanel();
        p.add(t);
        return p;
    }
    private JSplitPane makeSpiltPane(int orientation, Component a, Component b, double ratio){
        JSplitPane sp = new JSplitPane(orientation, a, b);
        sp.setEnabled(false);
        sp.setResizeWeight(ratio);
        sp.setDividerSize(2);
        return sp;
    }
    public GameFlow() {
        messagePanel = new MessagePanel(65);
        mapPanel = new MapPanel();
        detailPanel = new DetailPanel();
        toolbarPanel = new ToolbarPanel();
        infoPanel = new InfoPanel(400, 12, 31, 23, 59, 57);
        JSplitPane lsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, messagePanel, mapPanel, 0.0625);
        JSplitPane rbsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, toolbarPanel, detailPanel, 0.5);
        JSplitPane rsp = makeSpiltPane(JSplitPane.VERTICAL_SPLIT, infoPanel, rbsp, 0.2);
        JSplitPane sl = makeSpiltPane(JSplitPane.HORIZONTAL_SPLIT, lsp, rsp , 0);
        windowFrame = new WindowFrame();
        windowFrame.add(sl);

    }


    public void start() {
        showMessage();
        showTime();
    }

    private void showMessage() {
        messagePanel.addString("Test message 1!");
        messagePanel.addString("Test message 2?");
        messagePanel.addString("This is test message 3.");
        messagePanel.start();
    }
    private void showTime() {
        infoPanel.start();
    }
}