package gestorlevi;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class FormMenu extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 1L;
	private JButton btIr;
	private JLabel lbImagen;
	private ImageIcon imagen;
	private JPanel pnIra;
	private JComboBox<String> cbNuevo;
	private String ListaNuevos[] = {"Colores", "Ojetes", "Ribete"};
 
	public FormMenu()
	{
		this.IniComponentes();
	}
		
	private void IniComponentes()
	{
		this.setLayout(null);
		imagen = new ImageIcon("C:\\Users\\Levi\\Desktop\\zapato1.png");
		
		lbImagen = new JLabel(imagen);
		lbImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lbImagen.setBounds(1, 1, 882, 150);
		
		cbNuevo = new JComboBox<String>(ListaNuevos);
		cbNuevo.setBounds(15, 25, 130, 25);
		
		btIr = new JButton("Ir");
		btIr.addActionListener(this);
		btIr.setBounds(15, 60, 80, 25);
		
		pnIra = new JPanel(null);
		pnIra.setBounds(20, 170, 220, 100);
		pnIra.setBorder(BorderFactory.createTitledBorder("Selecciona un complemento"));
		pnIra.add(cbNuevo);
		pnIra.add(btIr);
		
		this.add(pnIra);
		this.add(lbImagen);
		
		this.setSize(900, 700);
		this.setLocation(300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if(cbNuevo.getSelectedItem().equals("Colores"))
			new FormColor();
		
		if(cbNuevo.getSelectedItem().equals("Ojetes"))
			new FormOjetes();
		
		this.dispose();
	}
}
