package CustMgtSys_1;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.*;


public class PMAview extends JFrame{
	
	public JLabel nameLabel, dateLabel, tagsLabel, vehicleYearLabel, vehicleMakeLabel, vehicleModelLabel, workOrderLabel, licLabel, vinLabel, engineLabel, transLabel, milesLabel;
	public JTextField nameField, dateField, tagsField, vehicleYearField, vehicleMakeField, vehicleModelField, workOrderField, licField, vinField, engineField, transField, milesField;
	public JPanel northPanel, centerPanel;
	
	
	public JCheckBox[][] checkBoxes;
	
	public JCheckBox DSFTOK,DSFTNOTOK,PSFTOK,PSFTNOTOK, PSRTOK,PSRTNOTOK, DSRTOK,DSRTNOTOK, spareOK,spareNOTOK, WWOK,WWNOTOK,locksOK,locksNOTOK, ACHOK,ACHNOTOK,heaterOK,heaterNOTOK,
						wiperOK,wiperNOTOK,hornOK,hornNOTOK,HLOK,HLNOTOK,PTLOK,PTLNOTOK,BLOK,BLNOTOK,DSOK,DSNOTOK,engineDiagOK, engineDiagNOTOK, MMOK,MMNOTOK,BCBOK,BCBNOTOK,
						EOOK,EONOTOK,TFOK,TFNOTOK,WFOK,WFNOTOK,BFOK,BFNOTOK, PSFOK,PSFNOTOK, coolantOK,coolantNOTOK, SBOK,SBNOTOK,AFOK,AFNOTOK, FFOK,FFNOTOK,radiatorOK,radiatorNOTOK,
						URHOK, URHNOTOK,LRHOK,LRHNOTOK,HBHOK,HBHNOTOK, SRPOK,SRPNOTOK, SLOK,SLNOTOK,suspOK,suspNOTOK,alignOK,alignNOTOK,FSSOK,FSSNOTOK,RSSOK,RSSNOTOK,FBOK,FBNOTOK,
						RBOK,RBNOTOK,CVADOK,CVADNOTOK,mufflerOK,mufflerNOTOK,EPOK,EPNOTOK,other1OK,other1NOTOK,other2OK,other2NOTOK,other3OK,other3NOTOK,other4OK,other4NOTOK,other5OK,
						other5NOTOK,other6OK,other6NOTOK,other7OK,other7NOTOK,other8OK,other8NOTOK;
	
	
	public JComboBox[][] comboBoxes;
	
	public JComboBox DSFTtechCommentBox,DSFTrecommendedCommentBox,DSFTpriorityBox,PSFTtechCommentBox,PSFTrecommendedCommentBox,PSFTpriorityBox,
	PSRTtechCommentBox,PSRTrecommendedCommentBox,PSRTpriorityBox,DSRTtechCommentBox,DSRTrecommendedCommentBox,DSRTpriorityBox,spareTechCommentBox,spareRecommendedCommentBox,sparePriorityBox,
	WWTechCommentBox,WWRecommendedCommentBox,WWPriorityBox,locksTechCommentBox,locksRecommendedCommentBox,locksPriorityBox,ACHTechCommentBox,ACHRecommendedCommentBox,ACHPriorityBox,
	heaterTechCommentBox,heaterRecommendedCommentBox,heaterPriorityBox,wiperTechCommentBox,wiperRecommendedCommentBox,wiperPriorityBox,hornTechCommentBox,hornRecommendedCommentBox,hornPriorityBox,
	HLTechCommentBox,HLRecommendedCommentBox,HLPriorityBox,PTLTechCommentBox,PTLRecommendedCommentBox,PTLPriorityBox,brakeTechCommentBox,brakeRecommendedCommentBox,brakePriorityBox,
	DSTechCommentBox,DSRecommendedCommentBox,DSPriorityBox,engineDiagTechCommentBox,engineDiagRecommendedCommentBox,engineDiagPriorityBox,MMTechCommentBox,MMRecommendedCommentBox,MMPriorityBox,
	BCBTechCommentBox,BCBRecommendedCommentBox,BCBPriorityBox,EOTechCommentBox,EORecommendedCommentBox,EOPriorityBox,TFTechCommentBox,TFRecommendedCommentBox,TFPriorityBox,
	WFTechCommentBox,WFRecommendedCommentBox,WFPriorityBox,BFTechCommentBox,BFRecommendedCommentBox,BFPriorityBox,PSFTechCommentBox,PSFRecommendedCommentBox,PSFPriorityBox,
	coolantTechCommentBox,coolantRecommendedCommentBox,coolantPriorityBox,SBTechCommentBox,SBRecommendedCommentBox,SBPriorityBox,AFTechCommentBox,AFRecommendedCommentBox,AFPriorityBox,
	FFTechCommentBox,FFRecommendedCommentBox,FFPriorityBox,radiatorTechCommentBox,radiatorRecommendedCommentBox,radiatorPriorityBox,URHTechCommentBox,URHRecommendedCommentBox,URHPriorityBox,
	LRHTechCommentBox,LRHRecommendedCommentBox,LRHPriorityBox,HBHTechCommentBox,HBHRecommendedCommentBox,HBHPriorityBox,SRPTechCommentBox,SRPRecommendedCommentBox,SRPPriorityBox,
	SLTechCommentBox,SLRecommendedCommentBox,SLPriorityBox,suspTechCommentBox,suspRecommendedCommentBox,suspPriorityBox,alignTechCommentBox,alignRecommendedCommentBox,alignPriorityBox,
	FSSTechCommentBox,FSSRecommendedCommentBox,FSSPriorityBox,RSSTechCommentBox,RSSRecommendedCommentBox,RSSPriorityBox,FBTechCommentBox,FBRecommendedCommentBox,FBPriorityBox,
	RBTechCommentBox,RBRecommendedCommentBox,RBPriorityBox,CVADTechCommentBox,CVADRecommendedCommentBox,CVADPriorityBox,mufflerTechCommentBox,mufflerRecommendedCommentBox,mufflerPriorityBox,
	EPTechCommentBox,EPRecommendedCommentBox,EPPriorityBox;
	
	
	public JTextField[][] otherFields;
	
	public JTextField other1Label,other1TechCommentBox,other1RecommendedCommentBox,other2Label,other2TechCommentBox,other2RecommendedCommentBox,other3Label,other3TechCommentBox,other3RecommendedCommentBox,
	other4Label,other4TechCommentBox,other4RecommendedCommentBox,other5Label,other5TechCommentBox,other5RecommendedCommentBox,other6Label,other6TechCommentBox,other6RecommendedCommentBox,
	other7Label,other7TechCommentBox,other7RecommendedCommentBox,other8Label,other8TechCommentBox,other8RecommendedCommentBox;
	
	
	public JComboBox[] otherComboBoxes;

	public JComboBox other1PriorityBox, other2PriorityBox, other3PriorityBox, other4PriorityBox, other5PriorityBox,other6PriorityBox,other7PriorityBox,other8PriorityBox;
	
	
	public ABMTextField[][] numberFields;
	
	public ABMTextField DSFTtireField,DSFTlaborField,DSFTTireField,DSFTLBRfield,PSFTtireField,PSFTlaborField,PSFTTireField,PSFTLBRfield,PSRTtireField,PSRTlaborField,PSRTTireField,PSRTLBRfield,
	DSRTtireField,DSRTlaborField,DSRTTireField,DSRTLBRfield,spareTireField,spareLaborField,otherSpareTireField,spareLBRfield,WWPartsField,WWLaborField,WWPartField,WWLBRfield,locksPartsField,locksLaborField,locksPartField,locksLBRfield,
	ACHPartsField,ACHLaborField,ACHPartField,ACHLBRfield,heaterPartsField,heaterLaborField,heaterPartField,heaterLBRfield,wiperPartsField,wiperLaborField,wiperPartField,wiperLBRfield,hornPartsField,hornLaborField,hornPartField,hornLBRfield,
	HLPartsField,HLLaborField,HLPartField,HLLBRfield,PTLPPartsField,PTLPLaborField,PTLPPartField,PTLPLBRfield,brakePartsField,brakeLaborField,BLPartField,BLLBRfield,DSPartsField,DSLaborField,DSPartField,DSLBRfield,engineDiagPartsField,engineDiagLaborField,engineDiagPartField,engineDiagLBRfield,
	MMPartsField,MMLaborField,MMPartField,MMLBRfield,BCBPartsField,BCBLaborField,BCBPartField,BCBLBRfield,EOPartsField,EOLaborField,EOPartField,EOLBRfield,TFPartsField,TFLaborField,TFPartField,TFLBRfield,WFPartsField,WFLaborField,WFPartField,WFLBRfield,
	BFPartsField,BFLaborField,BFPartField,BFLBRfield,PSFPartsField,PSFLaborField,PSFPartField,PSFLBRfield,coolantPartsField,coolantLaborField,coolantPartField,coolantLBRfield,SBPartsField,SBLaborField,SBPartField,SBLBRfield,AFPartsField,AFLaborField,AFPartField,AFLBRfield, 
	FFPartsField,FFLaborField,FFPartField,FFLBRfield,radiatorPartsField,radiatorLaborField,radiatorPartField,radiatorLBRfield,URHPartsField,URHLaborField,URHPartField,URHLBRfield,LRHPartsField,LRHLaborField,LRHPartField,LRHLBRfield,HBHPartsField,HBHLaborField,HBHPartField,HBHLBRfield,
	SRPPartsField,SRPLaborField,SRPPartField,SRPLBRfield,SLPartsField,SLLaborField,SLPartField,SLLBRfield,suspPartsField,suspLaborField,suspPartField,suspLBRfield,alignPartsField,alignLaborField,alignPartField,alignLBRfield,FSSPartsField,FSSLaborField,FSSPartField,FSSLBRfield,
	RSSPartsField,RSSLaborField,RSSPartField,RSSLBRfield,FBPartsField,FBLaborField,FBPartField,FBLBRfield,RBPartsField,RBLaborField,RBPartField,RBLBRfield,CVADPartsField,CVADLaborField,CVADSPartField,CVADSLBRfield,mufflerPartsField,mufflerLaborField,mufflerPartField,mufflerLBRfield,
	EPPartsField,EPLaborField,EPPartField,EPLBRfield,other1PartsField,other1LaborField,other1PartField,other1LBRfield,other2PartsField,other2LaborField,other2PartField,other2LBRfield,other3PartsField,other3LaborField,other3PartField,other3LBRfield,
	other4PartsField,other4LaborField,other4PartField,other4LBRfield,other5PartsField,other5LaborField,other5PartField,other5LBRfield,other6PartsField,other6LaborField,other6PartField,other6LBRfield,other7PartsField,other7LaborField,other7PartField,other7LBRfield,
	other8PartsField,other8LaborField,other8PartField,other8LBRfield;
	
	
	public JButton submit;
	
	
	public PMAview(){
		super("2FATGUYS PMA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);
		
		Container container = getContentPane();
		
		DecimalFormat format = new DecimalFormat("$###,##0.00");
		
		
		
		/*********************************************NORTH PANEL*********************************************/
		northPanel = new JPanel();

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		northPanel.setLayout(layout);
		northPanel.setBackground(Color.gray);
		c.insets = new Insets(3,5,2,5);
		
		Font font = new Font("SansSerif", Font.BOLD, 15);
		setFont(font);
		
		
		JPanel imagePanel = new JPanel();
		ImageIcon icon = new ImageIcon("2fatguyslogo.png");
		JLabel IconLabel = new JLabel();
		IconLabel.setIcon(icon);
		imagePanel.add(IconLabel);
		
		JLabel Title = new JLabel("2 FAT GUYS YOUR COMPLETE AUTOMOTIVE SERVICE CENTERS \"WE DO IT ALL\"    107 COMMERCE BOERNE TX. 78006    830-249-2727");
		Title.setFont(font);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 11;
		c.fill = GridBagConstraints.HORIZONTAL;
		Title.setBorder(new LineBorder(Color.BLACK, 2));
		northPanel.add(Title,c);
		
		font = new Font("SansSerif", Font.BOLD, 20);
		setFont(font);
		
		nameField = new JTextField(20);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		northPanel.add(nameField,c);
		nameLabel = new JLabel("CUSTOMER");
		c.gridx = 2;
		c.gridwidth = 1;
		northPanel.add(nameLabel,c);
		vehicleYearLabel = new JLabel("YEAR");
		c.gridx = 3;
		c.gridwidth = 1;
		northPanel.add(vehicleYearLabel,c);
		vehicleYearField = new JTextField(20);
		c.gridx = 4;
		c.gridwidth = 2;
		northPanel.add(vehicleYearField,c);
		workOrderField = new JTextField(20);
		c.gridx = 6;
		c.gridwidth = 2;
		northPanel.add(workOrderField,c);
		workOrderLabel = new JLabel("W/O");
		c.gridx = 8;
		c.gridwidth = 1;
		northPanel.add(workOrderLabel,c);
		engineLabel = new JLabel("ENGINE");
		c.gridx = 9;
		c.gridwidth = 1;
		northPanel.add(engineLabel,c);
		engineField = new JTextField(10);
		c.gridx = 10;
		c.gridwidth = 1;
		northPanel.add(engineField,c);
		
		
		dateField = new JTextField(20);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		northPanel.add(dateField,c);
		dateLabel = new JLabel("DATE");
		c.gridx = 2;
		c.gridwidth = 1;
		northPanel.add(dateLabel,c);
		vehicleMakeLabel = new JLabel("MAKE");
		c.gridx = 3;
		c.gridwidth = 1;
		northPanel.add(vehicleMakeLabel,c);
		vehicleMakeField = new JTextField(20);
		c.gridx = 4;
		c.gridwidth = 2;
		northPanel.add(vehicleMakeField,c);
		licField = new JTextField(20);
		c.gridx = 6;
		c.gridwidth = 2;
		northPanel.add(licField,c);
		licLabel = new JLabel("LIC NUM");
		c.gridx = 8;
		c.gridwidth = 1;
		northPanel.add(licLabel,c);
		transLabel = new JLabel("TRANS");
		c.gridx = 9;
		c.gridwidth = 1;
		northPanel.add(transLabel,c);
		transField = new JTextField(10);
		c.gridx = 10;
		c.gridwidth = 1;
		northPanel.add(transField,c);

		
		tagsField = new JTextField(20);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		northPanel.add(tagsField,c);
		tagsLabel = new JLabel("TAGS");
		c.gridx = 2;
		c.gridwidth = 1;
		northPanel.add(tagsLabel,c);
		vehicleModelLabel = new JLabel("MODEL");
		c.gridx = 3;
		c.gridwidth = 1;
		northPanel.add(vehicleModelLabel,c);
		vehicleModelField = new JTextField(20);
		c.gridx = 4;
		c.gridwidth = 2;
		northPanel.add(vehicleModelField,c);
		vinField = new JTextField(20);
		c.gridx = 6;
		c.gridwidth = 2;
		northPanel.add(vinField,c);
		vinLabel = new JLabel("VIN");
		c.gridx = 8;
		c.gridwidth = 2;
		northPanel.add(vinLabel,c);
		milesLabel = new JLabel("MILES");
		c.gridx = 9;
		c.gridwidth = 1;
		northPanel.add(milesLabel,c);
		milesField = new JTextField(10);
		c.gridx = 10;
		c.gridwidth = 1;
		northPanel.add(milesField,c);

		JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				imagePanel, northPanel);
		split.disable();
		
		northPanel.setBorder(new LineBorder(Color.BLACK, 2));
		container.add(split, BorderLayout.NORTH);
		
		/**********************************************MIDPANEL*************************************************************************/

		JPanel midPanel = new JPanel(new BorderLayout());
		
		JPanel techPanel = new JPanel();
		techPanel.setLayout(layout);
		
		/********************************TIRES******************************************/
		
		
		JLabel tireLabel = new JLabel("205/50ZR17    ");  // this obviosly needs to change 
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		tireLabel.setBackground(Color.gray);
		tireLabel.setBorder(new LineBorder(Color.BLACK, 2));
		tireLabel.setOpaque(true);
		techPanel.add(tireLabel,c);
		JLabel okayLabel = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		okayLabel.setBackground(Color.gray);
		okayLabel.setBorder(new LineBorder(Color.BLACK, 2));
		okayLabel.setOpaque(true);
		techPanel.add(okayLabel,c);
		JLabel notOkayLabel = new JLabel("NOT OK"); 
		c.gridx = 3;
		c.gridwidth = 1;
		notOkayLabel.setBackground(Color.gray);
		notOkayLabel.setBorder(new LineBorder(Color.BLACK, 2));
		notOkayLabel.setOpaque(true);
		techPanel.add(notOkayLabel,c);
		JLabel techCommentsLabel = new JLabel("TECHNICIANS COMMENTS");  
		c.gridx = 4;
		c.gridwidth = 2;
		techCommentsLabel.setBackground(Color.gray);
		techCommentsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		techCommentsLabel.setOpaque(true);
		techPanel.add(techCommentsLabel,c);
		JLabel recommendedRepairLabel = new JLabel("RECOMMENDED REPAIRS");  
		c.gridx = 6;
		c.gridwidth = 2;
		recommendedRepairLabel.setBackground(Color.gray);
		recommendedRepairLabel.setBorder(new LineBorder(Color.BLACK, 2));
		recommendedRepairLabel.setOpaque(true);
		techPanel.add(recommendedRepairLabel,c);
		JLabel priorityLabel = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		priorityLabel.setBackground(Color.gray);
		priorityLabel.setBorder(new LineBorder(Color.BLACK, 2));
		priorityLabel.setOpaque(true);
		techPanel.add(priorityLabel,c);
		JLabel tiresLabel = new JLabel("TIRES");
		c.gridx = 9;
		c.gridwidth = 1;
		tiresLabel.setBackground(Color.gray);
		tiresLabel.setBorder(new LineBorder(Color.BLACK, 2));
		tiresLabel.setOpaque(true);
		techPanel.add(tiresLabel,c);
		JLabel laborLabel = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		laborLabel.setBackground(Color.gray);
		laborLabel.setBorder(new LineBorder(Color.BLACK, 2));
		laborLabel.setOpaque(true);
		techPanel.add(laborLabel,c);
		

		
		String[] tireTechComments = {"", "TIRE IS WORN OUT", "TIRE IS FLAT", "TIRE HAS HOLE IN SIDE WALL", "TIRE HAS IRREGULAR WEAR", "TIRE HAS ALIGNMENT WEAR", 
					"1/32 TREAD", "2/32 TREAD", "3/32 TREAD", "4/32 TREAD", "5/32 TREAD", "6/32 TREAD", "7/32 TREAD", "8/32 TREAD", "9/32 TREAD", "10/32 TREAD", 
					"11/32 TREAD", "12/32 TREAD", "13/32 TREAD", "14/32 TREAD", "15/32 TREAD"};
		String[] tireRecommendedComments = {"", "REPLACE TIRE", "REPAIR TIRE", "ROTATE TIRE"};
		String[] priority = {"", "HIGH", "MED", "LOW"}; 
		MaskFormatter mask = null;
		try {
			mask = new MaskFormatter("   $####.##  "); // need to fix this!!
			mask.setPlaceholderCharacter('X');
		} catch (ParseException e) {
			e.printStackTrace();
		}

		JLabel DSFTLabel = new JLabel("DRIVER SIDE FRONT TIRE    ");
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		techPanel.add(DSFTLabel,c);
		DSFTOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(DSFTOK, c);
		DSFTNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(DSFTNOTOK, c);
		OKbuttonListener listener = new OKbuttonListener(DSFTOK,DSFTNOTOK);
		DSFTNOTOK.addActionListener(listener);
		DSFTOK.addActionListener(listener);
		DSFTtechCommentBox = new JComboBox(tireTechComments);
		DSFTtechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(DSFTtechCommentBox, c);
		DSFTrecommendedCommentBox = new JComboBox(tireRecommendedComments);
		DSFTrecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(DSFTrecommendedCommentBox,c);
		DSFTpriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(DSFTpriorityBox, c);
		DSFTtireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(DSFTtireField, c);
		DSFTlaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(DSFTlaborField,c);
		
		
		
		JLabel PSFTLabel = new JLabel("PASSENGER SIDE FRONT TIRE     ");
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		techPanel.add(PSFTLabel,c);
		PSFTOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(PSFTOK, c);
		PSFTNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(PSFTNOTOK, c);
		listener = new OKbuttonListener(PSFTOK,PSFTNOTOK);
		PSFTNOTOK.addActionListener(listener);
		PSFTOK.addActionListener(listener);
		PSFTtechCommentBox = new JComboBox(tireTechComments);
		PSFTtechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(PSFTtechCommentBox, c);
		PSFTrecommendedCommentBox = new JComboBox(tireRecommendedComments);
		PSFTrecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(PSFTrecommendedCommentBox,c);
		PSFTpriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(PSFTpriorityBox, c);
		PSFTtireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(PSFTtireField, c);
		PSFTlaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(PSFTlaborField,c);
		
		
		JLabel PSRTLabel = new JLabel("PASSENGER SIDE REAR TIRE    ");
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 1;
		techPanel.add(PSRTLabel,c);
		PSRTOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(PSRTOK, c);
		PSRTNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(PSRTNOTOK, c);
		listener = new OKbuttonListener(PSRTOK,PSRTNOTOK);
		PSRTNOTOK.addActionListener(listener);
		PSRTOK.addActionListener(listener);
		PSRTtechCommentBox = new JComboBox(tireTechComments);
		PSRTtechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(PSRTtechCommentBox, c);
		PSRTrecommendedCommentBox = new JComboBox(tireRecommendedComments);
		PSRTrecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(PSRTrecommendedCommentBox,c);
		PSRTpriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(PSRTpriorityBox, c);
		PSRTtireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(PSRTtireField, c);
		PSRTlaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(PSRTlaborField,c);
		
		
		JLabel DSRTLabel = new JLabel("DRIVER SIDE REAR TIRE");
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 1;
		techPanel.add(DSRTLabel,c);
		DSRTOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(DSRTOK, c);
		DSRTNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(DSRTNOTOK, c);
		listener = new OKbuttonListener(DSRTOK,DSRTNOTOK);
		DSRTNOTOK.addActionListener(listener);
		DSRTOK.addActionListener(listener);
		DSRTtechCommentBox = new JComboBox(tireTechComments);
		DSRTtechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(DSRTtechCommentBox, c);
		DSRTrecommendedCommentBox = new JComboBox(tireRecommendedComments);
		DSRTrecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(DSRTrecommendedCommentBox,c);
		DSRTpriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(DSRTpriorityBox, c);
		DSRTtireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(DSRTtireField, c);
		DSRTlaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(DSRTlaborField,c);
		
		
		JLabel spareLabel = new JLabel("SPARE");
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		techPanel.add(spareLabel,c);
		spareOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(spareOK, c);
		spareNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(spareNOTOK, c);
		listener = new OKbuttonListener(spareOK,spareNOTOK);
		spareNOTOK.addActionListener(listener);
		spareOK.addActionListener(listener);
		spareTechCommentBox = new JComboBox(tireTechComments);
		spareTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(spareTechCommentBox, c);
		spareRecommendedCommentBox = new JComboBox(tireRecommendedComments);
		spareRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(spareRecommendedCommentBox,c);
		sparePriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(sparePriorityBox, c);
		spareTireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(spareTireField, c);
		spareLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(spareLaborField,c);
		
		
		/***************************INTERIOR AND EXTERIOR********************/
		
		JLabel INTEXTLabel = new JLabel("INTERIOR & EXTERIOR");  // this obviosly needs to change 
		c.gridx = 0;
		c.gridy = 6;
		c.gridwidth = 2;
		INTEXTLabel.setBackground(Color.gray);
		INTEXTLabel.setBorder(new LineBorder(Color.BLACK, 2));
		INTEXTLabel.setOpaque(true);
		techPanel.add(INTEXTLabel,c);
		JLabel okay2Label = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		okay2Label.setBackground(Color.gray);
		okay2Label.setBorder(new LineBorder(Color.BLACK, 2));
		okay2Label.setOpaque(true);
		techPanel.add(okay2Label,c);
		JLabel notOkay2Label = new JLabel("NOT OK"); 
		c.gridx = 3;
		c.gridwidth = 1;
		notOkay2Label.setBackground(Color.gray);
		notOkay2Label.setBorder(new LineBorder(Color.BLACK, 2));
		notOkay2Label.setOpaque(true);
		techPanel.add(notOkay2Label,c);
		JLabel techComments2Label = new JLabel("TECHNICIANS COMMENTS");  
		c.gridx = 4;
		c.gridwidth = 2;
		techComments2Label.setBackground(Color.gray);
		techComments2Label.setBorder(new LineBorder(Color.BLACK, 2));
		techComments2Label.setOpaque(true);
		techPanel.add(techComments2Label,c);
		JLabel recommendedRepair2Label = new JLabel("RECOMMENDED REPAIRS");  
		c.gridx = 6;
		c.gridwidth = 2;
		recommendedRepair2Label.setBackground(Color.gray);
		recommendedRepair2Label.setBorder(new LineBorder(Color.BLACK, 2));
		recommendedRepair2Label.setOpaque(true);
		techPanel.add(recommendedRepair2Label,c);
		JLabel priority2Label = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		priority2Label.setBackground(Color.gray);
		priority2Label.setBorder(new LineBorder(Color.BLACK, 2));
		priority2Label.setOpaque(true);
		techPanel.add(priority2Label,c);
		JLabel partsLabel = new JLabel("PARTS");
		c.gridx = 9;
		c.gridwidth = 1;
		partsLabel.setBackground(Color.gray);
		partsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		partsLabel.setOpaque(true);
		techPanel.add(partsLabel,c);
		JLabel labor2Label = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		labor2Label.setBackground(Color.gray);
		labor2Label.setBorder(new LineBorder(Color.BLACK, 2));
		labor2Label.setOpaque(true);
		techPanel.add(labor2Label,c);
		
		
		String[] WWTechComments = {"", "WINDSHIELD IS CRACKED","WINDSHIELD HAS CHIP IN IT","WINDSHIELD IS SCRATCHED","DRIVERS SIDE FRONT WINDOW NOT GOING UP","PASSENGER SIDE FRONT WINDOW NOT GOING UP",
									"DRIVERS SIDE REAR WINDOW WILL NOT GO UP", "PASSENGER SIDE REAR WINDOW NOT GOING UP",};
		String[] WWRecommendedComments = {"", "REPLACE WINDSHIELD", "INFORMATION ONLY", "DO DIAGNOSTIC ON WINDOW"};
		
		JLabel WWLabel = new JLabel("WINDOWS & WINDSHIELD");
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 1;
		techPanel.add(WWLabel,c);
		WWOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(WWOK, c);
		WWNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(WWNOTOK, c);
		listener = new OKbuttonListener(WWOK,WWNOTOK);
		WWNOTOK.addActionListener(listener);
		WWOK.addActionListener(listener);
		WWTechCommentBox = new JComboBox(WWTechComments);
		WWTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(WWTechCommentBox, c);
		WWRecommendedCommentBox = new JComboBox(WWRecommendedComments);
		WWRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(WWRecommendedCommentBox,c);
		WWPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(WWPriorityBox, c);
		WWPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(WWPartsField, c);
		WWLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(WWLaborField,c);
		
		
		String[] locksTechComments = {"", "DRIVERS SIDE FRONT LOCK NOT WORKING","PASSENGER SIDE FRONT LOCK NOT WORKING",
							"DRIVERS SIDE REAR LOCK NOT WORKING","PASSENGER SIDE REAR LOCK NOT WORKING","POWER LOCKS NOT WORKING"};
		String[] locksRecommendedComments = {"", "DO DIAGNOSTICS ON LOCKS"};
		
		JLabel locksLabel = new JLabel("LOCKS");
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 1;
		techPanel.add(locksLabel,c);
		locksOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(locksOK, c);
		locksNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(locksNOTOK, c);
		listener = new OKbuttonListener(locksOK,locksNOTOK);
		locksNOTOK.addActionListener(listener);
		locksOK.addActionListener(listener);
		locksTechCommentBox = new JComboBox(locksTechComments);
		locksTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(locksTechCommentBox, c);
		locksRecommendedCommentBox = new JComboBox(locksRecommendedComments);
		locksRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(locksRecommendedCommentBox,c);
		locksPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(locksPriorityBox, c);
		locksPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(locksPartsField, c);
		locksLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(locksLaborField,c);
		
		
		String[] ACHTechComments = {"", "NEEDS DIAGNOSTIC ON A/C SYSTEM", "LOW ON FREON","HAS LEAK IN A/C SYSTEM","NOT COOLING VERY WELL","A/C SWITCH IS BROKEN"};
		String[] ACHRecommendedComments = {"","DO A/C SERVICE/DIAGNOSTIC"};
		
		JLabel ACHLabel = new JLabel("A/C AND HEATER");
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		techPanel.add(ACHLabel,c);
		ACHOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(ACHOK, c);
		ACHNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(ACHNOTOK, c);
		listener = new OKbuttonListener(ACHOK,ACHNOTOK);
		ACHNOTOK.addActionListener(listener);
		ACHOK.addActionListener(listener);
		ACHTechCommentBox = new JComboBox(ACHTechComments);
		ACHTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(ACHTechCommentBox, c);
		ACHRecommendedCommentBox = new JComboBox(ACHRecommendedComments);
		ACHRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(ACHRecommendedCommentBox,c);
		ACHPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(ACHPriorityBox, c);
		ACHPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(ACHPartsField, c);
		ACHLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(ACHLaborField,c);
		
		
		String[] heaterTechComments = {"","NEEDS DIAGNOSTIC ON HEATER","LOW ON COOLANT","NEEDS HEATER CONTROL VALVE","HEATER CORE LEAKING"};
		String[] heaterRecommendedComments = {"","DO DIAGNOSTIC ON HEATER SYSTEM", "DO COOLANT PRESSURE TEST","REPLACE HEATER CONTROL VALVE"};
		
		JLabel heaterLabel = new JLabel("HEATER");
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		techPanel.add(heaterLabel,c);
		heaterOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(heaterOK, c);
		heaterNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(heaterNOTOK, c);
		listener = new OKbuttonListener(heaterOK,heaterNOTOK);
		heaterNOTOK.addActionListener(listener);
		heaterOK.addActionListener(listener);
		heaterTechCommentBox = new JComboBox(heaterTechComments);
		heaterTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(heaterTechCommentBox, c);
		heaterRecommendedCommentBox = new JComboBox(heaterRecommendedComments);
		heaterRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(heaterRecommendedCommentBox,c);
		heaterPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(heaterPriorityBox, c);
		heaterPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(heaterPartsField, c);
		heaterLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(heaterLaborField,c);
		
		
		String[] wiperTechComments = {"","WIPERS NOT WORKING","WIPERS STREAKING","SQUIRTERS NOT WORKING"};
		String[] wiperRecommendedComments = {"","DO DIAGNOSTIC ON WIPER SYSTEM", "REPLACE WIPERS", "DO DIAGNOSTIC ON SQUIRTERS"};
		
		JLabel wiperLabel = new JLabel("WIPER");
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;
		techPanel.add(wiperLabel,c);
		wiperOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(wiperOK, c);
		wiperNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(wiperNOTOK, c);
		listener = new OKbuttonListener(wiperOK,wiperNOTOK);
		wiperNOTOK.addActionListener(listener);
		wiperOK.addActionListener(listener);
		wiperTechCommentBox = new JComboBox(wiperTechComments);
		wiperTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(wiperTechCommentBox, c);
		wiperRecommendedCommentBox = new JComboBox(wiperRecommendedComments);
		wiperRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(wiperRecommendedCommentBox,c);
		wiperPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(wiperPriorityBox, c);
		wiperPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(wiperPartsField, c);
		wiperLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(wiperLaborField,c);
		
		
		String[] hornTechComments = {"","",""};
		String[] hornRecommendedComments = {"", "DO DIAGNOSTIC ON HORN"};
		
		JLabel hornLabel = new JLabel("HORN");
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		techPanel.add(hornLabel,c);
		hornOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(hornOK, c);
		hornNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(hornNOTOK, c);
		listener = new OKbuttonListener(hornOK,hornNOTOK);
		hornNOTOK.addActionListener(listener);
		hornOK.addActionListener(listener);
		hornTechCommentBox = new JComboBox(hornTechComments);
		hornTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(hornTechCommentBox, c);
		hornRecommendedCommentBox = new JComboBox(hornRecommendedComments);
		hornRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(hornRecommendedCommentBox,c);
		hornPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(hornPriorityBox, c);
		hornPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(hornPartsField, c);
		hornLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(hornLaborField,c);
		
		
		String[] HLTechComments = {"","DRIVER SIDE HIGH BEAM IS OUT","DRIVER SIDE LOW BEAM IS OUT","PASSENGER SIDE HIGH BEAM IS OUT", "PASSENGER SIDE LOW BEAM IS OUT",
						"DRIVER SIDE HEALIGHT HAS CRACK IN IT", "PASSENGER SIDE HEADLIGHT HAS CRACK IN IT", "HEAD LIGHTS DO NOT WORK"};
		String[] HLRecommendedComments = {"", "REPLACE LIGHT(S)", "DO DIAGNOSTICS ON HEAD LIGHTS"};
		
		JLabel HLLabel = new JLabel("HEADLAMPS");
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 1;
		techPanel.add(HLLabel,c);
		HLOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(HLOK, c);
		HLNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(HLNOTOK, c);
		listener = new OKbuttonListener(HLOK,HLNOTOK);
		HLNOTOK.addActionListener(listener);
		HLOK.addActionListener(listener);
		HLTechCommentBox = new JComboBox(HLTechComments);
		HLTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(HLTechCommentBox, c);
		HLRecommendedCommentBox = new JComboBox(HLRecommendedComments);
		HLRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(HLRecommendedCommentBox,c);
		HLPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(HLPriorityBox, c);
		HLPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(HLPartsField, c);
		HLLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(HLLaborField,c);
		
		
		String[] PTLTechComments = {"","DRIVER SIDE REAR TAIL LIGHT IS OUT","PASSENGER SIDE REAR TAIL LIGHT IS OUT","PASSENGER SIDE TAIL LIGHT IS CRACKED",
						"DRIVER SIDE TAIL LIGHT IS CRACKED", "PARK/TAIL LAMP DO NOT WORK", "LIC PLATE LIGHT IS OUT"};
		String[] PTLRecommendedComments = {"", "REPLACE LIGHT(S)","DO DIAGNOSTIC ON PARK/TAIL LAMP"};
		
		JLabel PTLLabel = new JLabel("PARK/TAIL/LIC PLATE LAMP");
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 1;
		techPanel.add(PTLLabel,c);
		PTLOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(PTLOK, c);
		PTLNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(PTLNOTOK, c);
		listener = new OKbuttonListener(PTLOK,PTLNOTOK);
		PTLNOTOK.addActionListener(listener);
		PTLOK.addActionListener(listener);
		PTLTechCommentBox = new JComboBox(PTLTechComments);
		PTLTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(PTLTechCommentBox, c);
		PTLRecommendedCommentBox = new JComboBox(PTLRecommendedComments);
		PTLRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(PTLRecommendedCommentBox,c);
		PTLPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(PTLPriorityBox, c);
		PTLPPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(PTLPPartsField, c);
		PTLPLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(PTLPLaborField,c);
		
		
		String[] brakeTechComments = {"","DRIVER SIDE BRAKE LIGHT IS OUT","PASSENGER SIDE BRAKE LIGHT IS OUT","PASSENGER SIDE BRAKE LIGHT IS CRACKED",
							"DRIVER SIDE BRAKE LIGHT IS CRACKED", "BRAKE LIGHTS DO NOT WORK"};
		String[] brakeRecommendedComments = {"","REPLACE LIGHT(S)","DO DIAGNOSTICS ON BRAKE LIGHTS"};
		
		JLabel brakeLabel = new JLabel("BRAKE LAMP");
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 1;
		techPanel.add(brakeLabel,c);
		BLOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(BLOK, c);
		BLNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(BLNOTOK, c);
		listener = new OKbuttonListener(BLOK,BLNOTOK);
		BLNOTOK.addActionListener(listener);
		BLOK.addActionListener(listener);
		brakeTechCommentBox = new JComboBox(brakeTechComments);
		brakeTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(brakeTechCommentBox, c);
		brakeRecommendedCommentBox = new JComboBox(brakeRecommendedComments);
		brakeRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(brakeRecommendedCommentBox,c);
		brakePriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(brakePriorityBox, c);
		brakePartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(brakePartsField, c);
		brakeLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(brakeLaborField,c);
		
		
		String[] DSTechComments = {"","PASSENGER SIDE FENDER IS DENTED","DRIVER SIDE FENDER IS DENTED","DRIVER SIDE FRONT DOOR IS DENTED","DRIVER SIDE REAR DOOR IS DENTED",
								"PASSENGER FRONT DOOR IS DENTED","PASSENGER SIDE REAR DOOR IS DENTED","DRIVER SIDE REAR QUARTER IS DENTED", "PASSENGER SIDE REAR QUARTER IS DENTED",
								"TRUNK LID IS DENTED", "HOOD IS DENTED", "TAILGATE IS DENTED", "DENTS/SCRATCHES"};
		String[] DSRecommendedComments = {"", "DO ESTIMATE ON BODYWORK", "INFORMATION ONLY"};
		
		JLabel DSLabel = new JLabel("DENTS/SCRATCHES");
		c.gridx = 0;
		c.gridy = 16;
		c.gridwidth = 1;
		techPanel.add(DSLabel,c);
		DSOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(DSOK, c);
		DSNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(DSNOTOK, c);
		listener = new OKbuttonListener(DSOK,DSNOTOK);
		DSNOTOK.addActionListener(listener);
		DSOK.addActionListener(listener);
		DSTechCommentBox = new JComboBox(DSTechComments);
		DSTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(DSTechCommentBox, c);
		DSRecommendedCommentBox = new JComboBox(DSRecommendedComments);
		DSRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(DSRecommendedCommentBox,c);
		DSPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(DSPriorityBox, c);
		DSPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(DSPartsField, c);
		DSLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(DSLaborField,c);

		
		
		/********************************************UNDER HOOD*************************/
		
		JLabel underHoodLabel = new JLabel("UNDER HOOD");  // this obviosly needs to change 
		c.gridx = 0;
		c.gridy = 17;
		c.gridwidth = 2;
		underHoodLabel.setBackground(Color.gray);
		underHoodLabel.setBorder(new LineBorder(Color.BLACK, 2));
		underHoodLabel.setOpaque(true);
		techPanel.add(underHoodLabel,c);
		JLabel okay3Label = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		okay3Label.setBackground(Color.gray);
		okay3Label.setBorder(new LineBorder(Color.BLACK, 2));
		okay3Label.setOpaque(true);
		techPanel.add(okay3Label,c);
		JLabel notOkay3Label = new JLabel("NOT OK"); 
		c.gridx = 3;
		c.gridwidth = 1;
		notOkay3Label.setBackground(Color.gray);
		notOkay3Label.setBorder(new LineBorder(Color.BLACK, 2));
		notOkay3Label.setOpaque(true);
		techPanel.add(notOkay3Label,c);
		JLabel techComments3Label = new JLabel("TECHNICIANS COMMENTS");  
		c.gridx = 4;
		c.gridwidth = 2;
		techComments3Label.setBackground(Color.gray);
		techComments3Label.setBorder(new LineBorder(Color.BLACK, 2));
		techComments3Label.setOpaque(true);
		techPanel.add(techComments3Label,c);
		JLabel recommendedRepair3Label = new JLabel("RECOMMENDED REPAIRS");  
		c.gridx = 6;
		c.gridwidth = 2;
		recommendedRepair3Label.setBackground(Color.gray);
		recommendedRepair3Label.setBorder(new LineBorder(Color.BLACK, 2));
		recommendedRepair3Label.setOpaque(true);
		techPanel.add(recommendedRepair3Label,c);
		JLabel priority3Label = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		priority3Label.setBackground(Color.gray);
		priority3Label.setBorder(new LineBorder(Color.BLACK, 2));
		priority3Label.setOpaque(true);
		techPanel.add(priority3Label,c);
		JLabel parts2Label = new JLabel("PARTS");
		c.gridx = 9;
		c.gridwidth = 1;
		parts2Label.setBackground(Color.gray);
		parts2Label.setBorder(new LineBorder(Color.BLACK, 2));
		parts2Label.setOpaque(true);
		techPanel.add(parts2Label,c);
		JLabel labor3Label = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		labor3Label.setBackground(Color.gray);
		labor3Label.setBorder(new LineBorder(Color.BLACK, 2));
		labor3Label.setOpaque(true);
		techPanel.add(labor3Label,c);
		
		
		String[] engineDiagTechComments = {"", "CHECK ENGINE LIGHT IS ON","VEHICLE RUNS ROUGH","NEED TO DO DIAGNOSTICS"};
		String[] engineDiagRecommendedComments = {"","DO DIAGNOSTICS","INFORMATION ONLY"};
		
		JLabel engineDiagLabel = new JLabel("ENGINE DIAGNOSTIC");
		c.gridx = 0;
		c.gridy = 18;
		c.gridwidth = 1;
		techPanel.add(engineDiagLabel,c);
		engineDiagOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(engineDiagOK, c);
		engineDiagNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(engineDiagNOTOK, c);
		listener = new OKbuttonListener(engineDiagOK,engineDiagNOTOK);
		engineDiagNOTOK.addActionListener(listener);
		engineDiagOK.addActionListener(listener);
		engineDiagTechCommentBox = new JComboBox(engineDiagTechComments);
		engineDiagTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(engineDiagTechCommentBox, c);
		engineDiagRecommendedCommentBox = new JComboBox(engineDiagRecommendedComments);
		engineDiagRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(engineDiagRecommendedCommentBox,c);
		engineDiagPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(engineDiagPriorityBox, c);
		engineDiagPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(engineDiagPartsField, c);
		engineDiagLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(engineDiagLaborField,c);
		
		
		String[] MMTechComments = {"","DRIVER SIDE MOTOR MOUNT IS BROKEN","PASSENGER SIDE MOTOR MOUNT IS BROKEN","BOTH MOTOR MOUNTS ARE BROKEN", "MOTOR MOUNTS ARE WEAK", "NEED TO CHECK MOTOR MOUNTS"};
		String[] MMRecommendedComments = {"","REPLACE DRIVER SIDE MOTOR MOUNT","REPLACE PASSENGER SIDE MOTOR MOUNT","REPLACE BOTH MOTOR MOUNTS"};
		
		JLabel MMLabel = new JLabel("MOTOR MOUNTS");
		c.gridx = 0;
		c.gridy = 19;
		c.gridwidth = 1;
		techPanel.add(MMLabel,c);
		MMOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(MMOK, c);
		MMNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(MMNOTOK, c);
		listener = new OKbuttonListener(MMOK,MMNOTOK);
		MMNOTOK.addActionListener(listener);
		MMOK.addActionListener(listener);
		MMTechCommentBox = new JComboBox(MMTechComments);
		MMTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(MMTechCommentBox, c);
		MMRecommendedCommentBox = new JComboBox(MMRecommendedComments);
		MMRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(MMRecommendedCommentBox,c);
		MMPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(MMPriorityBox, c);
		MMPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(MMPartsField, c);
		MMLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(MMLaborField,c);
		
		
		String[] BCBTechComments = {"","BATTERY TERMINALS DIRTY","SEAL BATTERY TERMINALS","BATTERY TERMINALS CORRODED", "BATTERY CABLES BROKEN", "BATTERY CABLES CORRODED", "NEEDS BATTERY"};
		String[] BCBRecommendedComments = {"","CLEAN AND SEAL BATTERY TERMINALS","REPLACE(1) BATTERY END","REPLACE(2) BATTERY ENDS", "REPLACE POSITIVE BATTERY CABLE", 
										"REPLACE NEGATIVE BATTERY CABLE", "REPLACE BOTH BATTERY CABLES", "REPLACE BATTERY"};
		
		JLabel BCBLabel = new JLabel("BATTERY CABLES & BATTERY");
		c.gridx = 0;
		c.gridy = 20;
		c.gridwidth = 1;
		techPanel.add(BCBLabel,c);
		BCBOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(BCBOK, c);
		BCBNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(BCBNOTOK, c);
		listener = new OKbuttonListener(BCBOK,BCBNOTOK);
		BCBNOTOK.addActionListener(listener);
		BCBOK.addActionListener(listener);
		BCBTechCommentBox = new JComboBox(BCBTechComments);
		BCBTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(BCBTechCommentBox, c);
		BCBRecommendedCommentBox = new JComboBox(BCBRecommendedComments);
		BCBRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(BCBRecommendedCommentBox,c);
		BCBPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(BCBPriorityBox, c);
		BCBPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(BCBPartsField, c);
		BCBLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(BCBLaborField,c);
		
		
		String[] EOTechComments = {"","ENGINE OIL IS LOW","ENGINE OIL IS DIRTY","ENGINE OIL IS LOW AND DIRTY","ENGINE HAS OIL LEAK"};
		String[] EORecommendedComments = {"","DO OIL CHANGE","ADD OIL","DO DYE TEST TO CHECK FOR OIL LEAK"};
		
		JLabel EOLabel = new JLabel("ENGINE OIL");
		c.gridx = 0;
		c.gridy = 21;
		c.gridwidth = 1;
		techPanel.add(EOLabel,c);
		EOOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(EOOK, c);
		EONOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(EONOTOK, c);
		listener = new OKbuttonListener(EOOK,EONOTOK);
		EONOTOK.addActionListener(listener);
		EOOK.addActionListener(listener);
		EOTechCommentBox = new JComboBox(EOTechComments);
		EOTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(EOTechCommentBox, c);
		EORecommendedCommentBox = new JComboBox(EORecommendedComments);
		EORecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(EORecommendedCommentBox,c);
		EOPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(EOPriorityBox, c);
		EOPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(EOPartsField, c);
		EOLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(EOLaborField,c);
		
		
		String[] TFTechComments = {"","TRANSMISSION FLUID IS LOW","TRANSMISSION FLUID IS DIRTY","TRANSMISSION FLUID IS LOW AND DIRTY", "TRANSMISSION HAS OIL LEAK"};
		String[] TFRecommendedComments = {"","DO TRANSMISSION SERVICE","ADD TRANSMISSION FLUID", "DO DYE TEST TO CHECK FOR FLUID LEAK"};
		
		JLabel TFLabel = new JLabel("TRANSMISSION FLUID");
		c.gridx = 0;
		c.gridy = 22;
		c.gridwidth = 1;
		techPanel.add(TFLabel,c);
		TFOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(TFOK, c);
		TFNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(TFNOTOK, c);
		listener = new OKbuttonListener(TFOK,TFNOTOK);
		TFNOTOK.addActionListener(listener);
		TFOK.addActionListener(listener);
		TFTechCommentBox = new JComboBox(TFTechComments);
		TFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(TFTechCommentBox, c);
		TFRecommendedCommentBox = new JComboBox(TFRecommendedComments);
		TFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(TFRecommendedCommentBox,c);
		TFPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(TFPriorityBox, c);
		TFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(TFPartsField, c);
		TFLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(TFLaborField,c);
		
		
		String[] WFTechComments = {"", "WASHER FLUID IS LOW","SQUIRTERS NOT WORKING","WASHER FLUID BOTTLE IS CRACKED"};
		String[] WFRecommendedComments = {"","ADD WASHER FLUID","DO DIAGNOSTICS ON SQUIRTERS", "REPLACE WINDSHIELD WASHER BOTTLE"};
		
		JLabel WFLabel = new JLabel("WASHER FLUID");
		c.gridx = 0;
		c.gridy = 23;
		c.gridwidth = 1;
		techPanel.add(WFLabel,c);
		WFOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(WFOK, c);
		WFNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(WFNOTOK, c);
		listener = new OKbuttonListener(WFOK,WFNOTOK);
		WFNOTOK.addActionListener(listener);
		WFOK.addActionListener(listener);
		WFTechCommentBox = new JComboBox(WFTechComments);
		WFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(WFTechCommentBox, c);
		WFRecommendedCommentBox = new JComboBox(WFRecommendedComments);
		WFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(WFRecommendedCommentBox,c);
		WFPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(WFPriorityBox, c);
		WFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(WFPartsField, c);
		WFLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(WFLaborField,c);
		
		
		String[] BFTechComments = {"","BRAKE FLUID IS LOW","BRAKE FLUID IS DIRTY", "BRAKE FLUID IS LOW AND DIRTY"};
		String[] BFRecommendedComments = {"","DO BRAKE FLUSH","ADD BRAKE FLUID"};
		
		JLabel BFLabel = new JLabel("BRAKE FLUID");
		c.gridx = 0;
		c.gridy = 24;
		c.gridwidth = 1;
		techPanel.add(BFLabel,c);
		BFOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(BFOK, c);
		BFNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(BFNOTOK, c);
		listener = new OKbuttonListener(BFOK,BFNOTOK);
		BFNOTOK.addActionListener(listener);
		BFOK.addActionListener(listener);
		BFTechCommentBox = new JComboBox(BFTechComments);
		BFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(BFTechCommentBox, c);
		BFRecommendedCommentBox = new JComboBox(BFRecommendedComments);
		BFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(BFRecommendedCommentBox,c);
		BFPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(BFPriorityBox, c);
		BFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(BFPartsField, c);
		BFLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(BFLaborField,c);
		
		
		String[] PSFTechComments = {"","POWER STEERING FLUID IS LOW","POWER STEERING FLUID IS DIRTY", "POWER STEERING FLUID IS LOW AND DIRTY"};
		String[] PSFRecommendedComments = {"","DO A POWER STEERING FLUSH","ADD POWER STEERING FLUID"};
		
		JLabel PSFLabel = new JLabel("POWER STEERING FLUID");
		c.gridx = 0;
		c.gridy = 25;
		c.gridwidth = 1;
		techPanel.add(PSFLabel,c);
		PSFOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(PSFOK, c);
		PSFNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(PSFNOTOK, c);
		listener = new OKbuttonListener(PSFOK,PSFNOTOK);
		PSFNOTOK.addActionListener(listener);
		PSFOK.addActionListener(listener);
		PSFTechCommentBox = new JComboBox(PSFTechComments);
		PSFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(PSFTechCommentBox, c);
		PSFRecommendedCommentBox = new JComboBox(PSFRecommendedComments);
		PSFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(PSFRecommendedCommentBox,c);
		PSFPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(PSFPriorityBox, c);
		PSFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(PSFPartsField, c);
		PSFLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(PSFLaborField,c);
		
		
		String[] coolantTechComments = {"","COOLANT IS LOW","COOLANT IS DIRTY","COOLANT IS LOW AND DIRTY", "NEEDS COOLANT FLUSH WITH REPAIRS","HAS COOLANT LEAK DO PRESSURE TEST"};
		String[] coolantRecommendedComments = {"","DO COOLANT FLUSH","ADD COOLANT", "DO PRESSURE TEST TO CHECK FOR LEAKS"};
		
		JLabel coolantLabel = new JLabel("COOLANT");
		c.gridx = 0;
		c.gridy = 26;
		c.gridwidth = 1;
		techPanel.add(coolantLabel,c);
		coolantOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(coolantOK, c);
		coolantNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(coolantNOTOK, c);
		listener = new OKbuttonListener(coolantOK,coolantNOTOK);
		coolantNOTOK.addActionListener(listener);
		coolantOK.addActionListener(listener);
		coolantTechCommentBox = new JComboBox(coolantTechComments);
		coolantTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(coolantTechCommentBox, c);
		coolantRecommendedCommentBox = new JComboBox(coolantRecommendedComments);
		coolantRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(coolantRecommendedCommentBox,c);
		coolantPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(coolantPriorityBox, c);
		coolantPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(coolantPartsField, c);
		coolantLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(coolantLaborField,c);
		
		
		String[] SBTechComments = {"","SERPENTINE BELT IS CRACKED","SERPENTINE BELT IS MISSING","BELT TENSIONER IS WORN", "BELT TENSIONER AND BELT ARE WORN"};
		String[] SBRecommendedComments = {"","REPLACE BELT","ADJUST BELT", "REPLACE BELT TENSIONER","REPLACE BELT TENSIONER AND BELT"};
		
		JLabel SBLabel = new JLabel("SERPENTINE BELT");
		c.gridx = 0;
		c.gridy = 27;
		c.gridwidth = 1;
		techPanel.add(SBLabel,c);
		SBOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(SBOK, c);
		SBNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(SBNOTOK, c);
		listener = new OKbuttonListener(SBOK,SBNOTOK);
		SBNOTOK.addActionListener(listener);
		SBOK.addActionListener(listener);
		SBTechCommentBox = new JComboBox(SBTechComments);
		SBTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(SBTechCommentBox, c);
		SBRecommendedCommentBox = new JComboBox(SBRecommendedComments);
		SBRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(SBRecommendedCommentBox,c);
		SBPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(SBPriorityBox, c);
		SBPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(SBPartsField, c);
		SBLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(SBLaborField,c);
		
		
		String[] AFTechComments = {"","AIR FILTER IS DIRTY","K&N TYPE FILTER NEEDS TO BE CLEANED"};
		String[] AFRecommendedComments = {"", "REPLACE AIR FILTER", "CLEAN AND RE OIL AIR FILTER"};
		
		JLabel AFLabel = new JLabel("AIR FILTER");
		c.gridx = 0;
		c.gridy = 28;
		c.gridwidth = 1;
		techPanel.add(AFLabel,c);
		AFOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(AFOK, c);
		AFNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(AFNOTOK, c);
		listener = new OKbuttonListener(AFOK,AFNOTOK);
		AFNOTOK.addActionListener(listener);
		AFOK.addActionListener(listener);
		AFTechCommentBox = new JComboBox(AFTechComments);
		AFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(AFTechCommentBox, c);
		AFRecommendedCommentBox = new JComboBox(AFRecommendedComments);
		AFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(AFRecommendedCommentBox,c);
		AFPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(AFPriorityBox, c);
		AFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(AFPartsField, c);
		AFLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(AFLaborField,c);
		
		
		String[] FFTechComments = {"","REPLACE FILTER DUE TO MILEAGE","REPLACE FILTER MANUFACTURER RECOMMEND","FUE FILTER CLOGGED", "NEED TO REMOVE FUEL FILTER TO CHECK IT"};
		String[] FFRecommendedComments = {"","REPLACE FUEL FILTER","REMOVE FILTER AND CHECK FOR CLOGS"};
		
		JLabel FFLabel = new JLabel("FUEL FILTER");
		c.gridx = 0;
		c.gridy = 29;
		c.gridwidth = 1;
		techPanel.add(FFLabel,c);
		FFOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(FFOK, c);
		FFNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(FFNOTOK, c);
		listener = new OKbuttonListener(FFOK,FFNOTOK);
		FFNOTOK.addActionListener(listener);
		FFOK.addActionListener(listener);
		FFTechCommentBox = new JComboBox(AFTechComments);
		FFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(FFTechCommentBox, c);
		FFRecommendedCommentBox = new JComboBox(FFRecommendedComments);
		FFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(FFRecommendedCommentBox,c);
		FFPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(FFPriorityBox, c);
		FFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(FFPartsField, c);
		FFLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(FFLaborField,c);
		
		
		String[] radiatorTechComments = {"","RADIATOR IS LEAKING","RADIATOR IS CLOGGED", "OVERHEATING NEED TO CHECK SYSTEM"};
		String[] radiatorRecommendedComments = {"","DO PRESSURE CHECK TO CHECK LEAKS","REPLACE RADIATOR", "DO DIAGNOSTICS ON OVERHEATING"};
		
		JLabel radiatorLabel = new JLabel("RADIATOR");
		c.gridx = 0;
		c.gridy = 30;
		c.gridwidth = 1;
		techPanel.add(radiatorLabel,c);
		radiatorOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(radiatorOK, c);
		radiatorNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(radiatorNOTOK, c);
		listener = new OKbuttonListener(radiatorOK,radiatorNOTOK);
		radiatorNOTOK.addActionListener(listener);
		radiatorOK.addActionListener(listener);
		radiatorTechCommentBox = new JComboBox(radiatorTechComments);
		radiatorTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(radiatorTechCommentBox, c);
		radiatorRecommendedCommentBox = new JComboBox(radiatorRecommendedComments);
		radiatorRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(radiatorRecommendedCommentBox,c);
		radiatorPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(radiatorPriorityBox, c);
		radiatorPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(radiatorPartsField, c);
		radiatorLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(radiatorLaborField,c);
		
		
		String[] URHTechComments = {"","UPPER HODE IS LEAKING","UPPER HOSE FEELS BRITTLE","NEEDS HOSE CLAMPS REPLACED"};
		String[] URHRecommendedComments = {"","REPLACE UPPER RADIATOR HOSE"};
		
		JLabel URHLabel = new JLabel("UPPER RADIATOR HOSE");
		c.gridx = 0;
		c.gridy = 31;
		c.gridwidth = 1;
		techPanel.add(URHLabel,c);
		URHOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(URHOK, c);
		URHNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(URHNOTOK, c);
		listener = new OKbuttonListener(URHOK,URHNOTOK);
		URHNOTOK.addActionListener(listener);
		URHOK.addActionListener(listener);
		URHTechCommentBox = new JComboBox(URHTechComments);
		URHTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(URHTechCommentBox, c);
		URHRecommendedCommentBox = new JComboBox(URHRecommendedComments);
		URHRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(URHRecommendedCommentBox,c);
		URHPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(URHPriorityBox, c);
		URHPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(URHPartsField, c);
		URHLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(URHLaborField,c);
		
		
		String[] LRHTechComments = {"", "LOWER HOSE IS LEAKING", "LOWER HOSE FEELS BRITTLE","NEEDS HOSE CLAMPS REPLACED"};
		String[] LRHRecommendedComments = {"", "REPLACE LOWER RADIATOR HOSE"};
		
		JLabel LRHLabel = new JLabel("LOWER RADIATOR HOSE");
		c.gridx = 0;
		c.gridy = 32;
		c.gridwidth = 1;
		techPanel.add(LRHLabel,c);
		LRHOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(LRHOK, c);
		LRHNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(LRHNOTOK, c);
		listener = new OKbuttonListener(LRHOK,LRHNOTOK);
		LRHNOTOK.addActionListener(listener);
		LRHOK.addActionListener(listener);
		LRHTechCommentBox = new JComboBox(LRHTechComments);
		LRHTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(LRHTechCommentBox, c);
		LRHRecommendedCommentBox = new JComboBox(LRHRecommendedComments);
		LRHRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(LRHRecommendedCommentBox,c);
		LRHPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(LRHPriorityBox, c);
		LRHPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(LRHPartsField, c);
		LRHLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(LRHLaborField,c);
		
		
		String[] HBHTechComments = {"","HEATER IS LEAKING","HEATER HOSE FEELS BRITTLE","NEEDS HOSE CLAMPS REPLACED"};
		String[] HBHRecommendedComments = {"","REPLACE HEATER HOSES"};
		
		JLabel HBHLabel = new JLabel("HEATER/BYPASS HOSE");
		c.gridx = 0;
		c.gridy = 33;
		c.gridwidth = 1;
		techPanel.add(HBHLabel,c);
		HBHOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(HBHOK, c);
		HBHNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(HBHNOTOK, c);
		listener = new OKbuttonListener(HBHOK,HBHNOTOK);
		HBHNOTOK.addActionListener(listener);
		HBHOK.addActionListener(listener);
		HBHTechCommentBox = new JComboBox(HBHTechComments);
		HBHTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(HBHTechCommentBox, c);
		HBHRecommendedCommentBox = new JComboBox(HBHRecommendedComments);
		HBHRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(HBHRecommendedCommentBox,c);
		HBHPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(HBHPriorityBox, c);
		HBHPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(HBHPartsField, c);
		HBHLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(HBHLaborField,c);
		
		
		
		/*************************************UNDER CAR************************************/
		
		JLabel underCarLabel = new JLabel("UNDER CAR");  // this obviosly needs to change 
		c.gridx = 0;
		c.gridy = 34;
		c.gridwidth = 2;
		underCarLabel.setBackground(Color.gray);
		underCarLabel.setBorder(new LineBorder(Color.BLACK, 2));
		underCarLabel.setOpaque(true);
		techPanel.add(underCarLabel,c);
		JLabel okay4Label = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		okay4Label.setBackground(Color.gray);
		okay4Label.setBorder(new LineBorder(Color.BLACK, 2));
		okay4Label.setOpaque(true);
		techPanel.add(okay4Label,c);
		JLabel notOkay4Label = new JLabel("NOT OK"); 
		c.gridx = 3;
		c.gridwidth = 1;
		notOkay4Label.setBackground(Color.gray);
		notOkay4Label.setBorder(new LineBorder(Color.BLACK, 2));
		notOkay4Label.setOpaque(true);
		techPanel.add(notOkay4Label,c);
		JLabel techComments4Label = new JLabel("TECHNICIANS COMMENTS");  
		c.gridx = 4;
		c.gridwidth = 2;
		techComments4Label.setBackground(Color.gray);
		techComments4Label.setBorder(new LineBorder(Color.BLACK, 2));
		techComments4Label.setOpaque(true);
		techPanel.add(techComments4Label,c);
		JLabel recommendedRepair4Label = new JLabel("RECOMMENDED REPAIRS");  
		c.gridx = 6;
		c.gridwidth = 2;
		recommendedRepair4Label.setBackground(Color.gray);
		recommendedRepair4Label.setBorder(new LineBorder(Color.BLACK, 2));
		recommendedRepair4Label.setOpaque(true);
		techPanel.add(recommendedRepair4Label,c);
		JLabel priority4Label = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		priority4Label.setBackground(Color.gray);
		priority4Label.setBorder(new LineBorder(Color.BLACK, 2));
		priority4Label.setOpaque(true);
		techPanel.add(priority4Label,c);
		JLabel parts3Label = new JLabel("PARTS");
		c.gridx = 9;
		c.gridwidth = 1;
		parts3Label.setBackground(Color.gray);
		parts3Label.setBorder(new LineBorder(Color.BLACK, 2));
		parts3Label.setOpaque(true);
		techPanel.add(parts3Label,c);
		JLabel labor4Label = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		labor4Label.setBackground(Color.gray);
		labor4Label.setBorder(new LineBorder(Color.BLACK, 2));
		labor4Label.setOpaque(true);
		techPanel.add(labor4Label,c);
		
		
		String[] SRPTechComments = {"","STEERING BOX/GEAR IS LEAKING","STEERING BOX/GEAR IS LOOSE","RACK & PINION IS LEAKING","RACK AND PINION IS LOOSE", "RACK AND PINION MOUNTS ARE WORN", "RACK AND PINION MOUNTS ARE BROKEN"};
		String[] SRPRecommendedComments = {"","REPLACE STEERING BOX/GEAR","REPLACE RACK AND PINION","REPLACE RACK & PINION MOUNTS"};
		
		JLabel SRPLabel = new JLabel("STEERING RACK & PINION");
		c.gridx = 0;
		c.gridy = 35;
		c.gridwidth = 1;
		techPanel.add(SRPLabel,c);
		SRPOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(SRPOK, c);
		SRPNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(SRPNOTOK, c);
		listener = new OKbuttonListener(SRPOK,SRPNOTOK);
		SRPNOTOK.addActionListener(listener);
		SRPOK.addActionListener(listener);
		SRPTechCommentBox = new JComboBox(SRPTechComments);
		SRPTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(SRPTechCommentBox, c);
		SRPRecommendedCommentBox = new JComboBox(SRPRecommendedComments);
		SRPRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(SRPRecommendedCommentBox,c);
		SRPPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(SRPPriorityBox, c);
		SRPPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(SRPPartsField, c);
		SRPLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(SRPLaborField,c);
		
		
		
		String[] SLTechComments = {"","LEFT OUTER TIE ROD WORN","RIGHT OUTER TIE ROD WORN","LEFT INNER TIE ROD WORN","RIGHT INNER TIE ROD WORN","BOTH INNER TIE RODS WORN","BOTH OUTER TIE RODS WORN","BOTH INNER & OUTER TIE RODS WORN",
									"PITMAN ARM WORN","IDLER ARM WORN","DRAG LINK WORN","DRAG LINK, LEFT & RIGHT TIE ROD ENDS WORN","PITMAN ARM AND IDLER ARM WORN","PITMAN ARM IDLER ARM & DRAG LINK WORN","STEERING STABILIZER WORN"};
		String[] SLRecommendedComments = {"","REPLACE LEFT OUTER TIE ROD","REPLACE RIGHT OUTER TIE ROD","REPLACE LEFT INNER TIE ROD","REPLACE RIGHT INNER TIE ROD","REPLACE BOTH INNER TIE RODS","REPLACE BOTH OUTER TIE RODS","REPLACE BOTH INNER & OUTER TIE RODS",
										"REPLACE PITMAN ARM","REPLACE IDLER ARM","REPLACE DRAG LINK","REPLACE DRAG LINK, LEFT&RIGHT TIE ROD ENDS", "REPLACE PITMAN ARM AND IDLER ARM", "REPLACE PITMAN ARM, IDLER ARM & DRAG LINK", "REPLACE STEERING STABILIZER"};
		
		JLabel SLLabel = new JLabel("STEERING LINKAGE");
		c.gridx = 0;
		c.gridy = 36;
		c.gridwidth = 1;
		techPanel.add(SLLabel,c);
		SLOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(SLOK, c);
		SLNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(SLNOTOK, c);
		listener = new OKbuttonListener(SLOK,SLNOTOK);
		SLNOTOK.addActionListener(listener);
		SLOK.addActionListener(listener);
		SLTechCommentBox = new JComboBox(SLTechComments);
		SLTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(SLTechCommentBox, c);
		SLRecommendedCommentBox = new JComboBox(SLRecommendedComments);
		SLRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(SLRecommendedCommentBox,c);
		SLPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(SLPriorityBox, c);
		SLPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(SLPartsField, c);
		SLLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(SLLaborField,c);
		
		
		String[] suspTechComments = {"","LEFT UPPER BALL JOINT WORN","LEFT LOWER BALL JOINT WORN","RIGHT UPPER BALL JOINT WORN", "RIGHT LOWER BALL JOINT WORN", "BOTH LEFT UPPER & LOWER BALL JOINTS WORN",
									"BOTH RIGHT UPPER & LOWER BALL JOINTS WORN","ALL BALL JOINTS WORN","RIGHT SIDE LOWER A FRAME BUSHINGS WORN", "RIGHT SIDE UPPER A FRAME BUSHINGS WORN","LEFT SIDE LOWER A FRAME BUSHINGS WORN", "LEFT SIDE UPPER A FRAME BUSHINGS WORN",
									"ALL A FRAME BUSHINGS WORN", "LEFT SIDE KING PIN(S) WORN","RIGHT SIDE KING PIN(S) WORN","BOTH KING PINS WORN","LEFT SIDE UPPER STRUT MOUNT WORN","RIGHT SIDE UPPER STRUT MOUNT WORN"};
		String[] suspRecommendedComments = {"","REPLACE LEFT UPPER BALL JOINT","REPLACE LEFT LOWER BALL JOINT","REPLACE RIGHT UPPER BALL JOINT","REPLACE RIGHT LOWER BALL JOINT","REPLACE LEFT UPPER & LOWER BALL JOINTS", "REPLACE RIGHT UPPER & LOWER BALL JOINTS",
											"REPLACE ALL BALL JOINTS", "REPLACE RIGHT SIDE LOWER A FRAME BUSHINGS", "REPLACE RIGHT SIDE UPPER A FRAME BUSHINGS", "REPLACE LEFT SIDE LOWER A FRAME BUSHINGS", "REPLACE LEFT SIDE UPPER A FRAME BUSHINGS",
											"REPLACE ALL A FRAME BUSHINGS", "REPLACE LEFT SIDE KING PIN(S)", "REPLACE RIGHT SIDE KING PIN(S)", "REPLACE BOTH KING PINS", "REPLACE LEFT SIDE UPPER STRUT MOUNT", "REPLACE RIGHT SIDE UPPER STRUT MOUNT"};
		
		JLabel suspLabel = new JLabel("SUSPENSION");
		c.gridx = 0;
		c.gridy = 37;
		c.gridwidth = 1;
		techPanel.add(suspLabel,c);
		suspOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(suspOK, c);
		suspNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(suspNOTOK, c);
		listener = new OKbuttonListener(suspOK,suspNOTOK);
		suspNOTOK.addActionListener(listener);
		suspOK.addActionListener(listener);
		suspTechCommentBox = new JComboBox(suspTechComments);
		suspTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(suspTechCommentBox, c);
		suspRecommendedCommentBox = new JComboBox(suspRecommendedComments);
		suspRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(suspRecommendedCommentBox,c);
		suspPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(suspPriorityBox, c);
		suspPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(suspPartsField, c);
		suspLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(suspLaborField,c);


		String[] alignTechComments = {"","NEEDS ALIGNMENT CAUSING TIRE WEAR","NEEDS ALIGNMENT PULLS LEFT", "NEEDS ALIGNMENT PULLS RIGHT", "NEEDS ALIGNMENT AFTER FRONT END WORK"};
		String[] alignRecommendedComments = {"","DO ALIGNMENT"};
		
		JLabel alignLabel = new JLabel("ALIGNMENT");
		c.gridx = 0;
		c.gridy = 38;
		c.gridwidth = 1;
		techPanel.add(alignLabel,c);
		alignOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(alignOK, c);
		alignNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(alignNOTOK, c);
		listener = new OKbuttonListener(alignOK,alignNOTOK);
		alignNOTOK.addActionListener(listener);
		alignOK.addActionListener(listener);
		alignTechCommentBox = new JComboBox(alignTechComments);
		alignTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(alignTechCommentBox, c);
		alignRecommendedCommentBox = new JComboBox(alignRecommendedComments);
		alignRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(alignRecommendedCommentBox,c);
		alignPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(alignPriorityBox, c);
		alignPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(alignPartsField, c);
		alignLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(alignLaborField,c);
		
		
		String[] FSSTechComments = {"","FRONT SHOCKS WORN OUT","REAR SHOCKS WORN OUT","ALL (4) SHOCKS WORN OUT","FRONT SHOCKS LEAKING", "REAR SHOCKS LEAKING", "ALL (4) SHOCKS LEAKING", "FRONT SHOCKS CAUSING TIRE WEAR",
							"REAR SHOCKS CAUSING TIRE WEAR","ALL (4) SHOCKS CAUSING TIRE WEAR","FRONT STRUTS WORN OUT", "REAR STRUT WORN OUT","ALL (4) STRUTS WORN OUT","FRONT STRUTS LEAKING","REAR STRUTS LEAKING","ALL (4) STRUTS LEAKING",
							"FRONT STRUTS CAUSING TIRE WEAR","REAR STUTS CAUSING TIRE WEAR", "ALL (4) STRUTS CAUSING TIRE WEAR"};
		String[] FSSRecommendedComments = {"","REPLACE FRONT SHOCKS","REPLACE REAR SHOCKS","REPLACE ALL (4) SHOCKS","REPLACE FRONT STRUTS", "REPLACE REAR STRUTS", "REPLACE ALL (4) STRUTS"};
		
		JLabel FSSLabel = new JLabel("FRONT STRUT/SHOCKS");
		c.gridx = 0;
		c.gridy = 39;
		c.gridwidth = 1;
		techPanel.add(FSSLabel,c);
		FSSOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(FSSOK, c);
		FSSNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(FSSNOTOK, c);
		listener = new OKbuttonListener(FSSOK,FSSNOTOK);
		FSSNOTOK.addActionListener(listener);
		FSSOK.addActionListener(listener);
		FSSTechCommentBox = new JComboBox(FSSTechComments);
		FSSTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(FSSTechCommentBox, c);
		FSSRecommendedCommentBox = new JComboBox(FSSRecommendedComments);
		FSSRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(FSSRecommendedCommentBox,c);
		FSSPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(FSSPriorityBox, c);
		FSSPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(FSSPartsField, c);
		FSSLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(FSSLaborField,c);
		
		
		String[] RSSTechComments = {"","FRONT SHOCKS WORN OUT","REAR SHOCKS WORN OUT","ALL (4) SHOCKS WORN OUT","FRONT SHOCKS LEAKING","REAR SHOCKS LEAKING","ALL (4) SHOCKS LEAKING","FRONT SHOCKS CAUSING TIRE WEAR",
								"REAR SHOCKS CAUSING TIRE WEAR", "ALL (4) SHOCKS CAUSING TIRE WEAR", "FRONT STRUTS WORN OUT", "REAR STRUT WORN OUT", "ALL (4) STRUTS WORN OUT","FRONT STRUTS LEAKING", "REAR STRUTS LEAKING","ALL (4) STRUTS LEAKING","FRONT STRUTS CAUSING TIRE WEAR",
								"REAR STRUTS CAUSING TIRE WEAR", "ALL (4) STRUTS CAUSING TIRE WEAR"};
		String[] RSSRecommendedComments = {"","REPLACE FRONT SHOCKS","REPLACE REAR SHOCKS","REPLACE ALL (4) SHOCKS","REPLACE FRONT STRUTS","REPLACE REAR STRUTS", "REPLACE ALL (4) STRUTS"};
		
		JLabel RSSLabel = new JLabel("REAR STRUT/SHOCKS");
		c.gridx = 0;
		c.gridy = 40;
		c.gridwidth = 1;
		techPanel.add(RSSLabel,c);
		RSSOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(RSSOK, c);
		RSSNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(RSSNOTOK, c);
		listener = new OKbuttonListener(RSSOK,RSSNOTOK);
		RSSNOTOK.addActionListener(listener);
		RSSOK.addActionListener(listener);
		RSSTechCommentBox = new JComboBox(RSSTechComments);
		RSSTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(RSSTechCommentBox, c);
		RSSRecommendedCommentBox = new JComboBox(RSSRecommendedComments);
		RSSRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(RSSRecommendedCommentBox,c);
		RSSPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(RSSPriorityBox, c);
		RSSPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(RSSPartsField, c);
		RSSLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(RSSLaborField,c);
		
		
		String[] FBTechComments = {"","FRONT BRAKES WORN NEED TO BE REPLACED","NEEDS FRONT BRAKES AND BRAKE ROTORS","NEEDS FRONT BRAKES AND BRAKE CALIPERS","NEEDS FRONT BRAKES, ROTORS & CALIPERS"};
		String[] FBRecommendedComments = {"","REPLACE FRONT BRAKES","REPLACE FRONT BRAKES AND BRAKE ROTORS","REPLACE FRONT BRAKES AND BRAKE CALIPERS","REPLACE FRONT BRAKES, ROTORS & CALIPERS"};
		
		JLabel FBLabel = new JLabel("FRONT BRAKES");
		c.gridx = 0;
		c.gridy = 41;
		c.gridwidth = 1;
		techPanel.add(FBLabel,c);
		FBOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(FBOK, c);
		FBNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(FBNOTOK, c);
		listener = new OKbuttonListener(FBOK,FBNOTOK);
		FBNOTOK.addActionListener(listener);
		FBOK.addActionListener(listener);
		FBTechCommentBox = new JComboBox(FBTechComments);
		FBTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(FBTechCommentBox, c);
		FBRecommendedCommentBox = new JComboBox(FBRecommendedComments);
		FBRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(FBRecommendedCommentBox,c);
		FBPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(FBPriorityBox, c);
		FBPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(FBPartsField, c);
		FBLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(FBLaborField,c);
		
		
		String[] RBTechComments = {"","CLEAN AND ADJUST BRAKES","REAR BRAKES WORN NEED TO BE REPLACED", "NEEDS REAR BRAKES AND BRAKE ROTOR","NEEDS REAR BRAKES AND BRAKE DRUMS","NEEDS REAR BRAKES AND BRAKE CALIPERS","NEEDS REAR BRAKES AND WHEEL CYLINDERS","NEEDS REAR BRAKES,BRAKE ROTOR&CALIPERS","NEEDS REAR BRAKES,BRAKE DRUMS&CYLINDERS"};
		String[] RBRecommendedComments = {"","CLEAN AND ADJUST BRAKES","REPLACE REAR BRAKES","REPLACE REAR BRAKES AMD BRAKE ROTOR","REPLACE REAR BRAKES AND BRAKE DRUMS","REPLACE REAR BRAKES AND BRAKE CALIPERS","REPLACE REAR BRAKES AND WHEEL CYLINDERS","REPLACE REAR BRAKES,ROTORS & CALIPERS","REPLACE REAR BRAKES,DRUMS & CYLINDERS"};
		
		JLabel RBLabel = new JLabel("REAR BRAKES");
		c.gridx = 0;
		c.gridy = 42;
		c.gridwidth = 1;
		techPanel.add(RBLabel,c);
		RBOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(RBOK, c);
		RBNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(RBNOTOK, c);
		listener = new OKbuttonListener(RBOK,RBNOTOK);
		RBNOTOK.addActionListener(listener);
		RBOK.addActionListener(listener);
		RBTechCommentBox = new JComboBox(RBTechComments);
		RBTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(RBTechCommentBox, c);
		RBRecommendedCommentBox = new JComboBox(RBRecommendedComments);
		RBRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(RBRecommendedCommentBox,c);
		RBPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(RBPriorityBox, c);
		RBPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(RBPartsField, c);
		RBLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(RBLaborField,c);
		
		
		String[] CVADTechComments = {"", "FRONT U JOINT WORN","REAR U JOINT WORN","FRONT DRIVE SHAFT 4X4 U JOINT WORN","REAR DRIVE SHAFT 4X4 U JOINT WORN"};
		String[] CVADRecommendedComments = {"","REPLACE FRONT U JOINT","REPLACE REAR U JOINT","REPLACE FRONT DRIVE SHAFT 4X4 U JOINT","REPLACE REAR DRIVE SHAFT 4X4 U JOINT"};
		
		JLabel CVADLabel = new JLabel("CV AXLE/DRIVE SHAFT");
		c.gridx = 0;
		c.gridy = 43;
		c.gridwidth = 1;
		techPanel.add(CVADLabel,c);
		CVADOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(CVADOK, c);
		CVADNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(CVADNOTOK, c);
		listener = new OKbuttonListener(CVADOK,CVADNOTOK);
		CVADNOTOK.addActionListener(listener);
		CVADOK.addActionListener(listener);
		CVADTechCommentBox = new JComboBox(CVADTechComments);
		CVADTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(CVADTechCommentBox, c);
		CVADRecommendedCommentBox = new JComboBox(CVADRecommendedComments);
		CVADRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(CVADRecommendedCommentBox,c);
		CVADPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(CVADPriorityBox, c);
		CVADPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(CVADPartsField, c);
		CVADLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(CVADLaborField,c);
		
		
		String[] mufflerTechComments = {"","MUFFLER LEAKING EXHAUST","MUFFLER HAS HOLES IN IT","MUFFLER IS RUSTED","MUFFLER IS MISSING"};
		String[] mufflerRecommendedComments = {"","REPLACE MUFFLER"};
		
		JLabel mufflerLabel = new JLabel("MUFFLER");
		c.gridx = 0;
		c.gridy = 44;
		c.gridwidth = 1;
		techPanel.add(mufflerLabel,c);
		mufflerOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(mufflerOK, c);
		mufflerNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(mufflerNOTOK, c);
		listener = new OKbuttonListener(mufflerOK,mufflerNOTOK);
		mufflerNOTOK.addActionListener(listener);
		mufflerOK.addActionListener(listener);
		mufflerTechCommentBox = new JComboBox(mufflerTechComments);
		mufflerTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(mufflerTechCommentBox, c);
		mufflerRecommendedCommentBox = new JComboBox(mufflerRecommendedComments);
		mufflerRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(mufflerRecommendedCommentBox,c);
		mufflerPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(mufflerPriorityBox, c);
		mufflerPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(mufflerPartsField, c);
		mufflerLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(mufflerLaborField,c);
		
		
		String[] EPTechComments = {"","EXAUST PIPES ARE LEAKING EXHAUST","EXHAUST PIPES HOLES IN THEM","EXHAUST PIPES ARE RUSTED","EXHAUST PIPES ARE MISSING"};
		String[] EPRecommendedComments = {"","REPLACE EXHAUST PIPES"};
		
		JLabel EPLabel = new JLabel("EXHAUST PIPES");
		c.gridx = 0;
		c.gridy = 45;
		c.gridwidth = 1;
		techPanel.add(EPLabel,c);
		EPOK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(EPOK, c);
		EPNOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(EPNOTOK, c);
		listener = new OKbuttonListener(EPOK,EPNOTOK);
		EPNOTOK.addActionListener(listener);
		EPOK.addActionListener(listener);
		EPTechCommentBox = new JComboBox(EPTechComments);
		EPTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(EPTechCommentBox, c);
		EPRecommendedCommentBox = new JComboBox(EPRecommendedComments);
		EPRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(EPRecommendedCommentBox,c);
		EPPriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(EPPriorityBox, c);
		EPPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(EPPartsField, c);
		EPLaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(EPLaborField,c);
		
		
		/*****************************OTHER ITEMS*******************************************/
		
		
		c.insets = new Insets(3,4,3,4);
		
		JLabel otherLabel = new JLabel("OTHER ITEMS NEEDING ATTN");  // this obviosly needs to change 
		c.gridx = 0;
		c.gridy = 46;
		c.gridwidth = 2;
		otherLabel.setBackground(Color.gray);
		otherLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherLabel.setOpaque(true);
		techPanel.add(otherLabel,c);
		JLabel otherOKLabel = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		otherOKLabel.setBackground(Color.gray);
		otherOKLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherOKLabel.setOpaque(true);
		techPanel.add(otherOKLabel,c);
		JLabel otherNotOKLabel = new JLabel("NOT OK"); 
		c.gridx = 3;
		c.gridwidth = 1;
		otherNotOKLabel.setBackground(Color.gray);
		otherNotOKLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherNotOKLabel.setOpaque(true);
		techPanel.add(otherNotOKLabel,c);
		JLabel otherTechCommentsLabel = new JLabel("TECHNICIANS COMMENTS");  
		c.gridx = 4;
		c.gridwidth = 2;
		otherTechCommentsLabel.setBackground(Color.gray);
		otherTechCommentsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherTechCommentsLabel.setOpaque(true);
		techPanel.add(otherTechCommentsLabel,c);
		JLabel otherRecommendedLabel = new JLabel("RECOMMENDED REPAIRS");  
		c.gridx = 6;
		c.gridwidth = 2;
		otherRecommendedLabel.setBackground(Color.gray);
		otherRecommendedLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherRecommendedLabel.setOpaque(true);
		techPanel.add(otherRecommendedLabel,c);
		JLabel otherPriorityLabel = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		otherPriorityLabel.setBackground(Color.gray);
		otherPriorityLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherPriorityLabel.setOpaque(true);
		techPanel.add(otherPriorityLabel,c);
		JLabel otherPartsLabel = new JLabel("PARTS");
		c.gridx = 9;
		c.gridwidth = 1;
		otherPartsLabel.setBackground(Color.gray);
		otherPartsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherPartsLabel.setOpaque(true);
		techPanel.add(otherPartsLabel,c);
		JLabel otherLaborLabel = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		otherLaborLabel.setBackground(Color.gray);
		otherLaborLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherLaborLabel.setOpaque(true);
		techPanel.add(otherLaborLabel,c);
		
		
		
		
		JTextField other1Label = new JTextField();
		c.gridx = 0;
		c.gridy = 47;
		c.gridwidth = 1;
		techPanel.add(other1Label,c);
		other1OK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other1OK, c);
		other1NOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other1NOTOK, c);
		listener = new OKbuttonListener(other1OK,other1NOTOK);
		other1NOTOK.addActionListener(listener);
		other1OK.addActionListener(listener);
		JTextField other1TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other1TechCommentBox, c);
		JTextField other1RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other1RecommendedCommentBox,c);
		JComboBox other1PriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other1PriorityBox, c);
		other1PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other1PartsField, c);
		other1LaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other1LaborField,c);
		
		
		
		
		JTextField other2Label = new JTextField();
		c.gridx = 0;
		c.gridy = 48;
		c.gridwidth = 1;
		techPanel.add(other2Label,c);
		other2OK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other2OK, c);
		other2NOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other2NOTOK, c);
		listener = new OKbuttonListener(other2OK,other2NOTOK);
		other2NOTOK.addActionListener(listener);
		other2OK.addActionListener(listener);
		JTextField other2TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other2TechCommentBox, c);
		JTextField other2RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other2RecommendedCommentBox,c);
		JComboBox other2PriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other2PriorityBox, c);
		other2PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other2PartsField, c);
		other2LaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other2LaborField,c);
		
		
		
		JTextField other3Label = new JTextField();
		c.gridx = 0;
		c.gridy = 49;
		c.gridwidth = 1;
		techPanel.add(other3Label,c);
		other3OK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other3OK, c);
		other3NOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other3NOTOK, c);
		listener = new OKbuttonListener(other3OK,other3NOTOK);
		other3NOTOK.addActionListener(listener);
		other3OK.addActionListener(listener);
		JTextField other3TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other3TechCommentBox, c);
		JTextField other3RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other3RecommendedCommentBox,c);
		JComboBox other3PriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other3PriorityBox, c);
		other3PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other3PartsField, c);
		other3LaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other3LaborField,c);
		
		
		
		JTextField other4Label = new JTextField();
		c.gridx = 0;
		c.gridy = 50;
		c.gridwidth = 1;
		techPanel.add(other4Label,c);
		other4OK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other4OK, c);
		other4NOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other4NOTOK, c);
		listener = new OKbuttonListener(other4OK,other4NOTOK);
		other4NOTOK.addActionListener(listener);
		other4OK.addActionListener(listener);
		JTextField other4TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other4TechCommentBox, c);
		JTextField other4RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other4RecommendedCommentBox,c);
		JComboBox other4PriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other4PriorityBox, c);
		other4PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other4PartsField, c);
		other4LaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other4LaborField,c);
		
		
		
		JTextField other5Label = new JTextField();
		c.gridx = 0;
		c.gridy = 51;
		c.gridwidth = 1;
		techPanel.add(other5Label,c);
		other5OK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other5OK, c);
		other5NOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other5NOTOK, c);
		listener = new OKbuttonListener(other5OK,other5NOTOK);
		other5NOTOK.addActionListener(listener);
		other5OK.addActionListener(listener);
		JTextField other5TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other5TechCommentBox, c);
		JTextField other5RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other5RecommendedCommentBox,c);
		JComboBox other5PriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other5PriorityBox, c);
		other5PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other5PartsField, c);
		other5LaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other5LaborField,c);
		
		
		
		
		JTextField other6Label = new JTextField();
		c.gridx = 0;
		c.gridy = 52;
		c.gridwidth = 1;
		techPanel.add(other6Label,c);
		other6OK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other6OK, c);
		other6NOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other6NOTOK, c);
		listener = new OKbuttonListener(other6OK,other6NOTOK);
		other6NOTOK.addActionListener(listener);
		other6OK.addActionListener(listener);
		JTextField other6TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other6TechCommentBox, c);
		JTextField other6RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other6RecommendedCommentBox,c);
		JComboBox other6PriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other6PriorityBox, c);
		other6PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other6PartsField, c);
		other6LaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other6LaborField,c);
		
		
		
		JTextField other7Label = new JTextField();
		c.gridx = 0;
		c.gridy = 53;
		c.gridwidth = 1;
		techPanel.add(other7Label,c);
		other7OK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other7OK, c);
		other7NOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other7NOTOK, c);
		listener = new OKbuttonListener(other7OK,other7NOTOK);
		other7NOTOK.addActionListener(listener);
		other7OK.addActionListener(listener);
		JTextField other7TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other7TechCommentBox, c);
		JTextField other7RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other7RecommendedCommentBox,c);
		JComboBox other7PriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other7PriorityBox, c);
		other7PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other7PartsField, c);
		other7LaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other7LaborField,c);
		
		
		
		JTextField other8Label = new JTextField();
		c.gridx = 0;
		c.gridy = 54;
		c.gridwidth = 1;
		techPanel.add(other8Label,c);
		other8OK = new JCheckBox();
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other8OK, c);
		other8NOTOK = new JCheckBox();
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other8NOTOK, c);
		listener = new OKbuttonListener(other8OK,other8NOTOK);
		other8NOTOK.addActionListener(listener);
		other8OK.addActionListener(listener);
		JTextField other8TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other8TechCommentBox, c);
		JTextField other8RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other8RecommendedCommentBox,c);
		JComboBox other8PriorityBox = new JComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other8PriorityBox, c);
		other8PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other8PartsField, c);
		other8LaborField = new ABMTextField(format);  // change this to JFormattedTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other8LaborField,c);
		
		
		
		
		
		techPanel.setBorder(new LineBorder(Color.BLACK, 2));
		
		
		/*******************************************************************EAST PANEL***********************************************************/
		
		c.insets = new Insets(2,4,2,4);
		
		
		NumberFormat QTYformat = NumberFormat.getNumberInstance();
		DecimalFormat LBRformat = new DecimalFormat("##0.0");
		
		
		JPanel eastPanel = new JPanel(new GridBagLayout());
		JLabel QTY = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		QTY.setBackground(Color.gray);
		QTY.setBorder(new LineBorder(Color.BLACK, 2));
		QTY.setOpaque(true);
		eastPanel.add(QTY, c);
		JLabel westTire = new JLabel("TIRE");
		c.gridx = 1;
		westTire.setBackground(Color.gray);
		westTire.setBorder(new LineBorder(Color.BLACK, 2));
		westTire.setOpaque(true);
		eastPanel.add(westTire,c);
		JLabel LBRHRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBRHRS.setBackground(Color.gray);
		LBRHRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBRHRS.setOpaque(true);
		eastPanel.add(LBRHRS,c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		
		
		c.insets = new Insets(5,4,5,4);
		
		
		JFormattedTextField DSFTQTYfield = new JFormattedTextField(QTYformat);
		DSFTQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 1;
		c.gridx = 0;
		eastPanel.add(DSFTQTYfield, c);
		
		DSFTTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(DSFTTireField,c);
		
		DSFTLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(DSFTLBRfield,c);
		
		
		
		JFormattedTextField PSFTQTYfield = new JFormattedTextField(QTYformat);
		PSFTQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 2;
		c.gridx = 0;
		eastPanel.add(PSFTQTYfield, c);
		
		PSFTTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(PSFTTireField,c);
		
		PSFTLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(PSFTLBRfield,c);
		
		
		
		JFormattedTextField PSRTQTYfield = new JFormattedTextField(QTYformat);
		PSRTQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 3;
		c.gridx = 0;
		eastPanel.add(PSRTQTYfield, c);
		
		PSRTTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(PSRTTireField,c);
		
		PSRTLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(PSRTLBRfield,c);
		
		
		
		JFormattedTextField DSRTQTYfield = new JFormattedTextField(QTYformat);
		DSRTQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 4;
		c.gridx = 0;
		eastPanel.add(DSRTQTYfield, c);
		
		DSRTTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(DSRTTireField,c);
		
		DSRTLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(DSRTLBRfield,c);
		
		
		
		JFormattedTextField spareQTYfield = new JFormattedTextField(QTYformat);
		spareQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 5;
		c.gridx = 0;
		eastPanel.add(spareQTYfield, c);
		
		otherSpareTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(otherSpareTireField,c);
		
		spareLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(spareLBRfield,c);
		
		
		c.insets = new Insets(2,4,2,4);
		
		JLabel QTY2 = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 6;
		QTY2.setBackground(Color.gray);
		QTY2.setBorder(new LineBorder(Color.BLACK, 2));
		QTY2.setOpaque(true);
		eastPanel.add(QTY2, c);
		JLabel westParts = new JLabel("PART");
		c.gridx = 1;
		westParts.setBackground(Color.gray);
		westParts.setBorder(new LineBorder(Color.BLACK, 2));
		westParts.setOpaque(true);
		eastPanel.add(westParts,c);
		JLabel LBR2HRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBR2HRS.setBackground(Color.gray);
		LBR2HRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBR2HRS.setOpaque(true);
		eastPanel.add(LBR2HRS,c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		
		c.insets = new Insets(5,4,5,4);
		
		
		JFormattedTextField WWQTYfield = new JFormattedTextField(QTYformat);
		WWQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 7;
		c.gridx = 0;
		eastPanel.add(WWQTYfield, c);
		
		WWPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(WWPartField,c);
		
		WWLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(WWLBRfield,c);
		
		
		
		JFormattedTextField locksQTYfield = new JFormattedTextField(QTYformat);
		locksQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 8;
		c.gridx = 0;
		eastPanel.add(locksQTYfield, c);
		
		locksPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(locksPartField,c);
		
		locksLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(locksLBRfield,c);
		
		
		
		JFormattedTextField ACHQTYfield = new JFormattedTextField(QTYformat);
		ACHQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 9;
		c.gridx = 0;
		eastPanel.add(ACHQTYfield, c);
		
		ACHPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(ACHPartField,c);
		
		ACHLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(ACHLBRfield,c);
		
		
		
		JFormattedTextField heaterQTYfield = new JFormattedTextField(QTYformat);
		heaterQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 10;
		c.gridx = 0;
		eastPanel.add(heaterQTYfield, c);
		
		heaterPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(heaterPartField,c);
		
		heaterLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(heaterLBRfield,c);
		
		
		
		JFormattedTextField wiperQTYfield = new JFormattedTextField(QTYformat);
		wiperQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 11;
		c.gridx = 0;
		eastPanel.add(wiperQTYfield, c);
		
		wiperPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(wiperPartField,c);
		
		wiperLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(wiperLBRfield,c);
		
		
		
		JFormattedTextField hornQTYfield = new JFormattedTextField(QTYformat);
		hornQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 12;
		c.gridx = 0;
		eastPanel.add(hornQTYfield, c);
		
		hornPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(hornPartField,c);
		
		hornLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(hornLBRfield,c);
		
		
		
		JFormattedTextField HLQTYfield = new JFormattedTextField(QTYformat);
		HLQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 13;
		c.gridx = 0;
		eastPanel.add(HLQTYfield, c);
		
		HLPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(HLPartField,c);
		
		HLLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(HLLBRfield,c);
		
		
		
		JFormattedTextField PTLPQTYfield = new JFormattedTextField(QTYformat);
		PTLPQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 14;
		c.gridx = 0;
		eastPanel.add(PTLPQTYfield, c);
		
		PTLPPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(PTLPPartField,c);
		
		PTLPLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(PTLPLBRfield,c);
		
		
		
		JFormattedTextField BLQTYfield = new JFormattedTextField(QTYformat);
		BLQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 15;
		c.gridx = 0;
		eastPanel.add(BLQTYfield, c);
		
		BLPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(BLPartField,c);
		
		BLLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(BLLBRfield,c);
		
		
		
		JFormattedTextField DSQTYfield = new JFormattedTextField(QTYformat);
		DSQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 16;
		c.gridx = 0;
		eastPanel.add(DSQTYfield, c);
		
		DSPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(DSPartField,c);
		
		DSLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(DSLBRfield,c);
		

		c.insets = new Insets(2,4,2,4);
		
		JLabel QTY3 = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 17;
		QTY3.setBackground(Color.gray);
		QTY3.setBorder(new LineBorder(Color.BLACK, 2));
		QTY3.setOpaque(true);
		eastPanel.add(QTY3, c);
		JLabel westParts2 = new JLabel("PART");
		c.gridx = 1;
		westParts2.setBackground(Color.gray);
		westParts2.setBorder(new LineBorder(Color.BLACK, 2));
		westParts2.setOpaque(true);
		eastPanel.add(westParts2,c);
		JLabel LBR3HRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBR3HRS.setBackground(Color.gray);
		LBR3HRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBR3HRS.setOpaque(true);
		eastPanel.add(LBR3HRS,c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		
		
		c.insets = new Insets(5,4,5,4);
		
		
		JFormattedTextField engineDiagQTYfield = new JFormattedTextField(QTYformat);
		engineDiagQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 18;
		c.gridx = 0;
		eastPanel.add(engineDiagQTYfield, c);
		
		engineDiagPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(engineDiagPartField,c);
		
		engineDiagLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(engineDiagLBRfield,c);
		
		
		
		JFormattedTextField MMQTYfield = new JFormattedTextField(QTYformat);
		MMQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 19;
		c.gridx = 0;
		eastPanel.add(MMQTYfield, c);
		
		MMPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(MMPartField,c);
		
		MMLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(MMLBRfield,c);
		
		
		
		JFormattedTextField BCBQTYfield = new JFormattedTextField(QTYformat);
		BCBQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 20;
		c.gridx = 0;
		eastPanel.add(BCBQTYfield, c);
		
		BCBPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(BCBPartField,c);
		
		BCBLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(BCBLBRfield,c);
		
		
		
		JFormattedTextField EOQTYfield = new JFormattedTextField(QTYformat);
		EOQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 21;
		c.gridx = 0;
		eastPanel.add(EOQTYfield, c);
		
		EOPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(EOPartField,c);
		
		EOLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(EOLBRfield,c);
		
		
		
		JFormattedTextField TFQTYfield = new JFormattedTextField(QTYformat);
		TFQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 22;
		c.gridx = 0;
		eastPanel.add(TFQTYfield, c);
		
		TFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(TFPartField,c);
		
		TFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(TFLBRfield,c);
		
		
		
		JFormattedTextField WFQTYfield = new JFormattedTextField(QTYformat);
		WFQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 23;
		c.gridx = 0;
		eastPanel.add(WFQTYfield, c);
		
		WFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(WFPartField,c);
		
		WFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(WFLBRfield,c);
		
		
		
		JFormattedTextField BFQTYfield = new JFormattedTextField(QTYformat);
		BFQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 24;
		c.gridx = 0;
		eastPanel.add(BFQTYfield, c);
		
		BFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(BFPartField,c);
		
		BFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(BFLBRfield,c);
		
		
		
		JFormattedTextField PSFQTYfield = new JFormattedTextField(QTYformat);
		PSFQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 25;
		c.gridx = 0;
		eastPanel.add(PSFQTYfield, c);
		
		PSFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(PSFPartField,c);
		
		PSFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(PSFLBRfield,c);
		
		
		
		JFormattedTextField coolantQTYfield = new JFormattedTextField(QTYformat);
		coolantQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 26;
		c.gridx = 0;
		eastPanel.add(coolantQTYfield, c);
		
		coolantPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(coolantPartField,c);
		
		coolantLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(coolantLBRfield,c);
		
		
		
		JFormattedTextField SBQTYfield = new JFormattedTextField(QTYformat);
		SBQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 27;
		c.gridx = 0;
		eastPanel.add(SBQTYfield, c);
		
		SBPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(SBPartField,c);
		
		SBLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(SBLBRfield,c);
		
		
		
		JFormattedTextField AFQTYfield = new JFormattedTextField(QTYformat);
		AFQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 28;
		c.gridx = 0;
		eastPanel.add(AFQTYfield, c);
		
		AFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(AFPartField,c);
		
		AFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(AFLBRfield,c);
		
		
		
		JFormattedTextField FFQTYfield = new JFormattedTextField(QTYformat);
		FFQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 29;
		c.gridx = 0;
		eastPanel.add(FFQTYfield, c);
		
		FFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(FFPartField,c);
		
		FFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(FFLBRfield,c);
		
		
		
		JFormattedTextField radiatorQTYfield = new JFormattedTextField(QTYformat);
		radiatorQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 30;
		c.gridx = 0;
		eastPanel.add(radiatorQTYfield, c);
		
		radiatorPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(radiatorPartField,c);
		
		radiatorLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(radiatorLBRfield,c);
		
		
		
		JFormattedTextField URHQTYfield = new JFormattedTextField(QTYformat);
		URHQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 31;
		c.gridx = 0;
		eastPanel.add(URHQTYfield, c);
		
		URHPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(URHPartField,c);
		
		URHLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(URHLBRfield,c);
		
		
		
		JFormattedTextField LRHQTYfield = new JFormattedTextField(QTYformat);
		LRHQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 32;
		c.gridx = 0;
		eastPanel.add(LRHQTYfield, c);
		
		LRHPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(LRHPartField,c);
		
		LRHLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(LRHLBRfield,c);
		
		
		
		JFormattedTextField HBHQTYfield = new JFormattedTextField(QTYformat);
		HBHQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 33;
		c.gridx = 0;
		eastPanel.add(HBHQTYfield, c);
		
		HBHPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(HBHPartField,c);
		
		HBHLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(HBHLBRfield,c);
		
		
		
		c.insets = new Insets(2,4,2,4);
		
		JLabel QTY4 = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 34;
		QTY4.setBackground(Color.gray);
		QTY4.setBorder(new LineBorder(Color.BLACK, 2));
		QTY4.setOpaque(true);
		eastPanel.add(QTY4, c);
		JLabel westParts3 = new JLabel("PART");
		c.gridx = 1;
		westParts3.setBackground(Color.gray);
		westParts3.setBorder(new LineBorder(Color.BLACK, 2));
		westParts3.setOpaque(true);
		eastPanel.add(westParts3,c);
		JLabel LBR4HRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBR4HRS.setBackground(Color.gray);
		LBR4HRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBR4HRS.setOpaque(true);
		eastPanel.add(LBR4HRS,c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		
		c.insets = new Insets(5,4,5,4);
		
		
		
		JFormattedTextField SRPQTYfield = new JFormattedTextField(QTYformat);
		SRPQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 35;
		c.gridx = 0;
		eastPanel.add(SRPQTYfield, c);
		
		SRPPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(SRPPartField,c);
		
		SRPLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(SRPLBRfield,c);
		
		
		
		JFormattedTextField SLQTYfield = new JFormattedTextField(QTYformat);
		SLQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 36;
		c.gridx = 0;
		eastPanel.add(SLQTYfield, c);
		
		SLPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(SLPartField,c);
		
		SLLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(SLLBRfield,c);
		
		
		
		JFormattedTextField suspQTYfield = new JFormattedTextField(QTYformat);
		suspQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 37;
		c.gridx = 0;
		eastPanel.add(suspQTYfield, c);
		
		suspPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(suspPartField,c);
		
		suspLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(suspLBRfield,c);
		
		
		
		JFormattedTextField alignQTYfield = new JFormattedTextField(QTYformat);
		alignQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 38;
		c.gridx = 0;
		eastPanel.add(alignQTYfield, c);
		
		alignPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(alignPartField,c);
		
		alignLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(alignLBRfield,c);
		
		
		
		JFormattedTextField FSSQTYfield = new JFormattedTextField(QTYformat);
		FSSQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 39;
		c.gridx = 0;
		eastPanel.add(FSSQTYfield, c);
		
		FSSPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(FSSPartField,c);
		
		FSSLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(FSSLBRfield,c);
		
		
		
		JFormattedTextField RSSQTYfield = new JFormattedTextField(QTYformat);
		RSSQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 40;
		c.gridx = 0;
		eastPanel.add(RSSQTYfield, c);
		
		RSSPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(RSSPartField,c);
		
		RSSLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(RSSLBRfield,c);
		
		
		
		JFormattedTextField FBQTYfield = new JFormattedTextField(QTYformat);
		FBQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 41;
		c.gridx = 0;
		eastPanel.add(FBQTYfield, c);
		
		FBPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(FBPartField,c);
		
		FBLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(FBLBRfield,c);
		
		
		
		JFormattedTextField RBQTYfield = new JFormattedTextField(QTYformat);
		RBQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 42;
		c.gridx = 0;
		eastPanel.add(RBQTYfield, c);
		
		RBPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(RBPartField,c);
		
		RBLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(RBLBRfield,c);
		
		
		
		JFormattedTextField CVADSQTYfield = new JFormattedTextField(QTYformat);
		CVADSQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 43;
		c.gridx = 0;
		eastPanel.add(CVADSQTYfield, c);
		
		CVADSPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(CVADSPartField,c);
		
		CVADSLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(CVADSLBRfield,c);
		
		
		
		JFormattedTextField mufflerQTYfield = new JFormattedTextField(QTYformat);
		mufflerQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 44;
		c.gridx = 0;
		eastPanel.add(mufflerQTYfield, c);
		
		mufflerPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(mufflerPartField,c);
		
		mufflerLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(mufflerLBRfield,c);
		
		
		
		JFormattedTextField EPQTYfield = new JFormattedTextField(QTYformat);
		EPQTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 45;
		c.gridx = 0;
		eastPanel.add(EPQTYfield, c);
		
		EPPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(EPPartField,c);
		
		EPLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(EPLBRfield,c);
		
		
		
		c.insets = new Insets(2,4,3,4);
		
		JLabel QTY5 = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 46;
		QTY5.setBackground(Color.gray);
		QTY5.setBorder(new LineBorder(Color.BLACK, 2));
		QTY5.setOpaque(true);
		eastPanel.add(QTY5, c);
		JLabel westParts4 = new JLabel("PART");
		c.gridx = 1;
		westParts4.setBackground(Color.gray);
		westParts4.setBorder(new LineBorder(Color.BLACK, 2));
		westParts4.setOpaque(true);
		eastPanel.add(westParts4,c);
		JLabel LBR5HRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBR5HRS.setBackground(Color.gray);
		LBR5HRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBR5HRS.setOpaque(true);
		eastPanel.add(LBR5HRS,c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		
		c.insets = new Insets(5,4,5,4);
		
		
		
		JFormattedTextField other1QTYfield = new JFormattedTextField(QTYformat);
		other1QTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 47;
		c.gridx = 0;
		eastPanel.add(other1QTYfield, c);
		
		other1PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other1PartField,c);
		
		other1LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other1LBRfield,c);
		
		
		
		JFormattedTextField other2QTYfield = new JFormattedTextField(QTYformat);
		other2QTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 48;
		c.gridx = 0;
		eastPanel.add(other2QTYfield, c);
		
		other2PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other2PartField,c);
		
		other2LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other2LBRfield,c);
		
		
		
		JFormattedTextField other3QTYfield = new JFormattedTextField(QTYformat);
		other3QTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 49;
		c.gridx = 0;
		eastPanel.add(other3QTYfield, c);
		
		other3PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other3PartField,c);
		
		other3LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other3LBRfield,c);
		
		
		
		JFormattedTextField other4QTYfield = new JFormattedTextField(QTYformat);
		other4QTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 50;
		c.gridx = 0;
		eastPanel.add(other4QTYfield, c);
		
		other4PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other4PartField,c);
		
		other4LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other4LBRfield,c);
		
		
		
		JFormattedTextField other5QTYfield = new JFormattedTextField(QTYformat);
		other5QTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 51;
		c.gridx = 0;
		eastPanel.add(other5QTYfield, c);
		
		other5PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other5PartField,c);
		
		other5LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other5LBRfield,c);
		
		
		
		JFormattedTextField other6QTYfield = new JFormattedTextField(QTYformat);
		other6QTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 52;
		c.gridx = 0;
		eastPanel.add(other6QTYfield, c);
		
		other6PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other6PartField,c);
		
		other6LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other6LBRfield,c);
		
		
		
		JFormattedTextField other7QTYfield = new JFormattedTextField(QTYformat);
		other7QTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 53;
		c.gridx = 0;
		eastPanel.add(other7QTYfield, c);
		
		other7PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other7PartField,c);
		
		other7LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other7LBRfield,c);
		
		
		
		JFormattedTextField other8QTYfield = new JFormattedTextField(QTYformat);
		other8QTYfield.setHorizontalAlignment(JFormattedTextField.TRAILING);
		c.gridy = 54;
		c.gridx = 0;
		eastPanel.add(other8QTYfield, c);
		
		other8PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other8PartField,c);
		
		other8LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other8LBRfield,c);
		
		
		midPanel.add(eastPanel, BorderLayout.EAST);
		midPanel.add(techPanel, BorderLayout.CENTER);
		
		
		/**********************************SOUTH PANEL**************************************/
		
		
		JPanel south = new JPanel();
		submit = new JButton("Submit");
		
		south.add(submit);
		midPanel.add(south,BorderLayout.SOUTH);
		
		
		
		/************************************************************************END************************************************************/
		
		
		checkBoxes = new JCheckBox[][] {{DSFTOK,DSFTNOTOK},{PSFTOK,PSFTNOTOK}, {PSRTOK,PSRTNOTOK}, {DSRTOK,DSRTNOTOK},{spareOK,spareNOTOK},{WWOK,WWNOTOK},
				{locksOK,locksNOTOK}, {ACHOK,ACHNOTOK},{heaterOK, heaterNOTOK},{wiperOK, wiperNOTOK},{hornOK,hornNOTOK},{HLOK,HLNOTOK},
				{PTLOK,PTLNOTOK},{BLOK,BLNOTOK},{DSOK,DSNOTOK},{engineDiagOK,engineDiagNOTOK},{MMOK,MMNOTOK}, {BCBOK,BCBNOTOK},{EOOK,EONOTOK},
				{TFOK,TFNOTOK},{WFOK,WFNOTOK},{BFOK,BFNOTOK},{PSFOK,PSFNOTOK},{coolantOK,coolantNOTOK},{SBOK,SBNOTOK},{AFOK,AFNOTOK},{FFOK,FFNOTOK},
				{radiatorOK,radiatorNOTOK},{URHOK,URHNOTOK},{LRHOK,LRHNOTOK},{HBHOK,HBHNOTOK},{SRPOK,SRPNOTOK},{SLOK,SLNOTOK},{alignOK,alignNOTOK},
				{FSSOK,FSSNOTOK},{RSSOK,RSSNOTOK},{FBOK,FBNOTOK},{RBOK,RBNOTOK},{CVADOK,CVADNOTOK},{mufflerOK,mufflerNOTOK},{EPOK,EPNOTOK},
				{other1OK,other1NOTOK},{other2OK,other2NOTOK},{other3OK,other3NOTOK},{other4OK,other4NOTOK},{other5OK,other5NOTOK},{other6OK,other6NOTOK},
				{other7OK,other7NOTOK},{other8OK,other8NOTOK}};
		
		comboBoxes = new JComboBox[][] {{DSFTtechCommentBox,DSFTrecommendedCommentBox,DSFTpriorityBox},{PSFTtechCommentBox,PSFTrecommendedCommentBox,PSFTpriorityBox},
				{PSRTtechCommentBox,PSRTrecommendedCommentBox,PSRTpriorityBox},{DSRTtechCommentBox,DSRTrecommendedCommentBox,DSRTpriorityBox},{spareTechCommentBox,spareRecommendedCommentBox,sparePriorityBox},
				{WWTechCommentBox,WWRecommendedCommentBox,WWPriorityBox},{locksTechCommentBox,locksRecommendedCommentBox,locksPriorityBox},{ACHTechCommentBox,ACHRecommendedCommentBox,ACHPriorityBox},
				{heaterTechCommentBox,heaterRecommendedCommentBox,heaterPriorityBox},{wiperTechCommentBox,wiperRecommendedCommentBox,wiperPriorityBox},{hornTechCommentBox,hornRecommendedCommentBox,hornPriorityBox},
				{HLTechCommentBox,HLRecommendedCommentBox,HLPriorityBox},{PTLTechCommentBox,PTLRecommendedCommentBox,PTLPriorityBox},{brakeTechCommentBox,brakeRecommendedCommentBox,brakePriorityBox},
				{DSTechCommentBox,DSRecommendedCommentBox,DSPriorityBox},{engineDiagTechCommentBox,engineDiagRecommendedCommentBox,engineDiagPriorityBox},{MMTechCommentBox,MMRecommendedCommentBox,MMPriorityBox},
				{BCBTechCommentBox,BCBRecommendedCommentBox,BCBPriorityBox},{EOTechCommentBox,EORecommendedCommentBox,EOPriorityBox},{TFTechCommentBox,TFRecommendedCommentBox,TFPriorityBox},
				{WFTechCommentBox,WFRecommendedCommentBox,WFPriorityBox},{BFTechCommentBox,BFRecommendedCommentBox,BFPriorityBox},{PSFTechCommentBox,PSFRecommendedCommentBox,PSFPriorityBox},
				{coolantTechCommentBox,coolantRecommendedCommentBox,coolantPriorityBox},{SBTechCommentBox,SBRecommendedCommentBox,SBPriorityBox},{AFTechCommentBox,AFRecommendedCommentBox,AFPriorityBox},
				{FFTechCommentBox,FFRecommendedCommentBox,FFPriorityBox},{radiatorTechCommentBox,radiatorRecommendedCommentBox,radiatorPriorityBox},{URHTechCommentBox,URHRecommendedCommentBox,URHPriorityBox},
				{LRHTechCommentBox,LRHRecommendedCommentBox,LRHPriorityBox},{HBHTechCommentBox,HBHRecommendedCommentBox,HBHPriorityBox},{SRPTechCommentBox,SRPRecommendedCommentBox,SRPPriorityBox},
				{SLTechCommentBox,SLRecommendedCommentBox,SLPriorityBox},{suspTechCommentBox,suspRecommendedCommentBox,suspPriorityBox},{alignTechCommentBox,alignRecommendedCommentBox,alignPriorityBox},
				{FSSTechCommentBox,FSSRecommendedCommentBox,FSSPriorityBox},{RSSTechCommentBox,RSSRecommendedCommentBox,RSSPriorityBox},{FBTechCommentBox,FBRecommendedCommentBox,FBPriorityBox},
				{RBTechCommentBox,RBRecommendedCommentBox,RBPriorityBox},{CVADTechCommentBox,CVADRecommendedCommentBox,CVADPriorityBox},{mufflerTechCommentBox,mufflerRecommendedCommentBox,mufflerPriorityBox},
				{EPTechCommentBox,EPRecommendedCommentBox,EPPriorityBox}};
		
		
		
		otherFields = new JTextField[][] {{other1Label,other1TechCommentBox,other1RecommendedCommentBox},{other2Label,other2TechCommentBox,other2RecommendedCommentBox},{other3Label,other3TechCommentBox,other3RecommendedCommentBox},
			{other4Label,other4TechCommentBox,other4RecommendedCommentBox},{other5Label,other5TechCommentBox,other5RecommendedCommentBox},{other6Label,other6TechCommentBox,other6RecommendedCommentBox},
			{other7Label,other7TechCommentBox,other7RecommendedCommentBox},{other8Label,other8TechCommentBox,other8RecommendedCommentBox}};
		
		otherComboBoxes = new JComboBox[] {other1PriorityBox, other2PriorityBox, other3PriorityBox, other4PriorityBox, other5PriorityBox,other6PriorityBox,other7PriorityBox,other8PriorityBox};
		
		
		numberFields = new ABMTextField[][] {{DSFTtireField,DSFTlaborField,DSFTTireField,DSFTLBRfield},{PSFTtireField,PSFTlaborField,PSFTTireField,PSFTLBRfield},{PSRTtireField,PSRTlaborField,PSRTTireField,PSRTLBRfield},
				{DSRTtireField,DSRTlaborField,DSRTTireField,DSRTLBRfield},{spareTireField,spareLaborField,otherSpareTireField,spareLBRfield},{WWPartsField,WWLaborField,WWPartField,WWLBRfield},{locksPartsField,locksLaborField,locksPartField,locksLBRfield},
				{ACHPartsField,ACHLaborField,ACHPartField,ACHLBRfield},{heaterPartsField,heaterLaborField,heaterPartField,heaterLBRfield},{wiperPartsField,wiperLaborField,wiperPartField,wiperLBRfield},{hornPartsField,hornLaborField,hornPartField,hornLBRfield},
				{HLPartsField,HLLaborField,HLPartField,HLLBRfield},{PTLPPartsField,PTLPLaborField,PTLPPartField,PTLPLBRfield},{brakePartsField,brakeLaborField,BLPartField,BLLBRfield},{DSPartsField,DSLaborField,DSPartField,DSLBRfield},{engineDiagPartsField,engineDiagLaborField,engineDiagPartField,engineDiagLBRfield},
				{MMPartsField,MMLaborField,MMPartField,MMLBRfield},{BCBPartsField,BCBLaborField,BCBPartField,BCBLBRfield},{EOPartsField,EOLaborField,EOPartField,EOLBRfield},{TFPartsField,TFLaborField,TFPartField,TFLBRfield},{WFPartsField,WFLaborField,WFPartField,WFLBRfield},
				{BFPartsField,BFLaborField,BFPartField,BFLBRfield},{PSFPartsField,PSFLaborField,PSFPartField,PSFLBRfield},{coolantPartsField,coolantLaborField,coolantPartField,coolantLBRfield},{SBPartsField,SBLaborField,SBPartField,SBLBRfield},{AFPartsField,AFLaborField,AFPartField,AFLBRfield}, 
				{FFPartsField,FFLaborField,FFPartField,FFLBRfield},{radiatorPartsField,radiatorLaborField,radiatorPartField,radiatorLBRfield},{URHPartsField,URHLaborField,URHPartField,URHLBRfield},{LRHPartsField,LRHLaborField,LRHPartField,LRHLBRfield},{HBHPartsField,HBHLaborField,HBHPartField,HBHLBRfield},
				{SRPPartsField,SRPLaborField,SRPPartField,SRPLBRfield},{SLPartsField,SLLaborField,SLPartField,SLLBRfield},{suspPartsField,suspLaborField,suspPartField,suspLBRfield},{alignPartsField,alignLaborField,alignPartField,alignLBRfield},{FSSPartsField,FSSLaborField,FSSPartField,FSSLBRfield},
				{RSSPartsField,RSSLaborField,RSSPartField,RSSLBRfield},{FBPartsField,FBLaborField,FBPartField,FBLBRfield},{RBPartsField,RBLaborField,RBPartField,RBLBRfield},{CVADPartsField,CVADLaborField,CVADSPartField,CVADSLBRfield},{mufflerPartsField,mufflerLaborField,mufflerPartField,mufflerLBRfield},
				{EPPartsField,EPLaborField,EPPartField,EPLBRfield},{other1PartsField,other1LaborField,other1PartField,other1LBRfield},{other2PartsField,other2LaborField,other2PartField,other2LBRfield},{other3PartsField,other3LaborField,other3PartField,other3LBRfield},
				{other4PartsField,other4LaborField,other4PartField,other4LBRfield},{other5PartsField,other5LaborField,other5PartField,other5LBRfield},{other6PartsField,other6LaborField,other6PartField,other6LBRfield},{other7PartsField,other7LaborField,other7PartField,other7LBRfield},
				{other8PartsField,other8LaborField,other8PartField,other8LBRfield}};
		
		
		container.add(midPanel,BorderLayout.CENTER);
		JScrollPane window = new JScrollPane(container);
		window.getVerticalScrollBar().setUnitIncrement(16);
		setContentPane(window);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}
	



	public class OKbuttonListener implements ActionListener{
		private JCheckBox box1, box2;
		
		public OKbuttonListener(JCheckBox box1, JCheckBox box2){
			this.box1 = box1;
			this.box2 = box2;
		}
		public void actionPerformed(ActionEvent e){
			box1.setBackground(null);
			box2.setBackground(null);
			if(e.getSource() == box1){
				if(box1.isSelected())
					box1.setBackground(Color.green);
				box2.setSelected(false);
				box2.setBackground(null);
			}
			else{
				if(box2.isSelected())
					box2.setBackground(Color.red);
				box1.setSelected(false);
				box1.setBackground(null);
			}
		}
	}
}
	



