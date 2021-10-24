package src;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;

/**
 * Custom button to fit the coloring/theme of the progarm
 */
public class Button extends JButton implements TableCellRenderer {
    
    /**
     * Shorthand for Resource singleton
     * @return
     */
    public static Resource Resource(){
        return Resource.instance();
    }

    private Color pressedColor = Resource().general_button_pressed_color;
    private Color hoverColor = Resource().general_button_hover_color;

    private ICommand<?> onPressed;
    private ICommand<?> onHover;

    private String name;

    /**
     * Constructor accepting a string name to display
     * @param name
     */
    public Button(String name){
        super(name);
        super.setContentAreaFilled(false);
        this.name = name;
        
    }
    /**
     * Constructor
     */
    public Button(){
        super();
        super.setContentAreaFilled(false);
    }

    /**
     * Paints the component by catching its on hover, clicked states of the button to set its color
     */
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

    /**
     * (Not Used)
     * To put the button in a table cell, it overrides to set it text and returning itself
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(name);
        return this;
    }

    /**
     * Sets the on press color
     * @param pressedColor
     */
    public void setPressedColor(Color pressedColor){
        this.pressedColor = pressedColor;
    }
    /**
     * Sets the on hover color
     * @param hoverColor
     */
    public void setHoverColor(Color hoverColor){
        this.hoverColor = hoverColor;
    }

    /**
     * Sets the on pressed action command
     * @param onPressed
     */
    public void setOnPressed(ICommand<?> onPressed){
        this.onPressed = onPressed;
    }
    /**
     * Sets the on hover action command
     */
    public void setOnHover(ICommand<?> onHover){
        this.onHover = onHover;
    }

    /**
     * Executes (if any of) the on pressed action command
     */
    private void onPressed(){
        if (onPressed == null){
            return;
        }
        onPressed.execute(null);
    }
    /**
     * Executes (if any of) the on hover command
     */
    private void onHover(){
        if (onHover == null){
            return;
        }
        onHover.execute(null);
    }

    /**
     * Disallows this setting. (requires to display the button properly following the theme)
     */
    @Override
    public void setContentAreaFilled(boolean f){}

}
