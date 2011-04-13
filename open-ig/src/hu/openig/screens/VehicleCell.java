/*
 * Copyright 2008-2011, David Karnok 
 * The file is part of the Open Imperium Galactica project.
 * 
 * The code should be distributed under the LGPL license.
 * See http://www.gnu.org/licenses/lgpl.html for details.
 */

package hu.openig.screens;

import hu.openig.core.Action1;
import hu.openig.model.ResearchType;
import hu.openig.render.TextRenderer;
import hu.openig.ui.UIComponent;
import hu.openig.ui.UIMouse;
import hu.openig.ui.UIMouse.Button;
import hu.openig.ui.UIMouse.Type;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 * The renderer for a concrete vehicle. 
 * @author akarnokd, 2011.04.12.
 */
public class VehicleCell extends UIComponent {
	/** The type. */
	public ResearchType type;
	/** The count. */
	public int count;
	/** Indicate a selection? */
	public boolean selected;
	/** Place the image top center (true) or right middle (false)? */
	public boolean topCenter;
	/** The action to invoke when the user selects this cell. */
	public Action1<ResearchType> onSelect;
	/** The common resources. */
	private final CommonResources commons;
	/**
	 * Constructor.
	 * @param commons the common resources
	 */
	public VehicleCell(CommonResources commons) {
		this.commons = commons;
	}
	@Override
	public void draw(Graphics2D g2) {
		if (type != null) {
			if (topCenter) {
				g2.drawImage(type.equipmentImage, (width - type.equipmentImage.getWidth()) / 2, 2, null);
			} else {
				g2.drawImage(type.equipmentImage, width - type.equipmentImage.getWidth() - 2, (height - type.equipmentImage.getHeight()) / 2, null);
			}
			
			int textHeight = 7;
			
			String n = Integer.toString(count);
			commons.text().paintTo(g2, 2, height - 2 - textHeight, textHeight, TextRenderer.GREEN, n);
			
			if (selected) {
				g2.setColor(Color.ORANGE);
				g2.drawRect(0, 0, width - 1, height - 1);
			}
		}
	}
	@Override
	public boolean mouse(UIMouse e) {
		if (e.has(Button.LEFT) && e.has(Type.DOWN) && type != null) {
			this.selected = true;
			if (onSelect != null) {
				onSelect.invoke(type);
			}
			return true;
		}
		return false;
	}
}
