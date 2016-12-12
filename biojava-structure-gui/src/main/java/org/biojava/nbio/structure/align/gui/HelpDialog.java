/*
 *                    BioJava development code
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  If you do not have a copy,
 * see:
 *
 *      http://www.gnu.org/copyleft/lesser.html
 *
 * Copyright for this code is held jointly by the individual
 * authors.  These should be listed in @author doc comments.
 *
 * For more information on the BioJava project and its aims,
 * or to join the biojava-l mailing list, visit the home page
 * at:
 *
 *      http://www.biojava.org/
 *
 * Created on Oct 6, 2009
 * Author: Andreas Prlic
 *
 */

package org.biojava.nbio.structure.align.gui;

import org.biojava.nbio.structure.align.util.ResourceManager;
import org.biojava.nbio.structure.align.webstart.BrowserOpener;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpDialog {
	Box vBox;

	public void showDialog(){

	      JDialog dialog = new JDialog();

	      dialog.setSize(new Dimension(500,600));

	      ResourceManager mgr = ResourceManager.getResourceManager("ce");

	      String msg = mgr.getString("ce.help");

	      JEditorPane txt = new JEditorPane("text/html", msg);
	      txt.setEditable(false);

	      JScrollPane scroll = new JScrollPane(txt);
	      scroll.setSize(new Dimension(300,500));
	      vBox= Box.createVerticalBox();
	      vBox.add(scroll);

	      txt.addHyperlinkListener(new HyperlinkListener(){

	         @Override
			public void hyperlinkUpdate(HyperlinkEvent e) {

	             if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
	                 String href = e.getDescription();
	                 BrowserOpener.showDocument(href);
	             }
	             if ( e.getEventType() == HyperlinkEvent.EventType.ENTERED) {
	                 // change the mouse curor
	                 vBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	             }
	             if (e.getEventType() == HyperlinkEvent.EventType.EXITED) {
	                 vBox.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	             }
	         }
	     });


	      JButton close = new JButton("Close");

	      close.addActionListener(new ActionListener(){
	         @Override
			public void actionPerformed(ActionEvent event) {
	            Object source = event.getSource();

	            JButton but = (JButton)source;
	            Container parent = but.getParent().getParent().getParent().getParent().getParent().getParent() ;

	            JDialog dia = (JDialog) parent;
	            dia.dispose();
	         }
	      });

	      Box hBoxb = Box.createHorizontalBox();
	      hBoxb.add(Box.createGlue());
	      hBoxb.add(close,BorderLayout.EAST);

	      vBox.add(hBoxb);

	      dialog.getContentPane().add(vBox);
	      dialog.setVisible(true);



	}
}
