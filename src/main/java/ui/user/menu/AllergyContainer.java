package ui.user.menu;

import ui.user.UserUi;
import product.Allergy;
import ui.user.menu.controller.AllergyController;

import javax.swing.*;
import java.awt.*;

public class AllergyContainer extends JPanel {
    private final AllergyController allergyController;

    public AllergyContainer(MenuTab menuTab) {
        this.allergyController = menuTab.allergyController;
        this.setBackground(UserUi.MAIN_COLOR);
        this.setPreferredSize(new Dimension(Integer.MIN_VALUE, 32));
        this.add(getAllergyTitle());
        for (String allergy : Allergy.listOfAllergies) {
            this.add(getAllergyCheckBox(allergy));
        }
    }

    public JLabel getAllergyTitle() {
        JLabel allergyTitle = new JLabel("FILTER FOOD ALLERGY");
        allergyTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 10));
        return allergyTitle;
    }

    public JCheckBox getAllergyCheckBox(String allergy) {
        JCheckBox allergyCheckBox = new JCheckBox(allergy);
        allergyCheckBox.setFocusable(false);
        allergyCheckBox.setContentAreaFilled(false);
        allergyCheckBox.setFont(new Font(UserUi.FONT, Font.BOLD, 12));
        allergyCheckBox.setBackground(UserUi.MAIN_COLOR);
        allergyCheckBox.addItemListener(_ -> allergyController.updateAllergy(allergyCheckBox, allergy));
        return allergyCheckBox;
    }
}
