package src;

import java.util.UUID;
import java.awt.*;
import javax.swing.plaf.*;
import javax.swing.border.*;

/**
 * 
 * This Resource class contains string data of file paths, colors etc
 * 
 */
public final class Resource {
    
    //Singleton DP - object
    private static Resource _instance;

    //Singleton - Singleton request
    public final static Resource instance(){
        if (_instance != null){
            return _instance;
        }
        _instance = new Resource();
        return _instance;
    }

    /**
     * Prevent creation of additional Resource objects besides the singleton itself
     */
    private Resource(){}

    /**
     * Generates a new UUID
     * @return UUID
     */
    public final UUID getUUID(){
        return UUID.randomUUID();
    }
    public final UUID getUUIDFrom(String uuidStr){
        return UUID.fromString(uuidStr);
    }

    public final char splittingInputCharacter = '￼';
    public final char newLineReplacementCharacter = '�';
    public final char splittingReplaceCharacter = ' ';
    public final String splittingInputCharacterStr(){
        return Character.toString(splittingInputCharacter);
    }

    public final String savePath = "save/";
    public final String tenantLoginFile = "tenantLogin.txt";
    public final String ownerAgentLoginFile = "ownerAgentLogin.txt";
    public final String adminLoginFile = "adminLogin.txt";
    public final String propertyFile = "properties.txt";

    //WINDOW
    public final int mainMenu_window_Width = 1440;
    public final int mainMenu_window_Height = 900;

    public final int login_window_Width = 900;
    public final int login_window_Height = 600;

    //FONT
    public final int general_font_size = 16;
    public final Font general_font = new Font(Font.SERIF, Font.PLAIN, general_font_size);
    public final FontUIResource general_font_resource = new FontUIResource("Serif", Font.PLAIN, general_font_size);

    //COLOR
    //Palettes
    //Black Palette - higher the value, lighter the black color
    public final Color color_black_0 = Color.decode("#121212");
    public final Color color_black_1 = Color.decode("#1e1e1e");
    public final Color color_black_2 = Color.decode("#232323");
    public final Color color_black_3 = Color.decode("#252525");
    public final Color color_black_4 = Color.decode("#272727");
    public final Color color_black_5 = Color.decode("#2c2c2c");
    public final Color color_black_6 = Color.decode("#2e2e2e");
    public final Color color_black_7 = Color.decode("#333333");
    public final Color color_black_8 = Color.decode("#363636");
    public final Color color_black_9 = Color.decode("#383838");
    //Grey Palette - for smoothening colors
    public final Color color_bw_mid = Color.decode("#808080");
    public final Color color_bw_mid_light = Color.decode("#999999");
    public final Color color_bw_mid_dark = Color.decode("#666666");
    //White Palette - higher the value, darker the white color
    public final Color color_white_0 = Color.decode("#eeeeee");
    public final Color color_white_1 = Color.decode("#e2e2e2");
    public final Color color_white_2 = Color.decode("#d8d8d8");
    public final Color color_white_3 = Color.decode("#c9c9c9");

    //Main Colors
    public final Color window_background_color = color_black_0;
    public final Color panel_default_background_color = color_black_1;
    public final Color general_text_color = color_white_1;
    public final Color general_caret_color = color_white_3;
    public final Color general_background_color = color_black_9;
    public final Color general_border_color = color_bw_mid_dark;
    public final Color general_button_hover_color = color_bw_mid_dark;
    public final Color general_button_pressed_color = color_black_3;

    //BORDER
    public final int general_border_inset_value = 6;
    public final int general_border_width = 1;
    public final EmptyBorder general_border_empty = new EmptyBorder(general_border_inset_value, general_border_inset_value, general_border_inset_value, general_border_inset_value);
    public final LineBorder general_border_line = new LineBorder(general_border_color, general_border_width, true);
    public final CompoundBorder general_border_compound = new CompoundBorder(general_border_line, general_border_empty);

    //LAYOUT
    public final int general_inset_value = 5;
    public final int general_inset_major_value = 50;
    public final Insets general_inset_bottom = new Insets(0, 0, general_inset_value, 0);
    public final Insets general_inset_all = new Insets(general_inset_value, general_inset_value, general_inset_value, general_inset_value);
    public final Insets general_inset_bottom_major_spacing = new Insets(0, 0, general_inset_major_value, 0);

    //STRING
    public final String login_window_title = "Login";
    public final String login_str_button_LoginAsTenant = "Login As Tenant";
    public final String login_str_button_LoginAsOwnerAgent = "Login As Owner/Agent";
    public final String login_str_button_LoginAsAdmin = "Login As Admin";
    public final String login_str_prompt_Username = "Username: ";
    public final String login_str_prompt_Password = "Password: ";




}
