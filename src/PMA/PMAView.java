package PMA;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

import Custom.*;

public class PMAView extends JFrame {

	DecimalFormat format, LBRformat;

	public JLabel nameLabel, dateLabel, tagsLabel, vehicleYearLabel,
			vehicleMakeLabel, vehicleModelLabel, workOrderLabel, licLabel,
			vinLabel, engineLabel, transLabel, milesLabel, customerConcernsLabel;
	
	public JTextField nameField, dateField, tagsField, vehicleYearField,
			vehicleMakeField, vehicleModelField, workOrderField, licField,
			vinField, engineField, transField, milesField;
	
	public JPanel northPanel, centerPanel, midPanel, techPanel;

	public JCheckBox[][] checkBoxes;

	public JCheckBox DSFTOK, DSFTNOTOK, PSFTOK, PSFTNOTOK, PSRTOK, PSRTNOTOK,
			DSRTOK, DSRTNOTOK, spareOK, spareNOTOK, WWOK, WWNOTOK, locksOK,
			locksNOTOK, ACHOK, ACHNOTOK, heaterOK, heaterNOTOK, wiperOK,
			wiperNOTOK, hornOK, hornNOTOK, HLOK, HLNOTOK, PTLOK, PTLNOTOK,
			BLOK, BLNOTOK, DSOK, DSNOTOK, engineDiagOK, engineDiagNOTOK, MMOK,
			MMNOTOK, BCBOK, BCBNOTOK, EOOK, EONOTOK, TFOK, TFNOTOK, WFOK,
			WFNOTOK, BFOK, BFNOTOK, PSFOK, PSFNOTOK, coolantOK, coolantNOTOK,
			SBOK, SBNOTOK, AFOK, AFNOTOK, FFOK, FFNOTOK, radiatorOK,
			radiatorNOTOK, URHOK, URHNOTOK, LRHOK, LRHNOTOK, HBHOK, HBHNOTOK,
			SRPOK, SRPNOTOK, SLOK, SLNOTOK, suspOK, suspNOTOK, alignOK,
			alignNOTOK, FSSOK, FSSNOTOK, RSSOK, RSSNOTOK, FBOK, FBNOTOK, RBOK,
			RBNOTOK, CVADOK, CVADNOTOK, mufflerOK, mufflerNOTOK, EPOK, EPNOTOK,
			other1OK, other1NOTOK, other2OK, other2NOTOK, other3OK,
			other3NOTOK, other4OK, other4NOTOK, other5OK, other5NOTOK,
			other6OK, other6NOTOK, other7OK, other7NOTOK, other8OK,
			other8NOTOK;

	public myComboBox[][] comboBoxes;

	public myComboBox DSFTtechCommentBox, DSFTrecommendedCommentBox,
			DSFTpriorityBox, PSFTtechCommentBox, PSFTrecommendedCommentBox,
			PSFTpriorityBox, PSRTtechCommentBox, PSRTrecommendedCommentBox,
			PSRTpriorityBox, DSRTtechCommentBox, DSRTrecommendedCommentBox,
			DSRTpriorityBox, spareTechCommentBox, spareRecommendedCommentBox,
			sparePriorityBox, WWTechCommentBox, WWRecommendedCommentBox,
			WWPriorityBox, locksTechCommentBox, locksRecommendedCommentBox,
			locksPriorityBox, ACHTechCommentBox, ACHRecommendedCommentBox,
			ACHPriorityBox, heaterTechCommentBox, heaterRecommendedCommentBox,
			heaterPriorityBox, wiperTechCommentBox, wiperRecommendedCommentBox,
			wiperPriorityBox, hornTechCommentBox, hornRecommendedCommentBox,
			hornPriorityBox, HLTechCommentBox, HLRecommendedCommentBox,
			HLPriorityBox, PTLTechCommentBox, PTLRecommendedCommentBox,
			PTLPriorityBox, brakeTechCommentBox, brakeRecommendedCommentBox,
			brakePriorityBox, DSTechCommentBox, DSRecommendedCommentBox,
			DSPriorityBox, engineDiagTechCommentBox,
			engineDiagRecommendedCommentBox, engineDiagPriorityBox,
			MMTechCommentBox, MMRecommendedCommentBox, MMPriorityBox,
			BCBTechCommentBox, BCBRecommendedCommentBox, BCBPriorityBox,
			EOTechCommentBox, EORecommendedCommentBox, EOPriorityBox,
			TFTechCommentBox, TFRecommendedCommentBox, TFPriorityBox,
			WFTechCommentBox, WFRecommendedCommentBox, WFPriorityBox,
			BFTechCommentBox, BFRecommendedCommentBox, BFPriorityBox,
			PSFTechCommentBox, PSFRecommendedCommentBox, PSFPriorityBox,
			coolantTechCommentBox, coolantRecommendedCommentBox,
			coolantPriorityBox, SBTechCommentBox, SBRecommendedCommentBox,
			SBPriorityBox, AFTechCommentBox, AFRecommendedCommentBox,
			AFPriorityBox, FFTechCommentBox, FFRecommendedCommentBox,
			FFPriorityBox, radiatorTechCommentBox,
			radiatorRecommendedCommentBox, radiatorPriorityBox,
			URHTechCommentBox, URHRecommendedCommentBox, URHPriorityBox,
			LRHTechCommentBox, LRHRecommendedCommentBox, LRHPriorityBox,
			HBHTechCommentBox, HBHRecommendedCommentBox, HBHPriorityBox,
			SRPTechCommentBox, SRPRecommendedCommentBox, SRPPriorityBox,
			SLTechCommentBox, SLRecommendedCommentBox, SLPriorityBox,
			suspTechCommentBox, suspRecommendedCommentBox, suspPriorityBox,
			alignTechCommentBox, alignRecommendedCommentBox, alignPriorityBox,
			FSSTechCommentBox, FSSRecommendedCommentBox, FSSPriorityBox,
			RSSTechCommentBox, RSSRecommendedCommentBox, RSSPriorityBox,
			FBTechCommentBox, FBRecommendedCommentBox, FBPriorityBox,
			RBTechCommentBox, RBRecommendedCommentBox, RBPriorityBox,
			CVADTechCommentBox, CVADRecommendedCommentBox, CVADPriorityBox,
			mufflerTechCommentBox, mufflerRecommendedCommentBox,
			mufflerPriorityBox, EPTechCommentBox, EPRecommendedCommentBox,
			EPPriorityBox;

	public JTextField[][] otherFields;

	public JTextField other1Label, other1TechCommentBox,
			other1RecommendedCommentBox, other2Label, other2TechCommentBox,
			other2RecommendedCommentBox, other3Label, other3TechCommentBox,
			other3RecommendedCommentBox, other4Label, other4TechCommentBox,
			other4RecommendedCommentBox, other5Label, other5TechCommentBox,
			other5RecommendedCommentBox, other6Label, other6TechCommentBox,
			other6RecommendedCommentBox, other7Label, other7TechCommentBox,
			other7RecommendedCommentBox, other8Label, other8TechCommentBox,
			other8RecommendedCommentBox;

	public JComboBox[] otherComboBoxes;

	public JComboBox other1PriorityBox, other2PriorityBox, other3PriorityBox,
			other4PriorityBox, other5PriorityBox, other6PriorityBox,
			other7PriorityBox, other8PriorityBox;

	public ABMTextField[][] totalFields;

	public ABMTextField DSFTtireField, DSFTlaborField, PSFTtireField,
			PSFTlaborField, PSRTtireField, PSRTlaborField, DSRTtireField,
			DSRTlaborField, spareTireField, spareLaborField, WWPartsField,
			WWLaborField, locksPartsField, locksLaborField, ACHPartsField,
			ACHLaborField, heaterPartsField, heaterLaborField, wiperPartsField,
			wiperLaborField, hornPartsField, hornLaborField, HLPartsField,
			HLLaborField, PTLPPartsField, PTLPLaborField, brakePartsField,
			brakeLaborField, DSPartsField, DSLaborField, engineDiagPartsField,
			engineDiagLaborField, MMPartsField, MMLaborField, BCBPartsField,
			BCBLaborField, EOPartsField, EOLaborField, TFPartsField,
			TFLaborField, WFPartsField, WFLaborField, BFPartsField,
			BFLaborField, PSFPartsField, PSFLaborField, coolantPartsField,
			coolantLaborField, SBPartsField, SBLaborField, AFPartsField,
			AFLaborField, FFPartsField, FFLaborField, radiatorPartsField,
			radiatorLaborField, URHPartsField, URHLaborField, LRHPartsField,
			LRHLaborField, HBHPartsField, HBHLaborField, SRPPartsField,
			SRPLaborField, SLPartsField, SLLaborField, suspPartsField,
			suspLaborField, alignPartsField, alignLaborField, FSSPartsField,
			FSSLaborField, RSSPartsField, RSSLaborField, FBPartsField,
			FBLaborField, RBPartsField, RBLaborField, CVADPartsField,
			CVADLaborField, mufflerPartsField, mufflerLaborField, EPPartsField,
			EPLaborField, other1PartsField, other1LaborField, other2PartsField,
			other2LaborField, other3PartsField, other3LaborField,
			other4PartsField, other4LaborField, other5PartsField,
			other5LaborField, other6PartsField, other6LaborField,
			other7PartsField, other7LaborField, other8PartsField,
			other8LaborField;

	public ABMTextField[][] numberFields;

	public ABMTextField DSFTTireField, DSFTLBRfield, PSFTTireField,
			PSFTLBRfield, PSRTTireField, PSRTLBRfield, DSRTTireField,
			DSRTLBRfield, otherSpareTireField, spareLBRfield, WWPartField,
			WWLBRfield, locksPartField, locksLBRfield, ACHPartField,
			ACHLBRfield, heaterPartField, heaterLBRfield, wiperPartField,
			wiperLBRfield, hornPartField, hornLBRfield, HLPartField,
			HLLBRfield, PTLPPartField, PTLPLBRfield, BLPartField, BLLBRfield,
			DSPartField, DSLBRfield, engineDiagPartField, engineDiagLBRfield,
			MMPartField, MMLBRfield, BCBPartField, BCBLBRfield, EOPartField,
			EOLBRfield, TFPartField, TFLBRfield, WFPartField, WFLBRfield,
			BFPartField, BFLBRfield, PSFPartField, PSFLBRfield,
			coolantPartField, coolantLBRfield, SBPartField, SBLBRfield,
			AFPartField, AFLBRfield, FFPartField, FFLBRfield,
			radiatorPartField, radiatorLBRfield, URHPartField, URHLBRfield,
			LRHPartField, LRHLBRfield, HBHPartField, HBHLBRfield, SRPPartField,
			SRPLBRfield, SLPartField, SLLBRfield, suspPartField, suspLBRfield,
			alignPartField, alignLBRfield, FSSPartField, FSSLBRfield,
			RSSPartField, RSSLBRfield, FBPartField, FBLBRfield, RBPartField,
			RBLBRfield, CVADSPartField, CVADSLBRfield, mufflerPartField,
			mufflerLBRfield, EPPartField, EPLBRfield, other1PartField,
			other1LBRfield, other2PartField, other2LBRfield, other3PartField,
			other3LBRfield, other4PartField, other4LBRfield, other5PartField,
			other5LBRfield, other6PartField, other6LBRfield, other7PartField,
			other7LBRfield, other8PartField, other8LBRfield;

	public JFTextField[] QTYfields;

	public JFTextField DSFTQTYfield, PSFTQTYfield, PSRTQTYfield, DSRTQTYfield,
			spareQTYfield, WWQTYfield, locksQTYfield, ACHQTYfield,
			heaterQTYfield, wiperQTYfield, hornQTYfield, HLQTYfield,
			PTLPQTYfield, BLQTYfield, DSQTYfield, engineDiagQTYfield,
			MMQTYfield, BCBQTYfield, EOQTYfield, TFQTYfield, WFQTYfield,
			BFQTYfield, PSFQTYfield, coolantQTYfield, SBQTYfield, AFQTYfield,
			FFQTYfield, radiatorQTYfield, URHQTYfield, LRHQTYfield,
			HBHQTYfield, SRPQTYfield, SLQTYfield, suspQTYfield, alignQTYfield,
			FSSQTYfield, RSSQTYfield, FBQTYfield, RBQTYfield, CVADSQTYfield,
			mufflerQTYfield, EPQTYfield, other1QTYfield, other2QTYfield,
			other3QTYfield, other4QTYfield, other5QTYfield, other6QTYfield,
			other7QTYfield, other8QTYfield;

	
	public JLButton[] buttonLabels;
	
	public JLButton DSFTLabel, PSFTLabel, PSRTLabel, DSRTLabel, spareLabel, WWLabel, locksLabel, ACHLabel, heaterLabel, wiperLabel, hornLabel, HLLabel, PTLLabel, brakeLabel, DSLabel, 
	engineDiagLabel, MMLabel, BCBLabel, EOLabel, TFLabel, WFLabel, BFLabel, PSFLabel, coolantLabel, SBLabel, AFLabel, FFLabel, radiatorLabel, URHLabel, LRHLabel, HBHLabel, SRPLabel, SLLabel, 
	suspLabel, alignLabel, FSSLabel, RSSLabel, FBLabel, RBLabel, CVADLabel, mufflerLabel, EPLabel;
	
	
	public JFormattedTextField totalParts, totalLabor, totalPrice, tax, shopSupplies, grandTotal, low, med, high;
	
	
	public vendorTextField[] vendorFields;
	
	public vendorTextField DSFTvendor, PSFTvendor, PSRTvendor, DSRTvendor, sparevendor, WWvendor,locksvendor, ACHvendor, heatervendor, wipervendor, hornvendor, HLvendor, PTLvendor, brakevendor, DSvendor, 
	engineDiagvendor, MMvendor, BCBvendor, EOvendor, TFvendor, WFvendor, BFvendor, PSFvendor, coolantvendor, SBvendor, AFvendor, FFvendor, radiatorvendor, URHvendor, LRHvendor, HBHvendor, SRPvendor, SLvendor, 
	suspvendor, alignvendor, FSSvendor, RSSvendor, FBvendor, RBvendor, CVADvendor, mufflervendor, EPvendor;
	
	
	public JTextArea customerConcerns;
	
	public JButton submit;

	public PMAView() {
		super("2FATGUYS PMA");

		UIManager.put("TextField.disabledBackground", Color.white);

		Color theme = new Color(81,127,164);
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 800);

		Container container = getContentPane();
		Font font2 = new Font("Verdana", Font.BOLD, 16);
		format = new DecimalFormat("$###,##0.00");

		JPanel eastPanel = new JPanel(new GridBagLayout());
		NumberFormat QTYformat = NumberFormat.getNumberInstance();
		LBRformat = new DecimalFormat("##,##0.0");

		/********************************************* NORTH PANEL *********************************************/
		northPanel = new JPanel();
		
		String reportDate;
		
		DateFormat df = new SimpleDateFormat("MMM d, yyyy");
		java.util.Date today = Calendar.getInstance().getTime();
		reportDate = df.format(today);
		
		

		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		northPanel.setLayout(layout);
		northPanel.setBackground(theme);
		c.insets = new Insets(3, 5, 2, 5);
		
		

		midPanel = new JPanel(new BorderLayout());

		techPanel = new JPanel();
		techPanel.setLayout(layout);
		
		

		Font font = new Font("SansSerif", Font.BOLD, 20);
		setFont(font);

		JPanel imagePanel = new JPanel();
		ImageIcon icon = new ImageIcon("2fatguyslogo.png");
		JLabel IconLabel = new JLabel();
		IconLabel.setIcon(icon);
		imagePanel.add(IconLabel);

		
		
		JLabel Title = new JLabel(
				"2 FAT GUYS YOUR COMPLETE AUTOMOTIVE SERVICE CENTERS \"WE DO IT ALL\"    107 COMMERCE BOERNE TX. 78006    830-249-2727");
		Title.setFont(font);
		Title.setForeground(Color.LIGHT_GRAY.brighter());
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 11;
		c.fill = GridBagConstraints.HORIZONTAL;
		//Title.setBorder(new LineBorder(Color.BLACK, 2));
		northPanel.add(Title, c);
		midPanel.add(northPanel, BorderLayout.NORTH);

		font = new Font("SansSerif", Font.BOLD, 20);
		setFont(font);

		nameField = new JTextField(20);
		nameField.setFont(font2);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		techPanel.add(nameField, c);
		nameLabel = new JLabel("CUSTOM");
		nameLabel.setBackground(theme);
		nameLabel.setForeground(Color.white);
		nameLabel.setBorder(new LineBorder(Color.BLACK, 2));
		nameLabel.setOpaque(true);
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(nameLabel, c);
		vehicleYearLabel = new JLabel("YEAR");
		vehicleYearLabel.setBackground(theme);
		vehicleYearLabel.setBorder(new LineBorder(Color.BLACK, 2));
		vehicleYearLabel.setOpaque(true);
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(vehicleYearLabel, c);
		vehicleYearField = new JTextField(20);
		vehicleYearField.setFont(font2);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(vehicleYearField, c);
		workOrderField = new JTextField(20);
		workOrderField.setFont(font2);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(workOrderField, c);
		workOrderLabel = new JLabel("W/O");
		workOrderLabel.setBackground(theme);
		workOrderLabel.setBorder(new LineBorder(Color.BLACK, 2));
		workOrderLabel.setOpaque(true);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(workOrderLabel, c);
		engineLabel = new JLabel("ENGINE");
		engineLabel.setBackground(theme);
		engineLabel.setBorder(new LineBorder(Color.BLACK, 2));
		engineLabel.setOpaque(true);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(engineLabel, c);
		engineField = new JTextField(6);
		engineField.setFont(font2);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(engineField, c);

		
		
		dateField = new JTextField(20);
		dateField.setFont(font2);
		dateField.setText(reportDate);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		techPanel.add(dateField, c);
		dateLabel = new JLabel("DATE");
		dateLabel.setBackground(theme);
		dateLabel.setBorder(new LineBorder(Color.BLACK, 2));
		dateLabel.setOpaque(true);
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(dateLabel, c);
		vehicleMakeLabel = new JLabel("MAKE");
		vehicleMakeLabel.setBackground(theme);
		vehicleMakeLabel.setBorder(new LineBorder(Color.BLACK, 2));
		vehicleMakeLabel.setOpaque(true);
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(vehicleMakeLabel, c);
		vehicleMakeField = new JTextField(20);
		vehicleMakeField.setFont(font2);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(vehicleMakeField, c);
		licField = new JTextField(20);
		licField.setFont(font2);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(licField, c);
		licLabel = new JLabel("LIC NUM");
		licLabel.setBackground(theme);
		licLabel.setBorder(new LineBorder(Color.BLACK, 2));
		licLabel.setOpaque(true);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(licLabel, c);
		transLabel = new JLabel("TRANS");
		transLabel.setBackground(theme);
		transLabel.setBorder(new LineBorder(Color.BLACK, 2));
		transLabel.setOpaque(true);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(transLabel, c);
		transField = new JTextField(6);
		transField.setFont(font2);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(transField, c);

		
		
		tagsField = new JTextField(20);
		tagsField.setFont(font2);
		c.gridx = 0;
		c.gridy = 3;
		c.gridwidth = 2;
		techPanel.add(tagsField, c);
		tagsLabel = new JLabel("TAGS");
		tagsLabel.setBackground(theme);
		tagsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		tagsLabel.setOpaque(true);
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(tagsLabel, c);
		vehicleModelLabel = new JLabel("MODEL");
		vehicleModelLabel.setBackground(theme);
		vehicleModelLabel.setBorder(new LineBorder(Color.BLACK, 2));
		vehicleModelLabel.setOpaque(true);
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(vehicleModelLabel, c);
		vehicleModelField = new JTextField(20);
		vehicleModelField.setFont(font2);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(vehicleModelField, c);
		vinField = new JTextField(20);
		vinField.setFont(font2);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(vinField, c);
		vinLabel = new JLabel("VIN");
		vinLabel.setBackground(theme);
		vinLabel.setBorder(new LineBorder(Color.BLACK, 2));
		vinLabel.setOpaque(true);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(vinLabel, c);
		milesLabel = new JLabel("MILES");
		milesLabel.setBackground(theme);
		milesLabel.setBorder(new LineBorder(Color.BLACK, 2));
		milesLabel.setOpaque(true);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(milesLabel, c);
		milesField = new JTextField(5);
		milesField.setFont(font2);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(milesField, c);

		
		
		customerConcernsLabel = new JLabel("CUSTOMER CONCERNS");
		c.gridx = 0;
		c.gridy = 4;
		c.gridheight = 3;
		c.fill= GridBagConstraints.VERTICAL;
		techPanel.add(customerConcernsLabel,c);
		

		
		customerConcerns = new JTextArea(5,20);
		customerConcerns.setFont(font2);
		customerConcerns.setBorder(new LineBorder(Color.black, 2));
		c.gridx = 1;
		c.gridy = 4;
		c.gridwidth = 10;
		c.gridheight = 3;
		c.fill = GridBagConstraints.BOTH;
		techPanel.add(customerConcerns,c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridheight = 1;
		

		

		/********************************************** MIDPANEL *************************************************************************/


		/******************************** TIRES ******************************************/

		JLabel tireLabel = new JLabel("205/50ZR17    ");
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		tireLabel.setBackground(theme);
		tireLabel.setBorder(new LineBorder(Color.BLACK, 2));
		tireLabel.setOpaque(true);
		techPanel.add(tireLabel, c);
		JLabel okayLabel = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		okayLabel.setBackground(theme);
		okayLabel.setBorder(new LineBorder(Color.BLACK, 2));
		okayLabel.setOpaque(true);
		techPanel.add(okayLabel, c);
		JLabel notOkayLabel = new JLabel("NOT OK");
		c.gridx = 3;
		c.gridwidth = 1;
		notOkayLabel.setBackground(theme);
		notOkayLabel.setBorder(new LineBorder(Color.BLACK, 2));
		notOkayLabel.setOpaque(true);
		techPanel.add(notOkayLabel, c);
		JLabel techCommentsLabel = new JLabel("TECHNICIANS COMMENTS");
		c.gridx = 4;
		c.gridwidth = 2;
		techCommentsLabel.setBackground(theme);
		techCommentsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		techCommentsLabel.setOpaque(true);
		techPanel.add(techCommentsLabel, c);
		JLabel recommendedRepairLabel = new JLabel("RECOMMENDED REPAIRS");
		c.gridx = 6;
		c.gridwidth = 2;
		recommendedRepairLabel.setBackground(theme);
		recommendedRepairLabel.setBorder(new LineBorder(Color.BLACK, 2));
		recommendedRepairLabel.setOpaque(true);
		techPanel.add(recommendedRepairLabel, c);
		JLabel priorityLabel = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		priorityLabel.setBackground(theme);
		priorityLabel.setBorder(new LineBorder(Color.BLACK, 2));
		priorityLabel.setOpaque(true);
		techPanel.add(priorityLabel, c);
		JLabel tiresLabel = new JLabel("TIRES");
		c.gridx = 9;
		c.gridwidth = 1;
		tiresLabel.setBackground(theme);
		tiresLabel.setBorder(new LineBorder(Color.BLACK, 2));
		tiresLabel.setOpaque(true);
		techPanel.add(tiresLabel, c);
		JLabel laborLabel = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		laborLabel.setBackground(theme);
		laborLabel.setBorder(new LineBorder(Color.BLACK, 2));
		laborLabel.setOpaque(true);
		techPanel.add(laborLabel, c);

		String[] tireTechComments = { "", "TIRE IS WORN OUT", "TIRE IS FLAT",
				"TIRE HAS HOLE IN SIDE WALL", "TIRE HAS IRREGULAR WEAR",
				"TIRE HAS ALIGNMENT WEAR", "1/32 TREAD", "2/32 TREAD",
				"3/32 TREAD", "4/32 TREAD", "5/32 TREAD", "6/32 TREAD",
				"7/32 TREAD", "8/32 TREAD", "9/32 TREAD", "10/32 TREAD",
				"11/32 TREAD", "12/32 TREAD", "13/32 TREAD", "14/32 TREAD",
				"15/32 TREAD" };
		String[] tireRecommendedComments = { "", "REPLACE TIRE", "REPAIR TIRE",
				"ROTATE TIRE" };
		String[] priority = { "", "HIGH", "MED", "LOW" };


		
		
		JLButton DSFTLabel = new JLButton("DRIVER SIDE FRONT TIRE");
		c.gridx = 0;
		c.gridy = 8;
		c.gridwidth = 2;
		techPanel.add(DSFTLabel, c);
		DSFTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(DSFTOK, c);
		DSFTNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(DSFTNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		OKbuttonListener listener = new OKbuttonListener(DSFTOK, DSFTNOTOK, 0);
		DSFTNOTOK.addActionListener(listener);
		DSFTOK.addActionListener(listener);
		DSFTtechCommentBox = new myComboBox(tireTechComments);
		DSFTtechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(DSFTtechCommentBox, c);
		DSFTrecommendedCommentBox = new myComboBox(tireRecommendedComments);
		DSFTrecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(DSFTrecommendedCommentBox, c);
		DSFTpriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(DSFTpriorityBox, c);
		DSFTtireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(DSFTtireField, c);
		DSFTlaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(DSFTlaborField, c);

		JLButton PSFTLabel = new JLButton("PASSENGER SIDE FRONT TIRE");
		c.gridx = 0;
		c.gridy = 9;
		c.gridwidth = 1;
		techPanel.add(PSFTLabel, c);
		PSFTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(PSFTOK, c);
		PSFTNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(PSFTNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(PSFTOK, PSFTNOTOK, 1);
		PSFTNOTOK.addActionListener(listener);
		PSFTOK.addActionListener(listener);
		PSFTtechCommentBox = new myComboBox(tireTechComments);
		PSFTtechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(PSFTtechCommentBox, c);
		PSFTrecommendedCommentBox = new myComboBox(tireRecommendedComments);
		PSFTrecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(PSFTrecommendedCommentBox, c);
		PSFTpriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(PSFTpriorityBox, c);
		PSFTtireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(PSFTtireField, c);
		PSFTlaborField = new ABMTextField(format); 			
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(PSFTlaborField, c);
		

		JLButton PSRTLabel = new JLButton("PASSENGER SIDE REAR TIRE    ");
		c.gridx = 0;
		c.gridy = 10;
		c.gridwidth = 1;
		techPanel.add(PSRTLabel, c);
		PSRTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(PSRTOK, c);
		PSRTNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(PSRTNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(PSRTOK, PSRTNOTOK, 2);
		PSRTNOTOK.addActionListener(listener);
		PSRTOK.addActionListener(listener);
		PSRTtechCommentBox = new myComboBox(tireTechComments);
		PSRTtechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(PSRTtechCommentBox, c);
		PSRTrecommendedCommentBox = new myComboBox(tireRecommendedComments);
		PSRTrecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(PSRTrecommendedCommentBox, c);
		PSRTpriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(PSRTpriorityBox, c);
		PSRTtireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(PSRTtireField, c);
		PSRTlaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(PSRTlaborField, c);

		JLButton DSRTLabel = new JLButton("DRIVER SIDE REAR TIRE");
		c.gridx = 0;
		c.gridy = 11;
		c.gridwidth = 1;
		techPanel.add(DSRTLabel, c);
		DSRTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(DSRTOK, c);
		DSRTNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(DSRTNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(DSRTOK, DSRTNOTOK, 3);
		DSRTNOTOK.addActionListener(listener);
		DSRTOK.addActionListener(listener);
		DSRTtechCommentBox = new myComboBox(tireTechComments);
		DSRTtechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(DSRTtechCommentBox, c);
		DSRTrecommendedCommentBox = new myComboBox(tireRecommendedComments);
		DSRTrecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(DSRTrecommendedCommentBox, c);
		DSRTpriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(DSRTpriorityBox, c);
		DSRTtireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(DSRTtireField, c);
		DSRTlaborField = new ABMTextField(format); 
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(DSRTlaborField, c);
		

		JLButton spareLabel = new JLButton("SPARE");
		c.gridx = 0;
		c.gridy = 12;
		c.gridwidth = 1;
		techPanel.add(spareLabel, c);
		spareOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(spareOK, c);
		spareNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(spareNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(spareOK, spareNOTOK, 4);
		spareNOTOK.addActionListener(listener);
		spareOK.addActionListener(listener);
		spareTechCommentBox = new myComboBox(tireTechComments);
		spareTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(spareTechCommentBox, c);
		spareRecommendedCommentBox = new myComboBox(tireRecommendedComments);
		spareRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(spareRecommendedCommentBox, c);
		sparePriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(sparePriorityBox, c);
		spareTireField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(spareTireField, c);
		spareLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(spareLaborField, c);

		/*************************** INTERIOR AND EXTERIOR ********************/

		JLabel INTEXTLabel = new JLabel("INTERIOR & EXTERIOR"); // this obviosly
																// needs to
																// change
		c.gridx = 0;
		c.gridy = 13;
		c.gridwidth = 2;
		INTEXTLabel.setBackground(theme);
		INTEXTLabel.setBorder(new LineBorder(Color.BLACK, 2));
		INTEXTLabel.setOpaque(true);
		techPanel.add(INTEXTLabel, c);
		JLabel okay2Label = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		okay2Label.setBackground(theme);
		okay2Label.setBorder(new LineBorder(Color.BLACK, 2));
		okay2Label.setOpaque(true);
		techPanel.add(okay2Label, c);
		JLabel notOkay2Label = new JLabel("NOT OK");
		c.gridx = 3;
		c.gridwidth = 1;
		notOkay2Label.setBackground(theme);
		notOkay2Label.setBorder(new LineBorder(Color.BLACK, 2));
		notOkay2Label.setOpaque(true);
		techPanel.add(notOkay2Label, c);
		JLabel techComments2Label = new JLabel("TECHNICIANS COMMENTS");
		c.gridx = 4;
		c.gridwidth = 2;
		techComments2Label.setBackground(theme);
		techComments2Label.setBorder(new LineBorder(Color.BLACK, 2));
		techComments2Label.setOpaque(true);
		techPanel.add(techComments2Label, c);
		JLabel recommendedRepair2Label = new JLabel("RECOMMENDED REPAIRS");
		c.gridx = 6;
		c.gridwidth = 2;
		recommendedRepair2Label.setBackground(theme);
		recommendedRepair2Label.setBorder(new LineBorder(Color.BLACK, 2));
		recommendedRepair2Label.setOpaque(true);
		techPanel.add(recommendedRepair2Label, c);
		JLabel priority2Label = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		priority2Label.setBackground(theme);
		priority2Label.setBorder(new LineBorder(Color.BLACK, 2));
		priority2Label.setOpaque(true);
		techPanel.add(priority2Label, c);
		JLabel partsLabel = new JLabel("PARTS");
		c.gridx = 9;
		c.gridwidth = 1;
		partsLabel.setBackground(theme);
		partsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		partsLabel.setOpaque(true);
		techPanel.add(partsLabel, c);
		JLabel labor2Label = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		labor2Label.setBackground(theme);
		labor2Label.setBorder(new LineBorder(Color.BLACK, 2));
		labor2Label.setOpaque(true);
		techPanel.add(labor2Label, c);

		String[] WWTechComments = { "", "WINDSHIELD IS CRACKED",
				"WINDSHIELD HAS CHIP IN IT", "WINDSHIELD IS SCRATCHED",
				"DRIVERS SIDE FRONT WINDOW NOT GOING UP",
				"PASSENGER SIDE FRONT WINDOW NOT GOING UP",
				"DRIVERS SIDE REAR WINDOW WILL NOT GO UP",
				"PASSENGER SIDE REAR WINDOW NOT GOING UP", };
		String[] WWRecommendedComments = { "", "REPLACE WINDSHIELD",
				"INFORMATION ONLY", "DO DIAGNOSTIC ON WINDOW" };

		JLButton WWLabel = new JLButton("WINDOWS & WINDSHIELD");
		c.gridx = 0;
		c.gridy = 14;
		c.gridwidth = 1;
		techPanel.add(WWLabel, c);
		WWOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(WWOK, c);
		WWNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(WWNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(WWOK, WWNOTOK, 5);
		WWNOTOK.addActionListener(listener);
		WWOK.addActionListener(listener);
		WWTechCommentBox = new myComboBox(WWTechComments);
		WWTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(WWTechCommentBox, c);
		WWRecommendedCommentBox = new myComboBox(WWRecommendedComments);
		WWRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(WWRecommendedCommentBox, c);
		WWPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(WWPriorityBox, c);
		WWPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(WWPartsField, c);
		WWLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(WWLaborField, c);

		String[] locksTechComments = { "",
				"DRIVERS SIDE FRONT LOCK NOT WORKING",
				"PASSENGER SIDE FRONT LOCK NOT WORKING",
				"DRIVERS SIDE REAR LOCK NOT WORKING",
				"PASSENGER SIDE REAR LOCK NOT WORKING",
				"POWER LOCKS NOT WORKING" };
		String[] locksRecommendedComments = { "", "DO DIAGNOSTICS ON LOCKS" };

		locksLabel = new JLButton("LOCKS");
		c.gridx = 0;
		c.gridy = 15;
		c.gridwidth = 1;
		techPanel.add(locksLabel, c);
		locksOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(locksOK, c);
		locksNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(locksNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(locksOK, locksNOTOK, 6);
		locksNOTOK.addActionListener(listener);
		locksOK.addActionListener(listener);
		locksTechCommentBox = new myComboBox(locksTechComments);
		locksTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(locksTechCommentBox, c);
		locksRecommendedCommentBox = new myComboBox(locksRecommendedComments);
		locksRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(locksRecommendedCommentBox, c);
		locksPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(locksPriorityBox, c);
		locksPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(locksPartsField, c);
		locksLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(locksLaborField, c);

		String[] ACHTechComments = { "", "NEEDS DIAGNOSTIC ON A/C SYSTEM",
				"LOW ON FREON", "HAS LEAK IN A/C SYSTEM",
				"NOT COOLING VERY WELL", "A/C SWITCH IS BROKEN" };
		String[] ACHRecommendedComments = { "", "DO A/C SERVICE/DIAGNOSTIC" };

		JLButton ACHLabel = new JLButton("A/C AND HEATER");
		c.gridx = 0;
		c.gridy = 16;
		c.gridwidth = 1;
		techPanel.add(ACHLabel, c);
		ACHOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(ACHOK, c);
		ACHNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(ACHNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(ACHOK, ACHNOTOK, 7);
		ACHNOTOK.addActionListener(listener);
		ACHOK.addActionListener(listener);
		ACHTechCommentBox = new myComboBox(ACHTechComments);
		ACHTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(ACHTechCommentBox, c);
		ACHRecommendedCommentBox = new myComboBox(ACHRecommendedComments);
		ACHRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(ACHRecommendedCommentBox, c);
		ACHPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(ACHPriorityBox, c);
		ACHPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(ACHPartsField, c);
		ACHLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(ACHLaborField, c);

		String[] heaterTechComments = { "", "NEEDS DIAGNOSTIC ON HEATER",
				"LOW ON COOLANT", "NEEDS HEATER CONTROL VALVE",
				"HEATER CORE LEAKING" };
		String[] heaterRecommendedComments = { "",
				"DO DIAGNOSTIC ON HEATER SYSTEM", "DO COOLANT PRESSURE TEST",
				"REPLACE HEATER CONTROL VALVE" };

		JLButton heaterLabel = new JLButton("HEATER");
		c.gridx = 0;
		c.gridy = 17;
		c.gridwidth = 1;
		techPanel.add(heaterLabel, c);
		heaterOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(heaterOK, c);
		heaterNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(heaterNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(heaterOK, heaterNOTOK, 8);
		heaterNOTOK.addActionListener(listener);
		heaterOK.addActionListener(listener);
		heaterTechCommentBox = new myComboBox(heaterTechComments);
		heaterTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(heaterTechCommentBox, c);
		heaterRecommendedCommentBox = new myComboBox(heaterRecommendedComments);
		heaterRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(heaterRecommendedCommentBox, c);
		heaterPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(heaterPriorityBox, c);
		heaterPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(heaterPartsField, c);
		heaterLaborField = new ABMTextField(format); // change this to
														// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(heaterLaborField, c);

		String[] wiperTechComments = { "", "WIPERS NOT WORKING",
				"WIPERS STREAKING", "SQUIRTERS NOT WORKING" };
		String[] wiperRecommendedComments = { "",
				"DO DIAGNOSTIC ON WIPER SYSTEM", "REPLACE WIPERS",
				"DO DIAGNOSTIC ON SQUIRTERS" };

		JLButton wiperLabel = new JLButton("WIPER");
		c.gridx = 0;
		c.gridy = 18;
		c.gridwidth = 1;
		techPanel.add(wiperLabel, c);
		wiperOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(wiperOK, c);
		wiperNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(wiperNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(wiperOK, wiperNOTOK, 9);
		wiperNOTOK.addActionListener(listener);
		wiperOK.addActionListener(listener);
		wiperTechCommentBox = new myComboBox(wiperTechComments);
		wiperTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(wiperTechCommentBox, c);
		wiperRecommendedCommentBox = new myComboBox(wiperRecommendedComments);
		wiperRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(wiperRecommendedCommentBox, c);
		wiperPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(wiperPriorityBox, c);
		wiperPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(wiperPartsField, c);
		wiperLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(wiperLaborField, c);

		String[] hornTechComments = { "", "", "" };
		String[] hornRecommendedComments = { "", "DO DIAGNOSTIC ON HORN" };

		JLButton hornLabel = new JLButton("HORN");
		c.gridx = 0;
		c.gridy = 19;
		c.gridwidth = 1;
		techPanel.add(hornLabel, c);
		hornOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(hornOK, c);
		hornNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(hornNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(hornOK, hornNOTOK, 10);
		hornNOTOK.addActionListener(listener);
		hornOK.addActionListener(listener);
		hornTechCommentBox = new myComboBox(hornTechComments);
		hornTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(hornTechCommentBox, c);
		hornRecommendedCommentBox = new myComboBox(hornRecommendedComments);
		hornRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(hornRecommendedCommentBox, c);
		hornPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(hornPriorityBox, c);
		hornPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(hornPartsField, c);
		hornLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(hornLaborField, c);

		String[] HLTechComments = { "", "DRIVER SIDE HIGH BEAM IS OUT",
				"DRIVER SIDE LOW BEAM IS OUT",
				"PASSENGER SIDE HIGH BEAM IS OUT",
				"PASSENGER SIDE LOW BEAM IS OUT",
				"DRIVER SIDE HEALIGHT HAS CRACK IN IT",
				"PASSENGER SIDE HEADLIGHT HAS CRACK IN IT",
				"HEAD LIGHTS DO NOT WORK" };
		String[] HLRecommendedComments = { "", "REPLACE LIGHT(S)",
				"DO DIAGNOSTICS ON HEAD LIGHTS" };

		JLButton HLLabel = new JLButton("HEADLAMPS");
		c.gridx = 0;
		c.gridy = 20;
		c.gridwidth = 1;
		techPanel.add(HLLabel, c);
		HLOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(HLOK, c);
		HLNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(HLNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(HLOK, HLNOTOK, 11);
		HLNOTOK.addActionListener(listener);
		HLOK.addActionListener(listener);
		HLTechCommentBox = new myComboBox(HLTechComments);
		HLTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(HLTechCommentBox, c);
		HLRecommendedCommentBox = new myComboBox(HLRecommendedComments);
		HLRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(HLRecommendedCommentBox, c);
		HLPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(HLPriorityBox, c);
		HLPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(HLPartsField, c);
		HLLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(HLLaborField, c);

		String[] PTLTechComments = { "", "DRIVER SIDE REAR TAIL LIGHT IS OUT",
				"PASSENGER SIDE REAR TAIL LIGHT IS OUT",
				"PASSENGER SIDE TAIL LIGHT IS CRACKED",
				"DRIVER SIDE TAIL LIGHT IS CRACKED",
				"PARK/TAIL LAMP DO NOT WORK", "LIC PLATE LIGHT IS OUT" };
		String[] PTLRecommendedComments = { "", "REPLACE LIGHT(S)",
				"DO DIAGNOSTIC ON PARK/TAIL LAMP" };

		JLButton PTLLabel = new JLButton("PARK/TAIL/LIC PLATE LAMP");
		c.gridx = 0;
		c.gridy = 21;
		c.gridwidth = 1;
		techPanel.add(PTLLabel, c);
		PTLOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(PTLOK, c);
		PTLNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(PTLNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(PTLOK, PTLNOTOK, 12);
		PTLNOTOK.addActionListener(listener);
		PTLOK.addActionListener(listener);
		PTLTechCommentBox = new myComboBox(PTLTechComments);
		PTLTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(PTLTechCommentBox, c);
		PTLRecommendedCommentBox = new myComboBox(PTLRecommendedComments);
		PTLRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(PTLRecommendedCommentBox, c);
		PTLPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(PTLPriorityBox, c);
		PTLPPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(PTLPPartsField, c);
		PTLPLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(PTLPLaborField, c);

		String[] brakeTechComments = { "", "DRIVER SIDE BRAKE LIGHT IS OUT",
				"PASSENGER SIDE BRAKE LIGHT IS OUT",
				"PASSENGER SIDE BRAKE LIGHT IS CRACKED",
				"DRIVER SIDE BRAKE LIGHT IS CRACKED",
				"BRAKE LIGHTS DO NOT WORK" };
		String[] brakeRecommendedComments = { "", "REPLACE LIGHT(S)",
				"DO DIAGNOSTICS ON BRAKE LIGHTS" };

		JLButton brakeLabel = new JLButton("BRAKE LAMP");
		c.gridx = 0;
		c.gridy = 22;
		c.gridwidth = 1;
		techPanel.add(brakeLabel, c);
		BLOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(BLOK, c);
		BLNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(BLNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(BLOK, BLNOTOK, 13);
		BLNOTOK.addActionListener(listener);
		BLOK.addActionListener(listener);
		brakeTechCommentBox = new myComboBox(brakeTechComments);
		brakeTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(brakeTechCommentBox, c);
		brakeRecommendedCommentBox = new myComboBox(brakeRecommendedComments);
		brakeRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(brakeRecommendedCommentBox, c);
		brakePriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(brakePriorityBox, c);
		brakePartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(brakePartsField, c);
		brakeLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(brakeLaborField, c);

		String[] DSTechComments = { "", "PASSENGER SIDE FENDER IS DENTED",
				"DRIVER SIDE FENDER IS DENTED",
				"DRIVER SIDE FRONT DOOR IS DENTED",
				"DRIVER SIDE REAR DOOR IS DENTED",
				"PASSENGER FRONT DOOR IS DENTED",
				"PASSENGER SIDE REAR DOOR IS DENTED",
				"DRIVER SIDE REAR QUARTER IS DENTED",
				"PASSENGER SIDE REAR QUARTER IS DENTED", "TRUNK LID IS DENTED",
				"HOOD IS DENTED", "TAILGATE IS DENTED", "DENTS/SCRATCHES" };
		String[] DSRecommendedComments = { "", "DO ESTIMATE ON BODYWORK",
				"INFORMATION ONLY" };

		JLButton DSLabel = new JLButton("DENTS/SCRATCHES");
		c.gridx = 0;
		c.gridy = 23;
		c.gridwidth = 1;
		techPanel.add(DSLabel, c);
		DSOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(DSOK, c);
		DSNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(DSNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(DSOK, DSNOTOK, 14);
		DSNOTOK.addActionListener(listener);
		DSOK.addActionListener(listener);
		DSTechCommentBox = new myComboBox(DSTechComments);
		DSTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(DSTechCommentBox, c);
		DSRecommendedCommentBox = new myComboBox(DSRecommendedComments);
		DSRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(DSRecommendedCommentBox, c);
		DSPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(DSPriorityBox, c);
		DSPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(DSPartsField, c);
		DSLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(DSLaborField, c);

		/******************************************** UNDER HOOD *************************/

		JLabel underHoodLabel = new JLabel("UNDER HOOD"); // this obviosly needs
															// to change
		c.gridx = 0;
		c.gridy = 24;
		c.gridwidth = 2;
		underHoodLabel.setBackground(theme);
		underHoodLabel.setBorder(new LineBorder(Color.BLACK, 2));
		underHoodLabel.setOpaque(true);
		techPanel.add(underHoodLabel, c);
		JLabel okay3Label = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		okay3Label.setBackground(theme);
		okay3Label.setBorder(new LineBorder(Color.BLACK, 2));
		okay3Label.setOpaque(true);
		techPanel.add(okay3Label, c);
		JLabel notOkay3Label = new JLabel("NOT OK");
		c.gridx = 3;
		c.gridwidth = 1;
		notOkay3Label.setBackground(theme);
		notOkay3Label.setBorder(new LineBorder(Color.BLACK, 2));
		notOkay3Label.setOpaque(true);
		techPanel.add(notOkay3Label, c);
		JLabel techComments3Label = new JLabel("TECHNICIANS COMMENTS");
		c.gridx = 4;
		c.gridwidth = 2;
		techComments3Label.setBackground(theme);
		techComments3Label.setBorder(new LineBorder(Color.BLACK, 2));
		techComments3Label.setOpaque(true);
		techPanel.add(techComments3Label, c);
		JLabel recommendedRepair3Label = new JLabel("RECOMMENDED REPAIRS");
		c.gridx = 6;
		c.gridwidth = 2;
		recommendedRepair3Label.setBackground(theme);
		recommendedRepair3Label.setBorder(new LineBorder(Color.BLACK, 2));
		recommendedRepair3Label.setOpaque(true);
		techPanel.add(recommendedRepair3Label, c);
		JLabel priority3Label = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		priority3Label.setBackground(theme);
		priority3Label.setBorder(new LineBorder(Color.BLACK, 2));
		priority3Label.setOpaque(true);
		techPanel.add(priority3Label, c);
		JLabel parts2Label = new JLabel("PARTS");
		c.gridx = 9;
		c.gridwidth = 1;
		parts2Label.setBackground(theme);
		parts2Label.setBorder(new LineBorder(Color.BLACK, 2));
		parts2Label.setOpaque(true);
		techPanel.add(parts2Label, c);
		JLabel labor3Label = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		labor3Label.setBackground(theme);
		labor3Label.setBorder(new LineBorder(Color.BLACK, 2));
		labor3Label.setOpaque(true);
		techPanel.add(labor3Label, c);

		String[] engineDiagTechComments = { "", "CHECK ENGINE LIGHT IS ON",
				"VEHICLE RUNS ROUGH", "NEED TO DO DIAGNOSTICS" };
		String[] engineDiagRecommendedComments = { "", "DO DIAGNOSTICS",
				"INFORMATION ONLY" };

		JLButton engineDiagLabel = new JLButton("ENGINE DIAGNOSTIC");
		c.gridx = 0;
		c.gridy = 25;
		c.gridwidth = 1;
		techPanel.add(engineDiagLabel, c);
		engineDiagOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(engineDiagOK, c);
		engineDiagNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(engineDiagNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(engineDiagOK, engineDiagNOTOK, 15);
		engineDiagNOTOK.addActionListener(listener);
		engineDiagOK.addActionListener(listener);
		engineDiagTechCommentBox = new myComboBox(engineDiagTechComments);
		engineDiagTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(engineDiagTechCommentBox, c);
		engineDiagRecommendedCommentBox = new myComboBox(
				engineDiagRecommendedComments);
		engineDiagRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(engineDiagRecommendedCommentBox, c);
		engineDiagPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(engineDiagPriorityBox, c);
		engineDiagPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(engineDiagPartsField, c);
		engineDiagLaborField = new ABMTextField(format); // change this to
															// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(engineDiagLaborField, c);

		String[] MMTechComments = { "", "DRIVER SIDE MOTOR MOUNT IS BROKEN",
				"PASSENGER SIDE MOTOR MOUNT IS BROKEN",
				"BOTH MOTOR MOUNTS ARE BROKEN", "MOTOR MOUNTS ARE WEAK",
				"NEED TO CHECK MOTOR MOUNTS" };
		String[] MMRecommendedComments = { "",
				"REPLACE DRIVER SIDE MOTOR MOUNT",
				"REPLACE PASSENGER SIDE MOTOR MOUNT",
				"REPLACE BOTH MOTOR MOUNTS" };

		JLButton MMLabel = new JLButton("MOTOR MOUNTS");
		c.gridx = 0;
		c.gridy = 26;
		c.gridwidth = 1;
		techPanel.add(MMLabel, c);
		MMOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(MMOK, c);
		MMNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(MMNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(MMOK, MMNOTOK, 16);
		MMNOTOK.addActionListener(listener);
		MMOK.addActionListener(listener);
		MMTechCommentBox = new myComboBox(MMTechComments);
		MMTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(MMTechCommentBox, c);
		MMRecommendedCommentBox = new myComboBox(MMRecommendedComments);
		MMRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(MMRecommendedCommentBox, c);
		MMPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(MMPriorityBox, c);
		MMPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(MMPartsField, c);
		MMLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(MMLaborField, c);

		String[] BCBTechComments = { "", "BATTERY TERMINALS DIRTY",
				"SEAL BATTERY TERMINALS", "BATTERY TERMINALS CORRODED",
				"BATTERY CABLES BROKEN", "BATTERY CABLES CORRODED",
				"NEEDS BATTERY" };
		String[] BCBRecommendedComments = { "",
				"CLEAN AND SEAL BATTERY TERMINALS", "REPLACE(1) BATTERY END",
				"REPLACE(2) BATTERY ENDS", "REPLACE POSITIVE BATTERY CABLE",
				"REPLACE NEGATIVE BATTERY CABLE",
				"REPLACE BOTH BATTERY CABLES", "REPLACE BATTERY" };

		JLButton BCBLabel = new JLButton("BATTERY CABLES & BATTERY");
		c.gridx = 0;
		c.gridy = 27;
		c.gridwidth = 1;
		techPanel.add(BCBLabel, c);
		BCBOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(BCBOK, c);
		BCBNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(BCBNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(BCBOK, BCBNOTOK, 17);
		BCBNOTOK.addActionListener(listener);
		BCBOK.addActionListener(listener);
		BCBTechCommentBox = new myComboBox(BCBTechComments);
		BCBTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(BCBTechCommentBox, c);
		BCBRecommendedCommentBox = new myComboBox(BCBRecommendedComments);
		BCBRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(BCBRecommendedCommentBox, c);
		BCBPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(BCBPriorityBox, c);
		BCBPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(BCBPartsField, c);
		BCBLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(BCBLaborField, c);

		String[] EOTechComments = { "", "ENGINE OIL IS LOW",
				"ENGINE OIL IS DIRTY", "ENGINE OIL IS LOW AND DIRTY",
				"ENGINE HAS OIL LEAK" };
		String[] EORecommendedComments = { "", "DO OIL CHANGE", "ADD OIL",
				"DO DYE TEST TO CHECK FOR OIL LEAK" };

		JLButton EOLabel = new JLButton("ENGINE OIL");
		c.gridx = 0;
		c.gridy = 28;
		c.gridwidth = 1;
		techPanel.add(EOLabel, c);
		EOOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(EOOK, c);
		EONOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(EONOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(EOOK, EONOTOK, 18);
		EONOTOK.addActionListener(listener);
		EOOK.addActionListener(listener);
		EOTechCommentBox = new myComboBox(EOTechComments);
		EOTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(EOTechCommentBox, c);
		EORecommendedCommentBox = new myComboBox(EORecommendedComments);
		EORecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(EORecommendedCommentBox, c);
		EOPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(EOPriorityBox, c);
		EOPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(EOPartsField, c);
		EOLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(EOLaborField, c);

		String[] TFTechComments = { "", "TRANSMISSION FLUID IS LOW",
				"TRANSMISSION FLUID IS DIRTY",
				"TRANSMISSION FLUID IS LOW AND DIRTY",
				"TRANSMISSION HAS OIL LEAK" };
		String[] TFRecommendedComments = { "", "DO TRANSMISSION SERVICE",
				"ADD TRANSMISSION FLUID", "DO DYE TEST TO CHECK FOR FLUID LEAK" };

		JLButton TFLabel = new JLButton("TRANSMISSION FLUID");
		c.gridx = 0;
		c.gridy = 29;
		c.gridwidth = 1;
		techPanel.add(TFLabel, c);
		TFOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(TFOK, c);
		TFNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(TFNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(TFOK, TFNOTOK, 19);
		TFNOTOK.addActionListener(listener);
		TFOK.addActionListener(listener);
		TFTechCommentBox = new myComboBox(TFTechComments);
		TFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(TFTechCommentBox, c);
		TFRecommendedCommentBox = new myComboBox(TFRecommendedComments);
		TFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(TFRecommendedCommentBox, c);
		TFPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(TFPriorityBox, c);
		TFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(TFPartsField, c);
		TFLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(TFLaborField, c);

		String[] WFTechComments = { "", "WASHER FLUID IS LOW",
				"SQUIRTERS NOT WORKING", "WASHER FLUID BOTTLE IS CRACKED" };
		String[] WFRecommendedComments = { "", "ADD WASHER FLUID",
				"DO DIAGNOSTICS ON SQUIRTERS",
				"REPLACE WINDSHIELD WASHER BOTTLE" };

		JLButton WFLabel = new JLButton("WASHER FLUID");
		c.gridx = 0;
		c.gridy = 30;
		c.gridwidth = 1;
		techPanel.add(WFLabel, c);
		WFOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(WFOK, c);
		WFNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(WFNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(WFOK, WFNOTOK, 20);
		WFNOTOK.addActionListener(listener);
		WFOK.addActionListener(listener);
		WFTechCommentBox = new myComboBox(WFTechComments);
		WFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(WFTechCommentBox, c);
		WFRecommendedCommentBox = new myComboBox(WFRecommendedComments);
		WFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(WFRecommendedCommentBox, c);
		WFPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(WFPriorityBox, c);
		WFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(WFPartsField, c);
		WFLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(WFLaborField, c);

		String[] BFTechComments = { "", "BRAKE FLUID IS LOW",
				"BRAKE FLUID IS DIRTY", "BRAKE FLUID IS LOW AND DIRTY" };
		String[] BFRecommendedComments = { "", "DO BRAKE FLUSH",
				"ADD BRAKE FLUID" };

		JLButton BFLabel = new JLButton("BRAKE FLUID");
		c.gridx = 0;
		c.gridy = 31;
		c.gridwidth = 1;
		techPanel.add(BFLabel, c);
		BFOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(BFOK, c);
		BFNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(BFNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(BFOK, BFNOTOK, 21);
		BFNOTOK.addActionListener(listener);
		BFOK.addActionListener(listener);
		BFTechCommentBox = new myComboBox(BFTechComments);
		BFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(BFTechCommentBox, c);
		BFRecommendedCommentBox = new myComboBox(BFRecommendedComments);
		BFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(BFRecommendedCommentBox, c);
		BFPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(BFPriorityBox, c);
		BFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(BFPartsField, c);
		BFLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(BFLaborField, c);

		String[] PSFTechComments = { "", "POWER STEERING FLUID IS LOW",
				"POWER STEERING FLUID IS DIRTY",
				"POWER STEERING FLUID IS LOW AND DIRTY" };
		String[] PSFRecommendedComments = { "", "DO A POWER STEERING FLUSH",
				"ADD POWER STEERING FLUID" };

		JLButton PSFLabel = new JLButton("POWER STEERING FLUID");
		c.gridx = 0;
		c.gridy = 32;
		c.gridwidth = 1;
		techPanel.add(PSFLabel, c);
		PSFOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(PSFOK, c);
		PSFNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(PSFNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(PSFOK, PSFNOTOK, 22);
		PSFNOTOK.addActionListener(listener);
		PSFOK.addActionListener(listener);
		PSFTechCommentBox = new myComboBox(PSFTechComments);
		PSFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(PSFTechCommentBox, c);
		PSFRecommendedCommentBox = new myComboBox(PSFRecommendedComments);
		PSFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(PSFRecommendedCommentBox, c);
		PSFPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(PSFPriorityBox, c);
		PSFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(PSFPartsField, c);
		PSFLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(PSFLaborField, c);

		String[] coolantTechComments = { "", "COOLANT IS LOW",
				"COOLANT IS DIRTY", "COOLANT IS LOW AND DIRTY",
				"NEEDS COOLANT FLUSH WITH REPAIRS",
				"HAS COOLANT LEAK DO PRESSURE TEST" };
		String[] coolantRecommendedComments = { "", "DO COOLANT FLUSH",
				"ADD COOLANT", "DO PRESSURE TEST TO CHECK FOR LEAKS" };

		JLButton coolantLabel = new JLButton("COOLANT");
		c.gridx = 0;
		c.gridy = 33;
		c.gridwidth = 1;
		techPanel.add(coolantLabel, c);
		coolantOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(coolantOK, c);
		coolantNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(coolantNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(coolantOK, coolantNOTOK, 23);
		coolantNOTOK.addActionListener(listener);
		coolantOK.addActionListener(listener);
		coolantTechCommentBox = new myComboBox(coolantTechComments);
		coolantTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(coolantTechCommentBox, c);
		coolantRecommendedCommentBox = new myComboBox(coolantRecommendedComments);
		coolantRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(coolantRecommendedCommentBox, c);
		coolantPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(coolantPriorityBox, c);
		coolantPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(coolantPartsField, c);
		coolantLaborField = new ABMTextField(format); // change this to
														// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(coolantLaborField, c);

		String[] SBTechComments = { "", "SERPENTINE BELT IS CRACKED",
				"SERPENTINE BELT IS MISSING", "BELT TENSIONER IS WORN",
				"BELT TENSIONER AND BELT ARE WORN" };
		String[] SBRecommendedComments = { "", "REPLACE BELT", "ADJUST BELT",
				"REPLACE BELT TENSIONER", "REPLACE BELT TENSIONER AND BELT" };

		JLButton SBLabel = new JLButton("SERPENTINE BELT");
		c.gridx = 0;
		c.gridy = 34;
		c.gridwidth = 1;
		techPanel.add(SBLabel, c);
		SBOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(SBOK, c);
		SBNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(SBNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(SBOK, SBNOTOK, 24);
		SBNOTOK.addActionListener(listener);
		SBOK.addActionListener(listener);
		SBTechCommentBox = new myComboBox(SBTechComments);
		SBTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(SBTechCommentBox, c);
		SBRecommendedCommentBox = new myComboBox(SBRecommendedComments);
		SBRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(SBRecommendedCommentBox, c);
		SBPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(SBPriorityBox, c);
		SBPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(SBPartsField, c);
		SBLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(SBLaborField, c);

		String[] AFTechComments = { "", "AIR FILTER IS DIRTY",
				"K&N TYPE FILTER NEEDS TO BE CLEANED" };
		String[] AFRecommendedComments = { "", "REPLACE AIR FILTER",
				"CLEAN AND RE OIL AIR FILTER" };

		JLButton AFLabel = new JLButton("AIR FILTER");
		c.gridx = 0;
		c.gridy = 35;
		c.gridwidth = 1;
		techPanel.add(AFLabel, c);
		AFOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(AFOK, c);
		AFNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(AFNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(AFOK, AFNOTOK, 25);
		AFNOTOK.addActionListener(listener);
		AFOK.addActionListener(listener);
		AFTechCommentBox = new myComboBox(AFTechComments);
		AFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(AFTechCommentBox, c);
		AFRecommendedCommentBox = new myComboBox(AFRecommendedComments);
		AFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(AFRecommendedCommentBox, c);
		AFPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(AFPriorityBox, c);
		AFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(AFPartsField, c);
		AFLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(AFLaborField, c);

		String[] FFTechComments = { "", "REPLACE FILTER DUE TO MILEAGE",
				"REPLACE FILTER MANUFACTURER RECOMMEND", "FUE FILTER CLOGGED",
				"NEED TO REMOVE FUEL FILTER TO CHECK IT" };
		String[] FFRecommendedComments = { "", "REPLACE FUEL FILTER",
				"REMOVE FILTER AND CHECK FOR CLOGS" };

		JLButton FFLabel = new JLButton("FUEL FILTER");
		c.gridx = 0;
		c.gridy = 36;
		c.gridwidth = 1;
		techPanel.add(FFLabel, c);
		FFOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(FFOK, c);
		FFNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(FFNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(FFOK, FFNOTOK, 26);
		FFNOTOK.addActionListener(listener);
		FFOK.addActionListener(listener);
		FFTechCommentBox = new myComboBox(AFTechComments);
		FFTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(FFTechCommentBox, c);
		FFRecommendedCommentBox = new myComboBox(FFRecommendedComments);
		FFRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(FFRecommendedCommentBox, c);
		FFPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(FFPriorityBox, c);
		FFPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(FFPartsField, c);
		FFLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(FFLaborField, c);

		String[] radiatorTechComments = { "", "RADIATOR IS LEAKING",
				"RADIATOR IS CLOGGED", "OVERHEATING NEED TO CHECK SYSTEM" };
		String[] radiatorRecommendedComments = { "",
				"DO PRESSURE CHECK TO CHECK LEAKS", "REPLACE RADIATOR",
				"DO DIAGNOSTICS ON OVERHEATING" };

		JLButton radiatorLabel = new JLButton("RADIATOR");
		c.gridx = 0;
		c.gridy = 37;
		c.gridwidth = 1;
		techPanel.add(radiatorLabel, c);
		radiatorOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(radiatorOK, c);
		radiatorNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(radiatorNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(radiatorOK, radiatorNOTOK, 27);
		radiatorNOTOK.addActionListener(listener);
		radiatorOK.addActionListener(listener);
		radiatorTechCommentBox = new myComboBox(radiatorTechComments);
		radiatorTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(radiatorTechCommentBox, c);
		radiatorRecommendedCommentBox = new myComboBox(
				radiatorRecommendedComments);
		radiatorRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(radiatorRecommendedCommentBox, c);
		radiatorPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(radiatorPriorityBox, c);
		radiatorPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(radiatorPartsField, c);
		radiatorLaborField = new ABMTextField(format); // change this to
														// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(radiatorLaborField, c);

		String[] URHTechComments = { "", "UPPER HODE IS LEAKING",
				"UPPER HOSE FEELS BRITTLE", "NEEDS HOSE CLAMPS REPLACED" };
		String[] URHRecommendedComments = { "", "REPLACE UPPER RADIATOR HOSE" };

		JLButton URHLabel = new JLButton("UPPER RADIATOR HOSE");
		c.gridx = 0;
		c.gridy = 38;
		c.gridwidth = 1;
		techPanel.add(URHLabel, c);
		URHOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(URHOK, c);
		URHNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(URHNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(URHOK, URHNOTOK, 28);
		URHNOTOK.addActionListener(listener);
		URHOK.addActionListener(listener);
		URHTechCommentBox = new myComboBox(URHTechComments);
		URHTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(URHTechCommentBox, c);
		URHRecommendedCommentBox = new myComboBox(URHRecommendedComments);
		URHRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(URHRecommendedCommentBox, c);
		URHPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(URHPriorityBox, c);
		URHPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(URHPartsField, c);
		URHLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(URHLaborField, c);

		String[] LRHTechComments = { "", "LOWER HOSE IS LEAKING",
				"LOWER HOSE FEELS BRITTLE", "NEEDS HOSE CLAMPS REPLACED" };
		String[] LRHRecommendedComments = { "", "REPLACE LOWER RADIATOR HOSE" };

		JLButton LRHLabel = new JLButton("LOWER RADIATOR HOSE");
		c.gridx = 0;
		c.gridy = 39;
		c.gridwidth = 1;
		techPanel.add(LRHLabel, c);
		LRHOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(LRHOK, c);
		LRHNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(LRHNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(LRHOK, LRHNOTOK, 29);
		LRHNOTOK.addActionListener(listener);
		LRHOK.addActionListener(listener);
		LRHTechCommentBox = new myComboBox(LRHTechComments);
		LRHTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(LRHTechCommentBox, c);
		LRHRecommendedCommentBox = new myComboBox(LRHRecommendedComments);
		LRHRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(LRHRecommendedCommentBox, c);
		LRHPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(LRHPriorityBox, c);
		LRHPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(LRHPartsField, c);
		LRHLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(LRHLaborField, c);

		String[] HBHTechComments = { "", "HEATER IS LEAKING",
				"HEATER HOSE FEELS BRITTLE", "NEEDS HOSE CLAMPS REPLACED" };
		String[] HBHRecommendedComments = { "", "REPLACE HEATER HOSES" };

		JLButton HBHLabel = new JLButton("HEATER/BYPASS HOSE");
		c.gridx = 0;
		c.gridy = 40;
		c.gridwidth = 1;
		techPanel.add(HBHLabel, c);
		HBHOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(HBHOK, c);
		HBHNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(HBHNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(HBHOK, HBHNOTOK, 30);
		HBHNOTOK.addActionListener(listener);
		HBHOK.addActionListener(listener);
		HBHTechCommentBox = new myComboBox(HBHTechComments);
		HBHTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(HBHTechCommentBox, c);
		HBHRecommendedCommentBox = new myComboBox(HBHRecommendedComments);
		HBHRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(HBHRecommendedCommentBox, c);
		HBHPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(HBHPriorityBox, c);
		HBHPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(HBHPartsField, c);
		HBHLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(HBHLaborField, c);

		/************************************* UNDER CAR ************************************/

		JLabel underCarLabel = new JLabel("UNDER CAR"); // this obviosly needs
														// to change
		c.gridx = 0;
		c.gridy = 41;
		c.gridwidth = 2;
		underCarLabel.setBackground(theme);
		underCarLabel.setBorder(new LineBorder(Color.BLACK, 2));
		underCarLabel.setOpaque(true);
		techPanel.add(underCarLabel, c);
		JLabel okay4Label = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		okay4Label.setBackground(theme);
		okay4Label.setBorder(new LineBorder(Color.BLACK, 2));
		okay4Label.setOpaque(true);
		techPanel.add(okay4Label, c);
		JLabel notOkay4Label = new JLabel("NOT OK");
		c.gridx = 3;
		c.gridwidth = 1;
		notOkay4Label.setBackground(theme);
		notOkay4Label.setBorder(new LineBorder(Color.BLACK, 2));
		notOkay4Label.setOpaque(true);
		techPanel.add(notOkay4Label, c);
		JLabel techComments4Label = new JLabel("TECHNICIANS COMMENTS");
		c.gridx = 4;
		c.gridwidth = 2;
		techComments4Label.setBackground(theme);
		techComments4Label.setBorder(new LineBorder(Color.BLACK, 2));
		techComments4Label.setOpaque(true);
		techPanel.add(techComments4Label, c);
		JLabel recommendedRepair4Label = new JLabel("RECOMMENDED REPAIRS");
		c.gridx = 6;
		c.gridwidth = 2;
		recommendedRepair4Label.setBackground(theme);
		recommendedRepair4Label.setBorder(new LineBorder(Color.BLACK, 2));
		recommendedRepair4Label.setOpaque(true);
		techPanel.add(recommendedRepair4Label, c);
		JLabel priority4Label = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		priority4Label.setBackground(theme);
		priority4Label.setBorder(new LineBorder(Color.BLACK, 2));
		priority4Label.setOpaque(true);
		techPanel.add(priority4Label, c);
		JLabel parts3Label = new JLabel("PARTS");
		c.gridx = 9;
		c.gridwidth = 1;
		parts3Label.setBackground(theme);
		parts3Label.setBorder(new LineBorder(Color.BLACK, 2));
		parts3Label.setOpaque(true);
		techPanel.add(parts3Label, c);
		JLabel labor4Label = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		labor4Label.setBackground(theme);
		labor4Label.setBorder(new LineBorder(Color.BLACK, 2));
		labor4Label.setOpaque(true);
		techPanel.add(labor4Label, c);

		String[] SRPTechComments = { "", "STEERING BOX/GEAR IS LEAKING",
				"STEERING BOX/GEAR IS LOOSE", "RACK & PINION IS LEAKING",
				"RACK AND PINION IS LOOSE", "RACK AND PINION MOUNTS ARE WORN",
				"RACK AND PINION MOUNTS ARE BROKEN" };
		String[] SRPRecommendedComments = { "", "REPLACE STEERING BOX/GEAR",
				"REPLACE RACK AND PINION", "REPLACE RACK & PINION MOUNTS" };

		JLButton SRPLabel = new JLButton("STEERING RACK & PINION");
		c.gridx = 0;
		c.gridy = 42;
		c.gridwidth = 1;
		techPanel.add(SRPLabel, c);
		SRPOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(SRPOK, c);
		SRPNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(SRPNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(SRPOK, SRPNOTOK, 31);
		SRPNOTOK.addActionListener(listener);
		SRPOK.addActionListener(listener);
		SRPTechCommentBox = new myComboBox(SRPTechComments);
		SRPTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(SRPTechCommentBox, c);
		SRPRecommendedCommentBox = new myComboBox(SRPRecommendedComments);
		SRPRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(SRPRecommendedCommentBox, c);
		SRPPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(SRPPriorityBox, c);
		SRPPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(SRPPartsField, c);
		SRPLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(SRPLaborField, c);

		String[] SLTechComments = { "", "LEFT OUTER TIE ROD WORN",
				"RIGHT OUTER TIE ROD WORN", "LEFT INNER TIE ROD WORN",
				"RIGHT INNER TIE ROD WORN", "BOTH INNER TIE RODS WORN",
				"BOTH OUTER TIE RODS WORN", "BOTH INNER & OUTER TIE RODS WORN",
				"PITMAN ARM WORN", "IDLER ARM WORN", "DRAG LINK WORN",
				"DRAG LINK, LEFT & RIGHT TIE ROD ENDS WORN",
				"PITMAN ARM AND IDLER ARM WORN",
				"PITMAN ARM IDLER ARM & DRAG LINK WORN",
				"STEERING STABILIZER WORN" };
		String[] SLRecommendedComments = { "", "REPLACE LEFT OUTER TIE ROD",
				"REPLACE RIGHT OUTER TIE ROD", "REPLACE LEFT INNER TIE ROD",
				"REPLACE RIGHT INNER TIE ROD", "REPLACE BOTH INNER TIE RODS",
				"REPLACE BOTH OUTER TIE RODS",
				"REPLACE BOTH INNER & OUTER TIE RODS", "REPLACE PITMAN ARM",
				"REPLACE IDLER ARM", "REPLACE DRAG LINK",
				"REPLACE DRAG LINK, LEFT&RIGHT TIE ROD ENDS",
				"REPLACE PITMAN ARM AND IDLER ARM",
				"REPLACE PITMAN ARM, IDLER ARM & DRAG LINK",
				"REPLACE STEERING STABILIZER" };

		JLButton SLLabel = new JLButton("STEERING LINKAGE");
		c.gridx = 0;
		c.gridy = 43;
		c.gridwidth = 1;
		techPanel.add(SLLabel, c);
		SLOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(SLOK, c);
		SLNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(SLNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(SLOK, SLNOTOK, 32);
		SLNOTOK.addActionListener(listener);
		SLOK.addActionListener(listener);
		SLTechCommentBox = new myComboBox(SLTechComments);
		SLTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(SLTechCommentBox, c);
		SLRecommendedCommentBox = new myComboBox(SLRecommendedComments);
		SLRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(SLRecommendedCommentBox, c);
		SLPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(SLPriorityBox, c);
		SLPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(SLPartsField, c);
		SLLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(SLLaborField, c);

		String[] suspTechComments = { "", "LEFT UPPER BALL JOINT WORN",
				"LEFT LOWER BALL JOINT WORN", "RIGHT UPPER BALL JOINT WORN",
				"RIGHT LOWER BALL JOINT WORN",
				"BOTH LEFT UPPER & LOWER BALL JOINTS WORN",
				"BOTH RIGHT UPPER & LOWER BALL JOINTS WORN",
				"ALL BALL JOINTS WORN",
				"RIGHT SIDE LOWER A FRAME BUSHINGS WORN",
				"RIGHT SIDE UPPER A FRAME BUSHINGS WORN",
				"LEFT SIDE LOWER A FRAME BUSHINGS WORN",
				"LEFT SIDE UPPER A FRAME BUSHINGS WORN",
				"ALL A FRAME BUSHINGS WORN", "LEFT SIDE KING PIN(S) WORN",
				"RIGHT SIDE KING PIN(S) WORN", "BOTH KING PINS WORN",
				"LEFT SIDE UPPER STRUT MOUNT WORN",
				"RIGHT SIDE UPPER STRUT MOUNT WORN" };
		String[] suspRecommendedComments = { "",
				"REPLACE LEFT UPPER BALL JOINT",
				"REPLACE LEFT LOWER BALL JOINT",
				"REPLACE RIGHT UPPER BALL JOINT",
				"REPLACE RIGHT LOWER BALL JOINT",
				"REPLACE LEFT UPPER & LOWER BALL JOINTS",
				"REPLACE RIGHT UPPER & LOWER BALL JOINTS",
				"REPLACE ALL BALL JOINTS",
				"REPLACE RIGHT SIDE LOWER A FRAME BUSHINGS",
				"REPLACE RIGHT SIDE UPPER A FRAME BUSHINGS",
				"REPLACE LEFT SIDE LOWER A FRAME BUSHINGS",
				"REPLACE LEFT SIDE UPPER A FRAME BUSHINGS",
				"REPLACE ALL A FRAME BUSHINGS",
				"REPLACE LEFT SIDE KING PIN(S)",
				"REPLACE RIGHT SIDE KING PIN(S)", "REPLACE BOTH KING PINS",
				"REPLACE LEFT SIDE UPPER STRUT MOUNT",
				"REPLACE RIGHT SIDE UPPER STRUT MOUNT" };

		JLButton suspLabel = new JLButton("SUSPENSION");
		c.gridx = 0;
		c.gridy = 44;
		c.gridwidth = 1;
		techPanel.add(suspLabel, c);
		suspOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(suspOK, c);
		suspNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(suspNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(suspOK, suspNOTOK, 33);
		suspNOTOK.addActionListener(listener);
		suspOK.addActionListener(listener);
		suspTechCommentBox = new myComboBox(suspTechComments);
		suspTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(suspTechCommentBox, c);
		suspRecommendedCommentBox = new myComboBox(suspRecommendedComments);
		suspRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(suspRecommendedCommentBox, c);
		suspPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(suspPriorityBox, c);
		suspPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(suspPartsField, c);
		suspLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(suspLaborField, c);

		String[] alignTechComments = { "", "NEEDS ALIGNMENT CAUSING TIRE WEAR",
				"NEEDS ALIGNMENT PULLS LEFT", "NEEDS ALIGNMENT PULLS RIGHT",
				"NEEDS ALIGNMENT AFTER FRONT END WORK" };
		String[] alignRecommendedComments = { "", "DO ALIGNMENT" };

		JLButton alignLabel = new JLButton("ALIGNMENT");
		c.gridx = 0;
		c.gridy = 45;
		c.gridwidth = 1;
		techPanel.add(alignLabel, c);
		alignOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(alignOK, c);
		alignNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(alignNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(alignOK, alignNOTOK, 34);
		alignNOTOK.addActionListener(listener);
		alignOK.addActionListener(listener);
		alignTechCommentBox = new myComboBox(alignTechComments);
		alignTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(alignTechCommentBox, c);
		alignRecommendedCommentBox = new myComboBox(alignRecommendedComments);
		alignRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(alignRecommendedCommentBox, c);
		alignPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(alignPriorityBox, c);
		alignPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(alignPartsField, c);
		alignLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(alignLaborField, c);

		String[] FSSTechComments = { "", "FRONT SHOCKS WORN OUT",
				"REAR SHOCKS WORN OUT", "ALL (4) SHOCKS WORN OUT",
				"FRONT SHOCKS LEAKING", "REAR SHOCKS LEAKING",
				"ALL (4) SHOCKS LEAKING", "FRONT SHOCKS CAUSING TIRE WEAR",
				"REAR SHOCKS CAUSING TIRE WEAR",
				"ALL (4) SHOCKS CAUSING TIRE WEAR", "FRONT STRUTS WORN OUT",
				"REAR STRUT WORN OUT", "ALL (4) STRUTS WORN OUT",
				"FRONT STRUTS LEAKING", "REAR STRUTS LEAKING",
				"ALL (4) STRUTS LEAKING", "FRONT STRUTS CAUSING TIRE WEAR",
				"REAR STUTS CAUSING TIRE WEAR",
				"ALL (4) STRUTS CAUSING TIRE WEAR" };
		String[] FSSRecommendedComments = { "", "REPLACE FRONT SHOCKS",
				"REPLACE REAR SHOCKS", "REPLACE ALL (4) SHOCKS",
				"REPLACE FRONT STRUTS", "REPLACE REAR STRUTS",
				"REPLACE ALL (4) STRUTS" };

		JLButton FSSLabel = new JLButton("FRONT STRUT/SHOCKS");
		c.gridx = 0;
		c.gridy = 46;
		c.gridwidth = 1;
		techPanel.add(FSSLabel, c);
		FSSOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(FSSOK, c);
		FSSNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(FSSNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(FSSOK, FSSNOTOK, 35);
		FSSNOTOK.addActionListener(listener);
		FSSOK.addActionListener(listener);
		FSSTechCommentBox = new myComboBox(FSSTechComments);
		FSSTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(FSSTechCommentBox, c);
		FSSRecommendedCommentBox = new myComboBox(FSSRecommendedComments);
		FSSRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(FSSRecommendedCommentBox, c);
		FSSPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(FSSPriorityBox, c);
		FSSPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(FSSPartsField, c);
		FSSLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(FSSLaborField, c);

		String[] RSSTechComments = { "", "FRONT SHOCKS WORN OUT",
				"REAR SHOCKS WORN OUT", "ALL (4) SHOCKS WORN OUT",
				"FRONT SHOCKS LEAKING", "REAR SHOCKS LEAKING",
				"ALL (4) SHOCKS LEAKING", "FRONT SHOCKS CAUSING TIRE WEAR",
				"REAR SHOCKS CAUSING TIRE WEAR",
				"ALL (4) SHOCKS CAUSING TIRE WEAR", "FRONT STRUTS WORN OUT",
				"REAR STRUT WORN OUT", "ALL (4) STRUTS WORN OUT",
				"FRONT STRUTS LEAKING", "REAR STRUTS LEAKING",
				"ALL (4) STRUTS LEAKING", "FRONT STRUTS CAUSING TIRE WEAR",
				"REAR STRUTS CAUSING TIRE WEAR",
				"ALL (4) STRUTS CAUSING TIRE WEAR" };
		String[] RSSRecommendedComments = { "", "REPLACE FRONT SHOCKS",
				"REPLACE REAR SHOCKS", "REPLACE ALL (4) SHOCKS",
				"REPLACE FRONT STRUTS", "REPLACE REAR STRUTS",
				"REPLACE ALL (4) STRUTS" };

		JLButton RSSLabel = new JLButton("REAR STRUT/SHOCKS");
		c.gridx = 0;
		c.gridy = 47;
		c.gridwidth = 1;
		techPanel.add(RSSLabel, c);
		RSSOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(RSSOK, c);
		RSSNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(RSSNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(RSSOK, RSSNOTOK, 36);
		RSSNOTOK.addActionListener(listener);
		RSSOK.addActionListener(listener);
		RSSTechCommentBox = new myComboBox(RSSTechComments);
		RSSTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(RSSTechCommentBox, c);
		RSSRecommendedCommentBox = new myComboBox(RSSRecommendedComments);
		RSSRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(RSSRecommendedCommentBox, c);
		RSSPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(RSSPriorityBox, c);
		RSSPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(RSSPartsField, c);
		RSSLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(RSSLaborField, c);

		String[] FBTechComments = { "",
				"FRONT BRAKES WORN NEED TO BE REPLACED",
				"NEEDS FRONT BRAKES AND BRAKE ROTORS",
				"NEEDS FRONT BRAKES AND BRAKE CALIPERS",
				"NEEDS FRONT BRAKES, ROTORS & CALIPERS" };
		String[] FBRecommendedComments = { "", "REPLACE FRONT BRAKES",
				"REPLACE FRONT BRAKES AND BRAKE ROTORS",
				"REPLACE FRONT BRAKES AND BRAKE CALIPERS",
				"REPLACE FRONT BRAKES, ROTORS & CALIPERS" };

		JLButton FBLabel = new JLButton("FRONT BRAKES");
		c.gridx = 0;
		c.gridy = 48;
		c.gridwidth = 1;
		techPanel.add(FBLabel, c);
		FBOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(FBOK, c);
		FBNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(FBNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(FBOK, FBNOTOK, 37);
		FBNOTOK.addActionListener(listener);
		FBOK.addActionListener(listener);
		FBTechCommentBox = new myComboBox(FBTechComments);
		FBTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(FBTechCommentBox, c);
		FBRecommendedCommentBox = new myComboBox(FBRecommendedComments);
		FBRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(FBRecommendedCommentBox, c);
		FBPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(FBPriorityBox, c);
		FBPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(FBPartsField, c);
		FBLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(FBLaborField, c);

		String[] RBTechComments = { "", "CLEAN AND ADJUST BRAKES",
				"REAR BRAKES WORN NEED TO BE REPLACED",
				"NEEDS REAR BRAKES AND BRAKE ROTOR",
				"NEEDS REAR BRAKES AND BRAKE DRUMS",
				"NEEDS REAR BRAKES AND BRAKE CALIPERS",
				"NEEDS REAR BRAKES AND WHEEL CYLINDERS",
				"NEEDS REAR BRAKES,BRAKE ROTOR&CALIPERS",
				"NEEDS REAR BRAKES,BRAKE DRUMS&CYLINDERS" };
		String[] RBRecommendedComments = { "", "CLEAN AND ADJUST BRAKES",
				"REPLACE REAR BRAKES", "REPLACE REAR BRAKES AMD BRAKE ROTOR",
				"REPLACE REAR BRAKES AND BRAKE DRUMS",
				"REPLACE REAR BRAKES AND BRAKE CALIPERS",
				"REPLACE REAR BRAKES AND WHEEL CYLINDERS",
				"REPLACE REAR BRAKES,ROTORS & CALIPERS",
				"REPLACE REAR BRAKES,DRUMS & CYLINDERS" };

		JLButton RBLabel = new JLButton("REAR BRAKES");
		c.gridx = 0;
		c.gridy = 49;
		c.gridwidth = 1;
		techPanel.add(RBLabel, c);
		RBOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(RBOK, c);
		RBNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(RBNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(RBOK, RBNOTOK, 38);
		RBNOTOK.addActionListener(listener);
		RBOK.addActionListener(listener);
		RBTechCommentBox = new myComboBox(RBTechComments);
		RBTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(RBTechCommentBox, c);
		RBRecommendedCommentBox = new myComboBox(RBRecommendedComments);
		RBRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(RBRecommendedCommentBox, c);
		RBPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(RBPriorityBox, c);
		RBPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(RBPartsField, c);
		RBLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(RBLaborField, c);

		String[] CVADTechComments = { "", "FRONT U JOINT WORN",
				"REAR U JOINT WORN", "FRONT DRIVE SHAFT 4X4 U JOINT WORN",
				"REAR DRIVE SHAFT 4X4 U JOINT WORN" };
		String[] CVADRecommendedComments = { "", "REPLACE FRONT U JOINT",
				"REPLACE REAR U JOINT",
				"REPLACE FRONT DRIVE SHAFT 4X4 U JOINT",
				"REPLACE REAR DRIVE SHAFT 4X4 U JOINT" };

		JLButton CVADLabel = new JLButton("CV AXLE/DRIVE SHAFT");
		c.gridx = 0;
		c.gridy = 50;
		c.gridwidth = 1;
		techPanel.add(CVADLabel, c);
		CVADOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(CVADOK, c);
		CVADNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(CVADNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(CVADOK, CVADNOTOK, 39);
		CVADNOTOK.addActionListener(listener);
		CVADOK.addActionListener(listener);
		CVADTechCommentBox = new myComboBox(CVADTechComments);
		CVADTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(CVADTechCommentBox, c);
		CVADRecommendedCommentBox = new myComboBox(CVADRecommendedComments);
		CVADRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(CVADRecommendedCommentBox, c);
		CVADPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(CVADPriorityBox, c);
		CVADPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(CVADPartsField, c);
		CVADLaborField = new ABMTextField(format); // change this to
													// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(CVADLaborField, c);

		String[] mufflerTechComments = { "", "MUFFLER LEAKING EXHAUST",
				"MUFFLER HAS HOLES IN IT", "MUFFLER IS RUSTED",
				"MUFFLER IS MISSING" };
		String[] mufflerRecommendedComments = { "", "REPLACE MUFFLER" };

		JLButton mufflerLabel = new JLButton("MUFFLER");
		c.gridx = 0;
		c.gridy = 51;
		c.gridwidth = 1;
		techPanel.add(mufflerLabel, c);
		mufflerOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(mufflerOK, c);
		mufflerNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(mufflerNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(mufflerOK, mufflerNOTOK, 40);
		mufflerNOTOK.addActionListener(listener);
		mufflerOK.addActionListener(listener);
		mufflerTechCommentBox = new myComboBox(mufflerTechComments);
		mufflerTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(mufflerTechCommentBox, c);
		mufflerRecommendedCommentBox = new myComboBox(mufflerRecommendedComments);
		mufflerRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(mufflerRecommendedCommentBox, c);
		mufflerPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(mufflerPriorityBox, c);
		mufflerPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(mufflerPartsField, c);
		mufflerLaborField = new ABMTextField(format); // change this to
														// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(mufflerLaborField, c);

		String[] EPTechComments = { "", "EXAUST PIPES ARE LEAKING EXHAUST",
				"EXHAUST PIPES HOLES IN THEM", "EXHAUST PIPES ARE RUSTED",
				"EXHAUST PIPES ARE MISSING" };
		String[] EPRecommendedComments = { "", "REPLACE EXHAUST PIPES" };

		JLButton EPLabel = new JLButton("EXHAUST PIPES");
		c.gridx = 0;
		c.gridy = 52;
		c.gridwidth = 1;
		techPanel.add(EPLabel, c);
		EPOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(EPOK, c);
		EPNOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(EPNOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(EPOK, EPNOTOK, 41);
		EPNOTOK.addActionListener(listener);
		EPOK.addActionListener(listener);
		EPTechCommentBox = new myComboBox(EPTechComments);
		EPTechCommentBox.setEditable(true);
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(EPTechCommentBox, c);
		EPRecommendedCommentBox = new myComboBox(EPRecommendedComments);
		EPRecommendedCommentBox.setEditable(true);
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(EPRecommendedCommentBox, c);
		EPPriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(EPPriorityBox, c);
		EPPartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(EPPartsField, c);
		EPLaborField = new ABMTextField(format); // change this to ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(EPLaborField, c);

		/***************************** OTHER ITEMS *******************************************/

		c.insets = new Insets(3, 4, 3, 4);

		JLabel otherLabel = new JLabel("OTHER ITEMS NEEDING ATTN"); 
		
		/*
		c.gridx = 0;
		c.gridy = 53;
		c.gridwidth = 2;
		otherLabel.setBackground(theme);
		otherLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherLabel.setOpaque(true);
		techPanel.add(otherLabel, c);
		JLabel otherOKLabel = new JLabel("OK");
		c.gridx = 2;
		c.gridwidth = 1;
		otherOKLabel.setBackground(theme);
		otherOKLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherOKLabel.setOpaque(true);
		techPanel.add(otherOKLabel, c);
		JLabel otherNotOKLabel = new JLabel("NOT OK");
		c.gridx = 3;
		c.gridwidth = 1;
		otherNotOKLabel.setBackground(theme);
		otherNotOKLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherNotOKLabel.setOpaque(true);
		techPanel.add(otherNotOKLabel, c);
		JLabel otherTechCommentsLabel = new JLabel("TECHNICIANS COMMENTS");
		c.gridx = 4;
		c.gridwidth = 2;
		otherTechCommentsLabel.setBackground(theme);
		otherTechCommentsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherTechCommentsLabel.setOpaque(true);
		techPanel.add(otherTechCommentsLabel, c);
		JLabel otherRecommendedLabel = new JLabel("RECOMMENDED REPAIRS");
		c.gridx = 6;
		c.gridwidth = 2;
		otherRecommendedLabel.setBackground(theme);
		otherRecommendedLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherRecommendedLabel.setOpaque(true);
		techPanel.add(otherRecommendedLabel, c);
		JLabel otherPriorityLabel = new JLabel("PRIORITY");
		c.gridx = 8;
		c.gridwidth = 1;
		otherPriorityLabel.setBackground(theme);
		otherPriorityLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherPriorityLabel.setOpaque(true);
		techPanel.add(otherPriorityLabel, c);
		JLabel otherPartsLabel = new JLabel("PARTS");
		c.gridx = 9;
		c.gridwidth = 1;
		otherPartsLabel.setBackground(theme);
		otherPartsLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherPartsLabel.setOpaque(true);
		techPanel.add(otherPartsLabel, c);
		JLabel otherLaborLabel = new JLabel("LABOR");
		c.gridx = 10;
		c.gridwidth = 1;
		otherLaborLabel.setBackground(theme);
		otherLaborLabel.setBorder(new LineBorder(Color.BLACK, 2));
		otherLaborLabel.setOpaque(true);
		techPanel.add(otherLaborLabel, c);

		other1Label = new JTextField();
		c.gridx = 0;
		c.gridy = 54;
		c.gridwidth = 1;
		techPanel.add(other1Label, c);
		other1OK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other1OK, c);
		other1NOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other1NOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(other1OK, other1NOTOK, 42);
		other1NOTOK.addActionListener(listener);
		other1OK.addActionListener(listener);
		other1TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other1TechCommentBox, c);
		other1RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other1RecommendedCommentBox, c);
		other1PriorityBox = new myComboBox(priority);
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
		techPanel.add(other1LaborField, c);

		other2Label = new JTextField();
		c.gridx = 0;
		c.gridy = 55;
		c.gridwidth = 1;
		techPanel.add(other2Label, c);
		other2OK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other2OK, c);
		other2NOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other2NOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(other2OK, other2NOTOK, 43);
		other2NOTOK.addActionListener(listener);
		other2OK.addActionListener(listener);
		other2TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other2TechCommentBox, c);
		other2RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other2RecommendedCommentBox, c);
		other2PriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other2PriorityBox, c);
		other2PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other2PartsField, c);
		other2LaborField = new ABMTextField(format); // change this to
														// ABMTextField
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other2LaborField, c);

		other3Label = new JTextField();
		c.gridx = 0;
		c.gridy = 56;
		c.gridwidth = 1;
		techPanel.add(other3Label, c);
		other3OK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other3OK, c);
		other3NOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other3NOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(other3OK, other3NOTOK, 44);
		other3NOTOK.addActionListener(listener);
		other3OK.addActionListener(listener);
		other3TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other3TechCommentBox, c);
		other3RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other3RecommendedCommentBox, c);
		other3PriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other3PriorityBox, c);
		other3PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other3PartsField, c);
		other3LaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other3LaborField, c);

		other4Label = new JTextField();
		c.gridx = 0;
		c.gridy = 57;
		c.gridwidth = 1;
		techPanel.add(other4Label, c);
		other4OK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other4OK, c);
		other4NOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other4NOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(other4OK, other4NOTOK, 45);
		other4NOTOK.addActionListener(listener);
		other4OK.addActionListener(listener);
		other4TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other4TechCommentBox, c);
		other4RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other4RecommendedCommentBox, c);
		other4PriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other4PriorityBox, c);
		other4PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other4PartsField, c);
		other4LaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other4LaborField, c);

		other5Label = new JTextField();
		c.gridx = 0;
		c.gridy = 58;
		c.gridwidth = 1;
		techPanel.add(other5Label, c);
		other5OK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other5OK, c);
		other5NOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other5NOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(other5OK, other5NOTOK, 46);
		other5NOTOK.addActionListener(listener);
		other5OK.addActionListener(listener);
		other5TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other5TechCommentBox, c);
		other5RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other5RecommendedCommentBox, c);
		other5PriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other5PriorityBox, c);
		other5PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other5PartsField, c);
		other5LaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other5LaborField, c);

		other6Label = new JTextField();
		c.gridx = 0;
		c.gridy = 59;
		c.gridwidth = 1;
		techPanel.add(other6Label, c);
		other6OK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other6OK, c);
		other6NOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other6NOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(other6OK, other6NOTOK, 47);
		other6NOTOK.addActionListener(listener);
		other6OK.addActionListener(listener);
		other6TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other6TechCommentBox, c);
		other6RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other6RecommendedCommentBox, c);
		other6PriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other6PriorityBox, c);
		other6PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other6PartsField, c);
		other6LaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other6LaborField, c);

		other7Label = new JTextField();
		c.gridx = 0;
		c.gridy = 60;
		c.gridwidth = 1;
		techPanel.add(other7Label, c);
		other7OK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other7OK, c);
		other7NOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other7NOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(other7OK, other7NOTOK, 48);
		other7NOTOK.addActionListener(listener);
		other7OK.addActionListener(listener);
		other7TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other7TechCommentBox, c);
		other7RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other7RecommendedCommentBox, c);
		other7PriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other7PriorityBox, c);
		other7PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other7PartsField, c);
		other7LaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other7LaborField, c);
		
		

		other8Label = new JTextField();
		c.gridx = 0;
		c.gridy = 61;
		c.gridwidth = 1;
		c.gridheight = 1;
		techPanel.add(other8Label, c);
		other8OK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 2;
		c.gridwidth = 1;
		techPanel.add(other8OK, c);
		other8NOTOK = new JCheckBox();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 3;
		c.gridwidth = 1;
		techPanel.add(other8NOTOK, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		listener = new OKbuttonListener(other8OK, other8NOTOK, 49);
		other8NOTOK.addActionListener(listener);
		other8OK.addActionListener(listener);
		other8TechCommentBox = new JTextField();
		c.gridx = 4;
		c.gridwidth = 2;
		techPanel.add(other8TechCommentBox, c);
		other8RecommendedCommentBox = new JTextField();
		c.gridx = 6;
		c.gridwidth = 2;
		techPanel.add(other8RecommendedCommentBox, c);
		other8PriorityBox = new myComboBox(priority);
		c.gridx = 8;
		c.gridwidth = 1;
		techPanel.add(other8PriorityBox, c);
		other8PartsField = new ABMTextField(format);
		c.gridx = 9;
		c.gridwidth = 1;
		techPanel.add(other8PartsField, c);
		other8LaborField = new ABMTextField(format);
		c.gridx = 10;
		c.gridwidth = 1;
		techPanel.add(other8LaborField, c);
		*/
		
		


		techPanel.setBorder(new LineBorder(Color.BLACK, 2));

		/******************************************************************* EAST PANEL ***********************************************************/
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(2, 4, 2, 4);

		JLabel QTY = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		QTY.setBackground(theme);
		QTY.setBorder(new LineBorder(Color.BLACK, 2));
		QTY.setOpaque(true);
		eastPanel.add(QTY, c);
		JLabel westTire = new JLabel("TIRE");
		c.gridx = 1;
		westTire.setBackground(theme);
		westTire.setBorder(new LineBorder(Color.BLACK, 2));
		westTire.setOpaque(true);
		eastPanel.add(westTire, c);
		JLabel LBRHRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBRHRS.setBackground(theme);
		LBRHRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBRHRS.setOpaque(true);
		eastPanel.add(LBRHRS, c);
		JLabel vendorName = new JLabel("Vendor Name");
		c.gridx = 3;
		vendorName.setBackground(theme);
		vendorName.setBorder(new LineBorder(Color.BLACK, 2));
		vendorName.setOpaque(true);
		eastPanel.add(vendorName, c);
		
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		
		//c.insets = new Insets(5, 4, 5, 4);
		
		
		

		DSFTQTYfield = new JFTextField(QTYformat);
		DSFTQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 1;
		c.gridx = 0;
		eastPanel.add(DSFTQTYfield, c);

		DSFTTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(DSFTTireField, c);

		DSFTLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(DSFTLBRfield, c);
		
		DSFTvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(DSFTvendor,c);

		
		
		PSFTQTYfield = new JFTextField(QTYformat);
		PSFTQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 2;
		c.gridx = 0;
		eastPanel.add(PSFTQTYfield, c);

		PSFTTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(PSFTTireField, c);

		PSFTLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(PSFTLBRfield, c);
		
		PSFTvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(PSFTvendor,c );

		
		
		PSRTQTYfield = new JFTextField(QTYformat);
		PSRTQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 3;
		c.gridx = 0;
		eastPanel.add(PSRTQTYfield, c);

		PSRTTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(PSRTTireField, c);

		PSRTLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(PSRTLBRfield, c);
		
		PSRTvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(PSRTvendor,c );

		
		

		DSRTQTYfield = new JFTextField(QTYformat);
		DSRTQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 4;
		c.gridx = 0;
		eastPanel.add(DSRTQTYfield, c);

		DSRTTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(DSRTTireField, c);

		DSRTLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(DSRTLBRfield, c);
		
		DSRTvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(DSRTvendor,c );
		
		

		spareQTYfield = new JFTextField(QTYformat);
		spareQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 5;
		c.gridx = 0;
		eastPanel.add(spareQTYfield, c);

		otherSpareTireField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(otherSpareTireField, c);

		spareLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(spareLBRfield, c);
		
		sparevendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(sparevendor,c );
		
		
		

		//c.insets = new Insets(2, 4, 2, 4);

		JLabel QTY2 = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 6;
		QTY2.setBackground(theme);
		QTY2.setBorder(new LineBorder(Color.BLACK, 2));
		QTY2.setOpaque(true);
		eastPanel.add(QTY2, c);
		JLabel westParts = new JLabel("PART");
		c.gridx = 1;
		westParts.setBackground(theme);
		westParts.setBorder(new LineBorder(Color.BLACK, 2));
		westParts.setOpaque(true);
		eastPanel.add(westParts, c);
		JLabel LBR2HRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBR2HRS.setBackground(theme);
		LBR2HRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBR2HRS.setOpaque(true);
		eastPanel.add(LBR2HRS, c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		JLabel vendorName2 = new JLabel("Vendor Name");
		c.gridx = 3;
		vendorName2.setBackground(theme);
		vendorName2.setBorder(new LineBorder(Color.BLACK, 2));
		vendorName2.setOpaque(true);
		eastPanel.add(vendorName2, c);

		//c.insets = new Insets(5, 4, 5, 4);

		WWQTYfield = new JFTextField(QTYformat);
		WWQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 7;
		c.gridx = 0;
		eastPanel.add(WWQTYfield, c);

		WWPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(WWPartField, c);

		WWLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(WWLBRfield, c);
		
		WWvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(WWvendor,c );
		
		

		locksQTYfield = new JFTextField(QTYformat);
		locksQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 8;
		c.gridx = 0;
		eastPanel.add(locksQTYfield, c);

		locksPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(locksPartField, c);

		locksLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(locksLBRfield, c);
		
		locksvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(locksvendor,c );
		
		

		ACHQTYfield = new JFTextField(QTYformat);
		ACHQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 9;
		c.gridx = 0;
		eastPanel.add(ACHQTYfield, c);

		ACHPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(ACHPartField, c);

		ACHLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(ACHLBRfield, c);
		
		ACHvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(ACHvendor,c );
		
		

		heaterQTYfield = new JFTextField(QTYformat);
		heaterQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 10;
		c.gridx = 0;
		eastPanel.add(heaterQTYfield, c);

		heaterPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(heaterPartField, c);

		heaterLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(heaterLBRfield, c);
		
		heatervendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(heatervendor,c );
		
		

		wiperQTYfield = new JFTextField(QTYformat);
		wiperQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 11;
		c.gridx = 0;
		eastPanel.add(wiperQTYfield, c);

		wiperPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(wiperPartField, c);

		wiperLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(wiperLBRfield, c);
		
		wipervendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(wipervendor,c );
		
		

		hornQTYfield = new JFTextField(QTYformat);
		hornQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 12;
		c.gridx = 0;
		eastPanel.add(hornQTYfield, c);

		hornPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(hornPartField, c);

		hornLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(hornLBRfield, c);
		
		hornvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(hornvendor,c );
		
		

		HLQTYfield = new JFTextField(QTYformat);
		HLQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 13;
		c.gridx = 0;
		eastPanel.add(HLQTYfield, c);

		HLPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(HLPartField, c);

		HLLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(HLLBRfield, c);

		HLvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(HLvendor,c );
		
		
		
		PTLPQTYfield = new JFTextField(QTYformat);
		PTLPQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 14;
		c.gridx = 0;
		eastPanel.add(PTLPQTYfield, c);

		PTLPPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(PTLPPartField, c);

		PTLPLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(PTLPLBRfield, c);
		
		PTLvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(PTLvendor,c );
		
		

		BLQTYfield = new JFTextField(QTYformat);
		BLQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 15;
		c.gridx = 0;
		eastPanel.add(BLQTYfield, c);

		BLPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(BLPartField, c);

		BLLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(BLLBRfield, c);
		
		brakevendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(brakevendor,c );
		
		

		DSQTYfield = new JFTextField(QTYformat);
		DSQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 16;
		c.gridx = 0;
		eastPanel.add(DSQTYfield, c);

		DSPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(DSPartField, c);

		DSLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(DSLBRfield, c);
		
		DSvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(DSvendor,c );
		
		

		//c.insets = new Insets(2, 4, 2, 4);

		JLabel QTY3 = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 17;
		QTY3.setBackground(theme);
		QTY3.setBorder(new LineBorder(Color.BLACK, 2));
		QTY3.setOpaque(true);
		eastPanel.add(QTY3, c);
		JLabel westParts2 = new JLabel("PART");
		c.gridx = 1;
		westParts2.setBackground(theme);
		westParts2.setBorder(new LineBorder(Color.BLACK, 2));
		westParts2.setOpaque(true);
		eastPanel.add(westParts2, c);
		JLabel LBR3HRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBR3HRS.setBackground(theme);
		LBR3HRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBR3HRS.setOpaque(true);
		eastPanel.add(LBR3HRS, c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		JLabel vendorName3 = new JLabel("Vendor Name");
		c.gridx = 3;
		vendorName3.setBackground(theme);
		vendorName3.setBorder(new LineBorder(Color.BLACK, 2));
		vendorName3.setOpaque(true);
		eastPanel.add(vendorName3, c);

		//c.insets = new Insets(5, 4, 5, 4);

		engineDiagQTYfield = new JFTextField(QTYformat);
		engineDiagQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 18;
		c.gridx = 0;
		eastPanel.add(engineDiagQTYfield, c);

		engineDiagPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(engineDiagPartField, c);

		engineDiagLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(engineDiagLBRfield, c);
		
		engineDiagvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(engineDiagvendor,c );
		
		

		MMQTYfield = new JFTextField(QTYformat);
		MMQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 19;
		c.gridx = 0;
		eastPanel.add(MMQTYfield, c);

		MMPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(MMPartField, c);

		MMLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(MMLBRfield, c);
		
		MMvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(MMvendor,c );
		
		

		BCBQTYfield = new JFTextField(QTYformat);
		BCBQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 20;
		c.gridx = 0;
		eastPanel.add(BCBQTYfield, c);

		BCBPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(BCBPartField, c);

		BCBLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(BCBLBRfield, c);
		
		BCBvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(BCBvendor,c );
		
		

		EOQTYfield = new JFTextField(QTYformat);
		EOQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 21;
		c.gridx = 0;
		eastPanel.add(EOQTYfield, c);

		EOPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(EOPartField, c);

		EOLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(EOLBRfield, c);
		
		EOvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(EOvendor,c );
		
		

		TFQTYfield = new JFTextField(QTYformat);
		TFQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 22;
		c.gridx = 0;
		eastPanel.add(TFQTYfield, c);

		TFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(TFPartField, c);

		TFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(TFLBRfield, c);
		
		TFvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(TFvendor,c );
		
		

		WFQTYfield = new JFTextField(QTYformat);
		WFQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 23;
		c.gridx = 0;
		eastPanel.add(WFQTYfield, c);

		WFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(WFPartField, c);

		WFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(WFLBRfield, c);
		
		WFvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(WFvendor,c );
		
		

		BFQTYfield = new JFTextField(QTYformat);
		BFQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 24;
		c.gridx = 0;
		eastPanel.add(BFQTYfield, c);

		BFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(BFPartField, c);

		BFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(BFLBRfield, c);
		
		BFvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(BFvendor,c );
		
		

		PSFQTYfield = new JFTextField(QTYformat);
		PSFQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 25;
		c.gridx = 0;
		eastPanel.add(PSFQTYfield, c);

		PSFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(PSFPartField, c);

		PSFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(PSFLBRfield, c);
		
		PSFvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(PSFvendor,c );
		
		

		coolantQTYfield = new JFTextField(QTYformat);
		coolantQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 26;
		c.gridx = 0;
		eastPanel.add(coolantQTYfield, c);

		coolantPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(coolantPartField, c);

		coolantLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(coolantLBRfield, c);
		
		coolantvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(coolantvendor,c );
		
		

		SBQTYfield = new JFTextField(QTYformat);
		SBQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 27;
		c.gridx = 0;
		eastPanel.add(SBQTYfield, c);

		SBPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(SBPartField, c);

		SBLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(SBLBRfield, c);
		
		SBvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(SBvendor,c );
		
		

		AFQTYfield = new JFTextField(QTYformat);
		AFQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 28;
		c.gridx = 0;
		eastPanel.add(AFQTYfield, c);

		AFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(AFPartField, c);

		AFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(AFLBRfield, c);
		
		AFvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(AFvendor,c );
		
		

		FFQTYfield = new JFTextField(QTYformat);
		FFQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 29;
		c.gridx = 0;
		eastPanel.add(FFQTYfield, c);

		FFPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(FFPartField, c);

		FFLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(FFLBRfield, c);
		
		FFvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(FFvendor,c );
		
		

		radiatorQTYfield = new JFTextField(QTYformat);
		radiatorQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 30;
		c.gridx = 0;
		eastPanel.add(radiatorQTYfield, c);

		radiatorPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(radiatorPartField, c);

		radiatorLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(radiatorLBRfield, c);
		
		radiatorvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(radiatorvendor,c );
		
		

		URHQTYfield = new JFTextField(QTYformat);
		URHQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 31;
		c.gridx = 0;
		eastPanel.add(URHQTYfield, c);

		URHPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(URHPartField, c);

		URHLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(URHLBRfield, c);
		
		URHvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(URHvendor,c );
		
		

		LRHQTYfield = new JFTextField(QTYformat);
		LRHQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 32;
		c.gridx = 0;
		eastPanel.add(LRHQTYfield, c);

		LRHPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(LRHPartField, c);

		LRHLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(LRHLBRfield, c);
		
		LRHvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(LRHvendor,c );
		
		

		HBHQTYfield = new JFTextField(QTYformat);
		HBHQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 33;
		c.gridx = 0;
		eastPanel.add(HBHQTYfield, c);

		HBHPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(HBHPartField, c);

		HBHLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(HBHLBRfield, c);
		
		HBHvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(HBHvendor,c );
		
		

		//c.insets = new Insets(2, 4, 2, 4);

		JLabel QTY4 = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 34;
		QTY4.setBackground(theme);
		QTY4.setBorder(new LineBorder(Color.BLACK, 2));
		QTY4.setOpaque(true);
		eastPanel.add(QTY4, c);
		JLabel westParts3 = new JLabel("PART");
		c.gridx = 1;
		westParts3.setBackground(theme);
		westParts3.setBorder(new LineBorder(Color.BLACK, 2));
		westParts3.setOpaque(true);
		eastPanel.add(westParts3, c);
		JLabel LBR4HRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBR4HRS.setBackground(theme);
		LBR4HRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBR4HRS.setOpaque(true);
		eastPanel.add(LBR4HRS, c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		JLabel vendorName4 = new JLabel("Vendor Name");
		c.gridx = 3;
		vendorName4.setBackground(theme);
		vendorName4.setBorder(new LineBorder(Color.BLACK, 2));
		vendorName4.setOpaque(true);
		eastPanel.add(vendorName4, c);

		//c.insets = new Insets(5, 4, 5, 4);

		SRPQTYfield = new JFTextField(QTYformat);
		SRPQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 35;
		c.gridx = 0;
		eastPanel.add(SRPQTYfield, c);

		SRPPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(SRPPartField, c);

		SRPLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(SRPLBRfield, c);
		
		SRPvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(SRPvendor,c );
		
		

		SLQTYfield = new JFTextField(QTYformat);
		SLQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 36;
		c.gridx = 0;
		eastPanel.add(SLQTYfield, c);

		SLPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(SLPartField, c);

		SLLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(SLLBRfield, c);
		
		SLvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(SLvendor,c );
		
		

		suspQTYfield = new JFTextField(QTYformat);
		suspQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 37;
		c.gridx = 0;
		eastPanel.add(suspQTYfield, c);

		suspPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(suspPartField, c);

		suspLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(suspLBRfield, c);
		
		suspvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(suspvendor,c );
		
		

		alignQTYfield = new JFTextField(QTYformat);
		alignQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 38;
		c.gridx = 0;
		eastPanel.add(alignQTYfield, c);

		alignPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(alignPartField, c);

		alignLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(alignLBRfield, c);
		
		alignvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(alignvendor,c );
		
		

		FSSQTYfield = new JFTextField(QTYformat);
		FSSQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 39;
		c.gridx = 0;
		eastPanel.add(FSSQTYfield, c);

		FSSPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(FSSPartField, c);

		FSSLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(FSSLBRfield, c);
		
		FSSvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(FSSvendor,c );
		
		

		RSSQTYfield = new JFTextField(QTYformat);
		RSSQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 40;
		c.gridx = 0;
		eastPanel.add(RSSQTYfield, c);

		RSSPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(RSSPartField, c);

		RSSLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(RSSLBRfield, c);
		
		RSSvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(RSSvendor,c );
		
		

		FBQTYfield = new JFTextField(QTYformat);
		FBQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 41;
		c.gridx = 0;
		eastPanel.add(FBQTYfield, c);

		FBPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(FBPartField, c);

		FBLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(FBLBRfield, c);
		
		FBvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(FBvendor,c );
		
		

		RBQTYfield = new JFTextField(QTYformat);
		RBQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 42;
		c.gridx = 0;
		eastPanel.add(RBQTYfield, c);

		RBPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(RBPartField, c);

		RBLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(RBLBRfield, c);
		
		RBvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(RBvendor,c );
		
		

		CVADSQTYfield = new JFTextField(QTYformat);
		CVADSQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 43;
		c.gridx = 0;
		eastPanel.add(CVADSQTYfield, c);

		CVADSPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(CVADSPartField, c);

		CVADSLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(CVADSLBRfield, c);
		
		CVADvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(CVADvendor,c );
		
		

		mufflerQTYfield = new JFTextField(QTYformat);
		mufflerQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 44;
		c.gridx = 0;
		eastPanel.add(mufflerQTYfield, c);

		mufflerPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(mufflerPartField, c);

		mufflerLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(mufflerLBRfield, c);
		
		mufflervendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(mufflervendor,c );
		
		

		EPQTYfield = new JFTextField(QTYformat);
		EPQTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 45;
		c.gridx = 0;
		eastPanel.add(EPQTYfield, c);

		EPPartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(EPPartField, c);

		EPLBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(EPLBRfield, c);
		
		EPvendor = new vendorTextField();
		c.gridx = 3;
		eastPanel.add(EPvendor,c );
		
		
/*
		c.insets = new Insets(2, 4, 3, 4);

		JLabel QTY5 = new JLabel("QTY");
		c.gridx = 0;
		c.gridy = 46;
		QTY5.setBackground(theme);
		QTY5.setBorder(new LineBorder(Color.BLACK, 2));
		QTY5.setOpaque(true);
		eastPanel.add(QTY5, c);
		JLabel westParts4 = new JLabel("PART");
		c.gridx = 1;
		westParts4.setBackground(theme);
		westParts4.setBorder(new LineBorder(Color.BLACK, 2));
		westParts4.setOpaque(true);
		eastPanel.add(westParts4, c);
		JLabel LBR5HRS = new JLabel("LBR HRS");
		c.gridx = 2;
		LBR5HRS.setBackground(theme);
		LBR5HRS.setBorder(new LineBorder(Color.BLACK, 2));
		LBR5HRS.setOpaque(true);
		eastPanel.add(LBR5HRS, c);
		eastPanel.setBorder(new LineBorder(Color.BLACK, 2));
		JLabel vendorName5 = new JLabel("Vendor Name");
		c.gridx = 3;
		vendorName5.setBackground(theme);
		vendorName5.setBorder(new LineBorder(Color.BLACK, 2));
		vendorName5.setOpaque(true);
		eastPanel.add(vendorName5, c);

		c.insets = new Insets(5, 4, 5, 4);

		other1QTYfield = new JFTextField(QTYformat);
		other1QTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 47;
		c.gridx = 0;
		eastPanel.add(other1QTYfield, c);

		other1PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other1PartField, c);

		other1LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other1LBRfield, c);

		other2QTYfield = new JFTextField(QTYformat);
		other2QTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 48;
		c.gridx = 0;
		eastPanel.add(other2QTYfield, c);

		other2PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other2PartField, c);

		other2LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other2LBRfield, c);

		other3QTYfield = new JFTextField(QTYformat);
		other3QTYfield.setHorizontalAlignment(ABMTextField.TRAILING);
		c.gridy = 49;
		c.gridx = 0;
		eastPanel.add(other3QTYfield, c);

		other3PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other3PartField, c);

		other3LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other3LBRfield, c);

		other4QTYfield = new JFTextField(QTYformat);
		other4QTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 50;
		c.gridx = 0;
		eastPanel.add(other4QTYfield, c);

		other4PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other4PartField, c);

		other4LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other4LBRfield, c);

		other5QTYfield = new JFTextField(QTYformat);
		other5QTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 51;
		c.gridx = 0;
		eastPanel.add(other5QTYfield, c);

		other5PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other5PartField, c);

		other5LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other5LBRfield, c);

		other6QTYfield = new JFTextField(QTYformat);
		other6QTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 52;
		c.gridx = 0;
		eastPanel.add(other6QTYfield, c);

		other6PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other6PartField, c);

		other6LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other6LBRfield, c);

		other7QTYfield = new JFTextField(QTYformat);
		other7QTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 53;
		c.gridx = 0;
		eastPanel.add(other7QTYfield, c);

		other7PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other7PartField, c);

		other7LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other7LBRfield, c);

		other8QTYfield = new JFTextField(QTYformat);
		other8QTYfield.setHorizontalAlignment(JFTextField.TRAILING);
		c.gridy = 54;
		c.gridx = 0;
		eastPanel.add(other8QTYfield, c);

		other8PartField = new ABMTextField(format);
		c.gridx = 1;
		eastPanel.add(other8PartField, c);

		other8LBRfield = new ABMTextField(LBRformat);
		c.gridx = 2;
		eastPanel.add(other8LBRfield, c);
		*/
		imagePanel.setBackground(theme);
		
		JSplitPane eastSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				imagePanel, eastPanel);
		eastSplit.setDividerLocation(170);
		eastSplit.disable();

		midPanel.add(eastSplit, BorderLayout.EAST);
		midPanel.add(northPanel, BorderLayout.NORTH);
		midPanel.add(techPanel, BorderLayout.CENTER);

		/********************************** SOUTH PANEL **************************************/

		JPanel south = new JPanel(new GridBagLayout());
		south.setBackground(theme);
		//south.setBorder(new LineBorder(Color.BLACK, 2));
		
		

		
		JPanel southNorth = new JPanel(new GridBagLayout());
		southNorth.setBorder(new LineBorder(Color.BLACK, 2));
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(3,5,3,5);
		
		JLabel southWorkOrder = new JLabel("work order #");
		c.gridx = 0;
		c.gridy = 0;
		southNorth.add(southWorkOrder,c);
		JLabel workOrder = new JLabel("WORK ORDER");
		c.gridx = 1;
		southNorth.add(workOrder,c);
		JLabel southNorthFiller2 = new JLabel();
		c.gridx = 2;
		southNorth.add(southNorthFiller2,c);
		
		JTextField box1 = new JTextField(10);
		c.gridx = 0;
		c.gridy = 1;
		southNorth.add(box1,c);
		JLabel PMAdoneby = new JLabel("PMA DONE BY");
		c.gridx = 1;
		southNorth.add(PMAdoneby,c);

		JTextField box2 = new JTextField(10);
		c.gridx = 0;
		c.gridy = 2;
		southNorth.add(box2,c);
		JLabel checkedBy = new JLabel("CHECKED BY");
		c.gridx = 1;
		southNorth.add(checkedBy,c);

		JTextField box3 = new JTextField(10);
		c.gridx = 0;
		c.gridy = 3;
		southNorth.add(box3,c);
		JLabel filler = new JLabel();
		c.gridx = 1;
		southNorth.add(filler,c);

		JTextField box4 = new JTextField(10);
		c.gridx = 0;
		c.gridy = 4;
		southNorth.add(box4,c);

		c.insets = new Insets(3,200,3,240);
		c.fill = GridBagConstraints.CENTER;
		
		JLabel label1 = new JLabel("THE 2 FAT GUYS PRICE GUARANTEE");
		label1.setFont(font);
		c.gridx = 4;
		c.gridy = 1;
		southNorth.add(label1, c);
		JLabel label2 = new JLabel("IF YOU FIND A LOWER PRICE ON ANY OF THE");
		c.gridy = 2;
		southNorth.add(label2,c);
		JLabel label3 = new JLabel("SERVICES SHOWN ABOVE, LET US KNOW");
		c.gridy = 3;
		southNorth.add(label3,c);
		JLabel label4 = new JLabel("AND WE WILL BEAT IT! GUARANTEED");
		label4.setFont(font);
		c.gridy = 4;
		southNorth.add(label4,c);
		
		

		
		
		c.insets = new Insets(3,5,3,5);
		c.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel totalPartsLabel = new JLabel("TOTAL PARTS");
		c.gridx = 5;
		c.gridy = 0;
		southNorth.add(totalPartsLabel,c);
		
		totalParts = new JFormattedTextField(format);
		totalParts.setValue(0);
		totalParts.setEditable(false);
		c.gridx = 6;
		southNorth.add(totalParts,c);

		
		JLabel totalLaborLabel = new JLabel("TOTAL LABOR");
		c.gridx = 5;
		c.gridy = 1;
		southNorth.add(totalLaborLabel,c);
		
		totalLabor = new JFormattedTextField(format);
		totalLabor.setValue(0);
		totalLabor.setEditable(false);
		c.gridx = 6;
		southNorth.add(totalLabor,c);
		
		
		JLabel totalLabel = new JLabel("TOTAL");
		c.gridx = 5;
		c.gridy = 2;
		southNorth.add(totalLabel,c);
		
		totalPrice = new JFormattedTextField(format);
		totalPrice.setValue(0);
		totalPrice.setEditable(false);
		c.gridx = 6;
		southNorth.add(totalPrice,c);

		
		JLabel taxLabel = new JLabel("TAX");
		c.gridx = 5;
		c.gridy = 3;
		southNorth.add(taxLabel,c);
		tax = new JFormattedTextField(format);
		tax.setValue(0);
		tax.setEditable(false);
		c.gridx = 6;
		southNorth.add(tax,c);

		
		JLabel shopSuppliesLabel = new JLabel("SHOP SUPPLIES");
		c.gridx = 5;
		c.gridy = 4;
		southNorth.add(shopSuppliesLabel,c);
		
		shopSupplies = new JFormattedTextField(format);
		shopSupplies.setValue(0);
		shopSupplies.setEditable(false);
		c.gridx = 6;
		southNorth.add(shopSupplies,c);
		
		
		JLabel grandTotalLabel = new JLabel("GRAND TOTAL");
		c.gridx = 5;
		c.gridy = 5;
		southNorth.add(grandTotalLabel,c);
		
		grandTotal = new JFormattedTextField(format);
		grandTotal.setValue(0);
		grandTotal.setEditable(false);
		c.gridx = 6;
		southNorth.add(grandTotal,c);
		
		submit = new JButton("Submit");
		c.gridx = 6;
		c.gridy = 6;
		southNorth.add(submit,c);
		

		/*****************************************************************************/
		
		JPanel southSouth = new JPanel(new GridBagLayout());
		southSouth.setBorder(new LineBorder(Color.BLACK, 2));
		c = new GridBagConstraints();
		c.insets = new Insets(2,50,2,50);
		
		JLabel highLabel = new JLabel("HIGH");
		c.gridx = 0;
		c.gridy = 0;
		southSouth.add(highLabel, c);
		
		high = new JFormattedTextField(format);
		high.setValue(0);
		high.setEditable(false);
		c.gridx = 0;
		c.gridy = 1;
		southSouth.add(high, c);
		
		JLabel medLabel = new JLabel("MED");
		c.gridx = 1;
		c.gridy = 0;
		southSouth.add(medLabel, c);
		
		med = new JFormattedTextField(format);
		med.setValue(0);
		med.setEditable(false);
		c.gridx = 1;
		c.gridy = 1;
		southSouth.add(med, c);
		
		JLabel lowLabel = new JLabel("LOW");
		c.gridx = 2;
		c.gridy = 0;
		southSouth.add(lowLabel, c);
		
		low = new JFormattedTextField(format);
		low.setValue(0);
		low.setEditable(false);
		c.gridx = 2;
		c.gridy = 1;
		southSouth.add(low, c);
		
		c = new GridBagConstraints();
		c.insets = new Insets(5,20,5,20);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridy=0;
		south.add(southNorth, c);
		c.gridy = 1;
		south.add(southSouth, c);
		midPanel.add(south, BorderLayout.SOUTH);

		/************************************************************************ END ************************************************************/

		checkBoxes = new JCheckBox[][] { { DSFTOK, DSFTNOTOK },
				{ PSFTOK, PSFTNOTOK }, { PSRTOK, PSRTNOTOK },
				{ DSRTOK, DSRTNOTOK }, { spareOK, spareNOTOK },
				{ WWOK, WWNOTOK }, { locksOK, locksNOTOK },
				{ ACHOK, ACHNOTOK }, { heaterOK, heaterNOTOK },
				{ wiperOK, wiperNOTOK }, { hornOK, hornNOTOK },
				{ HLOK, HLNOTOK }, { PTLOK, PTLNOTOK }, { BLOK, BLNOTOK },
				{ DSOK, DSNOTOK }, { engineDiagOK, engineDiagNOTOK },
				{ MMOK, MMNOTOK }, { BCBOK, BCBNOTOK }, { EOOK, EONOTOK },
				{ TFOK, TFNOTOK }, { WFOK, WFNOTOK }, { BFOK, BFNOTOK },
				{ PSFOK, PSFNOTOK }, { coolantOK, coolantNOTOK },
				{ SBOK, SBNOTOK }, { AFOK, AFNOTOK }, { FFOK, FFNOTOK },
				{ radiatorOK, radiatorNOTOK }, { URHOK, URHNOTOK },
				{ LRHOK, LRHNOTOK }, { HBHOK, HBHNOTOK }, { SRPOK, SRPNOTOK },
				{ SLOK, SLNOTOK }, {suspOK, suspNOTOK}, { alignOK, alignNOTOK },
				{ FSSOK, FSSNOTOK }, { RSSOK, RSSNOTOK }, { FBOK, FBNOTOK },
				{ RBOK, RBNOTOK }, { CVADOK, CVADNOTOK },
				{ mufflerOK, mufflerNOTOK }, { EPOK, EPNOTOK } };

		comboBoxes = new myComboBox[][] {
				{ DSFTtechCommentBox, DSFTrecommendedCommentBox,
						DSFTpriorityBox },
				{ PSFTtechCommentBox, PSFTrecommendedCommentBox,
						PSFTpriorityBox },
				{ PSRTtechCommentBox, PSRTrecommendedCommentBox,
						PSRTpriorityBox },
				{ DSRTtechCommentBox, DSRTrecommendedCommentBox,
						DSRTpriorityBox },
				{ spareTechCommentBox, spareRecommendedCommentBox,
						sparePriorityBox },
				{ WWTechCommentBox, WWRecommendedCommentBox, WWPriorityBox },
				{ locksTechCommentBox, locksRecommendedCommentBox,
						locksPriorityBox },
				{ ACHTechCommentBox, ACHRecommendedCommentBox, ACHPriorityBox },
				{ heaterTechCommentBox, heaterRecommendedCommentBox,
						heaterPriorityBox },
				{ wiperTechCommentBox, wiperRecommendedCommentBox,
						wiperPriorityBox },
				{ hornTechCommentBox, hornRecommendedCommentBox,
						hornPriorityBox },
				{ HLTechCommentBox, HLRecommendedCommentBox, HLPriorityBox },
				{ PTLTechCommentBox, PTLRecommendedCommentBox, PTLPriorityBox },
				{ brakeTechCommentBox, brakeRecommendedCommentBox,
						brakePriorityBox },
				{ DSTechCommentBox, DSRecommendedCommentBox, DSPriorityBox },
				{ engineDiagTechCommentBox, engineDiagRecommendedCommentBox,
						engineDiagPriorityBox },
				{ MMTechCommentBox, MMRecommendedCommentBox, MMPriorityBox },
				{ BCBTechCommentBox, BCBRecommendedCommentBox, BCBPriorityBox },
				{ EOTechCommentBox, EORecommendedCommentBox, EOPriorityBox },
				{ TFTechCommentBox, TFRecommendedCommentBox, TFPriorityBox },
				{ WFTechCommentBox, WFRecommendedCommentBox, WFPriorityBox },
				{ BFTechCommentBox, BFRecommendedCommentBox, BFPriorityBox },
				{ PSFTechCommentBox, PSFRecommendedCommentBox, PSFPriorityBox },
				{ coolantTechCommentBox, coolantRecommendedCommentBox,
						coolantPriorityBox },
				{ SBTechCommentBox, SBRecommendedCommentBox, SBPriorityBox },
				{ AFTechCommentBox, AFRecommendedCommentBox, AFPriorityBox },
				{ FFTechCommentBox, FFRecommendedCommentBox, FFPriorityBox },
				{ radiatorTechCommentBox, radiatorRecommendedCommentBox,
						radiatorPriorityBox },
				{ URHTechCommentBox, URHRecommendedCommentBox, URHPriorityBox },
				{ LRHTechCommentBox, LRHRecommendedCommentBox, LRHPriorityBox },
				{ HBHTechCommentBox, HBHRecommendedCommentBox, HBHPriorityBox },
				{ SRPTechCommentBox, SRPRecommendedCommentBox, SRPPriorityBox },
				{ SLTechCommentBox, SLRecommendedCommentBox, SLPriorityBox },
				{ suspTechCommentBox, suspRecommendedCommentBox,
						suspPriorityBox },
				{ alignTechCommentBox, alignRecommendedCommentBox,
						alignPriorityBox },
				{ FSSTechCommentBox, FSSRecommendedCommentBox, FSSPriorityBox },
				{ RSSTechCommentBox, RSSRecommendedCommentBox, RSSPriorityBox },
				{ FBTechCommentBox, FBRecommendedCommentBox, FBPriorityBox },
				{ RBTechCommentBox, RBRecommendedCommentBox, RBPriorityBox },
				{ CVADTechCommentBox, CVADRecommendedCommentBox,
						CVADPriorityBox },
				{ mufflerTechCommentBox, mufflerRecommendedCommentBox,
						mufflerPriorityBox },
				{ EPTechCommentBox, EPRecommendedCommentBox, EPPriorityBox } };

		otherFields = new JTextField[][] {
				{ other1Label, other1TechCommentBox,
						other1RecommendedCommentBox },
				{ other2Label, other2TechCommentBox,
						other2RecommendedCommentBox },
				{ other3Label, other3TechCommentBox,
						other3RecommendedCommentBox },
				{ other4Label, other4TechCommentBox,
						other4RecommendedCommentBox },
				{ other5Label, other5TechCommentBox,
						other5RecommendedCommentBox },
				{ other6Label, other6TechCommentBox,
						other6RecommendedCommentBox },
				{ other7Label, other7TechCommentBox,
						other7RecommendedCommentBox },
				{ other8Label, other8TechCommentBox,
						other8RecommendedCommentBox } };

		otherComboBoxes = new JComboBox[] { other1PriorityBox,
				other2PriorityBox, other3PriorityBox, other4PriorityBox,
				other5PriorityBox, other6PriorityBox, other7PriorityBox,
				other8PriorityBox };

		totalFields = new ABMTextField[][] { { DSFTtireField, DSFTlaborField },
				{ PSFTtireField, PSFTlaborField },
				{ PSRTtireField, PSRTlaborField },
				{ DSRTtireField, DSRTlaborField },
				{ spareTireField, spareLaborField },
				{ WWPartsField, WWLaborField },
				{ locksPartsField, locksLaborField },
				{ ACHPartsField, ACHLaborField },
				{ heaterPartsField, heaterLaborField },
				{ wiperPartsField, wiperLaborField },
				{ hornPartsField, hornLaborField },
				{ HLPartsField, HLLaborField },
				{ PTLPPartsField, PTLPLaborField },
				{ brakePartsField, brakeLaborField },
				{ DSPartsField, DSLaborField },
				{ engineDiagPartsField, engineDiagLaborField },
				{ MMPartsField, MMLaborField },
				{ BCBPartsField, BCBLaborField },
				{ EOPartsField, EOLaborField }, { TFPartsField, TFLaborField },
				{ WFPartsField, WFLaborField }, { BFPartsField, BFLaborField },
				{ PSFPartsField, PSFLaborField },
				{ coolantPartsField, coolantLaborField },
				{ SBPartsField, SBLaborField }, { AFPartsField, AFLaborField },
				{ FFPartsField, FFLaborField },
				{ radiatorPartsField, radiatorLaborField },
				{ URHPartsField, URHLaborField },
				{ LRHPartsField, LRHLaborField },
				{ HBHPartsField, HBHLaborField },
				{ SRPPartsField, SRPLaborField },
				{ SLPartsField, SLLaborField },
				{ suspPartsField, suspLaborField },
				{ alignPartsField, alignLaborField },
				{ FSSPartsField, FSSLaborField },
				{ RSSPartsField, RSSLaborField },
				{ FBPartsField, FBLaborField }, { RBPartsField, RBLaborField },
				{ CVADPartsField, CVADLaborField },
				{ mufflerPartsField, mufflerLaborField },
				{ EPPartsField, EPLaborField } };

		numberFields = new ABMTextField[][] { { DSFTTireField, DSFTLBRfield },
				{ PSFTTireField, PSFTLBRfield },
				{ PSRTTireField, PSRTLBRfield },
				{ DSRTTireField, DSRTLBRfield },
				{ otherSpareTireField, spareLBRfield },
				{ WWPartField, WWLBRfield }, { locksPartField, locksLBRfield },
				{ ACHPartField, ACHLBRfield },
				{ heaterPartField, heaterLBRfield },
				{ wiperPartField, wiperLBRfield },
				{ hornPartField, hornLBRfield }, { HLPartField, HLLBRfield },
				{ PTLPPartField, PTLPLBRfield }, { BLPartField, BLLBRfield },
				{ DSPartField, DSLBRfield },
				{ engineDiagPartField, engineDiagLBRfield },
				{ MMPartField, MMLBRfield }, { BCBPartField, BCBLBRfield },
				{ EOPartField, EOLBRfield }, { TFPartField, TFLBRfield },
				{ WFPartField, WFLBRfield }, { BFPartField, BFLBRfield },
				{ PSFPartField, PSFLBRfield },
				{ coolantPartField, coolantLBRfield },
				{ SBPartField, SBLBRfield }, { AFPartField, AFLBRfield },
				{ FFPartField, FFLBRfield },
				{ radiatorPartField, radiatorLBRfield },
				{ URHPartField, URHLBRfield }, { LRHPartField, LRHLBRfield },
				{ HBHPartField, HBHLBRfield }, { SRPPartField, SRPLBRfield },
				{ SLPartField, SLLBRfield }, { suspPartField, suspLBRfield },
				{ alignPartField, alignLBRfield },
				{ FSSPartField, FSSLBRfield }, { RSSPartField, RSSLBRfield },
				{ FBPartField, FBLBRfield }, { RBPartField, RBLBRfield },
				{ CVADSPartField, CVADSLBRfield },
				{ mufflerPartField, mufflerLBRfield },
				{ EPPartField, EPLBRfield }};

		QTYfields = new JFTextField[] { DSFTQTYfield, PSFTQTYfield,
				PSRTQTYfield, DSRTQTYfield, spareQTYfield, WWQTYfield,
				locksQTYfield, ACHQTYfield, heaterQTYfield, wiperQTYfield,
				hornQTYfield, HLQTYfield, PTLPQTYfield, BLQTYfield, DSQTYfield,
				engineDiagQTYfield, MMQTYfield, BCBQTYfield, EOQTYfield,
				TFQTYfield, WFQTYfield, BFQTYfield, PSFQTYfield,
				coolantQTYfield, SBQTYfield, AFQTYfield, FFQTYfield,
				radiatorQTYfield, URHQTYfield, LRHQTYfield, HBHQTYfield,
				SRPQTYfield, SLQTYfield, suspQTYfield, alignQTYfield,
				FSSQTYfield, RSSQTYfield, FBQTYfield, RBQTYfield,
				CVADSQTYfield, mufflerQTYfield, EPQTYfield};

		
		buttonLabels = new JLButton[] {DSFTLabel, PSFTLabel, PSRTLabel, DSRTLabel, spareLabel, WWLabel, locksLabel, ACHLabel, heaterLabel, wiperLabel, hornLabel, HLLabel, PTLLabel, brakeLabel, DSLabel, 
				engineDiagLabel, MMLabel, BCBLabel, EOLabel, TFLabel, WFLabel, BFLabel, PSFLabel, coolantLabel, SBLabel, AFLabel, FFLabel, radiatorLabel, URHLabel, LRHLabel, HBHLabel, SRPLabel, SLLabel, 
				suspLabel, alignLabel, FSSLabel, RSSLabel, FBLabel, RBLabel, CVADLabel, mufflerLabel, EPLabel};
		
		
		vendorFields = new vendorTextField[] {DSFTvendor, PSFTvendor, PSRTvendor, DSRTvendor, sparevendor, WWvendor, locksvendor, ACHvendor, heatervendor, wipervendor, hornvendor, HLvendor, PTLvendor, brakevendor, DSvendor, 
				engineDiagvendor, MMvendor, BCBvendor, EOvendor, TFvendor, WFvendor, BFvendor, PSFvendor, coolantvendor, SBvendor, AFvendor, FFvendor, radiatorvendor, URHvendor, LRHvendor, HBHvendor, SRPvendor, SLvendor, 
				suspvendor, alignvendor, FSSvendor, RSSvendor, FBvendor, RBvendor, CVADvendor, mufflervendor, EPvendor};
		
		
		for (int i = 0; i < numberFields.length; i++) {
			if(i<5){
				changeProperty changer = new changeProperty("value",
					totalFields[i][0], totalFields[i][1], QTYfields[i],
					numberFields[i][0], numberFields[i][1], true);
			} else {
				changeProperty changer = new changeProperty("value",
						totalFields[i][0], totalFields[i][1], QTYfields[i],
						numberFields[i][0], numberFields[i][1], false);
			}
		}
		
		
		for(int i = 0; i < buttonLabels.length; i++) {
			JLButtonListener JLlistener = new JLButtonListener(i);
			buttonLabels[i].addActionListener(JLlistener);
		}
		
		

		container.add(midPanel, BorderLayout.CENTER);
		JScrollPane window = new JScrollPane(container);
		window.getVerticalScrollBar().setUnitIncrement(16);
		setContentPane(window);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setVisible(true);
	}

	public static double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}
	
	
	public void EnableTechPanel(boolean value){
		techPanel.setVisible(value);
	}
	
	
	/**
	 * OKbuttonListener implements an action listener for when the "OK" check box is selected
	 * or when the "NOT OK" check box is selected
	 * 
	 * 
	 * @author adrian
	 *
	 */

	public class OKbuttonListener implements ActionListener {
		private JCheckBox box1, box2;
		private int place;

		public OKbuttonListener(JCheckBox box1, JCheckBox box2, int i) {
			this.box1 = box1;
			this.box2 = box2;
			this.place = i;
		}

		public void actionPerformed(ActionEvent e) {
			for (int j = 0; j < 2; j++) {
				numberFields[place][j].setEnabled(false);
				totalFields[place][j].setEnabled(false);
				totalFields[place][j].setValue(0.0);
				totalFields[place][j].setBackground(Color.white);
			}
			comboBoxes[place][2].setBackground(null);
			QTYfields[place].setEnabled(false);
			vendorFields[place].setEnabled(false);
			box1.setBackground(null);
			box2.setBackground(null);
			if (e.getSource() == box1) {
				if (box1.isSelected()) {
					for (int j = 0; j < 2; j++) {
						numberFields[place][j].setEnabled(false);
						numberFields[place][j].setValue(0.0);
						totalFields[place][j].setEnabled(false);
						totalFields[place][j].setValue(0.0);
						totalFields[place][j].setBackground(Color.white);
					}
					QTYfields[place].setEnabled(false);
					box1.setBackground(Color.green);
					vendorFields[place].setEnabled(false);
				}
				box2.setSelected(false);
				box2.setBackground(null);
			} else if (e.getSource() == box2) {
				if (box2.isSelected()) {
					for (int j = 0; j < 2; j++) {
						numberFields[place][j].setEnabled(true);
						totalFields[place][j].setEnabled(true);
					}
					QTYfields[place].setEnabled(true);
					box2.setBackground(Color.red);
					vendorFields[place].setEnabled(true);
				}
				box1.setSelected(false);
				box1.setBackground(null);
			}
		}
	}
	

	
	/**
	 * JLButtonListener implements ActionListener for when the buttonLabel is pressed
	 * locks the entire line and add the parts and labor field to the total values.
	 * 
	 * 
	 * @author adrian
	 *
	 */
	public class JLButtonListener implements ActionListener {

		int place;
		
		public JLButtonListener(int place){
			this.place = place;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			if(checkBoxes[place][1].isSelected() && !buttonLabels[place].isSelected()){
		
				activateLine(false, place);
		
				double parts = new Double(totalParts.getValue().toString());
				totalParts.setValue(parts + totalFields[place][0].getValue());
				
				double labor = new Double(totalLabor.getValue().toString());
				totalLabor.setValue(labor + totalFields[place][1].getValue());
				
				double total = new Double(totalParts.getValue().toString()) + new Double(totalLabor.getValue().toString());
				totalPrice.setValue(total);
				
				parts = new Double(totalParts.getValue().toString());
				tax.setValue(parts*.0825);
				
				shopSupplies.setValue(total*.03);
				
				double shopSuppliesAmount = new Double(shopSupplies.getValue().toString());
				double taxAmount = new Double(tax.getValue().toString());
				grandTotal.setValue(total+taxAmount+shopSuppliesAmount);
				
				double val;
				if(comboBoxes[place][2].getSelectedIndex() == 1){
					val = new Double(high.getValue().toString());
					high.setValue(val + (totalFields[place][0].getValue() + totalFields[place][1].getValue()));
				} else if(comboBoxes[place][2].getSelectedIndex() == 2){
					val = new Double(med.getValue().toString());
					med.setValue(val + (totalFields[place][0].getValue() + totalFields[place][1].getValue()));
				} else if(comboBoxes[place][2].getSelectedIndex() == 3){
					val = new Double(low.getValue().toString());
					low.setValue(val + (totalFields[place][0].getValue() + totalFields[place][1].getValue()));
				}
				
			} else if(checkBoxes[place][1].isSelected() && buttonLabels[place].isSelected()){
				
				activateLine(true, place);
				
				double parts = new Double(totalParts.getValue().toString());
				totalParts.setValue(parts - totalFields[place][0].getValue());
				
				double labor = new Double(totalLabor.getValue().toString());
				totalLabor.setValue(labor - totalFields[place][1].getValue());
				
				double total = new Double(totalParts.getValue().toString()) + new Double(totalLabor.getValue().toString());
				totalPrice.setValue(total);
				
				parts = new Double(totalParts.getValue().toString());
				tax.setValue(parts*.0825);
				
				shopSupplies.setValue(total*.03);
				
				double shopSuppliesAmount = new Double(shopSupplies.getValue().toString());
				double taxAmount = new Double(tax.getValue().toString());
				grandTotal.setValue(total+taxAmount+shopSuppliesAmount);
				
				
				double val;
				if(comboBoxes[place][2].getSelectedIndex() == 1){
					val = new Double(high.getValue().toString());
					high.setValue(val - (totalFields[place][0].getValue() + totalFields[place][1].getValue()));
				} else if(comboBoxes[place][2].getSelectedIndex() == 2){
					val = new Double(med.getValue().toString());
					med.setValue(val - (totalFields[place][0].getValue() + totalFields[place][1].getValue()));
				} else if(comboBoxes[place][2].getSelectedIndex() == 3){
					val = new Double(low.getValue().toString());
					low.setValue(val - (totalFields[place][0].getValue() + totalFields[place][1].getValue()));
				}
			}
		}
		
		public class DisabledListCellRenderer extends DefaultListCellRenderer {
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel lbl = (JLabel) super.getListCellRendererComponent(list,
						value, index, isSelected, cellHasFocus);
				lbl.setText((String) value);
				lbl.setOpaque(false);
				return lbl;
			}
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void activateLine(boolean value, int place){
		buttonLabels[place].setSelected(!value);
		if(value) buttonLabels[place].setBackground(null);
		else buttonLabels[place].setBackground(new Color(81,127,164));
		vendorFields[place].setBackground(null);
		checkBoxes[place][0].setEnabled(value);
		checkBoxes[place][1].setEnabled(value);
		comboBoxes[place][0].setEnabled(value);
		comboBoxes[place][1].setEnabled(value);
		comboBoxes[place][2].setEnabled(value);
		if(!value){
			totalFields[place][0].setDisabledTextColor(Color.black);
			totalFields[place][1].setDisabledTextColor(Color.black);
			QTYfields[place].setDisabledTextColor(Color.black);
			numberFields[place][0].setDisabledTextColor(Color.black);
			numberFields[place][1].setDisabledTextColor(Color.black);
			vendorFields[place].setDisabledTextColor(Color.black);
		}else{
			totalFields[place][0].setDisabledTextColor(Color.white);
			totalFields[place][1].setDisabledTextColor(Color.white);
			QTYfields[place].setDisabledTextColor(Color.white);
			numberFields[place][0].setDisabledTextColor(Color.white);
			numberFields[place][1].setDisabledTextColor(Color.white);
			vendorFields[place].setDisabledTextColor(Color.white);
		}
		totalFields[place][0].setEnabled(value);
		totalFields[place][1].setEnabled(value);
		QTYfields[place].setEnabled(value);
		numberFields[place][0].setEnabled(value);
		numberFields[place][1].setEnabled(value);
		vendorFields[place].setEnabled(value);
	}
	

	public class changeProperty implements PropertyChangeListener {
		ABMTextField costField, LBRHRSField;
		ABMTextField allCostField, laborField;
		JFTextField QTYField;
		boolean tire;

		public changeProperty(String value, final ABMTextField allCostField,
				final ABMTextField laborField, final JFTextField QTYfield,
				final ABMTextField costField, final ABMTextField LBRHRSField,
				boolean tire) {
			this.allCostField = allCostField;
			this.laborField = laborField;
			this.QTYField = QTYfield;
			this.costField = costField;
			this.LBRHRSField = LBRHRSField;
			this.tire = tire;

			
			QTYfield.addPropertyChangeListener(value, this);
			costField.addPropertyChangeListener(value, this);
			LBRHRSField.addPropertyChangeListener(value, this);
			allCostField.addPropertyChangeListener(value, this);
			laborField.addPropertyChangeListener(value, this);
		}

		@Override
		public void propertyChange(PropertyChangeEvent e) {
			Object source = e.getSource();
			double amount, QTY;

			if (source == costField || source == QTYField) {
				amount = costField.getValue();
				QTY = ((Number) QTYField.getValue()).doubleValue();
				amount = amount * QTY * 2.05;
				allCostField.setValue(amount);
			} else if (source == LBRHRSField) {
				amount =  new Double(LBRHRSField.getValue().toString());
				if (tire && amount != 0)
					amount = 14.5;
				else 
					amount = amount * 99;
				
				laborField.setValue(amount);
			}

			if (e.getNewValue() == allCostField) {
				System.out.println("new field" + allCostField.getValue());
			}
		}
	}
}
