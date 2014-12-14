package dataobject;

public class Encounter {
	// age 10 means [10-20)
	private int age;
	private int numDiagnoses;
	private int admissionType;
	private int timeInHospital;
	private int dischargeDisposition;
	private int numMedicines;
	// u=up, d=down,s=steady, n=no
	private char insulin;
	// 1=yes, 0=no
	private int diabetesMedication;
	// 1=change, 0=no change
	private int changeMedication;
	// m,f,u
	private char gender;
	// <,>,N
	private char readmittedGroundTruth;
	private char readmittedCalculated;

	public Encounter(int age, int numDiagnoses, int admissionType,
			int timeInHospital, int dischargeDisposition, int numMedicines,
			char insulin, int diabetesMedication, int changeMedication,
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

	public int isDiabetesMedication() {
		return diabetesMedication;
	}

	public void setDiabetesMedication(int diabetesMedication) {
		this.diabetesMedication = diabetesMedication;
	}

	public int isChangeMedication() {
		return changeMedication;
	}

	public void setChangeMedication(int changeMedication) {
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
