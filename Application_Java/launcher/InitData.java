package launcher;

import java.awt.AWTException;
import java.io.IOException;

import action.Action;
import robot.*;
import upperClass.Syst;

public class InitData {
	
	public static void actived() throws AWTException, IOException {
		
		Action A1 = new Action("1","LoudSpeakerLeft","");
		Action A2 = new Action("2","LoudSpeakerRight","");
		Action A3 = new Action("3","FaceLedRight0","");
		Action A4 = new Action("4","FaceLedRight1","");
		Action A5 = new Action("5","FaceLedRight2","");
		Action A6 = new Action("6","FaceLedRight3","");
		Action A7 = new Action("7","FaceLedRight4","");
		Action A8 = new Action("8","FaceLedRight5","");
		Action A9 = new Action("9","FaceLedRight6","");
		Action A10 = new Action("10","FaceLedRight7","");
		Action A11 = new Action("11","FaceLedLeft0","");
		Action A12 = new Action("12","FaceLedLeft1","");
		Action A13 = new Action("13","FaceLedLeft2","");
		Action A14 = new Action("14","FaceLedLeft3","");
		Action A15 = new Action("15","FaceLedLeft4","");
		Action A16 = new Action("16","FaceLedLeft5","");
		Action A17 = new Action("17","FaceLedLeft6","");
		Action A18 = new Action("18","FaceLedLeft7","");
		Action A19 = new Action("19","RightEarLed1","");
		Action A20 = new Action("20","RightEarLed2","");
		Action A21 = new Action("21","RightEarLed3","");
		Action A22 = new Action("22","RightEarLed4","");
		Action A23 = new Action("23","RightEarLed5","");
		Action A24 = new Action("24","RightEarLed6","");
		Action A25 = new Action("25","RightEarLed7","");
		Action A26 = new Action("26","RightEarLed8","");
		Action A27 = new Action("27","RightEarLed9","");
		Action A28 = new Action("28","RightEarLed10","");
		Action A29 = new Action("29","LeftEarLed1","");
		Action A30 = new Action("30","LeftEarLed2","");
		Action A31 = new Action("31","LeftEarLed3","");
		Action A32 = new Action("32","LeftEarLed4","");
		Action A33 = new Action("33","LeftEarLed5","");
		Action A34 = new Action("34","LeftEarLed6","");
		Action A35 = new Action("35","LeftEarLed7","");
		Action A36 = new Action("36","LeftEarLed8","");
		Action A37 = new Action("37","LeftEarLed9","");
		Action A38 = new Action("38","LeftEarLed10","");
		Action A39 = new Action("39","FrontRightShoulder","");
		Action A40 = new Action("40","FrontLeftShoulder","");
		Action A41 = new Action("41","BackLeftShoulder","");
		Action A42 = new Action("42","BackLeftShoulder","");
		Action A43 = new Action("43","HeadYaw","");
		Action A44 = new Action("44","HeadPitch","");
		Action A45 = new Action("45","ShoulderPitch","");
		Action A46 = new Action("46","ShoulderRoll","");
		Action A47 = new Action("47","ElbowYaw","");
		Action A48 = new Action("48","ElbowRoll","");
		Action A49 = new Action("49","WristYaw","");
		Action A50 = new Action("50","Hand","");
		Action A51 = new Action("51","HipRoll","");
		Action A52 = new Action("52","HipPitch","");
		Action A53 = new Action("53","KneePitch","");
		Action A54 = new Action("54","WheelFL","");
		Action A55 = new Action("55","WheelFR","");
		Action A56 = new Action("56","WheelB","");
		Action A57 = new Action("57","elephant","");
		Action A58 = new Action("58","happy","");
		Action A59 = new Action("59","kisses","");
		Action A60 = new Action("60","excited","");
		Action A61 = new Action("61","thinking","");
		Action A62 = new Action("62","curious","");
		Action A63 = new Action("63","chill","");
		Action A64 = new Action("64","fear","");
		Action A65 = new Action("65","confused","");
		Action A66 = new Action("66","bored","");
		Action A67 = new Action("67","elephant","");
		Action A68 = new Action("68","mouse","");
		Action A69 = new Action("69","gorilla","");
		Action A70 = new Action("70","headbang","");
		Action A71 = new Action("71","vacuum","");
		Action A72 = new Action("72","mystical","");
		Action A73 = new Action("73","guitar","");
		Action A74 = new Action("74","moveTo","");
		Action A75 = new Action("75","sitDown","");
		Action A76 = new Action("76","standUp","");
		
		
		
		Pepper pepper = new Pepper("1", "Pepper", 0.0, 0.0, 0.0);
		pepper.addAction(A1);
		pepper.addAction(A2);
		pepper.addAction(A3);
		pepper.addAction(A4);
		pepper.addAction(A5);
		pepper.addAction(A6);
		pepper.addAction(A7);
		pepper.addAction(A8);
		pepper.addAction(A9);
		pepper.addAction(A10);
		pepper.addAction(A11);
		pepper.addAction(A12);
		pepper.addAction(A13);
		pepper.addAction(A14);
		pepper.addAction(A15);
		pepper.addAction(A16);
		pepper.addAction(A17);
		pepper.addAction(A18);
		pepper.addAction(A19);
		pepper.addAction(A20);
		pepper.addAction(A21);
		pepper.addAction(A22);
		pepper.addAction(A23);
		pepper.addAction(A24);
		pepper.addAction(A25);
		pepper.addAction(A26);
		pepper.addAction(A27);
		pepper.addAction(A28);
		pepper.addAction(A29);
		pepper.addAction(A30);
		pepper.addAction(A31);
		pepper.addAction(A32);
		pepper.addAction(A33);
		pepper.addAction(A34);
		pepper.addAction(A35);
		pepper.addAction(A36);
		pepper.addAction(A37);
		pepper.addAction(A38);
		pepper.addAction(A39);
		pepper.addAction(A40);
		pepper.addAction(A41);
		pepper.addAction(A42);
		pepper.addAction(A43);
		pepper.addAction(A44);
		pepper.addAction(A45);
		pepper.addAction(A46);
		pepper.addAction(A47);
		pepper.addAction(A48);
		pepper.addAction(A49);
		pepper.addAction(A50);
		pepper.addAction(A51);
		pepper.addAction(A52);
		pepper.addAction(A53);
		pepper.addAction(A54);
		pepper.addAction(A55);
		pepper.addAction(A56);
		pepper.addAction(A57);
		pepper.addAction(A58);
		pepper.addAction(A59);
		pepper.addAction(A60);
		pepper.addAction(A61);
		pepper.addAction(A62);
		pepper.addAction(A63);
		pepper.addAction(A64);
		pepper.addAction(A65);
		pepper.addAction(A66);
		pepper.addAction(A67);
		pepper.addAction(A68);
		pepper.addAction(A69);
		pepper.addAction(A70);
		pepper.addAction(A71);
		pepper.addAction(A72);
		pepper.addAction(A73);
		pepper.addAction(A74);
		pepper.addAction(A75);
		pepper.addAction(A76);
		
		Nao nao = new Nao("2", "Nao");
		Peekee1R peekee1R = new Peekee1R("3","Peekee1R");
		Robotino robotino = new Robotino("4","Robotino");
		
		Fleet fleet = new Fleet("1");
		fleet.setName("Flotte de données d'initialisations");
		fleet.addRobot(pepper);
		fleet.addRobot(nao);
		fleet.addRobot(peekee1R);
		fleet.addRobot(robotino);
		
		Syst syst = new Syst();
		syst.addFleet(fleet);
		syst.save();
	}

}