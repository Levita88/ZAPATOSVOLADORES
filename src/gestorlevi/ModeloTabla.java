package gestorlevi;

import javax.swing.table.DefaultTableModel;

public class ModeloTabla extends DefaultTableModel
{
	private static final long serialVersionUID = 1L;
	
	public boolean isCellEditable(int fila, int columna){return false;}
	
	public void VaciaTabla()
	{
		for(int i =  this.getRowCount() - 1; i >= 0; i--)
			this.removeRow(i);
	}
}
