package gestorlevi;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JTextField;

public class TextFieldCom extends JTextField implements FocusListener
{
	private static final long serialVersionUID = 1L;
	private String Frase;
	private Font guayFont;
	private Font auxFont;
	
	public TextFieldCom(String frase)
	{
		this.addFocusListener(this);
		Frase = frase;
		
		auxFont = this.getFont();
		guayFont = new Font(this.getFont().getName(), Font.ITALIC, this.getFont().getSize());
		this.setText(frase);
		this.setFont(guayFont);
		this.setBackground(new Color(254, 249, 231));
		this.setForeground(Color.GRAY);
	}
	
	public String getFrase()
	{
		return this.Frase;
	}
	
	@Override
	public void focusGained(FocusEvent e)
	{
		if(this.getText().equals(this.getFrase()))
		{
			this.setFont(auxFont);
			this.setText("");
			this.setBackground(Color.WHITE);
			this.setForeground(Color.BLACK);
		}
	}

	@Override
	public void focusLost(FocusEvent e)
	{
		if(this.getText().equals("") || this.getText().equals(this.getFrase()))
		{
			this.setText(Frase);
			this.setFont(guayFont);
			this.setBackground(new Color(254, 249, 231));
			this.setForeground(Color.GRAY);
		}
	}
}
