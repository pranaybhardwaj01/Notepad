import java.awt.*;
import java.io.*;
import java.awt.event.*;
class Open extends WindowAdapter implements ActionListener
{
	Frame fr,sfr;
	TextArea tr;
	String file;
	Button b1,b2,b3;
public boolean op=false;
boolean isChanged;
	public Open(Frame f,TextArea t,String s)
	{
		sfr=f;
		tr=t;
		file=s;
		try{BufferedInputStream bin=new BufferedInputStream(new FileInputStream(file));
		int ch;
		s="";
		while((ch=bin.read())!=-1)
		s=s+(char)ch;
	bin.close();}catch(Exception e)
	{}
		if(file==null)
		{
			if((tr.getText()).equals(""))
			openD();
			else
			{
				savePrompt();
			}
		}		else
				{
					if(s.equals(tr.getText()))
						openD();
					else
						savePrompt();
				}
		
	}	
	public void savePrompt()
	{
		fr=new Frame();
		fr.setSize(200,200);
		fr.setBounds(200,200,250,100);
		fr.addWindowListener(this);
		fr.setLayout(new GridBagLayout());
		GridBagConstraints gb=new GridBagConstraints();
Label l1=new Label("Do you want to save the file.");
		b1=new Button("Save");
		b1.addActionListener(this);
		 b3=new Button("Cancel");
		b3.addActionListener(this);
		  b2=new Button("Don't Save");
		b2.addActionListener(this);
		Insets i=new Insets(10,10,10,10);
		gb.insets=i;
gb.weightx=0.0;
gb.weighty=0.0;
gb.gridwidth=3;
fr.add(l1,gb);
gb.gridwidth=1;
gb.ipadx=1;
gb.gridx=0;
fr.add(b1,gb);
gb.gridx=1;
gb.gridy=1;
fr.add(b2,gb);
gb.gridx=2;
fr.add(b3,gb);
fr.setVisible(true);
	}
	public void openD()
	{
		FileDialog fd=new FileDialog(sfr,"Open",FileDialog.LOAD);
			fd.setBounds(100,100,400,400);
			fd.setVisible(true);
			file=fd.getDirectory()+fd.getFile();
			String data="";
			try{
				if(fd.getFile()!=null)
				{
					BufferedInputStream bin=new BufferedInputStream(new FileInputStream(file));
		int ch;
		data="";
		while((ch=bin.read())!=-1)
		data=data+(char)ch;
	bin.close();
	tr.setText(data);
			op=true;	}	
			}catch(Exception e){}
	}
public void actionPerformed(ActionEvent a)
{
String ss=tr.getText();
			String data="";
			try{
				if(file!=null)
				{
					BufferedInputStream bin=new BufferedInputStream(new FileInputStream(file));
		int ch;
		data="";
		while((ch=bin.read())!=-1)
		data=data+(char)ch;
	bin.close();
		}	
			}catch(Exception o){}
			if(data.equals(ss))
				isChanged=false;
			else isChanged=true;
	String e=a.getActionCommand();
	if(e.equals("Save"))
	{FileAction obj=new FileAction();
		if(file!=null && isChanged==true)
		{fr.setVisible(false);
			fr.dispose();
			obj.t=tr;
			obj.f=file;
			obj.sv();
			file=obj.rpat();
			
			openD();
		}
		else if(file==null&&isChanged)
		{fr.setVisible(false);
			obj.t=tr;
			obj.f=file;
			obj.sfr=sfr;
			obj.savefunc();
			file=obj.rpat();
			fr.dispose();
			openD();
			
		}
		else{
			fr.setVisible(false);
			fr.dispose();
			openD();
		}
	}
	else if(e.equals("Don't Save"))
	{fr.setVisible(false);
		fr.dispose();
		openD();
	}
		else
		{
			fr.setVisible(false);fr.dispose();}
	}

	public void windowClosing(WindowEvent e)
  {Window w=e.getWindow();
  w.setVisible(false);
  w.dispose();
  }
	}