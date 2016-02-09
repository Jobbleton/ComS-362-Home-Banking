import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.CompoundBorder;

public class HomeBankingView extends JFrame
{
	private static final long serialVersionUID = 1L;
	Image storedImage;
	private JPanel mainContentPane;

	//Launch the application.
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					HomeBankingView frame = new HomeBankingView();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	//Create the frame.
	public HomeBankingView()
	{
		//block for main window
		setTitle("ComS 362 Home Banking");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 720);
		mainContentPane = new JPanel();
		mainContentPane.setBorder(new EmptyBorder(3,3,3,3));
		setContentPane(mainContentPane);
		mainContentPane.setLayout(new BorderLayout(0, 0));
		
		//main internal panel
		JPanel largeInternalPanel = new JPanel();
		largeInternalPanel.setBorder(new CompoundBorder());
		largeInternalPanel.setBackground(SystemColor.controlHighlight);
		mainContentPane.add(largeInternalPanel);
		largeInternalPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		largeInternalPanel.add(scrollPane);
	}
}

