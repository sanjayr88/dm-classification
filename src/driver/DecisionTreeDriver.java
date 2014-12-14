package driver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import utils.ConfigUtils;
import utils.FileUtils;
import dataobject.Encounter;

public class DecisionTreeDriver {

	public static void main(String[] args) {
		FileUtils fileUtils = new FileUtils();
		try {
			ArrayList<Encounter> encounters = new ArrayList<Encounter>();
			//Scanner scanner = file.readFileUsingScanner("/cho.txt");
			Scanner scanner = fileUtils.readFileUsingScanner("/"+ConfigUtils.getProperties().getProperty("file.name"));
			String line;
			String[] tokens;
			int age, numDiagnoses, admissionType, timeInHospital, dischargeDisposition, numMedicines;
			char insulin, gender, readmittedGroundTruth;
			boolean diabetesMedication, changeMedication;
			//just to move to second line
			line = scanner.nextLine();
			while(scanner.hasNextLine()){
				line = scanner.nextLine();
				tokens = null;
				tokens = line.split(",");
				if(tokens[4].charAt(1)=='0')age = Integer.parseInt(tokens[4].substring(1,2));
				else age = Integer.parseInt(tokens[4].substring(1,3));
				
				numDiagnoses = Integer.parseInt(tokens[21]);
				admissionType = Integer.parseInt(tokens[6]);
				timeInHospital = Integer.parseInt(tokens[9]);
				dischargeDisposition = Integer.parseInt(tokens[7]);
				numMedicines = Integer.parseInt(tokens[14]);
				insulin = tokens[41].charAt(0);
				gender = tokens[3].charAt(0);
				readmittedGroundTruth = tokens[49].charAt(0);
				if(tokens[48].charAt(0)=='Y') diabetesMedication = true;
				else diabetesMedication = false;
				if(tokens[47].charAt(0)=='C') changeMedication = true;
				else changeMedication = false;
				encounters.add(new Encounter(age, numDiagnoses, admissionType,
						timeInHospital, dischargeDisposition, numMedicines,
						insulin, diabetesMedication, changeMedication,
						gender, readmittedGroundTruth));
			}
			scanner.close();
			//insert your code below
			
			
			
		}catch (FileNotFoundException e) {
			System.err.println(e.toString());
		}
	}

}
