package gestorlevi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FormOjetes extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;
	private Conex conex;
	private JLabel lbImagen;
	private ImageIcon imagen;
	private JPanel pnOjetes;
	private JTable taOjetes;
	private ModeloTabla modelotabla;
	
	public FormOjetes()
	{
		conex = new Conex();
		iniComponentes();
	}
	
	private void iniComponentes()
	{
		this.setLayout(null);
		this.setTitle("GestorLevi -- Version:prueba Jre 8.1");
		this.addWindowListener(this);
		
		imagen = new ImageIcon("C:\\Users\\Levi\\Desktop\\ojetes.jpg");
		lbImagen = new JLabel(imagen);
		lbImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lbImagen.setBounds(1, 1, 482, 80);
		
		modelotabla = new ModeloTabla();
		modelotabla.addColumn("Id");
		modelotabla.addColumn("Refer.");
		modelotabla.addColumn("Color");
		modelotabla.addColumn("Nombre");
		
		taOjetes = new JTable(modelotabla);
		taOjetes.getColumnModel().getColumn(0).setMaxWidth(40);
		//taOjetes.getColumnModel().getColumn(1).setMaxWidth(60);
		
		pnOjetes = new JPanel(new BorderLayout());
		pnOjetes.setBounds(10, 90, 260, 350);
		pnOjetes.setBorder(BorderFactory.createTitledBorder("Listado de ojetes"));
		pnOjetes.add(new JScrollPane(taOjetes), BorderLayout.CENTER);
		
		this.add(lbImagen);
		this.add(pnOjetes);
		
		this.setSize(500, 490);
		this.setLocation(300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void windowActivated(WindowEvent arg0){}
	@Override
	public void windowClosed(WindowEvent arg0)
	{
		conex.close();
		new FormMenu();
	}
	@Override
	public void windowClosing(WindowEvent arg0){}
	@Override
	public void windowDeactivated(WindowEvent arg0){	}
	@Override
	public void windowDeiconified(WindowEvent arg0){}
	@Override
	public void windowIconified(WindowEvent arg0){}
	@Override
	public void windowOpened(WindowEvent arg0){}
}
