package driver;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import classification.FiveFoldCrossValidationAlgorithm;

import utils.ConfigUtils;
import utils.FileUtils;
import dataobject.BayeProb;
import dataobject.Encounter;

public class NaiveBayesDriver {

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
			int diabetesMedication, changeMedication;
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
				if(tokens[48].charAt(0)=='Y') diabetesMedication = 1;
				else diabetesMedication = 0;
				if(tokens[47].charAt(0)=='C') changeMedication = 1;
				else changeMedication = 0;
				encounters.add(new Encounter(age, numDiagnoses, admissionType,
						timeInHospital, dischargeDisposition, numMedicines,
						insulin, diabetesMedication, changeMedication,
						gender, readmittedGroundTruth));
			}
			scanner.close();
			//insert your code below
			
			if(encounters.size()!=0){
			   //Create a Bayes Object to store probabilities
			   BayeProb bp = new BayeProb();
			
			   //Generate Id for encounter
			   int numberOfPartitions = Integer.parseInt(ConfigUtils.getProperties().getProperty("numberOfPartitions"));
			   FiveFoldCrossValidationAlgorithm.generateEncounterID(encounters,numberOfPartitions);
			   
			   //Store Cardinality 
			   ArrayList<HashSet<Integer>> featureCardinality = new ArrayList<HashSet<Integer>>();
			   for(int i=0;i<10;i++){
				   featureCardinality.add(new HashSet<Integer>());
			   }
			   for(Encounter en:encounters){				   
				   for(int j=0;j<=9;j++){
					   if (!featureCardinality.get(j).contains(en.getFeatures()[j])){
						   featureCardinality.get(j).add(en.getFeatures()[j]);
					   }
				   }				   
			   }
			   			
			   //Execute Five fold cross validation
			   double accuracy = FiveFoldCrossValidationAlgorithm.executeCrossValidation(encounters,featureCardinality,bp,numberOfPartitions);
						
			   //Print the accuracy
			   System.out.println(accuracy);
			}
			else{
				System.out.println("Number of samples = 0");
			}			
		}catch (FileNotFoundException e) {
			System.err.println(e.toString());
		}
	}

}

