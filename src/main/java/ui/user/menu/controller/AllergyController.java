package ui.user.menu.controller;

import product.Allergy;
import ui.user.menu.MenuTab;

import javax.swing.*;
import java.util.HashMap;

public class AllergyController {
    public final HashMap<String, JCheckBox> allergyFilterResult = new HashMap<>();
    private final MenuController menuController;

    public AllergyController(MenuTab menuTab) {
        this.menuController = menuTab.menuController;
    }

    public void updateAllergy(JCheckBox checkBox, String allergy) {
        if (checkBox.isSelected()) {
            allergyFilterResult.put(allergy, checkBox);
        } else {
            allergyFilterResult.remove(allergy);
        }
        menuController.updateMenuContainer();
    }

    public boolean isAllergyMatch(Allergy allergy) {
        for (String allergyFilter : allergyFilterResult.keySet()) {
            if (allergy == null) {
                break;
            } else if (allergy.productAllergies.contains(allergyFilter)) {
                return true;
            }
        }
        return false;
    }
}
