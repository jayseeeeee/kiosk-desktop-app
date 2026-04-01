package ui.user.menu;

import ui.user.UserUi;
import app.store.Allergy;

import javax.swing.*;
import java.awt.*;

public final class AllergyContainer extends JPanel {
    private final MenuTab menuTab;

    AllergyContainer(MenuTab menuTab) {
        this.menuTab = menuTab;
        this.setOpaque(false);
        this.add(getAllergyTitle());
        this.add(Box.createHorizontalStrut(32));
        for (String allergy : Allergy.listOfAllergies) {
            this.add(getAllergyCheckBox(allergy));
        }
    }

    private JLabel getAllergyTitle() {
        JLabel allergyTitle = new JLabel("FILTER FOOD ALLERGY");
        allergyTitle.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        return allergyTitle;
    }

    private JCheckBox getAllergyCheckBox(String allergy) {
        JCheckBox allergyCheckBox = new JCheckBox(allergy);
        allergyCheckBox.setFocusable(false);
        allergyCheckBox.setContentAreaFilled(false);
        allergyCheckBox.setFont(new Font(UserUi.FONT, Font.BOLD, 18));
        allergyCheckBox.setOpaque(false);
        allergyCheckBox.addItemListener(_ -> menuTab.updateAllergy(allergyCheckBox, allergy));
        return allergyCheckBox;
    }
}
