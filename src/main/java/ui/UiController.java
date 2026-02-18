package ui;

import ui.admin.AdminUi;
import ui.user.UserUi;

public class UiController {
    public final AdminUi adminUi = new AdminUi(this);
    public final UserUi userUi = new UserUi(this);
}
