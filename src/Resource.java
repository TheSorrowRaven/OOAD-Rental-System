package src;

import java.util.UUID;
import java.awt.*;
import javax.swing.plaf.*;

import src.SystemComponents.CLI;

import javax.swing.*;
import java.util.*;
import javax.swing.border.*;
import javax.imageio.*;
import java.io.*;

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
    private Resource(){
        setDefaultUIFont(general_font_resource);
    }

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

    private static void setDefaultUIFont(FontUIResource f){
        Enumeration<?> keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource){
                UIManager.put(key, f);
            }
        }
    }




    public static class Theme{

        public Resource Resource(){
            return Resource.instance();
        }

        public Color window_background_color;
        public Color panel_background_color;
        public Color text_color;
        public Color caret_color;
        public Color background_color;
        public Color button_hover_color;
        public Color button_pressed_color;
        public Color bar_color;

        public Color sub_background_color;
        public Color sub_text_color;

        public Color border_color;
        public CompoundBorder border;

        public Theme border(Color color, int width, EmptyBorder emptyBorder){
            border_color = color;
            LineBorder line_border = new LineBorder(color, width, true);
            border = new CompoundBorder(line_border, emptyBorder);
            return this;
            //border = new CompoundBorder(line_border, emptyBorder);
        }
        public Theme windowBackground(Color color){
            window_background_color = color;
            return this;
        }
        public Theme panelBackgroundColor(Color color){
            panel_background_color = color;
            return this;
        }
        public Theme textColor(Color color){
            text_color = color;
            return this;
        }
        public Theme caretColor(Color color){
            caret_color = color;
            return this;
        }
        public Theme background(Color color){
            background_color = color;
            return this;
        }
        public Theme buttonHover(Color color){
            button_hover_color = color;
            return this;
        }
        public Theme buttonPressed(Color color){
            button_pressed_color = color;
            return this;
        }
        public Theme barColor(Color color){
            bar_color = color;
            return this;
        }
        public Theme subBackground(Color color){
            sub_background_color = color;
            return this;
        }
        public Theme subTextColor(Color color){
            sub_text_color = color;
            return this;
        }

    }





    public final char splittingInputCharacter = '\u2000';
    public final char newLineReplacementCharacter = '\u2001';
    public final char nullPlacementCharacter = '\u2002';
    public final char arraySplittingCharacter = '\u2003';
    public final String splittingInputCharacterStr(){
        return Character.toString(splittingInputCharacter);
    }
    public final String arraySplittingCharacterStr(){
        return Character.toString(arraySplittingCharacter);
    }

    public final String defaultAdminUUID = "00000000-0000-0000-0000-000000000000";
    public final String defaultAdminUsername = "admin";
    public final String defaultAdminPassword = "password";
    public final String defaultAdminName = "Admin";
    public final String defaultAdminEntry = defaultAdminUUID + 
        splittingInputCharacter + defaultAdminUsername + 
        splittingInputCharacter + defaultAdminPassword + 
        splittingInputCharacter + defaultAdminName;

    public final String savePath = "save/";
    public final String tenantLoginFile = "tenantLogin.txt";
    public final String ownerAgentLoginFile = "ownerAgentLogin.txt";
    public final String adminLoginFile = "adminLogin.txt";
    public final String propertyFile = "properties.txt";

    //IMAGES
    public final String imgPath = "img/";
    public final String logout_icon_img = imgPath + "logout_icon.png";
    public final int icon_width = 25;
    public final int icon_height = 25;

    private Image getImgFrom(String path){
        File file = new File(path);
        try{
            return ImageIO.read(file).getScaledInstance(icon_width, icon_height, Image.SCALE_SMOOTH);
        }
        catch (IOException ex){
            CLI.log("Invalid image path - " + path);
            ex.printStackTrace();
        }
        return null;
    }
    public Image getLogoutIcon(){
        return getImgFrom(logout_icon_img);
    }

    //WINDOW
    public final int menu_window_width = 900;
    public final int menu_window_height = 600;

    public final int login_window_width = 900;
    public final int login_window_height = 600;

    //FONT
    public final int general_font_size = 16;
    public final int general_font_highlight_size = 20;
    public final int general_font_title_size = 24;
    public final int general_font_minor_size = 14;
    public final Font general_font = new Font(Font.SERIF, Font.PLAIN, general_font_size);
    public final Font general_font_highlight = new Font(Font.SERIF, Font.PLAIN, general_font_highlight_size);
    public final Font general_font_title = new Font(Font.SERIF, Font.PLAIN, general_font_title_size);
    public final Font general_font_minor = new Font(Font.SERIF, Font.PLAIN, general_font_minor_size);
    public final FontUIResource general_font_resource = new FontUIResource("Serif", Font.PLAIN, general_font_size);

    //SCROLL PANE
    public final int scroll_speed = 16;

    //TABLE
    public final int table_cell_height = 24;

    //COLOR
    public final Color color_invisible = new Color(0, 0, 0, 0);
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
    public final Color color_black_sub_0 = Color.decode("#f5f5b9");
    public final Color color_black_sub_1 = Color.decode("#8d8d14");
    //Grey Palette - for smoothening colors
    public final Color color_bw_mid = Color.decode("#808080");
    public final Color color_bw_mid_light = Color.decode("#999999");
    public final Color color_bw_mid_dark = Color.decode("#666666");
    //White Palette - higher the value, darker the white color
    public final Color color_white_0 = Color.decode("#eeeeee");
    public final Color color_white_1 = Color.decode("#e2e2e2");
    public final Color color_white_2 = Color.decode("#d8d8d8");
    public final Color color_white_3 = Color.decode("#c9c9c9");
    //Dark Red Palette - higher the value, lighter the red color
    public final Color color_red_dark_0 = Color.decode("#b71c1c");
    public final Color color_red_dark_1 = Color.decode("#d32f2f");
    public final Color color_red_dark_2 = Color.decode("#f44336");

    //Themes
    //Purple Theme - light to dark
    public final Color color_theme_purple_0 = Color.decode("#f7e7f8");
    public final Color color_theme_purple_1 = Color.decode("#dfbae2");
    public final Color color_theme_purple_2 = Color.decode("#C996CC");
    public final Color color_theme_purple_3 = Color.decode("#916BBF");
    public final Color color_theme_purple_4 = Color.decode("#3D2C8D");
    public final Color color_theme_purple_5 = Color.decode("#120933");
    public final Color color_theme_purple_bar = color_black_0;
    public final Color color_theme_purple_sub_0 = Color.decode("#f5d0e9");
    public final Color color_theme_purple_sub_1 = Color.decode("#a11373");
    //Red Theme - light to dark
    public final Color color_theme_red_0 = Color.decode("#f3cfd9");
    public final Color color_theme_red_1 = Color.decode("#e88ca6");
    public final Color color_theme_red_2 = Color.decode("#E63E6D");
    public final Color color_theme_red_3 = Color.decode("#B42B51");
    public final Color color_theme_red_4 = Color.decode("#7D1935");
    public final Color color_theme_red_5 = Color.decode("#300b15");
    public final Color color_theme_red_bar = color_black_0;
    public final Color color_theme_red_sub_0 = Color.decode("#ffddc6");
    public final Color color_theme_red_sub_1 = Color.decode("#c75b14");
    //Violeto Theme - light to dark
    public final Color color_theme_violeto_0 = Color.decode("#e2e9f6");
    public final Color color_theme_violeto_1 = Color.decode("#a6b6d4");
    public final Color color_theme_violeto_2 = Color.decode("#6E85B2");
    public final Color color_theme_violeto_3 = Color.decode("#5C527F");
    public final Color color_theme_violeto_4 = Color.decode("#3E2C41");
    public final Color color_theme_violeto_5 = Color.decode("#1b1320");
    public final Color color_theme_violeto_bar = color_black_0;
    public final Color color_theme_violeto_sub_0 = Color.decode("#beeef3");
    public final Color color_theme_violeto_sub_1 = Color.decode("#328c95");

    //Main Colors
    public final Color window_background_color = color_black_0;
    public final Color panel_background_color = color_black_1;
    public final Color general_text_color = color_white_1;
    public final Color general_caret_color = color_white_3;
    public final Color general_background_color = color_black_9;
    public final Color general_border_color = color_bw_mid_dark;
    public final Color general_button_hover_color = color_bw_mid_dark;
    public final Color general_button_pressed_color = color_black_3;
    public final Color general_error_text_color = color_white_0;
    public final Color general_error_background_color = color_red_dark_0;
    public final Color general_bar_color = color_black_0;
    public final Color general_sub_background_color = color_black_sub_1;
    public final Color general_sub_text_color = color_black_sub_0;



    //BORDER
    public final int general_border_inset_value = 6;
    public final int general_border_width = 1;
    public final EmptyBorder general_border_empty = new EmptyBorder(general_border_inset_value, general_border_inset_value, general_border_inset_value, general_border_inset_value);
    public final LineBorder general_border_line = new LineBorder(general_border_color, general_border_width, true);
    public final CompoundBorder general_border_compound = new CompoundBorder(general_border_line, general_border_empty);


    //THEME
    public final Theme black_theme = new Theme().windowBackground(window_background_color)
        .panelBackgroundColor(panel_background_color)
        .textColor(general_text_color)
        .caretColor(general_caret_color)
        .background(general_background_color)
        .border(general_border_color, general_border_width, general_border_empty)
        .buttonHover(general_button_hover_color)
        .buttonPressed(general_button_pressed_color)
        .barColor(general_bar_color)
        .subBackground(general_sub_background_color)
        .subTextColor(general_sub_text_color);
    public final Theme purple_theme = new Theme().windowBackground(window_background_color)
        .panelBackgroundColor(color_theme_purple_5)
        .textColor(color_theme_purple_0)
        .caretColor(color_theme_purple_1)
        .background(color_theme_purple_4)
        .border(color_theme_purple_2, general_border_width, general_border_empty)
        .buttonHover(color_theme_purple_3)
        .buttonPressed(color_theme_purple_4)
        .barColor(color_theme_purple_bar)
        .subBackground(color_theme_purple_sub_1)
        .subTextColor(color_theme_purple_sub_0);
    public final Theme red_theme = new Theme().windowBackground(window_background_color)
        .panelBackgroundColor(color_theme_red_5)
        .textColor(color_theme_red_0)
        .caretColor(color_theme_red_1)
        .background(color_theme_red_4)
        .border(color_theme_red_2, general_border_width, general_border_empty)
        .buttonHover(color_theme_red_3)
        .buttonPressed(color_theme_red_4)
        .barColor(color_theme_red_bar)
        .subBackground(color_theme_red_sub_1)
        .subTextColor(color_theme_red_sub_0);
    public final Theme violeto_theme = new Theme().windowBackground(window_background_color)
        .panelBackgroundColor(color_theme_violeto_5)
        .textColor(color_theme_violeto_0)
        .caretColor(color_theme_violeto_1)
        .background(color_theme_violeto_4)
        .border(color_theme_violeto_2, general_border_width, general_border_empty)
        .buttonHover(color_theme_violeto_3)
        .buttonPressed(color_theme_violeto_4)
        .barColor(color_theme_violeto_bar)
        .subBackground(color_theme_violeto_sub_1)
        .subTextColor(color_theme_violeto_sub_0);

    public final Theme login_theme = black_theme;
    public final Theme admin_theme = red_theme;
    public final Theme tenant_theme = violeto_theme;
    public final Theme ownerAgent_theme = purple_theme;

    

    //LAYOUT
    public final int general_inset_value = 5;
    public final int general_inset_tiny_value = 2;
    public final int general_inset_medium_value = 10;
    public final int general_inset_major_value = 50;
    public final int general_inset_wide_value = 40;
    public final Insets general_inset_bottom = new Insets(0, 0, general_inset_value, 0);
    public final Insets general_inset_all = new Insets(general_inset_value, general_inset_value, general_inset_value, general_inset_value);
    public final Insets general_inset_bottom_major_spacing = new Insets(0, 0, general_inset_major_value, 0);
    public final Insets general_inset_wide = new Insets(general_inset_value, general_inset_wide_value, general_inset_value, general_inset_wide_value);
    public final Insets general_inset_medium = new Insets(general_inset_medium_value, general_inset_medium_value, general_inset_medium_value, general_inset_medium_value);
    public final Insets general_inset_tiny = new Insets(general_inset_tiny_value, general_inset_tiny_value, general_inset_tiny_value, general_inset_tiny_value);

    //STRING
    public final String str_user = "User";
    public final String str_username = "Username";
    public final String str_password = "Password";
    public final String str_name = "Name";
    public final String str_address = "Address";
    public final String str_active = "Status";
    public final String str_type = "Type";
    public final String str_rent = "Rent";
    public final String str_facilities = "Facilities";
    public final String str_rooms = "Rooms";
    public final String str_size = "Size";
    public final String str_bathrooms = "Bathrooms";
    public final String str_owner = "Owner";
    public final String str_agent = "Agent";
    public final String str_unassigned_ownerAgent = ""; //show empty
    public final String str_filter = "Filter Facilities";


    public final String login_window_title = "Login";
    public final String login_str_button_LoginAsTenant = "  Login As Tenant  ";
    public final String login_str_button_LoginAsOwnerAgent = "  Login As Owner/Agent  ";
    public final String login_str_button_LoginAsAdmin = "  Login As Admin  ";
    public final String login_str_prompt_Username = "Username:  ";
    public final String login_str_prompt_Password = "Password:  ";
    public final String login_str_error_text = "Invalid Credentials";

    public final String bar_back_logout = "Logout";

    public final String admin_window_title = "Admin";
    public final String admin_str_submenu_ViewAllUsers = "Users";
    public final String admin_str_submenu_ViewTenants = "Tenants";
    public final String admin_str_submenu_ViewOwners = "Owners";
    public final String admin_str_submenu_ViewAgents = "Agents";
    public final String admin_str_submenu_ViewAdmins = "Admins";
    public final String admin_str_content_title_AllUsers = "Users";
    public final String admin_str_content_title_Tenants = "Tenants";
    public final String admin_str_content_title_Owners = "Owners";
    public final String admin_str_content_title_Agents = "Agents";
    public final String admin_str_content_title_Admins = "Admins";
    public final String admin_str_content_deletion_table_header = "Delete";
    public final String admin_str_content_deletion_title = "User List & Deletion";
    public final String admin_str_content_deletion_instructions = "To delete, tick the checkboxes then click delete";
    public final String admin_str_content_deletion_button = "  Delete Selected  ";
    public final String admin_str_content_create_title = "User Creation";
    public final String admin_str_content_create_instructions = "Note: Username must be unique";
    public final String admin_str_content_create_acc_button = "  Create Account  ";
    public final String admin_str_content_create_username = "  Username:  ";
    public final String admin_str_content_create_password = "  Password:  ";
    public final String admin_str_content_create_name = "  Name:  ";
    public final String admin_str_content_create_success = "Successful";
    public final String admin_str_content_create_fail = "Failure!";

    public final String tenant_window_title = "Tenant";

    public final String ownerAgent_window_title = "Owner/Agent";
    public final String ownerAgent_str_edit_active = "Is Active:  ";
    public final String ownerAgent_str_edit_name = "Name:  ";
    public final String ownerAgent_str_edit_address = "Address:  ";
    public final String ownerAgent_str_edit_type = "Type:  ";
    public final String ownerAgent_str_edit_size = "Size:  ";
    public final String ownerAgent_str_edit_rooms = "Rooms:  ";
    public final String ownerAgent_str_edit_bathrooms = "Bathrooms:  ";
    public final String ownerAgent_str_edit_rent = "Rent:  ";
    public final String ownerAgent_str_edit_facilities = "Facilities:  ";
    public final String ownerAgent_str_table_header_delete = "Delete";
    public final String ownerAgent_str_table_header_edit = "Edit";
    public final String ownerAgent_str_table_button_edit = "Edit";
    public final String ownerAgent_str_button_createEdit_create = "Create";
    public final String ownerAgent_str_button_createEdit_edit = "Apply Changes";
    public final String ownerAgent_str_button_restoreEdit = "Stop Editing";
    public final String ownerAgent_str_button_delete = "Delete Selected";

    public final String property_str_filter_instructions = "Filter facilities by ticking any facility. Untick all to show all";
    public final String property_str_sort_instructions = "Sort properties by clicking on the header. Click again on the same one to sort in reverse order";

}
