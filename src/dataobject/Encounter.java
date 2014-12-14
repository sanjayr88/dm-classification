package dataobject;

public class Encounter {
	// age 10 means [10-20)
	private int age;                                     //mapped to 0
	private int numDiagnoses;                            //mapped to 1
	private int admissionType;                           //mapped to 2
	private int timeInHospital;                          //mapped to 3
	private int dischargeDisposition;                    //mapped to 4
	private int numMedicines;                            //mapped to 5
	// u=up, d=down,s=steady, n=no
	private char insulin;                                //mapped to 6
	// true=yes, false=no
	private boolean diabetesMedication;                  //mapped to 7
	// true=change, false=no change
	private boolean changeMedication;                    //mapped to 8
	// m,f,u
	private char gender;                                 //mapped to 9
	private char readmittedGroundTruth;
	private char readmittedCalculated;
	int[] features;

	public Encounter(int age, int numDiagnoses, int admissionType,
			int timeInHospital, int dischargeDisposition, int numMedicines,
			char insulin, boolean diabetesMedication, boolean changeMedication,
			char gender, char readmitted) {
		this.age = age;
		this.numDiagnoses = numDiagnoses;
		this.admissionType = admissionType;
		this.timeInHospital = timeInHospital;
		this.dischargeDisposition = dischargeDisposition;
		this.numMedicines = numMedicines;
		this.insulin = insulin;
		this.diabetesMedication = diabetesMedication;
		this.changeMedication = changeMedication;
		this.gender = gender;
		this.readmittedGroundTruth = readmitted;
		features = new int[10];
		features[0] = age;
		features[1] = numDiagnoses;
		features[2] = admissionType;
		features[3] = timeInHospital;
		features[4] = dischargeDisposition;
		features[5] = numMedicines;
		features[6] = insulin;
		features[7] = diabetesMedication;
		features[8] = changeMedication;
		features[9] = gender;
		
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getNumDiagnoses() {
		return numDiagnoses;
	}

	public void setNumDiagnoses(int numDiagnoses) {
		this.numDiagnoses = numDiagnoses;
	}

	public int getAdmissionType() {
		return admissionType;
	}

	public void setAdmissionType(int admissionType) {
		this.admissionType = admissionType;
	}

	public int getTimeInHospital() {
		return timeInHospital;
	}

	public void setTimeInHospital(int timeInHospital) {
		this.timeInHospital = timeInHospital;
	}

	public int getDischargeDisposition() {
		return dischargeDisposition;
	}

	public void setDischargeDisposition(int dischargeDisposition) {
		this.dischargeDisposition = dischargeDisposition;
	}

	public int getNumMedicines() {
		return numMedicines;
	}

	public void setNumMedicines(int numMedicines) {
		this.numMedicines = numMedicines;
	}

	public char getInsulin() {
		return insulin;
	}

	public void setInsulin(char insulin) {
		this.insulin = insulin;
	}

	public boolean isDiabetesMedication() {
		return diabetesMedication;
	}

	public void setDiabetesMedication(boolean diabetesMedication) {
		this.diabetesMedication = diabetesMedication;
	}

	public boolean isChangeMedication() {
		return changeMedication;
	}

	public void setChangeMedication(boolean changeMedication) {
		this.changeMedication = changeMedication;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public char getReadmittedGroundTruth() {
		return readmittedGroundTruth;
	}

	public void setReadmittedGroundTruth(char readmittedGroundTruth) {
		this.readmittedGroundTruth = readmittedGroundTruth;
	}

	public char getReadmittedCalculated() {
		return readmittedCalculated;
	}

	public void setReadmittedCalculated(char readmittedCalculated) {
		this.readmittedCalculated = readmittedCalculated;
	}

}
