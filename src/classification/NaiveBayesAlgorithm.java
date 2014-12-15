package classification;

import java.util.ArrayList;
import java.util.HashSet;

import dataobject.BayeProb;
import dataobject.Encounter;

public class NaiveBayesAlgorithm {
	
	public static int findMax(double probH1x,double probH2x,double probH3x){	
		if(probH1x >= probH2x  && probH1x>=probH3x){ return 1;}
		if(probH2x >= probH1x  && probH2x>=probH3x){ return 2;}
		if(probH3x >= probH1x  && probH3x>=probH2x){ return 3;}
		return 4;										
	}
	
  public static void classify(ArrayList<Encounter> encounters,ArrayList<HashSet<Integer>> featureCardinality,BayeProb bp ,int testID){
	  for(Encounter en : encounters){
		  if(en.getId()==testID){
			  classify(en,featureCardinality,bp); 
		  }	
	  }
  }
	
   public static void classify(Encounter en,ArrayList<HashSet<Integer>> featureCardinality,BayeProb bp){	   
	   
	   double probH1;
	   double probH2;
	   double probH3;
	   int h1Size;
	   int h2Size;
	   int h3Size;
	   int i;
	   int attribute;
	   int freq=0;
	   double probxH1=1;
	   double probxH2=1;
	   double probxH3=1;
	   double probH1x=1;
	   double probH2x=1;
	   double probH3x=1;
	   int result;
	   	   
	   //Set Values
	   probH1=bp.getProbH1();
	   probH2=bp.getProbH2();
	   probH3=bp.getProbH3();
	   h1Size=bp.getH1Size();
	   h2Size=bp.getH2Size();
	   h3Size=bp.getH3Size();
	   
	   //Find P(x/h)
	   for(i=0;i<=9;i++){
		   //Get the attribute
		   attribute = en.getFeatures()[i];	 
		   //Find p(x/h1)
		   if(h1Size!=0){
			  freq=0; 
		      if (bp.getH1List().get(i).containsKey(attribute)){
			     freq = bp.getH1List().get(i).get(attribute);
		      }		   	   
		      //Apply Laplace correction
		      probxH1 = probxH1 * ((freq+1)/((h1Size + featureCardinality.get(i).size() ) * 1.0 ));
		   }
		   		   
           //Find p(x/h2)
		   if(h2Size!=0){
		      freq=0;
		      if (bp.getH2List().get(i).containsKey(attribute)){
			     freq = bp.getH2List().get(i).get(attribute);
		      }		   	   
		      //Apply Laplace correction
		      probxH2 = probxH2 * ((freq+1)/((h2Size + featureCardinality.get(i).size()) * 1.0 ));
		   }
		   		   
		   //Find p(x/h3)
		   if(h3Size!=0){
		      freq=0;
		      if (bp.getH3List().get(i).containsKey(attribute)){
			     freq = bp.getH3List().get(i).get(attribute);
		      }		   		   
		      //Apply Laplace correction
		      probxH3 = probxH3 * ((freq+1)/((h3Size + featureCardinality.get(i).size() ) * 1.0 ));   
		   }
	     }
	   
	      //Find probabilities of h1 , h2 and h3 given x
          probH1x = probxH1 * probH1;
          probH2x = probxH2 * probH2;
          probH3x = probxH3 * probH3;
          
          //Find max
          result = findMax(probH1x,probH2x,probH3x);  
          
          //Assign result
          switch(result){          
          case 1 : en.setReadmittedCalculated('N');
                   break;
          case 2 : en.setReadmittedCalculated('<');
                   break;
          case 3 : en.setReadmittedCalculated('>');
                   break;
          }
                   
   }
     
   public static void calculateFeatureProbabilities(ArrayList<Encounter> encounters,BayeProb bp, int testID){	   
	   int attribute;
	   int freq;
	   int i;
	   int h1Size=0;  //Classifier 1 is for No
	   int h2Size=0;  //Classifier 2 is for <30
	   int h3Size=0;  //Classifier 3 is for >30
	   int trainingSampleSize=0;
	   for(Encounter en : encounters){
		 if(en.getId()!=testID){
			 trainingSampleSize++;	 
		   //Calculate featureProbabilities
		   switch(en.getReadmittedGroundTruth()){
		      case 'N' : h1Size++;
		    	         for(i=0;i<=9;i++){
		    	           	attribute = en.getFeatures()[i]; 
		                    if (bp.getH1List().get(i).containsKey(attribute)){
		                	   freq = bp.getH1List().get(i).get(attribute);
		                	   bp.getH1List().get(i).put(attribute,freq+1);
		                    }
		                    else{
		                	   bp.getH1List().get(i).put(attribute,1); 
		                    }
		    	          }
		    	          break;		    	          
		      case '<'  : h2Size++;
		    	          for(i=0;i<=9;i++){
  	        	             attribute = en.getFeatures()[i]; 
                             if (bp.getH2List().get(i).containsKey(attribute)){
            	                freq = bp.getH2List().get(i).get(attribute);
            	                bp.getH2List().get(i).put(attribute,freq+1);
                             }
                             else{
            	                bp.getH2List().get(i).put(attribute,1); 
                             }
	                      }
	                      break;         
		      case '>'  : h3Size++;
		    	          for(i=0;i<=9;i++){
   	                         attribute = en.getFeatures()[i]; 
                             if (bp.getH3List().get(i).containsKey(attribute)){
 	                            freq = bp.getH3List().get(i).get(attribute);
 	                            bp.getH3List().get(i).put(attribute,freq+1);
                             }
                             else{
 	                            bp.getH3List().get(i).put(attribute,1); 
                             }
                           }
                           break;             		                 		                 		                 		                 
		   }
		 }
		   
		   //Set the sizes
		   bp.setSampleSize(trainingSampleSize);
		   bp.setH1Size(h1Size);
		   bp.setH2Size(h2Size);
		   bp.setH3Size(h3Size);
		   
		   //Set the classifier probabilities
           bp.setProbH1(bp.getH1Size()/(trainingSampleSize*1.0));
           bp.setProbH2(bp.getH2Size()/(trainingSampleSize*1.0));
           bp.setProbH3(bp.getH3Size()/(trainingSampleSize*1.0));
		 }  	   	   
   }

   
   
}
