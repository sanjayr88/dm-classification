package classification;

import java.util.ArrayList;
import java.util.HashSet;

import dataobject.BayeProb;
import dataobject.Encounter;

  public class FiveFoldCrossValidationAlgorithm {
   //This function assign ids from 0 to n-1 to each encounter(sample)	
   public static void generateEncounterID(ArrayList<Encounter> encounters , int n){	
		for(int i = 0;i<encounters.size();i++){			
			encounters.get(i).setId(i%n);			
		}		
	}
      
   public static double executeCrossValidation(ArrayList<Encounter> encounters,ArrayList<HashSet<Integer>> featureCardinality , BayeProb bp,int n){	 
	 int i;
	 double accuracy=0;
	 double partitionAccuracy=0;
	 for(i=0;i<n;i++){
	    //Calculate Probabilities
	    NaiveBayesAlgorithm.calculateFeatureProbabilities(encounters, bp,i);	    
	    NaiveBayesAlgorithm.classify(encounters,featureCardinality, bp,i);
	    partitionAccuracy = calculateAccuracy(encounters,i);
	    accuracy+=partitionAccuracy;
	 }	 
	 return accuracy/n;	   
   }
   
   public static double calculateAccuracy(ArrayList<Encounter> encounters , int testID){
	   
	   int totalCorrectClassifications=0;
	   int totalTestSamples=0;
	   double accuracy=0;
	   for(Encounter en : encounters){		   
		   if(en.getId()==testID){
			   totalTestSamples++;
			   if(en.getReadmittedCalculated()==en.getReadmittedGroundTruth()){
				     totalCorrectClassifications++;
			   }
		   }
	   }
	   
	   //Calculate accuracy
	   accuracy = (totalCorrectClassifications*1.0)/totalTestSamples;
	   return accuracy;
   }
}
