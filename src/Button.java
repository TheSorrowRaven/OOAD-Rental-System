package src;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;

public class Button extends JButton implements TableCellRenderer {
    
    public static Resource Resource(){
        return Resource.instance();
    }

    private Color pressedColor = Resource().general_button_pressed_color;
    private Color hoverColor = Resource().general_button_hover_color;

    private ICommand<?> onPressed;
    private ICommand<?> onHover;

    private String name;

    public Button(String name){
        super(name);
        super.setContentAreaFilled(false);
        this.name = name;
        
    }
    public Button(){
        super();
        super.setContentAreaFilled(false);
    }

    @Override
    public void paintComponent(Graphics g){
        ButtonModel model = getModel();
        if (model.isPressed()){
            g.setColor(pressedColor);
            onPressed();
        }
        else if (model.isRollover()){
            g.setColor(hoverColor);
            onHover();
        }
        else{
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());


        super.paintComponent(g);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(name);
        return this;
    }

    public void setPressedColor(Color pressedColor){
        this.pressedColor = pressedColor;
    }
    public void setHoverColor(Color hoverColor){
        this.hoverColor = hoverColor;
    }

    public void setOnPressed(ICommand<?> onPressed){
        this.onPressed = onPressed;
    }
    public void setOnHover(ICommand<?> onHover){
        this.onHover = onHover;
    }

    private void onPressed(){
        if (onPressed == null){
            return;
        }
        onPressed.execute(null);
    }
    private void onHover(){
        if (onHover == null){
            return;
        }
        onHover.execute(null);
    }

    /**
     * Disallow
     */
    @Override
    public void setContentAreaFilled(boolean f){}

}
