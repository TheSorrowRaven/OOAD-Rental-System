package src;

import javax.swing.*;

import src.SystemComponents.CLI;

import java.awt.*;
import java.awt.image.*;

public class Button extends JButton {
    
    public static Resource Resource(){
        return Resource.instance();
    }

    private Color pressedColor = Resource().general_button_pressed_color;
    private Color hoverColor = Resource().general_button_hover_color;

    private ICommand<?> onPressed;
    private ICommand<?> onHover;

    private BufferedImage image;

    public Button(String name){
        super(name);
        super.setContentAreaFilled(false);
        
    }
    public Button(){
        super();
        super.setContentAreaFilled(false);
    }



    public void setBufferedImage(BufferedImage image){
        this.image = image;
        repaint();
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
        // Graphics ig = getImageGraphics();
        // if (ig != null){
        //     CLI.log("Painting ");
        //     super.paintComponent(ig);
        // }
        // else{
        // }


        super.paintComponent(g);
        //tryPaintWithImage(model, g);
    }

    private Graphics getImageGraphics(){
        if (image == null){
            return null;
        }
        return image.getGraphics();
    }
    private void tryPaintWithImage(ButtonModel model, Graphics g){
        //g.drawImage(image, 0, 0);
        if (image == null){
            return;
        }
        paint(image.createGraphics());
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
