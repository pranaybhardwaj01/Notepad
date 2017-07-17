import java.awt.*;
import java.awt.event.*;
import java.io.*;
class MainClass extends WindowAdapter implements ActionListener
{
	Frame f;
	MenuBar mb;
	Menu file ,edit;
	MenuItem nw,open,save,saveAs,exit,find,findR;
	String ff;
	TextArea tr;
	boolean isChanged;
	long size;
	FileAction obj=new FileAction();
public MainClass()
  {
	 f=new Frame();
	 f.setSize(600,600);
	 f.addWindowListener(this);
	 mb=new MenuBar();
	 file=new Menu("File");
	 edit=new Menu("Edit");
	 nw=new MenuItem("New");
	 open=new MenuItem("Open");
	 save=new MenuItem("Save");
	 saveAs=new MenuItem("Save As");
	 exit=new MenuItem("Exit");
	 find=new MenuItem("Find");
	 findR=new MenuItem("Find and Replace");
	tr=new TextArea(); 
	
		nw.addActionListener(this);
		open.addActionListener(this);
		save.addActionListener(this);
		saveAs.addActionListener(this);
		exit.addActionListener(this);
		find.addActionListener(this);
		findR.addActionListener(this);
  file.add(nw); file.add(open);file.add(save);file.add(saveAs);   file.addSeparator(); file.add(exit);
  edit.add(find);edit.add(findR);
  mb.add(file);mb.add(edit);
  f.add(tr);
  f.setMenuBar(mb); f.setVisible(true);
  
  }	
  public void windowClosing(WindowEvent e)
  {Exit eobj=new Exit(f,tr,ff);
  }
  
  public void actionPerformed(ActionEvent e) 
  {
	String s=e.getActionCommand();

	if(s.equals("New"))
		{
			String ss=tr.getText();
			String data="";
			try{
				if(ff!=null)
				{
					BufferedInputStream bin=new BufferedInputStream(new FileInputStream(ff));
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
		if(ff!=null&&isChanged)		
		{
	      obj.savePrompt(ff,tr);
		  isChanged=false;
		  ff=null;
		  }
		 else if(ff!=null&&!isChanged) 
		     {tr.setText("");
              ff=null;
			  size=0;
              isChanged=false;			  }
			  else if(!ss.equals(""))
			  {obj.saveDefault(tr,f);
isChanged=false;
		  ff=null;
		  		  } 
		  else{
			  isChanged=false;
		  ff=null;
		  tr.setText("");
		  size=0;
		  }
        }
else if(s.equals("Save"))
    {
		if(obj.f!=null)
		{
	obj.t=tr;

	obj.sv();
		try{
			FileInputStream fis=new FileInputStream(ff);
			int sie=0;
			while(fis.read()!=-1)
				sie++;
			size=sie;	
		}catch(Exception e2){System.out.println(e2.getMessage());}	
		}	
		else
		{obj.t=tr;
		obj.savefunc();
		
		ff=obj.rpat();
		updateSize();
		}
  }
  else if(s.equals("Save As"))
    {
	  obj.saveas(tr);
	  ff=obj.rpat();
	updateSize();
    }
else if(s.equals("Open"))
{Open op1=new Open(f,tr,ff);
if(op1.op==true)
{ff=op1.file;
updateSize();}
  }
  else if(s.equals("Exit"))
  {
	  Exit eobj=new Exit(f,tr,ff);
  }
  }  
public void updateSize()
	{try{
			FileInputStream fis=new FileInputStream(ff);
			int sizze=0;
			while(fis.read()!=-1)
				sizze++;
			size=sizze;
		}catch(Exception e1){System.out.println(e1.getMessage());}
	} 
  	
public static void main(String []y)
{
	MainClass mc=new MainClass();
}
 }
  