package edu.wpi.cs.wpisuitetng.modules.RequirementManager.view;

import edu.wpi.cs.wpisuitetng.modules.RequirementManager.controller.GetRequirementsController;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.models.RequirementModel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.Requirements.NewRequirementPanel;
import edu.wpi.cs.wpisuitetng.modules.RequirementManager.view.overview.OverviewTable;

public class ViewEventController {
	private static ViewEventController instance = null;
	private MainView main = null;
	private ToolbarView tool = null;
	private OverviewTable overviewTable = null;

	public void setOverviewTable(OverviewTable overviewTable) {
		this.overviewTable = overviewTable;
	}

	/**
	 * Default constructor for ViewEventController.  Is protected to prevent instantiation.
	 */
	protected ViewEventController() {}

	/**
	 * Returns the singleton instance of the vieweventcontroller.
	 * @return The instance of this controller.
	 */
	public static ViewEventController getInstance() {
		if (instance == null) {
			instance = new ViewEventController();
		}
		return instance;
	}

	/**
	 * Sets the main view to the given view.
	 * @param main2 the main view to be set as active.
	 */
	public void setMainView(MainView mainview) {
		main = mainview;
	}

	/**
	 * 
	 * Sets the toolbarview to the given toolbar
	 * @param tool2 the toolbar to be set as active.
	 */
	public void setToolBar(ToolbarView toolbar) {
		tool = toolbar;
	}

	/**
	 * Opens a new tab for the creation of a requirement.
	 */
	public void createRequirement() {
		NewRequirementPanel newReq = new NewRequirementPanel(new RequirementModel());
		main.addTab("Create Requirement", newReq);
		main.invalidate(); //force the tabbedpane to redraw.
		main.repaint();
	}

	public void updateTable(Requirement[] requirements) {
		overviewTable.updateTable(requirements);
	}
}