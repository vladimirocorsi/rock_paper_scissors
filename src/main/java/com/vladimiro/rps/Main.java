package com.vladimiro.rps;

import com.vladimiro.rps.cmdline.CmdRunner;
import com.vladimiro.rps.gui.SwingRunner;

public class Main {

	public static void main(String[] args) {
		if(args.length > 0){ 
			if(args[0].equals("-cmd")){
				new CmdRunner().run();
			}else{
				System.out.println("Type '-cmd' for command line version");
			}
		}else{
			javax.swing.SwingUtilities.invokeLater(new SwingRunner());
		}
		
	}
}
