package src;

import javax.swing.*;

import java.awt.*;

public class Button extends JButton {
    
    public static Resource Resource(){
        return Resource.instance();
    }

    private Color pressedColor = Resource().general_button_pressed_color;
    private Color hoverColor = Resource().general_button_hover_color;

    private ICommand<?> onPressed;
    private ICommand<?> onHover;

    public Button(String name){
        super(name);
        super.setContentAreaFilled(false);
        
    }

    @Override
    public void paintComponent(Graphics g){

        if (getModel().isPressed()){
            g.setColor(pressedColor);
            onPressed();
        }
        else if (getModel().isRollover()){
            g.setColor(hoverColor);
            onHover();
        }
        else{
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);

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
