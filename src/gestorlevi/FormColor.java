package gestorlevi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class FormColor extends JFrame implements WindowListener, ActionListener, MouseListener, FocusListener
{
	private static final long serialVersionUID = 1L;
	//private JScrollPane scColores;
	private JTable taColores;
	private JPanel pnColores, pnNuevocolor, pnModcolor;
	private JLabel lbImagen;
	private JButton btNuevocolor, btModificar, btGestion, btBorrar, btSalir;
	private TextFieldCom tfcRef, tfcNombre;
	private JTextField tfRef, tfNombre;
	private ImageIcon imagen;
	private ModeloTabla modelotabla;
	private Conex conex;
	private String auxRef, auxNombre, id;
	
	//public FormColor(JFrame parent, boolean modal)
	public FormColor()
	{
		//super(parent, modal);
		conex = new Conex();
		IniComponentes();
		conex.CargaTablaColores(modelotabla);
	}
	
	private void IniComponentes()
	{
		this.setLayout(null);
		this.setTitle("GestorLevi -- Version:prueba Jre 8.1");
		this.addWindowListener(this);
		auxRef = "";
		auxNombre = "";
		
		imagen = new ImageIcon("C:\\Users\\Levi\\Desktop\\colores.jpg");
		lbImagen = new JLabel(imagen);
		lbImagen.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		lbImagen.setBounds(1, 1, 482, 80);
		
		tfcRef = new TextFieldCom("Introduce Referencia");
		tfcRef.setBounds(12, 20, 175, 25);
		tfcNombre = new TextFieldCom("Introduce Nombre");
		tfcNombre.setBounds(12, 50, 175, 25);
		
		tfRef = new JTextField();
		tfRef.setBounds(12, 20, 175, 25);
		tfRef.addFocusListener(this);
		tfRef.setEnabled(false);
		tfNombre = new JTextField();
		tfNombre.setBounds(12, 50, 175, 25);
		tfNombre.addFocusListener(this);
		tfNombre.setEnabled(false);
		
		btNuevocolor = new JButton("Crear");
		btNuevocolor.addActionListener(this);
		btNuevocolor.setBounds(12, 90, 80, 25);
		btModificar = new JButton("Modificar");
		btModificar.addActionListener(this);
		btModificar.setBounds(12, 90, 80, 25);
		btModificar.setEnabled(false);
		btBorrar = new JButton("Borrar");
		btBorrar.addActionListener(this);
		btBorrar.setBounds(105, 90, 80, 25);
		btBorrar.setEnabled(false);
		btSalir = new JButton("Salir");
		btSalir.addActionListener(this);
		btSalir.setBounds(390, 405, 80, 25);
		
		btGestion = new JButton("Gestion");
		btGestion.addActionListener(this);
		btGestion.setBounds(284, 405, 80, 25);
		btGestion.setEnabled(false);

		modelotabla = new ModeloTabla();
		modelotabla.addColumn("ID");
		modelotabla.addColumn("Refer.");
		modelotabla.addColumn("Nombre");
		taColores = new JTable(modelotabla);
		taColores.getColumnModel().getColumn(0).setMaxWidth(40);
		taColores.getColumnModel().getColumn(1).setMaxWidth(60);
		//taColores.getColumnModel().getColumn(2).setMaxWidth(200);
		taColores.addMouseListener(this);
		
		pnColores = new JPanel(new BorderLayout());
		pnColores.setBounds(10, 90, 260, 350);
		pnColores.setBorder(BorderFactory.createTitledBorder("Listado de colores"));
		//scColores = new JScrollPane(taColores);
		//pnColores.add(scColores, BorderLayout.CENTER);
		pnColores.add(new JScrollPane(taColores), BorderLayout.CENTER);
		pnNuevocolor = new JPanel(null);
		pnNuevocolor.setBounds(280, 90, 195, 130);
		pnNuevocolor.setBorder(BorderFactory.createTitledBorder("Crear nuevo color"));
		pnNuevocolor.add(tfcRef);
		pnNuevocolor.add(tfcNombre);
		pnNuevocolor.add(btNuevocolor);
		
		pnModcolor = new JPanel(null);
		pnModcolor.setBounds(280, 235, 195, 130);
		pnModcolor.setBorder(BorderFactory.createTitledBorder("Gestion color"));
		pnModcolor.add(tfRef);
		pnModcolor.add(tfNombre);
		pnModcolor.add(btModificar);
		pnModcolor.add(btBorrar);
		pnModcolor.setEnabled(false);

		
		this.add(lbImagen);
		this.add(pnColores);
		this.add(pnNuevocolor);
		this.add(pnModcolor);
		this.add(btGestion);
		this.add(btSalir);
		
		this.setSize(500, 490);
		this.setLocation(300, 300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	@Override//Capturando los eventos procedentes de los botones.
	public void actionPerformed(ActionEvent e)
	{	
		
		if(e.getSource() == btNuevocolor)
		{
			if(!tfcRef.getText().equals(tfcRef.getFrase()) && !tfcRef.getText().equals("") && !tfcNombre.getText().equals(tfcNombre.getFrase()) && !tfcNombre.getText().equals(""))
			{
				conex.InsertColores(tfcRef.getText(), tfcNombre.getText());
				modelotabla.VaciaTabla();
				conex.CargaTablaColores(modelotabla);
				
				tfcRef.setText("");
				tfcRef.grabFocus();
				tfcNombre.setText("");
				tfcNombre.grabFocus();
				btNuevocolor.grabFocus();
				
				JOptionPane.showMessageDialog(getParent(), "COLOR CREADO CORRECTAMENTE", "Creando nuevo color",  JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == btModificar)
		{
			boolean puedomodificar = true;
			
			if(tfRef.getText().equals("") || tfNombre.getText().equals(""))
				puedomodificar = false;
			
			if(tfRef.getText().equals(this._getAuxRef()) && tfNombre.getText().equals(this._getAuxNombre()))
				puedomodificar = false;

			if(puedomodificar)
			{
				System.out.println("puedo modificar");
				conex.updateColores(this._getAuxid(), tfRef.getText(), tfNombre.getText());
				modelotabla.VaciaTabla();
				conex.CargaTablaColores(modelotabla);
				
				tfRef.setText("");
				tfRef.setEnabled(false);
				tfNombre.setText("");
				tfNombre.setEnabled(false);
				pnModcolor.setEnabled(false);
				btModificar.setEnabled(false);
				btGestion.setEnabled(false);
				JOptionPane.showMessageDialog(getParent(), "COLOR MODIFICADO CORRECTAMENTE", "Modificando color",  JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		if(e.getSource() == btBorrar)
		{
			int JORes = JOptionPane.showConfirmDialog(getParent(), "Se eliminará el color seleccionado de forma permanente. ¿Estás seguro?", "Confirmación" , 0);
			if(JORes == JOptionPane.OK_OPTION)
			{
				conex.BorrarColor(tfRef.getText());
				modelotabla.VaciaTabla();
				conex.CargaTablaColores(modelotabla);
				tfRef.setText("");
				tfRef.setEnabled(false);
				tfNombre.setText("");
				tfNombre.setEnabled(false);
				pnModcolor.setEnabled(false);
				btBorrar.setEnabled(false);
				btGestion.setEnabled(false);
				JOptionPane.showMessageDialog(getParent(), "   COLOR ELIMINADO", "Borrando color",  JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				tfRef.setEnabled(false);
				tfNombre.setEnabled(false);
				pnModcolor.setEnabled(false);
				btBorrar.setEnabled(false);
			}
		}
		
		if(e.getSource() == btGestion)
		{
			pnModcolor.setEnabled(true);	
			tfNombre.setEnabled(true);
			tfRef.setEnabled(true);
			btBorrar.setEnabled(true);
			btModificar.setEnabled(false);
			btBorrar.grabFocus();
			
			this._setAuxRef(tfRef.getText());
			this._setAuxNombre(tfNombre.getText());
			this._setAuxid(taColores.getValueAt(taColores.getSelectedRow(), 0).toString());
		}
		
		if(e.getSource() == btSalir){this.dispose();}
	}
	
	//Capturando el evento procedente de la ventana
	public void windowActivated(WindowEvent arg0) {}
	public void windowClosed(WindowEvent arg0)
	{
		conex.close();
		new FormMenu();
	}
	public void windowClosing(WindowEvent arg0){}
	public void windowDeactivated(WindowEvent arg0) {}
	public void windowDeiconified(WindowEvent arg0) {}
	public void windowIconified(WindowEvent arg0) {}
	public void windowOpened(WindowEvent arg0) {}

	@Override//Capturando el evento del raton (al picar en la tabla)
	public void mouseClicked(MouseEvent e)
	{
		tfRef.setText(taColores.getValueAt(taColores.getSelectedRow(), 1).toString());
		tfNombre.setText(taColores.getValueAt(taColores.getSelectedRow(), 2).toString());
		btGestion.setEnabled(true);
		btBorrar.setEnabled(false);
		btModificar.setEnabled(false);
		pnModcolor.setEnabled(false);
		tfRef.setEnabled(false);
		tfNombre.setEnabled(false);
	
	}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e){}
	@Override
	public void focusGained(FocusEvent e)
	{
		btBorrar.setEnabled(false);
		btGestion.setEnabled(false);
		btModificar.setEnabled(true);
	}

	@Override//Capturando evento Focus en los JTextFiel
	public void focusLost(FocusEvent e) {this.btBorrar.setEnabled(false);}
	
	//gages y setes
	private void _setAuxid(String i){this.id = i;}
	private String _getAuxid(){return this.id;}
	private void _setAuxRef(String r){this.auxRef = r;}
	private String _getAuxRef(){return this.auxRef;}
	private void _setAuxNombre(String n){this.auxNombre = n;}
	private String _getAuxNombre(){return this.auxNombre;}
}
