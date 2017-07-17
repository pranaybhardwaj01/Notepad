import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
class FileAction extends WindowAdapter implements ActionListener
{    
Frame fr,sfr;
public String f;public TextArea t;
Button b1,b2,b3,sb1,sb2,sb3,nb1,nb2,nb3,nb4;
boolean dialog=false;
	public void savePrompt(String ff,TextArea tr)
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
f=ff;
t=tr;
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
	public void saveDefault(TextArea tr,Frame fe)
	{  fr=new Frame();
		fr.setSize(200,200);
		fr.setBounds(200,200,250,100);
		fr.addWindowListener(this);
		fr.setLayout(new GridBagLayout());
		GridBagConstraints gb=new GridBagConstraints();
Label l1=new Label("Do you want to save the file.");
		sb1=new Button("Save");
		sb1.addActionListener(this);
		 sb3=new Button("Cancel");
		sb3.addActionListener(this);
		  sb2=new Button("Don't Save");
		sb2.addActionListener(this);
		Insets i=new Insets(10,10,10,10);
t=tr;
sfr=fe;
		gb.insets=i;
gb.weightx=0.0;
gb.weighty=0.0;
gb.gridwidth=3;
fr.add(l1,gb);
gb.gridwidth=1;
gb.ipadx=1;
gb.gridx=0;
fr.add(sb1,gb);
gb.gridx=1;
gb.gridy=1;
fr.add(sb2,gb);
gb.gridx=2;
fr.add(sb3,gb);
fr.setVisible(true);

		}
		public void sv() 
		{try{
			FileWriter dos=new FileWriter(f);
			String text=t.getText();
			dos.write(text);
			dos.close();
			
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		}
	public void windowClosing(WindowEvent e)
	{
		Window w=e.getWindow();
  w.setVisible(false);
  w.dispose();
  	}
public void actionPerformed(ActionEvent e)
{
 int count;
 if(e.getSource()==nb1)
 {
  Find();
 }
 if(e.getSource()==nb4)
 {
  count=0;
  str2=t2.getText();
  savedString=str2;
  if(str2.length()==0)
  {}
  else
  {
   String str;
   str=t.getText();		
   Pattern p=Pattern.compile(str2);
   Matcher m=p.matcher(str);
   if(m.find())
   {
    int j=0;
    for(int i=0;i<m.start();i++)
    if(str.charAt(i)=='\r')
    j++;
    f.toFront();
    t.select(m.start()-j,m.end()-j);
   }
  count=1;
  }
 }
 if(e.getSource()==nb2)
 {
  str2=t2.getText();
  if(str2.length()==0)
  {}
  else
  {
   if(start==true)
   count+=1;
   if(!(str2.equals(savedString)))
   {
    count=0;
   }
   String str;
   str=t.getText();
   Pattern p=Pattern.compile(str2);
   Matcher m=p.matcher(str);
   int i=0;
   while(i<count)
   {
    m.find();
    i++;
   }
   if(m.find())
   {
    int j=0;
    for(i=0;i<m.start();i++)
    if(str.charAt(i)=='\r')
    j++;
    f.toFront();
    t.select(m.start()-j,m.end()-j);
    savedString=str2;
    count+=1;
    }
   start=false;
   }
 }
if(e.getSource()==nb3)
{
 str2=t2.getText();
 if(str2.length()==0)
 {}
 else
 {
  if((!(count-1==0))&&start==false)
  count-=1;
  if(!(str2.equals(savedString)))
  {
   count=0;
  }
  String str;
  str=t.getText();
  Pattern p=Pattern.compile(str2);
  Matcher m=p.matcher(str);
  int i=0;
  while(i<count-1)
  {
   m.find();
   i++;
  }
  if(m.find())
  {
   int j=0;
   for(i=0;i<m.start();i++)
   if(str.charAt(i)=='\r')
   j++;
   f.toFront();
   t.select(m.start()-j,m.end()-j);
   if(count>0)
   count-=1;
   savedString=str2;
  }
  start=true;
 }
}
if(e.getSource()==nb5)
 {
  
		str2=t3.getText();
		if(str2.length()==0)
		{
		}
		else
		{
		if(start==true)
		count+=1;
		if(!(str2.equals(savedString)))
		{
		count=0;
		}
		String str;
		str=t.getText();
		Pattern p=Pattern.compile(str2);
		Matcher m=p.matcher(str);
		int i=0;
		while(i<count)
		{
		m.find();
		i++;
		}
		if(m.find())
		{
		int j=0;
		for(i=0;i<m.start();i++)
		if(str.charAt(i)=='\r')
		j++;

		diff=j;
		System.out.println(diff);
		f.toFront();
		t.select(m.start()-j,m.end()-j);
		savedString=str2;
		count+=1;
		}
		start=false;
		isFind=true;
		}
 }
if(e.getSource()==nb6)
{
 
		if(!isFind)
		t.replaceText(t4.getText(),t.getSelectionStart(),t.getSelectionEnd());
		else
		{
		
		t.replaceText(t4.getText(),t.getSelectionStart()-diff,t.getSelectionEnd()-diff);
		isFind=false;
		diff=0;
		}
		if(count>0)
		count--;
		break;
}
if(e.getSource()==nb7)
{str2=t3.getText();
		
		if(str2.length()==0)
		{
		}
		else
		{
		String str;
		str=t.getText();
		
		Pattern p=Pattern.compile(str2);
		Matcher m=p.matcher(str);
		
		while(m.find())
		{
		int j=0;
		for(int i=0;i<m.start();i++)
		if(str.charAt(i)=='\r')
		j++;
		f.toFront();
		t.replaceText(t4.getText(),m.start()-j,m.end()-j);
		str=t.getText();
		m=p.matcher(str);
		}
		
		}
} 
 if(e.getSource()==b1)
 {
  sv();
  t.setText("");
  fr.dispose();
  dialog=true;
 }
 
 if(e.getSource()==b2||e.getSource()==sb2)
 {
  t.setText("");fr.dispose();
  dialog=true;
 }
 if(e.getSource()==b3||e.getSource()==sb3)
 {
  fr.setVisible(false);
  fr.dispose();
 }

 
 else
 {
  savefunc();
 }
}

public void savefunc()
{
	FileDialog fd=new FileDialog(sfr,"SAVE",FileDialog.SAVE);
			fd.setBounds(100,100,400,400);
			fd.setVisible(true);
			f=fd.getDirectory()+fd.getFile();
			
			//System.out.println(path);
			sv();
			fr.dispose();
}
public void saveas(TextArea tr)
	{
		FileDialog fd=new FileDialog(sfr,"SAVE AS",FileDialog.SAVE);
			fd.setBounds(100,100,400,400);
			fd.setVisible(true);
			f=fd.getDirectory()+fd.getFile();
			t=tr;
			sv();
			fr.dispose();
			
	}	
	public String rpat()
	{return f;}
	
void find()
{

f2=new Frame("Find ");

f2.setSize(400,300);
f2.setLayout(new GridBagLayout());
GridBagConstraints gbc=new GridBagConstraints();
gbc.fill=GridBagConstraints.HORIZONTAL;
Insets i=new Insets(5,5,5,5);
gbc.insets=i;
gbc.ipady=10;
gbc.anchor=GridBagConstraints.SOUTH;
gbc.weightx=gbc.weighty=1.0;
l=new Label("Enter Word:");
f2.add(l,gbc);
gbc.gridx=1;
t2=new TextField(12);
f2.add(t2,gbc);
gbc.gridx=0;
gbc.gridy=1;
gbc.gridwidth=2;
nb1=new Button("Find");
nb1.addActionListener(this);

f2.add(nb1,gbc);
gbc.gridy=2;
gbc.gridwidth=1;
gbc.gridx=0;
gbc.anchor=GridBagConstraints.NORTH;

nb2=new Button("Find next");
nb2.addActionListener(this);
f2.add(nb2,gbc);
gbc.gridx=1;
nb3=new Button("Find previous");
nb3.addActionListener(this);
f2 .addWindowListener(this);
f2.add(nb3,gbc);
f2.setVisible(true);
}

void replace()
{

f3=new Frame("Find & Replace ");

f3.setSize(400,300);
f3.setLayout(new GridBagLayout());
GridBagConstraints gbc=new GridBagConstraints();
gbc.fill=GridBagConstraints.HORIZONTAL;
Insets i=new Insets(5,5,5,5);
gbc.insets=i;
gbc.ipady=10;
gbc.anchor=GridBagConstraints.SOUTH;
gbc.weightx=gbc.weighty=1.0;
l2=new Label("Find What:");
f3.add(l2,gbc);
t3=new TextField(12);
gbc.gridx=1;
f3.add(t3,gbc);
l3=new Label("Replace with:");
gbc.gridy=1;
gbc.gridx=0;
f3.add(l3,gbc);
t4=new TextField(12);
gbc.gridx=1;
f3.add(t4,gbc);


gbc.gridx=0;
gbc.gridy=2;
nb4=new Button(" Find ");
nb4.addActionListener(this);

f3.add(nb4,gbc);
gbc.gridy=2;
gbc.gridx=1;



nb5=new Button("Find Next");
nb5.addActionListener(this);
f3.add(nb5,gbc);
gbc.gridx=0;
gbc.gridy=3;
//gbc.gridwidth=2;
nb6=new Button("Replace");
nb6.addActionListener(this);
f3.add(nb6,gbc);
gbc.gridx=1;

nb7=new Button("Replace All");
nb7.addActionListener(this);
f3.add(nb7,gbc);

f3.addWindowListener(this);
f3.setVisible(true);
}
}
